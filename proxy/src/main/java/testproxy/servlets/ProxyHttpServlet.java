/* Copyright 2009 EB2 International Limited */
package testproxy.servlets;

//import com.sabre.sabresonic.mockserver.core.beans.HostCommand;
import com.sabre.sabresonic.mockserver.core.service.flowcontrol.IfExpression;
import com.sabre.sabresonic.mockserver.frg.chain.FrgHandler;
import com.sabre.sabresonic.mockserver.frg.object.FrgRequest;
import com.sabre.sabresonic.mockserver.frg.object.FrgResponse;

import com.sabre.ssw.proxy.defines.ProxyMode;
import fileManagers.InfoFileManager;
import fileManagers.MainFileManager;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import testproxy.beans.HostCommand;
import testproxy.config.Configuration;
import testproxy.config.ProxyConstants;
import testproxy.connectors.Connector;
import testproxy.connectors.ConnectorList;
import testproxy.connectors.EndTestConnector;
import testproxy.connectors.ProxyAuthenticator;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.httpservletwrappers.GenericResponseWrapper;
import testproxy.httpservletwrappers.GenericWrapper;
import testproxy.message.datagrabbers.DataGrabber;
import testproxy.message.replacers.DataReplacer;
import testproxy.message.replacers.DateReplaceEngine;
import testproxy.servicemanagment.ServiceManager;
import testproxy.sessionsinfo.SessionInfo;
import testproxy.sessionsinfo.SessionsInfoManager;
import testproxy.utils.SpringBeanContainer;
import testproxy.utils.TimeBreakdown;
import testproxy.utils.XmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Authenticator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;
import java.util.zip.GZIPInputStream;
import testproxy.logs2mock.LogReader;


public class ProxyHttpServlet extends HttpServlet implements ProxyConstants
{
    private static final long serialVersionUID = 1904775186555978555L;
    private HttpsSampleServlet httpsSampleServlet;
    private static final Logger LOG = LoggerFactory.getLogger(ProxyHttpServlet.class);
    private static final Logger LOG1 = LoggerFactory.getLogger("proxy.info");
    Configuration configuration;
    ServiceManager serviceManager;
    private ConnectorList connectorList;
    private DateReplaceEngine dateReplaceEngine;
    SessionsInfoManager sessionsInfoManager;
    MainFileManager mainFileManager;
    DataGrabber dataGrabberComposite;
    DataReplacer dataReplacerComposite;
    ProxyMode mode;
    FrgHandler frgHandler;
    private int numTravelItineraryHistory = 0;
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        SpringBeanContainer.setApplicationContext(webApplicationContext);
        configuration = SpringBeanContainer.getConfiguration();
        sessionsInfoManager = SpringBeanContainer.getSessionsInfoManager();
        serviceManager = SpringBeanContainer.getServiceManager();
        connectorList = (ConnectorList) webApplicationContext.getBean("connectorList");
        dateReplaceEngine = (DateReplaceEngine) webApplicationContext.getBean("dateReplaceEngine");
        mainFileManager = (MainFileManager) webApplicationContext.getBean("mainFileManager");
        dataGrabberComposite = SpringBeanContainer.getDataGrabber();
        dataReplacerComposite = SpringBeanContainer.getDataReplacer();
        frgHandler = (FrgHandler) webApplicationContext.getBean("frgHandler");
        ProxyAuthenticator proxyAuthenticator = (ProxyAuthenticator) webApplicationContext.getBean("proxyAuthenticator");
        Authenticator.setDefault(proxyAuthenticator);

        LOG1.info("----- Default proxy configuration start -----");
        LOG1.info("Default proxy mode: " + configuration.getMode());
        LOG1.info("SaveDirectory: " + configuration.getSavePath());
        LOG1.info("Generate \"CreateSession\" requests for replay mode: " + configuration.isGenerateCreateSession());
        LOG1.info("Fix response : " + configuration.isFixResponse());
        LOG1.info("RecordFixedResponses : " + configuration.isRecordFixedResponses());
        LOG1.info("----- Default proxy configuration end -----");

        if (ProxyMode.valueOf(configuration.getMode()) == ProxyMode.MOCK)
        {
            httpsSampleServlet = new HttpsSampleServlet();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String airlineId = req.getQueryString();
        GenericRequestWrapper genReq = new GenericRequestWrapper(req);
        GenericResponseWrapper genRes = new GenericResponseWrapper(resp);
        try {
            processRequest(genReq, genRes, airlineId);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * We don't entertain GET requests
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        LOG.info("GET");
        resp.getWriter().println("<html><head><title>GET requests not supported</title></head><body><h1>GET requests not supported</h1></body></html>");
    }

    void processRequest(GenericRequestWrapper genReq, GenericResponseWrapper genRes, String airlineId) throws ServletException, IOException, ParseException, InterruptedException
    {
        // we start by grabbing any state related data from the request
        dataGrabberComposite.grab(genReq);
        String sessionId = genReq.getConversationId();
        String pnr =  genReq.getPNR();
        String uniqeId = genReq.getUniqeId();
        String commandName = getCommandName(genReq) + "_";
        String testId = genReq.getSoapAction();
        LOG.debug("FOUND uniqeId ::::   "  + uniqeId);
        if (testId.equals("TravelItineraryHistoryLLS") && uniqeId.equals("GDMKPU")){
            LOG.debug("FOUND testId ::::   "  + testId);
            int number = sessionsInfoManager.getCount(sessionId, testId);
            testId = testId + number;
            LOG.debug("FOUND testId with number  ::::   "  + testId);
            if (number >= 4)
            {
                LOG.debug("Cleaning TravelItineraryHistoryLLS ::::   "  + testId);
                sessionsInfoManager.clearCount(sessionId, "TravelItineraryHistoryLLS");
                LOG.debug("After Cleaning TravelItineraryHistoryLLS ::::   "  + number);
            }
            LOG.debug("FOUND testId with name after number  ::::   "  + testId);
        }

        if (testId.equals("ACS_PassengerData") && pnr.equals("GDMKPU")){
            LOG.debug("FOUND testId  for acs::::   "  + testId);
            int number = sessionsInfoManager.getCount(sessionId, testId);
            LOG.debug("FOUND testId for acs with number  ::::   "  + number);
            testId = testId + number;
            LOG.debug("FOUND testId with number  ::::   "  + testId);
            if (number >= 10)
            {
                LOG.debug("Cleaning ACS_PassengerData ::::   "  + testId);
                sessionsInfoManager.clearCount(sessionId, "ACS_PassengerData");
                LOG.debug("After Cleaning ACS_PassengerData ::::   "  + number);
            }
            LOG.debug("FOUND testId for acs with name after number  ::::   "  + testId);
        }

        String testName = testId;
        LOG.debug("AirlineId ID::: " + airlineId);
        if (airlineId != null && airlineId != "")
        {
          testName = airlineId + File.separator + testName;
        } else
        {
            airlineId = genReq.getCustomerID();
            testName = airlineId + File.separator + testName;
            LOG.debug("TEST NAME IS::: " + testName);
        }
        Thread.currentThread().setName(testName + "@" + sessionId);
        ProxyMode sessionMode = sessionsInfoManager.getProxyModeForSession(sessionId);
        if (sessionMode != null)
        {
            LOG.debug("Setting Session ID::: " + sessionId + " to run with mode " + sessionMode);
            mode = sessionMode;
        }
        else
        {
            LOG.debug("Session ID::: " + sessionId + " not set via JMX getting the mode from configuration");
            mode = ProxyMode.valueOf(configuration.getMode());
        }
        String fullPath = mainFileManager.getFullTestPath(mode, airlineId, airlineId, testId);
        setPath(sessionId, fullPath);

        Connector connector = getConnector(genReq);
        String requestName = connector.getFileName(genReq);
        if (requestName != null)
        {
//        	sgogna
//            requestName = connector.getRequestFullName(sessionId, commandName + requestName, mode);
            requestName = commandName + requestName ;
        }

        String serviceMode= getServiceMode(genReq);
        // check if the service has a transparent node in service registry if it has
        // bypass the service to go to usg directly instead of getting mocked response.
        if (serviceMode!= null && !serviceMode.equals("0"))
        {
            mode = ProxyMode.valueOf(serviceMode.toUpperCase());
        }

        switch (mode)
        {
            case MOCK:
                httpsSampleServlet.service(genReq, genRes);
                break;

            case RECORD:
                LOG.info("(RECORD" + "): " + fullPath + File.separator + getRequestName(requestName, connector));
//				checkStartTestTime(sessionId, fullPath);
                saveMessage(genReq, requestName, true, fullPath, getHeadersMap(genReq));
                if (forwardRequest(genReq, genRes, configuration.getOriginalHost(), connector, testName, sessionId, requestName + "_RS.xml") >= 0) {
                    LOG.debug("REQUEST  :  " + new String(genReq.getArrayByte()));
                    saveMessage(genRes, requestName, false, fullPath, genRes.getHeaders());
                    LOG.debug("RESPONSE  :  " + new String(genRes.getArrayByte()));
                }
                break;

            case REPLAY:
                long time = System.currentTimeMillis();
                String pathToRecord = mainFileManager.getFullTestPath(ProxyMode.RECORD, airlineId, airlineId, testId);
                LOG.info("(REPLAY" + "): " + pathToRecord + File.separator + getRequestName(requestName, connector));
                if (connector instanceof EndTestConnector)
                {
                    forwardRequest(genReq, genRes, configuration.getOriginalHost(), connector, testName, sessionId, requestName + "_RS.xml");
                }
                else
                {
                    getReplayResponse(requestName, genReq, genRes, pathToRecord, fullPath, configuration.isGenerateCreateSession(), connector, configuration.getOriginalHost(), testName);
                }
                LOG.info("[" + "REPLAY" + ": " + getRequestName(requestName, connector) + "] Execution time: " + new TimeBreakdown(time));
                break;

            case COMPARE:
                long time1 = System.currentTimeMillis();

                LOG.info("(COMPARE" + "): " + fullPath + File.separator + getRequestName(requestName, connector));
				//commented for now not sure why it was used sgogna
				//checkStartTestTime(sessionId, fullPath);
                saveMessage(genReq, requestName, true, fullPath, getHeadersMap(genReq));
                if (forwardRequest(genReq, genRes, configuration.getOriginalHost(), connector, testName, sessionId, requestName + "_RS.xml") >= 0) {
                    saveMessage(genRes, requestName, false, fullPath, genRes.getHeaders());
                }
                LOG.info("[" + "COMPARE" + ": " + getRequestName(requestName, connector) + "] Execution time: " + new TimeBreakdown(time1));
                break;

            case TRANSPARENT:
                LOG.info("(TRANSPARENT" + "): " + getRequestName(requestName, connector));
                forwardRequest(genReq, genRes, configuration.getOriginalHost(), connector, testName, sessionId, requestName);
                break;
        }

    }

//    private void checkStartTestTime(String sessionId, String path)
//    {
//        if (sessionsInfoManager.getCreateTestTime(sessionId).isEmpty())
//        {
//            InfoFileManager.createInfoFile(path);
//            InfoFileManager.addInfo(path, InfoFileManager.TEST_EXECUTION_STATUS, "" + false);
//            InfoFileManager.addInfo(path, InfoFileManager.FAILURE_REASON, "UNKNOWN");
//            InfoFileManager.addInfo(path, InfoFileManager.COMPARE_RESULT, "" + false);
//            InfoFileManager.addInfo(path, InfoFileManager.COMPARE_ALGORITHM, "");
//            InfoFileManager.addInfo(path, InfoFileManager.SESSIONID, sessionId);
//            Long startTestTime = System.currentTimeMillis();
//            InfoFileManager.addInfo(path, InfoFileManager.START_TEST_TIME, startTestTime.toString());
//            InfoFileManager.addInfo(path, InfoFileManager.FORMATTED_START_TEST_TIME, DateReplaceEngine.getFormatedDate(startTestTime));
//
//            sessionsInfoManager.setCreateTestTime(sessionId, startTestTime.toString());
//
//        }
//    }

    void setPath(String sessionId, String path)
    {
        if (sessionsInfoManager.getPath(sessionId).isEmpty())
        {
            sessionsInfoManager.setPath(sessionId, path);
            SpringBeanContainer.getConcurrentRequestsManager().readRules();
        }
    }

    private long getDiffTime(long startTestDate, long testRecordDate)
    {
        // difference in days
        startTestDate = setTimeZero(startTestDate);
        testRecordDate = setTimeZero(testRecordDate);
        if (testRecordDate == 0 || startTestDate == 0)
            return 0;
        return startTestDate - testRecordDate;
    }

    private long setTimeZero(long time)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private String getRequestName(String requestname, Connector connector)
    {

        if (requestname == null && connector.isSpecialRequestAllowed())
        {
            return connector.getSpecialFileName(null) + " --  Not recorded.";
        }
        return requestname;
    }

    void getReplayResponse(String requestName, GenericRequestWrapper req, GenericResponseWrapper resp, String pathToRecord, String recordName, boolean generateCreateSession, Connector connector,
                           String originalhost, String testName) throws InterruptedException, UnsupportedEncodingException {
        String sessionId = req.getConversationId();
        long delayTime = getDelayTime(req);
        if (delayTime != 0) {
            LOG1.debug("Going to sleep for [" + delayTime + "] milliSeconds for " + req.getSoapAction() + " Service....");
            TimeUnit.MILLISECONDS.sleep(delayTime);
            LOG1.debug("Waking up after sleep");
		}
        if (configuration.isRecordFixedResponses()) {
			// commented for now not sure why it was used sgogna        	
			//checkStartTestTime(sessionId, recordName);
            if (requestName != null) {
                saveMessage(req, requestName, true, recordName, getHeadersMap(req));
            }
        }
        if ((requestName != null) || generateCreateSession)
        {
          	// Map<String, String> headers = new HashMap<String, String>();
            Properties headers = new Properties();
            if (requestName == null && generateCreateSession) {
                resp.setContentType("text/xml;charset=utf-8");
            }
            
            byte[] respo;
            
            // prod log reader
            byte[] logReaderResponse = null;
            if(configuration.isProdLogsEnabled()){
                LogReader logReader = new LogReader(configuration,req, resp);
                if( (requestName != null) ){ //  is is not create session
                    logReaderResponse = logReader.read();
                }
            }

            if(logReaderResponse != null){
                respo = logReaderResponse;
            }else{
                respo = requestName != null ? getRecordedMessage(requestName, pathToRecord, headers, false, req.getRequestString(), getHeadersMap(req)) : connector.getSpecialResponse(req, headers);
            }

            // Fake response generator
            if(configuration.isGenerateFakeResponses()){
                try {
                    String orginalResponse = null;
                    if(respo != null && respo.length > 0){
                        orginalResponse = new String(respo, "UTF-8");
                    }

                    String frgResponse = fakeResponseHandler(requestName, req.getRequestString(), orginalResponse);
                    if(frgResponse != null){
                        respo = frgResponse.getBytes("UTF-8");
                        resp.setContentType("application/soap+xml;charset=utf-8");
                        resp.putHeader("Content-Length", String.valueOf(respo.length));
                        resp.setCharacterEncoding("UTF-8");
                    }
                } catch (Exception e) {
                    LOG.error("Fake data generator error.", e);
                }
            }
            if (respo.length == 0) {
                LOG.debug("REQUEST:" + req.getRequestString());
                LOG.debug(requestName + " -  Recorded response not found. Recording message....");
            	LOG.debug("(RECORD" + "): " + pathToRecord + File.separator + getRequestName(requestName, connector));
                saveMessage(req, requestName, true, pathToRecord, getHeadersMap(req));
                if (forwardRequest(req, resp, originalhost, connector, testName, sessionId, requestName + "_RS.xml") >= 0) {
                	saveMessage(resp, requestName, false, pathToRecord, resp.getHeaders());
                }
                respo = requestName != null ? getRecordedMessage(requestName, pathToRecord, headers, false, req.getRequestString(), getHeadersMap(req)) : connector.getSpecialResponse(req, headers);
                LOG.debug("RESPONSE:" + new String(respo));
            }
            LOG.debug("RESPONSE:" + new String(respo));
            if (configuration.isFixResponse())
            {
                if (sessionsInfoManager.getCreateTestTime(sessionId).isEmpty())
                {
                    String date = String.valueOf(System.currentTimeMillis());
                    sessionsInfoManager.setCreateTestTime(sessionId, date);
                }

//                long startTestDate = Long.valueOf(sessionsInfoManager.getCreateTestTime(sessionId));
                respo = replaceDatesHardcodedVersion(pathToRecord, sessionId, respo, requestName, req);
                respo = dataReplacerComposite.replace(sessionId, new String(respo, "UTF-8")).getBytes("UTF-8");
                if (new String(respo, "UTF-8").contains("ACS_") || new String(respo, "UTF-8").contains("getReservation"))
                {
                    LOG.debug("INSIDE ACS RESPONSE TO CHANGE DATES: " + new String(respo, "UTF-8") );
                    respo = dateReplaceEngine.replaceDatesWithCurrentDate(respo);
                    LOG.debug("INSIDE ACS RESPONSE AFTER CHANGE DATES: " + new String(respo, "UTF-8") );
                }
               if (configuration.isRecordFixedResponses())
                {
                    MainFileManager.saveMessage(respo, requestName, recordName, headers, false);

                }
            }
            setResponseHeaders(headers, resp);

            OutputStream o = null;
            try
            {
                o = resp.getOutputStream();
                o.write(respo);
                o.flush();
            }
            catch (IOException e)
            {
                LOG.error("Error writing message - " + requestName + ": ", e);
            } finally {
                IOUtils.closeQuietly(o);
            }
        }
        else if ("GDSSabreConnector".equals(connector.getName()))
        {
            LOG.info("Forwarding SessionCreate request.");
            forwardRequest(req, resp, originalhost, connector, "", "", "");
        }
        else
        {

            LOG.error("Not recognized name. Name: " + requestName);
            LOG.error("REQUEST:" + req.getRequestString());
            try
            {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UNKNOWN TYPE OF REQUEST.");
            }
            catch (IOException e)
            {
                LOG.error(e.getMessage());
            }

        }
    }
    
    protected String fakeResponseHandler(String requestType, String request, String response){
        FrgRequest frgRequest = new FrgRequest();
        frgRequest.setRequestType(requestType);
        frgRequest.setRequest(request);
        frgRequest.setResponse(response);
        FrgResponse frgResponse = frgHandler.handleRequest(frgRequest);
        if(frgResponse != null){
            return frgResponse.getResponse();
        }
        return null;
    }
    
    private byte[] replaceDatesHardcodedVersion(String pathToRecord, String sessionId, byte[] respo, String requestName, GenericRequestWrapper genReq)
    {
        if (requestName != null && requestName.equals(getCommandName(genReq) + "_SABREGDS_SSWIntellisell"))
        {
            String rqnow = genReq.getRequestString();
            String rqold = new String(MainFileManager.readMessage(requestName, pathToRecord, null, true, null, null, false));
            try
            {
                long oldDate = getDateFromRequest(rqold);
                long currentDate = getDateFromRequest(rqnow);

                long difftime = getDiffTime(currentDate, oldDate);
                sessionsInfoManager.setDiffTime(sessionId, difftime);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return respo;
            }
        }

        long difftime = sessionsInfoManager.getDiffTime(sessionId);


            if (difftime != 0)
            {
                respo = dateReplaceEngine.replaceDates(respo, difftime);
            }
        else
        {
            LOG.error("Difference beetween test record date and now is 0. Dates wont't be replaced.");
        }

        return respo;
    }

    private long getDateFromRequest(String rq) throws ParseException
    {
        Pattern DATE_PATTERN = Pattern.compile("DepartureDateTime>(\\d{4}-[01]\\d-[0-3]\\d((T[0-2]\\d:[0-5]\\d:[0-5]\\d)?))<");
        String DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat format1 = new SimpleDateFormat(DATE_FORMAT1);
        Matcher m = DATE_PATTERN.matcher(rq);
        if (m.find())
        {
            String date = rq.substring(m.start(1), m.end(1));
            return format1.parse(date).getTime();
        }
        return 0;
    }

    byte[] getRecordedMessage(String requestName, String pathToRecord, Properties headers, boolean isRequest, String rqMessage, Properties rqHeaders)
    {

    	 return MainFileManager.readMessage(requestName, pathToRecord, headers, isRequest, rqMessage, rqHeaders, configuration.isUseSharedCache());
    }

    private void setResponseHeaders(Properties headers, HttpServletResponse response)
    {
        for (String name : headers.stringPropertyNames())
        {
            if (!name.equals("Content-Length") || !name.equals("Content-Encoding") )
            {
            String value = headers.getProperty(name);
            response.addHeader(name, value);
            }
        }
    }

    private int forwardRequest(HttpServletRequest req, HttpServletResponse resp, String originalhost, Connector connector, String testId, String sessionId, String filename)
    {
        int connectTimeout = configuration.getConnectTimeout() != 0 ? configuration.getConnectTimeout() : 0;
        if (originalhost == null ||originalhost.equalsIgnoreCase("null"))
        {
            originalhost =  connector.getEndpoint(req, serviceManager);
        }

        return connector.callRealService(req, resp, originalhost == null ? connector.getEndpoint(req, serviceManager) : originalhost, testId, sessionId, connectTimeout, filename);
        
    }

    void saveMessage(GenericWrapper message, String requestName, boolean isRequest, String path, Properties headers)
    {
    if (requestName == null)
    {
        return;
    }

    MainFileManager.saveMessage(message.getArrayByte(), requestName, path, headers, isRequest);
    if (isRequest)
    {
        InfoFileManager.addFileToOrderList(path, requestName);
    }

}

    Connector getConnector(HttpServletRequest req)
    {
        for (Connector connector : connectorList.getConnectors())
        {
            if (connector.isProperConnector(req))
            {
                return connector;
            }
        }
        LOG.warn("UNKNOWN CONNECTOR FOR REQUEST: ");
        LOG.warn(((GenericRequestWrapper)req).getRequestString());
        return connectorList.getDefaultConnector();
    }

    Properties getHeadersMap(HttpServletRequest request)
    {
        Properties headers = new Properties();
        Enumeration< ? > en = request.getHeaderNames();
        while (en.hasMoreElements())
        {
            String headerName = (String) en.nextElement();
            String headerValue = request.getHeader(headerName);
//            if (headerName.equals("accept"))
//            {
//                headers.remove("accept");
//                headers.setProperty("accept", "text/xml");
//            }
            if (!headerName.equals("accept-encoding"))
            {
//            headers.put("accept-encoding", "deflate");
//            headers.put("accept", "text/xml");
            headers.setProperty(headerName, headerValue);
            }
        }
        return headers;
    }

    String getCommandName(GenericRequestWrapper genReq)
    {
        StringBuilder strBuff = new StringBuilder();
        HostCommand hostCommand = genReq.getHostCommand();
        String sessionId = genReq.getConversationId();
        LOG.info("Action found ::: " + genReq.getSoapAction() + " with sessionID ::: " + sessionId);
//        if (genReq.getSoapAction().equals("TravelItineraryHistoryLLS"))
//        {
//            if (numTravelItineraryHistory > 4)
//                numTravelItineraryHistory = 0;
//            numTravelItineraryHistory = numTravelItineraryHistory + 1;
//            LOG.info("TravelItineraryHistoryLLS Command found increasing sessionid ::: " + sessionId + " ::: to  ::: " + numTravelItineraryHistory );
//            SpringBeanContainer.getSessionsInfoManager().setServiceCounter(sessionId, numTravelItineraryHistory);
//        }
       if (genReq.getSoapAction().equals("getReservation"))
        {
            LOG.info("getReservation Command found setting sessionid ::: " + sessionId + " ::: to false");
            SpringBeanContainer.getSessionsInfoManager().setExchangeStarted(sessionId, false);
        }
        if (genReq.getSoapAction().equals("TravelItineraryReadLLS"))
        {
            LOG.debug("TravelItineraryReadLLS Command found setting sessionid ::: " + sessionId + " ::: to true");
            SpringBeanContainer.getSessionsInfoManager().setExchangeStarted(sessionId, true);
        }
      	if (genReq.getSoapAction().equals("SabreCommandLLS")) {
             ///////////////////////////////////////////////////
                  if ( hostCommand.isVICommand()) {
                      LOG1.debug("VI Command found :: " + hostCommand);
                      if (SpringBeanContainer.getSessionsInfoManager().getExchangeStarted(sessionId) == false)
                      {
                          strBuff.append("ExchangeStarted");
    //                      SpringBeanContainer.getSessionsInfoManager().setExchangeStarted(sessionId, true);
                          LOG1.debug("This is First *VI Command appending :: ExchangeStarted to ::: " + hostCommand + " ::: and sessionId is ::: " + sessionId);
                      }
                      else
                      {
                          strBuff.append("ExchangeEnded");
    //                      SpringBeanContainer.getSessionsInfoManager().setExchangeStarted(sessionId, false);
                          LOG.debug("This is second *VI Command appending :: ExchangeEnded to ::: " + hostCommand + " ::: and sessionId is ::: " + sessionId);
                      }
                      int num = SpringBeanContainer.getSessionsInfoManager().getCount(sessionId, "*VI");
                      LOG.debug("VI Command found :: " + num + " :: many times for session ID :::" + sessionId );
                  }
            ////////////////////////////////////////////////////////////////


            LOG1.info("Parameters to build file name from Request :: " + hostCommand);
            strBuff.append(hostCommand);
		}
    	else {
            String requestContent = genReq.getRequestString();
            requestContent = XmlUtil.prettyFormat(requestContent, 2);
            String regExp = serviceManager.getNodeByRequestType(genReq.getSoapAction()).getRegex();
    		if (regExp.contains("#")) {
    			String delims = "[#]";
				String [] temp = regExp.split(delims);
                for (String test : temp) {
                    Pattern PASS_PATTERN = Pattern.compile("<" + test + ">");
                    Matcher m = PASS_PATTERN.matcher(requestContent);
                    while (m.find())
                        strBuff.append(m.group(1));
                    strBuff.append("_");
                }
                LOG1.info("Parameters to build file name from Request :: " + strBuff);
            }
			
		}
    		
    	return strBuff.toString();
    }


    private long getDelayTime(GenericRequestWrapper genReq)
    {
        return serviceManager.getNodeByRequestType(genReq.getSoapAction()).getDelayTimeInMilliseconds();
//        String delayTime = serviceManager.getNodeByRequestType(genReq.getSoapAction()).getDelayTimeInSeconds();
//        try {
//            return Integer.valueOf(delayTime);
//        } catch (NumberFormatException e) {
//            return 0;
//        }
    }

    private String getServiceMode(GenericRequestWrapper genReq)
    {
        return serviceManager.getNodeByRequestType(genReq.getSoapAction()).getServiceMode();
    }


    private String getTravelItineraryHistoryName(GenericRequestWrapper genReq)
    {
        StringBuilder strBuff = null;
        if (genReq.getSoapAction().equals("TravelItineraryHistoryLLS"))
        {
            strBuff = new StringBuilder();
            numTravelItineraryHistory = SpringBeanContainer.getSessionsInfoManager().getServiceCounter(genReq.getConversationId());
            strBuff.append("TravelItineraryHistoryLLS" + numTravelItineraryHistory);
        }

        return strBuff.toString();
    }
}
