package testproxy.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HttpsSampleServlet
 */
public class HttpsSampleServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    int nr = 0;

    boolean GZLLQT_ota_done = false;
    boolean GZLLQT_VI = false;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpsSampleServlet()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        res.setContentType("text/html");

        // url passed in as browser query string
        String url = req.getParameter("httpsURL");
        String url3 = req.getParameter("URL");
        String urlReceived = url;
        System.out.println("\n htts server url received: " + url + " url3="
                + url3);
        // //////////
        String queryStrng = req.getQueryString();
        String url2 = req.getRequestURL().toString();
        String uri2 = req.getRequestURI();
        System.out.println("\n doGet called url=" + url2 + " queryString="
                + queryStrng + " uri=" + uri2);
        // /////////

        if (null != url)
            url = URLDecoder.decode(url);
        else
        {
            // url passed in as servlet init parameter
            url = getInitParameter("httpsURL");
        }

        URLConnection conn = null;
        URL connectURL = null;

        // send result to the caller
        try
        {

            PrintWriter out = res.getWriter();
            if (null == url || url.length() == 0)
            {
                out.println("No Https URL provided to retrieve");
            }
            else
            {
                connectURL = new URL(url);
                conn = connectURL.openConnection();
                DataInputStream theHTML = new DataInputStream(
                        conn.getInputStream());
                String thisLine;
                while ((thisLine = theHTML.readLine()) != null)
                {
                    out.println(thisLine);
                }
            }
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception in HttpsSampleServlet: "
                    + e.getMessage());
            e.printStackTrace();
        }

    }// get end

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("----------" + request.getHeader("testId"));

        boolean SessionCreateRQ = false;
        boolean sswIntellisellRQ_RQ = false;
        boolean ChangeAAALLSRQ = false;
        boolean endTransactionRQ_RQ = false;
        boolean AirlineBrandingRQ = false;
        boolean SessionCloseRQ = false;
        boolean OTA_AirRulesLLSRQ = false;
        boolean IgnoreTransactionLLSRQ = false;
        boolean OTA_AirBookLLSRQ = false;
        boolean SabreCommandLLSRQ = false;
        boolean IMAP_AirSeatMapLLSRQ = false;
        boolean TravelItineraryAddInfoLLSRQ = false;
        boolean AddRemarkLLSRQ = false;
        boolean EndTransactionLLSRQ = false;
        boolean OTA_TravelItineraryReadLLSRQ = false;
        boolean QPlaceLLSRQ = false;
        boolean OTA_ProfileReadRQ = false;
        boolean Sabre_CDIRQ = false;//
        boolean MTS_TravelExtrasShopRQ = false;
        boolean VCRDisplayLLSRQ = false;
        boolean MTS_TravelExtrasResRQ = false;
        boolean VerifyFlightDetailsLLSRQ = false;
        boolean OTA_CancelLLSRQ = false;
        boolean AERRQ = false;
        boolean PaymentRQ = false;

        String sessiionID4applicClient = "";

        String[] RequestFlightData = new String[6];

        String[] RequestFlightDataRPH3 = new String[3];

        String[] PlusMinus3daysDatesOB = new String[6];
        String[] PlusMinus3daysDatesIB = new String[6];
        String[] PlusMinus3daysDatesRPH3 = new String[3];

        String[] PlusMinus3daysDatesforResponseOW = new String[6];

        // List<String> converts = new ArrayList<String>();

        String queryStrng = request.getQueryString();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        String requstHeader = request.getHeader(remoteHost);
        HttpSession rq_sessionID = request.getSession();
        String servlet_session_id = rq_sessionID.getId();

        String requestContent = "";
        String requestStorefront = "";
        String currentSessionID = "";

        System.out.println("\n doPost called url=" + url + " queryString="
                + queryStrng + " uri=" + uri + " remoteHost: " + remoteHost);
        System.out.println("\n REmoteUSer: " + remoteUser + " respNr=" + nr);

        Enumeration parmNames = request.getParameterNames();
        System.out.println(" requestParameters " + parmNames.toString()
                + " req length:" + request.getContentLength() + " req method: "
                + request.getMethod());// + " "
                                       // +(String)parmNames.nextElement());
        System.out.println(" request2str: " + request.toString());
        System.out.println(" requestHeader: " + requstHeader);
        System.out.println(" servlet_session_id: " + servlet_session_id);

        try
        {

            // /collectRequestContent
            String RequestLineCollect = "";
            BufferedReader bf = request.getReader();
            while ((RequestLineCollect = bf.readLine()) != null)
            {
                requestContent = requestContent + RequestLineCollect;
            }// whilw

            System.out.println("\n Servlet:: requestContent=" + requestContent);

            // /end collectRequest

            System.out.println(" BufReadLine: " + requestContent);

            // get sesion ID
            //
            // <ns5:ConversationId>7B47_30D4B631F52CFAD715D655CA6EFBE8D5
            // </ns5:ConversationId>
            //

            currentSessionID = getRequestSessionID(requestContent);

            if (requestContent.contains("SessionCreateRQ"))
            {
                SessionCreateRQ = true;
                System.out.println("\n request SesionCreateRQ received");
            }// if
            if (requestContent.contains("SSWIntellisellRQ"))
            {
                sswIntellisellRQ_RQ = true;
                System.out.println("\n request SSWIntellisellRQ received");
            }// if
            if (requestContent.contains("AirlineBrandingRQ"))
            {
                AirlineBrandingRQ = true;
            }
            // ChangeAAALLSRQ
            if (requestContent.contains("ChangeAAALLSRQ"))
            {
                ChangeAAALLSRQ = true;
            }// if

            if (requestContent.contains("endTransactionRQ_RQ"))
            {
                endTransactionRQ_RQ = true;
            }// if
            if (requestContent.contains("QPlaceLLSRQ"))
            {
                QPlaceLLSRQ = true;
            }// if
            if (requestContent.contains("SessionCloseRQ"))
            {
                SessionCloseRQ = true;
            }// if
             // OTA_AirRulesRQ
            if (requestContent.contains("OTA_AirRulesLLSRQ"))
            {
                OTA_AirRulesLLSRQ = true;
            }// if
             // IgnoreTransactionLLSRQ
            if (requestContent.contains("IgnoreTransactionLLSRQ"))
            {
                IgnoreTransactionLLSRQ = true;
            }// if
             // OTA_AirBookLLSRQ
            if (requestContent.contains("OTA_AirBookLLSRQ"))
            {
                OTA_AirBookLLSRQ = true;
            }// if
             // SabreCommandLLSRQ
            if (requestContent.contains("SabreCommandLLSRQ"))
            {
                SabreCommandLLSRQ = true;
            }// if
             // AirSeatMap
            if (requestContent.contains("IMAP_AirSeatMapLLSRQ"))
            {
                IMAP_AirSeatMapLLSRQ = true;
            }// if
             // ChangeAAAService
             // TravelItineraryAddInfoLLSRQ
            if (requestContent.contains("TravelItineraryAddInfoLLSRQ"))
            {
                TravelItineraryAddInfoLLSRQ = true;
            }// if
             // AddRemarkLLSRQ
            if (requestContent.contains("AddRemarkLLSRQ"))
            {
                AddRemarkLLSRQ = true;
            }// if
             // EndTransactionLLSRQ
            if (requestContent.contains("EndTransactionLLSRQ"))
            {
                EndTransactionLLSRQ = true;
            }// if
             // OTA_TravelItineraryReadLLSRQ
            if (requestContent.contains("OTA_TravelItineraryReadLLSRQ"))
            {
                OTA_TravelItineraryReadLLSRQ = true;
            }// if
             // OTA_ProfileReadRQ
            if (requestContent.contains("OTA_ProfileReadRQ"))
            {
                OTA_ProfileReadRQ = true;
            }// if
             // Sabre_CDIRQ
            if (requestContent.contains("Sabre_CDIRQ"))
            {
                Sabre_CDIRQ = true;
            }// if
             // MTS_TravelExtrasShopRQ
            if (requestContent.contains("MTS_TravelExtrasShopRQ"))
            {
                MTS_TravelExtrasShopRQ = true;
            }// if
             // VCRDisplayLLSRQ
            if (requestContent.contains("VCRDisplayLLSRQ"))
            {
                VCRDisplayLLSRQ = true;
            }// if
             // MTS_TravelExtrasResRQ
            if (requestContent.contains("MTS_TravelExtrasResRQ"))
            {
                MTS_TravelExtrasResRQ = true;
            }// if
             // VerifyFlightDetailsLLSRQ
            if (requestContent.contains("VerifyFlightDetailsLLSRQ"))
            {
                VerifyFlightDetailsLLSRQ = true;
            }// if
             // OTA_CancelLLSRQ
            if (requestContent.contains("OTA_CancelLLSRQ"))
            {
                OTA_CancelLLSRQ = true;
            }// if
             // AERRQ
            if (requestContent.contains("AERRQ"))
            {
                AERRQ = true;
            }// if
             // PaymentRQ
            if (requestContent.contains("PaymentRQ"))
            {
                PaymentRQ = true;
            }// if

        }// try End
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // response.setContentType("text/html");
        // ("application/x-www-form-urlencoded");
        response.setContentType("application/soap+xml");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out_response = response.getWriter();

        // out_response.println(" response to POST" + nr );
        nr++;

        // boolean wasItRBErequest = iSitRbeBookingREQ(requestContent);
        boolean wasItRBErequest = false;
        boolean wasItOWrequest = false;
        boolean wasItRTrequest = false;
        boolean wasItMCrequest = false;
        boolean wasItCALDATEsearchRQ = false;
        int rphNumber = 0;

        // ////////////////evaluate type of request///////////////
        if (SessionCreateRQ)
        {
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            System.out.println("\n prepearing reponse SessionCreateRS for Storefront=" + requestStorefront);
            // String
            // mockResponse=ReadFileBuf("/IntelisellFilesRepo/SessionCreateRS.xml");
            // F7_SessionCreateRS_response.xml //SU_SessionCreateRS_response.xml
            String responseFileName = "";
            if (requestStorefront.equals("F7"))
                responseFileName = "/IntelisellFilesRepo/F7_SessionCreateRS_response.xml";
            if (requestStorefront.equals("WYWY"))
                responseFileName = "/IntelisellFilesRepo/WY_SessionCreateRS_response.xml";
            if (requestStorefront.equals("PG"))
                responseFileName = "/IntelisellFilesRepo/PG_SessionCreateRS_response.xml";
            if (requestStorefront.equals("SU"))
                responseFileName = "/IntelisellFilesRepo/SU_SessionCreateRS_response.xml";
            if (requestStorefront.equals("B6"))
                responseFileName = "/IntelisellFilesRepo/B6_SessionCreateRS_response.xml";
            if (responseFileName.equals(""))
                responseFileName = "/IntelisellFilesRepo/TokeResponse.xml";

            String mockResponse = readFile(responseFileName);
            sessiionID4applicClient = requestStorefront + requestStorefront + "_" + servlet_session_id;
            String oldFileSessionID = getRequestSessionID(mockResponse);
            // oldFileSessionID=:ConversationId>PGPG_03D9C22AC8E7658BE7B06392971A1847
            System.out.println(" SessionCreateRS OLD session oldFileSessionID=" + oldFileSessionID);
            // // request2=request2.replaceAll("StartAirport", StartAirport);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, sessiionID4applicClient);
            // readFile
            out_response.println(mockResponse);
            System.out.println("\n will be returned SessionCreateRS mockResponse :" + mockResponse);
            System.out.println(" SessionCreateRS session Assigned ID=" + sessiionID4applicClient);
        }// if SessionCreateRQ

        if (sswIntellisellRQ_RQ)
        {
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            wasItCALDATEsearchRQ = checkIfCALDATEsearch(requestContent);
            wasItRBErequest = iSitRbeBookingREQ(requestContent);
            // TODO check if OW or RT
            rphNumber = checkOWRTMCrequestType(requestContent);
            if (rphNumber > 2)
                wasItMCrequest = true;
            if (rphNumber == 2)
                wasItRTrequest = true;
            if (rphNumber == 1)
                wasItOWrequest = true;

            System.out.println("Servlet::sswIntellisellRQ_RQ:: wasItMCrequest=" + wasItMCrequest);
            System.out.println("Servlet::sswIntellisellRQ_RQ:: wasItRTrequest=" + wasItRTrequest);
            System.out.println("Servlet::sswIntellisellRQ_RQ:: wasItOWrequest=" + wasItOWrequest);
            System.out.println("Servlet::sswIntellisellRQ_RQ:: rphNumber=" + rphNumber);

            RequestFlightData = getRequestFlightData(requestContent, rphNumber);
            System.out.println("Servlet:: RequestFlightData[0]=" + RequestFlightData[0] + "<--");// outbound
                                                                                                 // flight
                                                                                                 // date
            System.out.println("Servlet:: RequestFlightData[1]=" + RequestFlightData[1] + "<--");// start
                                                                                                 // airport
            System.out.println("Servlet:: RequestFlightData[2]=" + RequestFlightData[2] + "<--");// end
                                                                                                 // airport
            System.out.println("Servlet:: RequestFlightData[3]=" + RequestFlightData[3] + "<--");// inbound
                                                                                                 // flight
                                                                                                 // date
            System.out.println("Servlet:: RequestFlightData[4]=" + RequestFlightData[4] + "<--");// inbound
                                                                                                 // start
                                                                                                 // airport
            System.out.println("Servlet:: RequestFlightData[5]=" + RequestFlightData[5] + "<--");// inbound
                                                                                                 // end
                                                                                                 // airport

            // MC checkOWRTMCrequestType
            if (rphNumber > 2)
            {
                RequestFlightDataRPH3 = getRequestFlightDataMCrph3(requestContent, rphNumber);
                System.out.println("Servlet:: RequestFlightDataRPH3[0]=" + RequestFlightDataRPH3[0]);
                System.out.println("Servlet:: RequestFlightDataRPH3[1]=" + RequestFlightDataRPH3[1]);
                System.out.println("Servlet:: RequestFlightDataRPH3[2]=" + RequestFlightDataRPH3[2]);

                // /////////calculate +-3 days dates//////////
                PlusMinus3daysDatesOB = calculatePlusMinus3daysDatesOB(RequestFlightData[0]);// outbound
                                                                                             // flight
                                                                                             // date
                if (wasItRTrequest)
                    PlusMinus3daysDatesIB = calculatePlusMinus3daysDatesOB(RequestFlightData[3]);// inbound
                                                                                                 // flight
                                                                                                 // date
                if (rphNumber == 3 && wasItMCrequest)
                    PlusMinus3daysDatesRPH3 = calculatePlusMinus3daysDatesOB(RequestFlightDataRPH3[0]);// inbound
                                                                                                       // flight
                                                                                                       // date
                                                                                                       // rph3
            }

        }// end if
        if (sswIntellisellRQ_RQ && wasItRBErequest && wasItRTrequest)
        {
            System.out
                    .println("\n prepearing RBE reponse sswIntellisellRQ_RQRS  ");
            // String
            // mockResponse=ReadFileBuf("/IntelisellFilesRepo/SessionCreateRS.xml");
            String mockResponse = "";
            String responseFileNameRBE = "/IntelisellFilesRepo/PG_RBE_response_BKK_REP.xml";
            String OB_StartAirportRBE = RequestFlightData[1];
            String OB_End_AirportRBE = RequestFlightData[2];
            if (requestStorefront.equals("PG") && OB_StartAirportRBE.equals("BKK") && OB_End_AirportRBE.equals("REP"))
                responseFileNameRBE = "/IntelisellFilesRepo/PG_RBE_response_BKK_REP.xml";
            if (OB_StartAirportRBE.equals("BKK") && OB_End_AirportRBE.equals("USM"))
                responseFileNameRBE = "/IntelisellFilesRepo/PG_RBE_response_BKK_USM.xml";
            // readFile
            mockResponse = readFile(responseFileNameRBE);
            System.out.println("\n will be returned RBE sswIntellisellRQ_RQ mockResponse filename " + responseFileNameRBE);
            System.out.println("   will be returned RBE sswIntellisellRQ_RQ mockResponse before :" + mockResponse);
            String mockResponseDatesReplaced = replaceDatesInRTresponse(mockResponse, RequestFlightData);

            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponseDatesReplaced);
            // oldFileSessionID=:ConversationId>PGPG_03D9C22AC8E7658BE7B06392971A1847
            // //System.out.println(" SessionCreateRS OLD session oldFileSessionID="+oldFileSessionID);
            // // request2=request2.replaceAll("StartAirport", StartAirport);
            mockResponseDatesReplaced = mockResponseDatesReplaced.replaceAll(oldFileSessionID, currentRequestSessionID);

            out_response.println(mockResponseDatesReplaced);
            System.out.println("\n will be returned RBE sswIntellisellRQ_RQ mockResponse after :" + mockResponseDatesReplaced);
            mockResponseDatesReplaced = null;
            // out_response.println(mockResponse);
        }// if SessionCreateRQ
        else
        {
            // System.out.println("\n prepearing reponse non RBE  sswIntellisellRQ_RQRS  ");
            if (sswIntellisellRQ_RQ && !wasItRBErequest && wasItRTrequest)// nonRBE
                                                                          // RT
                                                                          // request
                if (wasItRTrequest)// RT request
                {
                    String mockResponse = "";
                    String OB_StartAirport = RequestFlightData[1];
                    String OB_End_Airport = RequestFlightData[2];
                    // SU_repsponse_AMS_SVO.xml //SU_repsponse_MOW_LED.xml
                    if (requestStorefront.equals("PG") && !wasItCALDATEsearchRQ)
                    {
                        String responseFilename = "/IntelisellFilesRepo/PG_repsponse_BKK_REP.xml";

                        if (OB_StartAirport.equals("BKK") && OB_End_Airport.equals("CNX"))
                            responseFilename = ("/IntelisellFilesRepo/PG_repsponse_BKK_CNX.xml");

                        if (OB_StartAirport.equals("BKK") && OB_End_Airport.equals("REP"))
                            responseFilename = ("/IntelisellFilesRepo/PG_repsponse_BKK_REP.xml");

                        mockResponse = readFile(responseFilename);
                        System.out.println("\n prepearing reponse non RBE  BKK_REP or SIN PG   ");
                    }
                    if (requestStorefront.equals("SU"))// >>
                        if (OB_StartAirport.equals("MOW") && OB_End_Airport.equals("IKT"))
                        {
                            mockResponse = readFile("/IntelisellFilesRepo/SU_repsponse_MOW_IKT.xml");
                            System.out.println("\n will be returned NON RBE sswIntellisellRQ_RQ mockResponse before MOW-IKT :"
                                    + mockResponse);
                        }
                        else
                            mockResponse = readFile("/IntelisellFilesRepo/SU_repsponse_AMS_SVO.xml");
                    if (requestStorefront.equals("SU") && OB_StartAirport.equals("MOW") && OB_End_Airport.equals("LED"))
                    {
                        mockResponse = "";
                        mockResponse = readFile("/IntelisellFilesRepo/SU_repsponse_MOW_LED.xml");
                        System.out
                                .println("\n will be returned NON RBE sswIntellisellRQ_RQ mockResponse before MOW-LED :"
                                        + mockResponse);
                    }

                    if (requestStorefront.equals("F7"))
                        mockResponse = readFile("/IntelisellFilesRepo/F7_repsponse_GVA_FCO.xml");
                    if (requestStorefront.equals("WY"))
                        mockResponse = readFile("/IntelisellFilesRepo/WY_repsponse_AUH_BAH_caldates.xml");

                    if (requestStorefront.equals("B6"))
                        mockResponse = readFile("/IntelisellFilesRepo/B6_repsponse_AUS_JFK_caldates.xml");

                    if (requestStorefront.equals("PG") && wasItCALDATEsearchRQ)
                    {
                        mockResponse = readFile("/IntelisellFilesRepo/PG_repsponse_CALDATES_BKK_REP_caldates.xml");
                        System.out.println("\n PG CALDATES search");

                    }
                    // System.out.println("\n will be returned NON RBE sswIntellisellRQ_RQ mockResponse before :"
                    // +mockResponse); //wasItCALDATEsearchRQ
                    // replace dates
                    String mockResponseDatesReplaced = "";
                    if (!OB_End_Airport.equals("CNX"))// check if on t OR
                                                      // logical
                        mockResponseDatesReplaced = replaceDatesInRTresponse(mockResponse, RequestFlightData);
                    else
                        mockResponseDatesReplaced = mockResponse;

                    if (requestStorefront.equals("B6") && wasItCALDATEsearchRQ)
                        mockResponseDatesReplaced = replaceDatesInRTresponseLFS(mockResponse, RequestFlightData);

                    if (requestStorefront.equals("PG") && wasItCALDATEsearchRQ)
                        mockResponseDatesReplaced = replaceDatesInRTresponseLFS(mockResponse, RequestFlightData);

                    if (requestStorefront.equals("WY") && wasItCALDATEsearchRQ)
                        mockResponseDatesReplaced = replaceDatesInRTresponseLFS(mockResponse, RequestFlightData);
                    // mockResponseDatesReplaced = mockResponse;
                    // replaceDatesInRTresponseLFS

                    mockResponse = null;
                    // readFile

                    String currentRequestSessionID = getRequestSessionID(requestContent);
                    String oldFileSessionID = getRequestSessionID(mockResponseDatesReplaced);
                    mockResponseDatesReplaced = mockResponseDatesReplaced.replaceAll(oldFileSessionID, currentRequestSessionID);

                    out_response.println(mockResponseDatesReplaced);
                    System.out.println("\n will be returned NON RBE sswIntellisellRQ_RQ mockResponse After :" + mockResponseDatesReplaced);

                    mockResponseDatesReplaced = null;
                }// RTend wasItRTrequest
            if (wasItOWrequest)// OW
            {
                String mockResponse = "";
                if (requestStorefront.equals("PG"))
                    mockResponse = readFile("/IntelisellFilesRepo/PG_OW_response_BKK_REP.xml");
                if (requestStorefront.equals("SU"))
                    mockResponse = readFile("/IntelisellFilesRepo/SU_OW_response_MOV_IKT.xml");
                if (requestStorefront.equals("B6"))
                    mockResponse = readFile("/IntelisellFilesRepo/B6_repsponse_AUS_JFK_caldates.xml");

                String mockResponseDatesReplaced = replaceDatesInOWresponse(mockResponse, RequestFlightData);
                // readFile

                // sessiionID4applicClient = requestStorefront
                // +requestStorefront+ "_"+ servlet_session_id;
                String oldFileSessionID = getRequestSessionID(mockResponseDatesReplaced);
                String currentRequestSessionID = getRequestSessionID(requestContent);
                oldFileSessionID = getRequestSessionID(mockResponseDatesReplaced);

                mockResponseDatesReplaced = mockResponseDatesReplaced.replaceAll(oldFileSessionID, currentRequestSessionID);

                out_response.println(mockResponseDatesReplaced);
                System.out.println("\n will be returned OW NON RBE sswIntellisellRQ_RQ mockResponse after :" + mockResponseDatesReplaced);
                mockResponseDatesReplaced = "";
                mockResponse = "";
            }// OWend

        }// else sswIntellisell
         // MC requests
        if (sswIntellisellRQ_RQ & wasItMCrequest && rphNumber == 3)// MC 3rph
        {

            System.out.println("\n will be returned MC!!!!");
            String mockResponse = "";
            if (requestStorefront.equals("WY"))
                mockResponse = readFile("/IntelisellFilesRepo/WY_MC_response_AUH_MCT.xml");
            if (requestStorefront.equals("SU"))
                mockResponse = readFile("/IntelisellFilesRepo/SU_MC_response_MOV_ARH.xml");

            String mockResponseDatesReplaced = "";
            // mockResponseDatesReplaced=replaceDatesInRTresponse(mockResponse,
            // RequestFlightData);
            // RequestFlightDataRPH3
            // //mockResponseDatesReplaced =
            // replaceDatesInOWresponse(mockResponseDatesReplaced,
            // RequestFlightDataRPH3);
            // readFile
            // out_response.println(mockResponseDatesReplaced);

            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);

            out_response.println(mockResponse);
            // System.out.println("\n will be returned MC NON RBE sswIntellisellRQ_RQ mockResponse after :"+
            // mockResponseDatesReplaced);
            System.out.println("\n will be returned MC NON RBE sswIntellisellRQ_RQ mockResponse after :" + mockResponse);
            mockResponseDatesReplaced = "";
            mockResponse = "";
        }// MCend

        if (endTransactionRQ_RQ)
        {
            System.out.println("\n prepearing reponse WY_qPlace_response.xml  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "/IntelisellFilesRepo/TokeResponse.xml";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_EndTransaction.xml";
            // responseFilename
            String mockResponse = readFile(responseFilename);
            // readFile
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out.println("\n will be returned SessionCreateRS mockResponse :" + mockResponse);

        }
        // SessionCloseRQ
        if (SessionCloseRQ)
        {
            System.out.println("\n prepearing reponse SessionCloseRS  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            // String
            // mockResponse=ReadFileBuf("/IntelisellFilesRepo/SessionCreateRS.xml");
            String mockResponse = readFile("/IntelisellFilesRepo/SessionCloseRS.xml");
            if (requestStorefront.equals("SU"))
            {
                mockResponse = "";
                mockResponse = readFile("/IntelisellFilesRepo/SU_SessionCloseRS.xml");
                ;
            }
            if (requestStorefront.equals("B6"))
            {
                mockResponse = "";
                mockResponse = readFile("/IntelisellFilesRepo/B6_SessionCloseRS.xml");
                ;
            }
            // readFile

            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out.println("\n will be returned SessionCloseRS mockResponse :" + mockResponse);

        }
        // AirlineBrandingRQ
        if (AirlineBrandingRQ)
        {
            System.out.println("\n prepearing reponse AirlineBrandingRS   ");
            String mockResponse = "";
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            if (requestStorefront.equals("PG"))
                mockResponse = readFile("/IntelisellFilesRepo/PG_AirlineBrandingRS_BKK_REP_RT_RSfix.xml");
            if (requestStorefront.equals("SU"))
                // mockResponse=readFile("/IntelisellFilesRepo/SU_AirlineBrandingRS_AMS_SVO_RT_RSfix.xml");
                mockResponse = readFile("/IntelisellFilesRepo/SU_AirlineBrandingRS_MOW_IKT_RT_RS.xml");
            if (requestStorefront.equals("F7"))
                mockResponse = readFile("/IntelisellFilesRepo/F7_AirlineBrandingRS_GVA_FCO_RT_RS.xml");
            if (requestStorefront.equals("WY"))
                mockResponse = readFile("/IntelisellFilesRepo/WY_AirlineBrandingRS_AUH_BAH_RT_RS.xml");

            // readFile
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out.println("\n will be returned AirlineBrandingRS mockResponse :" + mockResponse);

        }

        // ChangeAAALLSRQ
        if (ChangeAAALLSRQ)
        {
            System.out.println("\n prepearing reponse ChangeAAALLSRQR  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "/IntelisellFilesRepo/ChangeAAALLSRS_BKK_REP_RT.xml";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_ChangeAAALLSRS_GVA_FCO_RT.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_ChangeAAALLSRS_BKK_REP_RT.xml";
            // su SU_ChangeAAALLSRS_MOW_IKT_RT.xml
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_ChangeAAALLSRS_MOW_IKT_RT.xml";
            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_ChangeAAALLSRS_AUH_BAH_RT.xml";
            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_ChangeAAALLSRS_AUS_JFK_RT.xml";

            System.out
                    .println("\n prepearing reponse ChangeAAALLSRQR::   responseFilename="
                            + responseFilename
                            + "  requestStorefront="
                            + requestStorefront);
            String mockResponse = readFile(responseFilename);
            // readFile
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out
                    .println("\n will be returned ChangeAAALLSRQRS mockResponse :"
                            + mockResponse);

        }
        // OTA_AirRulesLLSRQ
        if (OTA_AirRulesLLSRQ)
        {
            System.out.println("\n prepearing reponse OTA_AirRulesLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String[] requestAirRilesLLSairports = new String[6];
            requestAirRilesLLSairports = getAirRulesLLSairports(requestContent);
            String mockResponse = "";
            String responseFilename = "/IntelisellFilesRepo/PG_nonRBE_OTA_AirRilesLLSRS_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_nonRBE_OTA_AirRilesLLSRS_response.xml";

            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_nonRBE_OTA_AirRilesLLSRS_response.xml";
            if (requestStorefront.equals("SU")
                    && requestAirRilesLLSairports[0].equals("SVO")
                    && requestAirRilesLLSairports[0].equals("IKT"))
                responseFilename = "/IntelisellFilesRepo/SU_nonRBE_OTA_AirRilesLLSRS_SVO_IKT_response.xml";

            if (requestStorefront.equals("SU")
                    && requestAirRilesLLSairports[0].equals("SVO")
                    && requestAirRilesLLSairports[0].equals("MMK"))
                responseFilename = "/IntelisellFilesRepo/SU_nonRBE_OTA_AirRilesLLSRS_SVO_MMK_response.xml";

            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_nonRBE_OTA_AirRilesLLSRS_response.xml";

            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_nonRBE_OTA_AirRilesLLSRS_AUH_BAH_response.xml";
            mockResponse = readFile(responseFilename);
            // readFile
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out
                    .println("\n will be returned OTA_AirRulesLLSRQ mockResponse :"
                            + mockResponse);

        }
        if (IgnoreTransactionLLSRQ)
        {
            System.out
                    .println("\n prepearing reponse IgnoreTransactionLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            // SU_IgnoreTransationRS_response.xml
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_IgnoreTransationRS_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_IgnoreTransationRS_response.xml";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_IgnoreTransationRS_response.xml";
            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_IgnoreTransationRS_response.xml";
            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_IgnoreTransationRS_response.xml";
            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end IgnoreTransactionLLSRQ

        // OTA_AirBookLLSRQ
        if (OTA_AirBookLLSRQ)
        {
            System.out.println("\n prepearing reponse OTA_AirBookLLSRQ   ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            // SU_OTA_AirBookRS_MOW_IKT_RT.xml
            String responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_MOW_IKT_RT.xml";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_MOW_IKT_RT.xml";
            /*
             * ActionCode="NN" ResBookDesigCode="J"
             * ArrivalDateTime="2011-06-16T08:15:00" FlightNumber="829"
             * DepartureDateTime="2011-06-16T06:55:00"> <ns9:DepartureAirport
             * LocationCode="SVO" /><ns9:ArrivalAirport LocationCode="LED" />
             * <ns9:OperatingAirline Code="SU" /><ns9:MarketingAirline Code="SU"
             * /></ns9:FlightSegment></
             */
            String[] requestAirports = getAirBookLSRQairports(requestContent);
            responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_SVO_IKT_RT.xml";
            System.out.println("\n prepearing reponse OTA_AirBookLLSRQ    requestAirports[0]="
                    + requestAirports[0]
                    + "<-- requestAirports[1]="
                    + requestAirports[1] + "<--");

            if (requestStorefront.equals("SU") && requestAirports[0].contains("MOW") && requestAirports[1].contains("LED"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_MOW_LED_RT.xml";
            if (requestStorefront.equals("SU") && requestAirports[0].contains("SVO") && requestAirports[1].contains("LED"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_MOW_LED_RT.xml";
            if (requestStorefront.equals("SU") && requestAirports[0].contains("IKT") && requestAirports[1].contains("SVO"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_IKT_SVO_RT.xml";
            if (requestStorefront.equals("SU") && requestAirports[0].contains("SVO") && requestAirports[1].contains("IKT"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_SVO_IKT_RT.xml";
            if (requestStorefront.equals("SU") && requestAirports[0].contains("MOW") && requestAirports[1].contains("IKT"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_IKT_SVO_RT.xml";
            if (requestStorefront.equals("SU") && requestAirports[0].contains("IKT") && requestAirports[1].contains("SVO"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_IKT_SVO_RT2.xml";
            // override
            responseFilename = "/IntelisellFilesRepo/SU_OTA_AirBookRS_SVO_IKT_RTuniversal.xml";

            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_OTA_AirBookRS_GVA_FCO_RT.xml";
            if (requestStorefront.equals("F7") && requestAirports[0].contains("GVA") && requestAirports[1].contains("FCO"))
                responseFilename = "/IntelisellFilesRepo/F7_OTA_AirBookRS_GVA_FCO_RT2.xml";

            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_OTA_AirBookRS_AUH_BAH_RT.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_OTA_AirBookRS_AUS_JFK_RT.xml";

            if (requestStorefront.equals("PG")
                    && requestAirports[0].contains("BKK")
                    && requestAirports[1].contains("REP"))
                responseFilename = "/IntelisellFilesRepo/PG_OTA_AirBookRS_BKK_REP_RT.xml";
            char codzyslow = '"';
            String PG_OTAAirBook_Flight924 = "FlightNumber=" + codzyslow + "924";
            String PG_OTAAirBook_Flight903 = "FlightNumber=" + codzyslow + "903";

            if (requestStorefront.equals("PG") && requestAirports[0].contains("BKK") && requestAirports[1].contains("REP") && requestContent.contains(PG_OTAAirBook_Flight924))
                responseFilename = "/IntelisellFilesRepo/PG_OTA_AirBookRS_BKK_REP_RT1.xml";

            System.out.println("\n prepearing reponse OTA_AirBookLLSRQ    responseFilename=" + responseFilename + "<-----requestAirports[0]=" + requestAirports[0] + "<----requestAirports[1]=" + requestAirports[0] + "<-");

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out
                    .println("\n prepearing reponse  SENT OTA_AirBookLLSRQ  ::mockResponse =" + mockResponse);
        }
        // SabreCommandLLSRQ
        if (SabreCommandLLSRQ)
        {
            System.out.println("\n prepearing reponse SabreCommandLLSRQ   ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            // ":GenericHostCommand>WPMRUBP1ADT</"
            String hostCommand = getHostCommand(requestContent);
            System.out.println("\n SabreCommandLLSRQ:: hostCommand="
                    + hostCommand + "   storefront=" + requestStorefront);
            // SU_OTA_AirBookRS_MOW_IKT_RT.xml
            String responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1.xml";
            // mock_WPMRUB_MOW_IKT
            // if (requestStorefront.equals("SU")&&
            // hostCommand.contains("WPMRUB")) responseFilename =
            // "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("WPMRUB"))
                responseFilename = "/IntelisellFilesRepo/mock_WPMRUB_MOW_IKT.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("WPMRUB"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1b.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*ADT§"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT2.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*PE"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT3.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("PE"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT3.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("WPRQ"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT4.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4DOCS/"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT5.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4G1/"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT6.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4G2/"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT7.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("/0"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT23.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("CK*"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT8.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("TBM*"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT9.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("W*"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT10.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("F*") && hostCommand.contains("*C") && hostCommand.contains("*Z") && hostCommand.contains("VCR"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT11.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*DSCKRM"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT12.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("-1"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT13.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("WPRQ"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT15.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("EM"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT16.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*PI"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT17.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*P4"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT18.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*PQ"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT19.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("*VI") && !GZLLQT_VI)
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT20.xml";

            if (requestStorefront.equals("SU") && hostCommand.contains("*VI") && GZLLQT_VI)
            {
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MMS_RT_Vi_PNR_GZLLQT.xml";
                GZLLQT_VI = false;
            }

            if (requestStorefront.equals("SU") && hostCommand.contains("VCR*"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT21.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("VCR*") && requestContent.contains("5552155257031"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT21b.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("VCR*5552155274020") && requestContent.contains("5552155274020"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MMS_RT21c.xml";

            if (requestStorefront.equals("SU") && hostCommand.contains("*") && hostCommand.length() == 7)
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT22.xml";

            if (requestStorefront.equals("SU") && hostCommand.contains("*IAB"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT24.xml";
            if (requestStorefront.equals("SU") && hostCommand.equals("*IA"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MSQ_RT25.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4DOCS/DB/"))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_SabreCommandLLSRS_MOW_IKT_RT26.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4DOCS//A"))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_SabreCommandLLSRS_MOW_IKT_RT27.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4DOCO//R"))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_SabreCommandLLSRS_MOW_IKT_RT27b.xml";
            if (requestStorefront.equals("SU") && hostCommand.contains("4DOCS/P"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT28.xml";

            // 4exchange
            if (requestStorefront.equals("SU") && hostCommand.contains("*IA"))
                responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_MMK_RT40.xml";

            if (requestStorefront.equals("F7"))
            {
                if (requestStorefront.equals("F7") && hostCommand.contains("WPMCHF"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT1.xml";
                // if (requestStorefront.equals("F7")&&
                // hostCommand.contains("WPMCHF")) responseFilename =
                // "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1b.xml";

                if (requestStorefront.equals("F7") && hostCommand.contains("-1"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT13.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("PE"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT3.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("WPRQ"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT4.xml";
                // if (requestStorefront.equals("F7") &&
                // hostCommand.contains("WPRQ")) responseFilename =
                // "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT4.xml";

                if (requestStorefront.equals("F7") && hostCommand.contains("4DOCS/"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT5.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("4G1/"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT6.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("CK*"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT8.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("TBM*"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT9.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("W*"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT10.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("W") && hostCommand.contains("F*"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT10a.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("*PI"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT17.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("*PQ"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT18.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("*VI"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT19.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("0INS"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT20.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("EM"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT16.xml";
                if (requestStorefront.equals("F7") && hostCommand.contains("AAAZDH"))
                    responseFilename = "/IntelisellFilesRepo/F7_SabreCommandLLSRS_GVA_FCO_RT21.xml";
                // if(requestStorefront.equals("F7")&&hostCommand.contains("WPMCHF"))
                // responseFilename="/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1.xml";

            }// if F7

            if (requestStorefront.equals("WY"))
            {
                if (requestStorefront.equals("WY") && hostCommand.contains("WPMOMR"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT1.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("-1"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT13.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("PE"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT3.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("WPRQ"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT4.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("4DOCS/"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT5.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("4G1/"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT6.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("4G2/"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT7.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("4G3/"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT17.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("4G4/"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT18.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("CK*"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT8.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("TBM*"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT9.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("W*"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT10.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("W") && hostCommand.contains("F*"))
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT10a.xml";
                if (requestStorefront.equals("WY") && hostCommand.contains("*") && hostCommand.length() == 7)
                    responseFilename = "/IntelisellFilesRepo/WY_SabreCommandLLSRS_AUH_BAH_RT19.xml";
            }// if WY

            if (requestStorefront.equals("PG"))
            {
                if (requestStorefront.equals("PG") && hostCommand.contains("WPMTHB"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT1.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("-1"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT2.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("WPRQ"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT3.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("4DOCS/DB/"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT4.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("CK*"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT5.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("TBM*"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT6.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("W*2"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT7.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("F*"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT8.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("*PI"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT9.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("*VI"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT10.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("INSMDHK"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT11.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("EM"))
                    responseFilename = "/IntelisellFilesRepo/PG_SabreCommandLLSRS_BKK_REP_RT12.xml";
                if (requestStorefront.equals("PG") && hostCommand.contains("PE"))
                    responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT3.xml";// temp
                                                                                                   // fix

            }// if PG

            if (requestStorefront.equals("B6"))
            {
                if (requestStorefront.equals("B6") && hostCommand.contains("A*"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT1.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("WPMUSD"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT2.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("1WSVQ"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT3.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("WPRQ"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT4.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("4DOCS/DB"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT5.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("CK*"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT6.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("W*"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT7.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("F*") && hostCommand.contains("W") && hostCommand.contains("VCR"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT8.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("*PI"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT9.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("*P4"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT10.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("*VI"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT11.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("VCR*"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT12.xml";
                if (requestStorefront.equals("B6") && hostCommand.contains("EM"))
                    responseFilename = "/IntelisellFilesRepo/B6_SabreCommandLLSRS_AUS_JFK_RT13.xml";
            }// if B6
            System.out.println("\n SabreCommandLLSRS::   file name ="
                    + responseFilename + "<--   requestStorefront="
                    + requestStorefront + "<--");
            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            // out_response.p

            System.out.println("\n SabreCommandLLSRS::   mockResponse=" + mockResponse);
        }// end SabreCommandLLSRQ

        // IMAP_AirSeatMapLLSRQ
        if (IMAP_AirSeatMapLLSRQ)
        {
            System.out.println("\n prepearing reponse IMAP_AirSeatMapLLSRQ   ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            // ":GenericHostCommand>WPMRUBP1ADT</"

            String responseFilename = "/IntelisellFilesRepo/SU_nonRBE_IMAP_AirSeatMapRS_response.xml";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_nonRBE_IMAP_AirSeatMapRS_response.xml";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_nonRBE_IMAP_AirSeatMapRS_response.xml";
            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_nonRBE_IMAP_AirSeatMapRS_AUH_BAH_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_nonRBE_IMAP_AirSeatMapRS_BKK_REP_response.xml";
            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_nonRBE_IMAP_AirSeatMapRS_AUS_JFK_response.xml";
            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }
        // TravelItineraryAddInfoLLSRQ
        if (TravelItineraryAddInfoLLSRQ)
        {
            System.out
                    .println("\n prepearing reponse TravelItineraryAddInfoLLSRQ   ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "/IntelisellFilesRepo/SU_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";

            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";

            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";

            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_nonRBE_TravelItineraryAddInfoLLSRS_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }
        // AddRemarkLLSRQ
        if (AddRemarkLLSRQ)
        {
            System.out.println("\n prepearing reponse AddRemarkLLSRQ   ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            // ":GenericHostCommand>WPMRUBP1ADT</"
            // HistoricalRemark Text="FARE/PUB/DRTRF
            char codzyslow = '"';
            String addRemark2STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/DR";
            // ns9:HistoricalRemark
            // Text="FARE/PUB/AMT/1ADT/100000/2509/102509/RUB" />
            String addRemark3STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/AMT/";
            String addRemark4STR = "HistoricalRemark Text=" + codzyslow + "B"; // HistoricalRemark
                                                                               // Text="BA/
            String addRemark5STR = "HistoricalRemark Text=" + codzyslow + "B/";
            String addRemark6STR = "BasicRemark Text=" + codzyslow + "B/";
            String addRemark7STR = "HistoricalRemark Text=" + codzyslow + "SSWMOD-DT/";
            String addRemark8STR = "HistoricalRemark Text=" + codzyslow + "CARDHOLDERNAME-"; // HistoricalRemark
            // Text="CARDHOLDERNAME-
            String addRemark9STR = "HistoricalRemark Text=" + codzyslow + "TRAVEL DOC - ADD SSR";
            // HistoricalRemark Text="SENT CONFIRMATION EMAIL
            String addRemark10STR = "HistoricalRemark Text=" + codzyslow + "SENT CONFIRMATION EMAIL";
            String addRemark11STR = "HistoricalRemark Text=" + codzyslow + "ADD SF ERR - ";
            String addRemark12STR = "HistoricalRemark Text=" + codzyslow + "TRAVEL DOC - ADD SSR ERR";
            String addRemark126TR = "HistoricalRemark Text=" + codzyslow + "FEE/EXCHANGE";
            String addRemark127TR = "HistoricalRemark Text=" + codzyslow + "FEE/EXCHCREDIT";
            String addRemark128TR = "HistoricalRemark Text=" + codzyslow + "EXCHFARE/AMT/";
            String addRemark129TR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/SNRT";

            String responseFilename = "/IntelisellFilesRepo/SU_SabreCommandLLSRS_MOW_IKT_RT1.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark2STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT2.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark3STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT3.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark4STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT4.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark5STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT5.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark6STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT6.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark7STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT7.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark8STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT8.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(":HistoricalRemark Text="))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT1.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark9STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT9.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark10STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT10.xml";
            if (requestStorefront.equals("SU") && requestContent.contains("EM"))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT11.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark11STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT12.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark12STR))
                responseFilename = "/IntelisellFilesRepo/SU_AddRemarkLLSRQ_MOW_IKT_RT9err.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark126TR))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_AddRemarkLLSRQ_MOW_IKT_RT26.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark127TR))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_AddRemarkLLSRQ_MOW_IKT_RT27.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark128TR))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_AddRemarkLLSRQ_MOW_IKT_RT28.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(addRemark129TR))
                responseFilename = "/IntelisellFilesRepo/SU_EXCH_AddRemarkLLSRQ_MOW_IKT_RT29.xml";

            String addRemark11_F7_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/HR";
            String addRemark3_F7_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/AMT/";
            String addRemark4_F7_STR = "HistoricalRemark Text=" + codzyslow + "B"; // HistoricalRemark
                                                                                   // Text="BA/
            String addRemark5_F7_STR = "HistoricalRemark Text=" + codzyslow + "B/";
            String addRemark6_F7_STR = "BasicRemark Text=" + codzyslow + "B/";
            String addRemark12_F7_STR = "HistoricalRemark Text=" + codzyslow + "FEE/PROCESS";
            String addRemark7_F7_STR = "HistoricalRemark Text=" + codzyslow + "SSWMOD-DT/";
            String addRemark8_F7_STR = "HistoricalRemark Text=" + codzyslow + "CARDHOLDERNAME-";
            String addRemark10_F7_STR = "HistoricalRemark Text=" + codzyslow + "SENT CONFIRMATION EMAIL";

            if (requestStorefront.equals("F7") && requestContent.contains(addRemark11_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT2.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark3_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT3.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark4_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT4.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark5_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT5.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark6_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT6.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark12_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT12.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark7_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT7.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark8_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT8.xml";
            if (requestStorefront.equals("F7") && requestContent.contains(addRemark10_F7_STR))
                responseFilename = "/IntelisellFilesRepo/F7_AddRemarkLLSRQ_GVA_FCO_RT10.xml";

            String addRemark11_WY_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/QE";
            String addRemark12_WY_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/AMT/";
            String addRemark4_WY_STR = "HistoricalRemark Text=" + codzyslow + "B";
            String addRemark5_WY_STR = "HistoricalRemark Text=" + codzyslow + "B/";
            String addRemark7_WY_STR = "HistoricalRemark Text=" + codzyslow + "SSWMOD-DT/";
            String addRemark8_WY_STR = "HistoricalRemark Text=" + codzyslow + "CARDHOLDERNAME-";

            if (requestStorefront.equals("WY") && requestContent.contains(addRemark11_WY_STR))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT11.xml";
            if (requestStorefront.equals("WY") && requestContent.contains(addRemark12_WY_STR))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT12.xml";
            if (requestStorefront.equals("WY") && requestContent.contains(addRemark4_WY_STR) && requestContent.contains("Z/"))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT4.xml";
            if (requestStorefront.equals("WY") && requestContent.contains(addRemark5_WY_STR) && requestContent.contains("C/"))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT5.xml";
            if (requestStorefront.equals("WY") && requestContent.contains(addRemark7_WY_STR))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT7.xml";
            if (requestStorefront.equals("WY") && requestContent.contains(addRemark8_WY_STR))
                responseFilename = "/IntelisellFilesRepo/WY_AddRemarkLLSRQ_AUH_BAH_RT8.xml";

            String addRemark1_PG_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/YRT";
            if (requestStorefront.equals("PG") && requestContent.contains(addRemark1_PG_STR))
                responseFilename = "/IntelisellFilesRepo/PG_AddRemarkLLSRQ_BKK_REP_RT1.xml";
            String addRemark2_PG_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/AMT/";
            if (requestStorefront.equals("PG") && requestContent.contains(addRemark2_PG_STR))
                responseFilename = "/IntelisellFilesRepo/PG_AddRemarkLLSRQ_BKK_REP_RT2.xml";
            String addRemark3_PG_STR = "HistoricalRemark Text=" + codzyslow + "SSWMOD-DT/";
            if (requestStorefront.equals("PG") && requestContent.contains(addRemark3_PG_STR))
                responseFilename = "/IntelisellFilesRepo/PG_AddRemarkLLSRQ_BKK_REP_RT3.xml";
            String addRemark4_PGP_STR = "HistoricalRemark Text=" + codzyslow + "CARDHOLDERNAME-";
            if (requestStorefront.equals("PG") && requestContent.contains(addRemark4_PGP_STR))
                responseFilename = "/IntelisellFilesRepo/PG_AddRemarkLLSRQ_BKK_REP_RT4.xml";
            String addRemark5_PGP_STR = "HistoricalRemark Text=" + codzyslow + "SENT CONFIRMATION EMAIL";
            if (requestStorefront.equals("PG") && requestContent.contains(addRemark5_PGP_STR))
                responseFilename = "/IntelisellFilesRepo/PG_AddRemarkLLSRQ_BKK_REP_RT5.xml";

            String addRemark1_B6_STR = "HistoricalRemark Text=" + codzyslow + "B";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark1_B6_STR) && requestContent.contains("A/"))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT1.xml";
            String addRemark2_B6_STR = "HistoricalRemark Text=" + codzyslow + "Purpose ";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark2_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT2.xml";
            String addRemark3_B6_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/YB";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark3_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT3.xml";
            String addRemark4_B6_STR = "HistoricalRemark Text=" + codzyslow + "FARE/PUB/AMT/";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark4_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT4.xml";
            String addRemark5_B6_STR = "HistoricalRemark Text=" + codzyslow + "SSWMOD-";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark5_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT5.xml";
            String addRemark6_B6_STR = "HistoricalRemark Text=" + codzyslow + "RISK MGNT/";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark6_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT7.xml";
            String addRemark8_B6_STR = "HistoricalRemark Text=" + codzyslow + "SENT CONFIRMATION EMAIL";
            if (requestStorefront.equals("B6") && requestContent.contains(addRemark8_B6_STR))
                responseFilename = "/IntelisellFilesRepo/B6_AddRemarkLLSRQ_AUS_JFK_RT8.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }
        // EndTransactionLLSRQ
        if (EndTransactionLLSRQ)
        {
            System.out.println("\n prepearing reponse EndTransactionLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_EndTransactionLLSRS_response.xml";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_EndTransactionLLSRS_response.xml";
            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_EndTransactionLLSRS_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_EndTransactionLLSRS_response.xml";
            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_EndTransactionLLSRS_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end EndTransactionLLSRQ
         // OTA_TravelItineraryReadLLSRQ
        if (OTA_TravelItineraryReadLLSRQ)
        {
            System.out
                    .println("\n prepearing reponse OTA_TravelItineraryReadLLSRQ    GZLLQT_ota_done=" + GZLLQT_ota_done);
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            char codzyslow = '"';
            // SU_IgnoreTransationRS_response.xml
            String endTransactionSTR2 = "Transaction Code=" + codzyslow + "PNR"; // Transaction
                                                                                 // Code="PNR"
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response2.xml";
            if (requestStorefront.equals("SU") && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response3.xml";
            if (requestStorefront.equals("SU") && !requestContent.contains("ISKNIU")) // /was
                                                                                      // MEFDJV
                                                                                      // change
                                                                                      // to
                                                                                      // ISKNIU
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response5.xml";
            // PN38
            if (requestStorefront.equals("SU") && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response4.xml";// quickfix
            if (requestStorefront.equals("SU") && requestContent.contains("PN38")
                    && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response4.xml";
            ;

            if (requestStorefront.equals("SU") && requestContent.contains("MRFDJV"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response4.xml";

            if (requestStorefront.equals("SU") && requestContent.contains("GZLLQT"))
            {
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response8.xml";
                GZLLQT_ota_done = true;
            }

            if (requestStorefront.equals("SU") && requestContent.contains("PN38") && GZLLQT_ota_done && !requestContent.contains("GZLLQT"))
            {
                System.out.println(" OTA_TravelItineraryReadLLSRQ::::::   GZLLQT_ota_done=" + GZLLQT_ota_done);
                responseFilename = "/IntelisellFilesRepo/SU_OTA_TravelItineraryReadLLSRQ_response8.xml";
                GZLLQT_ota_done = false;
                GZLLQT_VI = true;
            }

            if (requestStorefront.equals("F7")
                    && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/F7_OTA_TravelItineraryReadLLSRQ_response1.xml";
            if (requestStorefront.equals("F7") && requestContent.contains("LHCBIB"))
                responseFilename = "/IntelisellFilesRepo/PG_OTA_TravelItineraryReadLLSRQ_response3.xml";// temporarily
                                                                                                        // Wy
                                                                                                        // pnr

            if (requestStorefront.equals("WY")
                    && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/WY_OTA_TravelItineraryReadLLSRQ_response1.xml";

            if (requestStorefront.equals("PG") && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/PG_OTA_TravelItineraryReadLLSRQ_response1.xml";// temporarily
                                                                                                        // Wy
                                                                                                        // pnr

            if (requestStorefront.equals("PG") && requestContent.contains(endTransactionSTR2) && requestContent.contains("JULJUI"))
                responseFilename = "/IntelisellFilesRepo/PG_OTA_TravelItineraryReadLLSRQ_response2.xml";// temporarily
                                                                                                        // Wy
                                                                                                        // pnr

            if (requestStorefront.equals("B6") && requestContent.contains(endTransactionSTR2))
                responseFilename = "/IntelisellFilesRepo/B6_OTA_TravelItineraryReadLLSRQ_response1.xml";
            if (requestStorefront.equals("B6") && requestContent.contains("UniqueID") && requestContent.contains("GXAGKB"))
                responseFilename = "/IntelisellFilesRepo/B6_OTA_TravelItineraryReadLLSRQ_response2.xml";

            String mockResponse = readFile(responseFilename);
            // String mockResponse = readFileNoCR(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out.println("\n prepearing reponse OTA_TravelItineraryReadLLSRQ  responseFilename=" + responseFilename);
        }// end EndTransactionLLSRQ

        // QPlaceLLSRQ
        if (QPlaceLLSRQ)
        {
            System.out.println("\n prepearing reponse QPlaceLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            char codzyslow = '"';
            // SU_IgnoreTransationRS_response.xml
            String qPlaceSUSTR = "QueueIdentifier Number=" + codzyslow + "111"
                    + codzyslow + "PseudoCityCode=" + codzyslow + "DSU"; // QueueIdentifier
                                                                         // Number="111"
                                                                         // PseudoCityCode="DSU"
            if (requestStorefront.equals("SU")
                    && requestContent.contains(qPlaceSUSTR))
                responseFilename = "/IntelisellFilesRepo/SU_qPlace_response.xml";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_qPlace_response.xml";

            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_qPlace_response.xml";

            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_qPlace_response.xml";

            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_qPlace_response.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_qPlace_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end EndTransactionLLSRQ
         // OTA_ProfileReadRQ
        if (OTA_ProfileReadRQ)
        {
            System.out.println("\n prepearing reponse OTA_ProfileReadRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_ProfileReadRQ_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end ProfileReadRQ
         // Sabre_CDIRQ
        if (Sabre_CDIRQ)
        {
            System.out.println("\n prepearing reponse Sabre_CDIRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_Sabre_CDIRQ_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end Sabre_CDIRQ
         // MTS_TravelExtrasShopRQ
        if (MTS_TravelExtrasShopRQ)
        {
            System.out
                    .println("\n prepearing reponse MTS_TravelExtrasShopRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_MTS_TravelExtrasShopRQ_response.xml";

            if (requestStorefront.equals("WY"))
                responseFilename = "/IntelisellFilesRepo/WY_MTS_TravelExtrasShopRQ_response.xml";

            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_MTS_TravelExtrasShopRQ_response.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_MTS_TravelExtrasShopRQ_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end MTS_TravelExtrasShopRQ

        // VCRDisplayLLSRQ
        if (VCRDisplayLLSRQ)
        {
            System.out.println("\n prepearing reponse VCRDisplayLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "/IntelisellFilesRepo/F7_VCRDisplayLLSRQ_GVA_FCO_response.xml";
            char codzyslow = '"';
            String SU_VCRstring_1 = "eTicketNumber" + codzyslow
                    + "5552155257031";// eTicketNumber="5552155257031
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_VCRDisplayLLSRQ_GVA_FCO_response.xml";

            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_VCRDisplayLLSRQ_SVO_MSQ_response.xml";

            if (requestStorefront.equals("SU") && requestContent.contains(SU_VCRstring_1))
                responseFilename = "/IntelisellFilesRepo/SU_VCRDisplayLLSRQ_SVO_MSQ_response.xml";

            String SU_VCRstring_2 = "eTicketNumber" + codzyslow + "5552155274020";// eTicketNumber="5552155274020"
            if (requestStorefront.equals("SU") && requestContent.contains("5552155274020"))
            {
                responseFilename = "/IntelisellFilesRepo/SU_VCRDisplayLLSRQ_SVO_MMS_response.xml";
                System.out.println(" VCRDisplayLLSRQ::  VCR nr 5552155274020  call received");
            }

            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_VCRDisplayLLSRQ_BKK_REP_response.xml";

            String PG_VCRstring_1 = "eTicketNumber" + codzyslow + "8292168006591";// eTicketNumber="8292168006591
            if (requestStorefront.equals("PG") && requestContent.contains(PG_VCRstring_1))
                responseFilename = "/IntelisellFilesRepo/PG_VCRDisplayLLSRQ_BKK_REP_response.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_VCRDisplayLLSRQ_AUS_JFK_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

            System.out.println("\n  reponse VCRDisplayLLSRQ  responseFilename=" + responseFilename + "<--");
        }// end VCRDisplayLLSRQ
         // MTS_TravelExtrasResRQ

        if (MTS_TravelExtrasResRQ)
        {
            System.out.println("\n prepearing reponse MTS_TravelExtrasResRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("F7"))
                responseFilename = "/IntelisellFilesRepo/F7_MTS_TravelExtrasResRQ_GVA_FCO_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/PG_MTS_TravelExtrasResRQ_BKK_REP_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end VCRDisplayLLSRQ
         // VerifyFlightDetailsLLSRQ
        if (VerifyFlightDetailsLLSRQ)
        {
            System.out.println("\n prepearing reponse VerifyFlightDetailsLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>747"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MSQ_response.xml";
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>750"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MSQ_response1.xml";
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>476"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MSQ_response2a.xml";
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>475"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MSQ_response2b.xml";
            // <ns9:FlightNumber>637</ns9:FlightNumber>
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>637"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MMS_response3a.xml";
            // FlightNumber>622<
            if (requestStorefront.equals("SU") && requestContent.contains("FlightNumber>622"))
                responseFilename = "/IntelisellFilesRepo/SU_VerifyFlightDetailsLLSRS_SVO_MMS_response3b.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);
            System.out.println("\n  VerifyFlightDetailsLLSRQ  will return responseFilename=" + responseFilename);
        }// end VerifyFlightDetailsLLSRQ

        // OTA_CancelLLSRQ
        if (OTA_CancelLLSRQ)
        {
            System.out.println("\n prepearing reponse OTA_CancelLLSRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("SU"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_CancelLLSRQ_SVO_MSQ_response.xml";
            if (requestStorefront.equals("PG"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_CancelLLSRQ_SVO_MSQ_response.xml";

            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/SU_OTA_CancelLLSRQ_SVO_MSQ_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end OTA_CancelLLSRQ
         // AERRQ
        if (AERRQ)
        {
            System.out.println("\n prepearing reponse AERRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            char codzyslow = '"';
            String requestAERexchangeRetainSTR1 = "Transaction Action="
                    + codzyslow + "ExchangeRetain";
            String requestAERexchangeRetainSTR2 = "Transaction Action="
                    + codzyslow + "TicketRetained";
            if (requestStorefront.equals("SU")
                    && requestContent.contains(requestAERexchangeRetainSTR1))
                responseFilename = "/IntelisellFilesRepo/SU_AERRQ_response1.xml";
            if (requestStorefront.equals("SU")
                    && requestContent.contains(requestAERexchangeRetainSTR2))
                responseFilename = "/IntelisellFilesRepo/SU_AERRQ_response2.xml";
            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end OTA_CancelLLSRQ
         // PaymentRQ
        if (PaymentRQ)
        {
            System.out.println("\n prepearing reponse PaymentRQ  ");
            requestStorefront = getRQstorefront_SessionCreateRQ(requestContent);
            String responseFilename = "";
            if (requestStorefront.equals("B6"))
                responseFilename = "/IntelisellFilesRepo/B6_OTA_PaymentRQ_AUS_JFK_response.xml";

            String mockResponse = readFile(responseFilename);
            String currentRequestSessionID = getRequestSessionID(requestContent);
            String oldFileSessionID = getRequestSessionID(mockResponse);
            mockResponse = mockResponse.replaceAll(oldFileSessionID, currentRequestSessionID);
            out_response.println(mockResponse);

        }// end OTA_CancelLLSRQ

        // ///////////////end evaluate type of request////////////

    }// doPost END

    private String getRequestSessionID(String requestContent)
    {
        // TODO Auto-generated method stub

        // <ns5:ConversationId>7B47_30D4B631F52CFAD715D655CA6EFBE8D5

        // oldFileSessionID=:ConversationId>PGPG_03D9C22AC8E7658BE7B06392971A1847
        // </ns5:ConversationId>
        String SessionIDStart_STR = ":ConversationId>";
        String SessionIDEnd_STR = "</";
        int SessionIDStart_INT = 0;
        int SessionIDEnd_INT = 0;
        String currentSessionID = "";

        SessionIDStart_INT = requestContent.indexOf(SessionIDStart_STR);
        if (SessionIDStart_INT > 0)
        {
            SessionIDEnd_INT = requestContent.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
            currentSessionID = requestContent.substring(SessionIDStart_INT + 16, SessionIDEnd_INT);
            System.out.println("\n getConversationId=" + currentSessionID + "<--");
        }

        return currentSessionID;
    }

    private boolean checkIfCALDATEsearch(String requestContent)
    // <ns4:RequestType Name="CALDATES" />
    {
        // TODO Auto-generated method stub
        char codzyslow = '"';
        String CaldateSTR = "<ns4:RequestType Name=" + codzyslow + "CALDATES";
        boolean toBeReturned = false;
        if (requestContent.contains(CaldateSTR))
            toBeReturned = true;
        System.out.println("Servled::DoPOST::checkIfCALDATEsearch returns=" + toBeReturned);
        return toBeReturned;
    }

    private boolean checkIfBRANDEDsearch(String requestContent)
    // <ns4:RequestType Name="CALDATES" />
    {
        // TODO Auto-generated method stub
        char codzyslow = '"';
        String BrandedSTR = "<ns4:RequestType Name=" + codzyslow + "BRDFARES";
        boolean toBeReturned = false;
        if (requestContent.contains(BrandedSTR))
            toBeReturned = true;
        System.out.println("Servled::DoPOST::checkIfBRANDEDsearch returns=" + toBeReturned);
        return toBeReturned;
    }

    private String[] getAirRulesLLSairports(String requestSTR)
    {
        // TODO Auto-generated method stub
        /*
         * <ns9:FareReference Code="DRTRF" /> <ns9:FilingAirline Code="SU" />
         * <ns9:DepartureAirport LocationCode="SVO" /> <ns9:ArrivalAirport
         * LocationCode="IKT" /> <ns9:DepartureDate
         * DateTime="2011-06-17T01:15:00" />
         * </ns9:RuleReqInfo></ns9:OTA_AirRulesRQ>
         */
        String[] aRequestFlightData = new String[6];
        // //////////////////
        System.out.println("\n getAirRulesLLSairports start ");

        System.out.println("\n Servlet::getAirRulesLLSairports: requestSTR="
                + requestSTR);
        String OriginDestinationOption_Start_String = "FareReference Code=";
        String OriginDestinationOption_End_String = "FilingAirline Code=";
        int StartOriginDestinationOption = 0;
        int EndOriginDestinationOption = 0;
        StartOriginDestinationOption = requestSTR
                .indexOf(OriginDestinationOption_Start_String);
        EndOriginDestinationOption = requestSTR.indexOf(
                OriginDestinationOption_End_String,
                StartOriginDestinationOption);

        int StartDepartureAirport = 0;
        int EndDepartureAirport = 0;
        String Start_DepartureAirport = "DepartureAirport LocationCode=";
        String End__DepartureAirport = "/>";
        StartDepartureAirport = requestSTR.indexOf(Start_DepartureAirport,
                EndOriginDestinationOption);
        EndDepartureAirport = requestSTR.indexOf(End__DepartureAirport,
                StartDepartureAirport);
        String airBookLSRQ_OriginAirport = requestSTR.substring(
                StartDepartureAirport + 2, EndDepartureAirport - 2);
        System.out
                .println("\n Servlet::getAirBookLSRQairports: airBookLSRQ_OriginAirport="
                        + airBookLSRQ_OriginAirport + "<--");

        int Start_ArrivalAirport = 0;
        int End_ArrivalAirport = 0;
        String RPH1_Start_ArrivalAirport = "ArrivalAirport LocationCode=";
        String RPH1_End_ArrivalAirport = "/>";
        Start_ArrivalAirport = requestSTR.indexOf(RPH1_Start_ArrivalAirport,
                EndDepartureAirport);
        End_ArrivalAirport = requestSTR.indexOf(RPH1_End_ArrivalAirport,
                Start_ArrivalAirport);
        String airBookLSRQ_ArrivalAirport = requestSTR.substring(
                Start_ArrivalAirport + 2, End_ArrivalAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: airBookLSRQ_ArrivalAirport="
                        + airBookLSRQ_ArrivalAirport + "<--");

        aRequestFlightData[0] = airBookLSRQ_OriginAirport;
        aRequestFlightData[1] = airBookLSRQ_ArrivalAirport;
        aRequestFlightData[2] = "";

        aRequestFlightData[3] = "";
        aRequestFlightData[4] = "";
        aRequestFlightData[5] = "";

        // //////////////////
        return aRequestFlightData;
    }

    private String[] getAirBookLSRQairports(String requestSTR)
    {
        // TODO Auto-generated method stub
        String[] aRequestFlightData = new String[6];
        // //////////////////
        System.out.println("\n getAirBookLSRQairports start ");
        //
        /*
         * <ns9:AirItinerary> <ns9:OriginDestinationOptions>
         * <ns9:OriginDestinationOption> <ns9:FlightSegment NumberInParty="1"
         * ActionCode="NN" ResBookDesigCode="J"
         * ArrivalDateTime="2011-06-16T08:15:00" FlightNumber="829"
         * DepartureDateTime="2011-06-16T06:55:00"> <ns9:DepartureAirport
         * LocationCode="SVO" /> <ns9:ArrivalAirport LocationCode="LED" />
         * <ns9:OperatingAirline Code="SU" /> <ns9:MarketingAirline Code="SU" />
         * </ns9:FlightSegment> </ns9:OriginDestinationOption>
         * <ns9:OriginDestinationOption> <ns9:FlightSegment NumberInParty="1"
         * ActionCode="NN" ResBookDesigCode="D"
         * ArrivalDateTime="2011-06-23T07:30:00" FlightNumber="864"
         * DepartureDateTime="2011-06-23T06:15:00"> <ns9:DepartureAirport
         * LocationCode="LED" /> <ns9:ArrivalAirport LocationCode="SVO" />
         * <ns9:OperatingAirline Code="SU" /> <ns9:MarketingAirline Code="SU" />
         * </ns9:FlightSegment> </ns9:OriginDestinationOption>
         * </ns9:OriginDestinationOptions> </ns9:AirItinerary>
         * </ns9:OTA_AirBookRQ>
         */
        // requestContent
        System.out.println("\n Servlet::getAirBookLSRQairports: requestSTR="
                + requestSTR);
        String OriginDestinationOption_Start_String = "OriginDestinationOption>";
        String OriginDestinationOption_End_String = "NumberInParty=";
        int StartOriginDestinationOption = 0;
        int EndOriginDestinationOption = 0;
        StartOriginDestinationOption = requestSTR
                .indexOf(OriginDestinationOption_Start_String);
        EndOriginDestinationOption = requestSTR.indexOf(
                OriginDestinationOption_End_String,
                StartOriginDestinationOption);

        int StartDepartureAirport = 0;
        int EndDepartureAirport = 0;
        String Start_DepartureAirport = "DepartureAirport LocationCode=";
        String End__DepartureAirport = "/>";
        StartDepartureAirport = requestSTR.indexOf(Start_DepartureAirport,
                EndOriginDestinationOption);
        EndDepartureAirport = requestSTR.indexOf(End__DepartureAirport,
                StartDepartureAirport);
        String airBookLSRQ_OriginAirport = requestSTR.substring(
                StartDepartureAirport, EndDepartureAirport - 2);
        System.out
                .println("\n Servlet::getAirBookLSRQairports: airBookLSRQ_OriginAirport="
                        + airBookLSRQ_OriginAirport + "<--");

        int Start_ArrivalAirport = 0;
        int End_ArrivalAirport = 0;
        String RPH1_Start_ArrivalAirport = "ArrivalAirport LocationCode=";
        String RPH1_End_ArrivalAirport = "/>";
        Start_ArrivalAirport = requestSTR.indexOf(RPH1_Start_ArrivalAirport,
                EndDepartureAirport);
        End_ArrivalAirport = requestSTR.indexOf(RPH1_End_ArrivalAirport,
                Start_ArrivalAirport);
        String airBookLSRQ_ArrivalAirport = requestSTR.substring(
                Start_ArrivalAirport, End_ArrivalAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: airBookLSRQ_ArrivalAirport="
                        + airBookLSRQ_ArrivalAirport + "<--");

        aRequestFlightData[0] = airBookLSRQ_OriginAirport;
        aRequestFlightData[1] = airBookLSRQ_ArrivalAirport;
        aRequestFlightData[2] = "";

        aRequestFlightData[3] = "";
        aRequestFlightData[4] = "";
        aRequestFlightData[5] = "";

        // //////////////////
        return aRequestFlightData;

    }

    private String getHostCommand(String requestContent)
    {
        // TODO Auto-generated method stub
        /*
         * <ns9:Request
         * Output="SCREEN"><ns9:GenericHostCommand>WPMRUBP1ADT</ns9:GenericHostCommand
         * ></ns9:Request></ns9:SabreCommandLLSRQ></
         */
        int hostCommandTag_startINT = 0;
        int hostCommandTag_endINT = 0;
        String hostCommandTag_startSTR = ":GenericHostCommand>";
        String hostCommandTag_endSTR = "</";
        String hostCommendInRequest = "";
        hostCommandTag_startINT = requestContent
                .indexOf(hostCommandTag_startSTR);
        hostCommandTag_endINT = requestContent.indexOf(hostCommandTag_endSTR,
                hostCommandTag_startINT);
        hostCommendInRequest = requestContent.substring(
                hostCommandTag_startINT + 13, hostCommandTag_endINT);
        System.out.println("\n getHostCommand:: hostCommendInRequest="
                + hostCommendInRequest + "<--");
        return hostCommendInRequest;
    }

    private String getRQstorefront_SessionCreateRQ(String requestContent)
    {
        // TODO Auto-generated method stub
        /*
         * <ns6:From> <ns6:PartyId>99999</ns6:PartyId> </ns6:From> <ns6:To>
         * <ns6:PartyId>123123</ns6:PartyId> </ns6:To> <ns6:CPAId>PG</ns6:CPAId>
         */
        String startSessionStorefront = "";
        String CPAId_Start_String = ":CPAId>";
        String CPAId_End_String = "</";
        int CPAId_Start_INT = 0;
        int CPAId_End_INT = 0;
        CPAId_Start_INT = requestContent.indexOf(CPAId_Start_String);
        CPAId_End_INT = requestContent.indexOf(CPAId_End_String,
                CPAId_Start_INT);
        startSessionStorefront = requestContent.substring(CPAId_Start_INT + 7,
                CPAId_End_INT);
        System.out.println(" getRQstorefront request STOREFRONT="
                + startSessionStorefront + "<--");
        return startSessionStorefront;
    }

    private int checkOWRTMCrequestType(String requestContent)
    {
        // TODO Auto-generated method stub
        char Codzyslow = '"';
        String RPH1Start_String = "RPH=" + Codzyslow + "1" + Codzyslow + ">";
        String RPH2Start_String = "RPH=" + Codzyslow + "2" + Codzyslow + ">";
        String RPH3Start_String = "RPH=" + Codzyslow + "3" + Codzyslow + ">";
        String RPH4Start_String = "RPH=" + Codzyslow + "4" + Codzyslow + ">";
        String RPH5Start_String = "RPH=" + Codzyslow + "5" + Codzyslow + ">";
        String RPH6Start_String = "RPH=" + Codzyslow + "6" + Codzyslow + ">";

        int rphNumbers = 0;
        int StartRPH1 = 0;
        int StartRPH2 = 0;
        int StartRPH3 = 0;
        int StartRPH4 = 0;
        int StartRPH5 = 0;
        int StartRPH6 = 0;

        StartRPH1 = requestContent.indexOf(RPH1Start_String);
        StartRPH2 = requestContent.indexOf(RPH2Start_String);
        StartRPH3 = requestContent.indexOf(RPH3Start_String);
        StartRPH4 = requestContent.indexOf(RPH4Start_String);
        StartRPH5 = requestContent.indexOf(RPH5Start_String);
        StartRPH6 = requestContent.indexOf(RPH6Start_String);

        if (StartRPH6 > 0)
            rphNumbers = 6;
        if (StartRPH5 > 0)
            rphNumbers = 5;
        if (StartRPH4 > 0)
            rphNumbers = 4;
        if (StartRPH3 > 0)
        {
            rphNumbers = 3;
            System.out
                    .println("Servlet:: checkOWRTMCrequestType rphs RPH3!!   MC search  found="
                            + rphNumbers);
        }
        if (StartRPH2 > 0 && StartRPH1 > 0 && StartRPH3 < 0)
            rphNumbers = 2;
        if (StartRPH1 > 0 && StartRPH2 < 0)
            rphNumbers = 1;

        return rphNumbers;
    }

    private String[] calculatePlusMinus3daysDatesOB(String OBdate)
    {
        System.out
                .println("\n calculatePlusMinus3daysDatesOB  OBdate passed to meth:"
                        + OBdate);
        // TODO Auto-generated method stub // =2011-06-15<--
        String[] aPlusMinus3daysDatesOB = new String[6];
        String OB_date_year = OBdate.substring(0, 4);
        String OB_date_month = OBdate.substring(5, 7);
        String OB_date_day = OBdate.substring(8, 10);
        System.out.println("\n calculatePlusMinus3daysDatesOB  OBdate="
                + OB_date_year + OB_date_month + OB_date_day + "<--");

        GregorianCalendar DayOfOBflight = new GregorianCalendar(
                Integer.valueOf(OB_date_year),
                Integer.valueOf(OB_date_month) - 1,
                Integer.valueOf(OB_date_day));

        System.out.println(" calculatePlusMinus3daysDatesOB_calendar=" + DayOfOBflight.getTime());

        GregorianCalendar DayOfOBflight_1 = new GregorianCalendar(
                Integer.valueOf(OB_date_year),
                Integer.valueOf(OB_date_month) - 1,
                Integer.valueOf(OB_date_day));
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(" calculatePlusMinus3daysDatesOB   DayOfOBflight_1="
                + DayOfOBflight_1.getTime());
        int DayOfOBflight_1_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_1_monthSTR = "";
        if (DayOfOBflight_1_monthINT < 10)
            DayOfOBflight_1_monthSTR = "0" + DayOfOBflight_1_monthINT;
        else
            DayOfOBflight_1_monthSTR = "" + DayOfOBflight_1_monthINT;

        int DayOfOBflight_1_dayINT = DayOfOBflight_1.get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_1_daySTR = "";
        if (DayOfOBflight_1_dayINT < 10)
            DayOfOBflight_1_daySTR = "0" + DayOfOBflight_1_dayINT;
        else
            DayOfOBflight_1_daySTR = "" + DayOfOBflight_1_dayINT;

        String rt_DayOfOBflight_1_STR = DayOfOBflight_1.get(Calendar.YEAR) + "-" + DayOfOBflight_1_monthSTR + "-" + DayOfOBflight_1_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_1_STR="
                        + rt_DayOfOBflight_1_STR);
        aPlusMinus3daysDatesOB[2] = rt_DayOfOBflight_1_STR;
        // -2days
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, -1);
        DayOfOBflight_1_dayINT = DayOfOBflight_1.get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_2_daySTR = "";
        if (DayOfOBflight_1_dayINT < 10)
            DayOfOBflight_2_daySTR = "0" + DayOfOBflight_1_dayINT;
        else
            DayOfOBflight_2_daySTR = "" + DayOfOBflight_1_dayINT;

        DayOfOBflight_1_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_2_monthSTR = "";
        if (DayOfOBflight_1_monthINT < 10)
            DayOfOBflight_2_monthSTR = "0" + DayOfOBflight_1_monthINT;
        else
            DayOfOBflight_2_monthSTR = "" + DayOfOBflight_1_monthINT;

        String rt_DayOfOBflight_2_STR = DayOfOBflight_1.get(Calendar.YEAR)
                + "-" + DayOfOBflight_2_monthSTR + "-" + DayOfOBflight_2_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_2_STR="
                        + rt_DayOfOBflight_2_STR);
        aPlusMinus3daysDatesOB[1] = rt_DayOfOBflight_2_STR;
        // -3days
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, -1);
        DayOfOBflight_1_dayINT = DayOfOBflight_1.get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_3_daySTR = "";
        if (DayOfOBflight_1_dayINT < 10)
            DayOfOBflight_3_daySTR = "0" + DayOfOBflight_1_dayINT;
        else
            DayOfOBflight_3_daySTR = "" + DayOfOBflight_1_dayINT;

        DayOfOBflight_1_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_3_monthSTR = "";
        if (DayOfOBflight_1_monthINT < 10)
            DayOfOBflight_3_monthSTR = "0" + DayOfOBflight_1_monthINT;
        else
            DayOfOBflight_3_monthSTR = "" + DayOfOBflight_1_monthINT;

        String rt_DayOfOBflight_3_STR = DayOfOBflight_1.get(Calendar.YEAR)
                + "-" + DayOfOBflight_3_monthSTR + "-" + DayOfOBflight_3_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_3_STR="
                        + rt_DayOfOBflight_3_STR);
        aPlusMinus3daysDatesOB[0] = rt_DayOfOBflight_3_STR;

        // day+1
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1); // -2days
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1);// -1day
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1);// day0
        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1);// day+1

        int DayOfOBflight_plus1_dayINT = DayOfOBflight_1
                .get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_plus1_daySTR = "";
        if (DayOfOBflight_plus1_dayINT < 10)
            DayOfOBflight_plus1_daySTR = "0" + DayOfOBflight_plus1_dayINT;
        else
            DayOfOBflight_plus1_daySTR = "" + DayOfOBflight_plus1_dayINT;

        int DayOfOBflight_plus1_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_plus1_monthSTR = "";
        if (DayOfOBflight_plus1_monthINT < 10)
            DayOfOBflight_plus1_monthSTR = "0" + DayOfOBflight_plus1_monthINT;
        else
            DayOfOBflight_plus1_monthSTR = "" + DayOfOBflight_plus1_monthINT;

        String rt_DayOfOBflight_plus1_STR = DayOfOBflight_1.get(Calendar.YEAR)
                + "-" + DayOfOBflight_plus1_monthSTR + "-"
                + DayOfOBflight_plus1_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_plus1_STR="
                        + rt_DayOfOBflight_plus1_STR);
        aPlusMinus3daysDatesOB[3] = rt_DayOfOBflight_plus1_STR;

        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1);// day+2
        int DayOfOBflight_plus2_dayINT = DayOfOBflight_1
                .get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_plus2_daySTR = "";
        if (DayOfOBflight_plus2_dayINT < 10)
            DayOfOBflight_plus2_daySTR = "0" + DayOfOBflight_plus2_dayINT;
        else
            DayOfOBflight_plus2_daySTR = "" + DayOfOBflight_plus2_dayINT;

        int DayOfOBflight_plus2_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_plus2_monthSTR = "";
        if (DayOfOBflight_plus2_monthINT < 10)
            DayOfOBflight_plus2_monthSTR = "0" + DayOfOBflight_plus2_monthINT;
        else
            DayOfOBflight_plus2_monthSTR = "" + DayOfOBflight_plus2_monthINT;

        String rt_DayOfOBflight_plus2_STR = DayOfOBflight_1.get(Calendar.YEAR)
                + "-" + DayOfOBflight_plus2_monthSTR + "-"
                + DayOfOBflight_plus2_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_plus2_STR="
                        + rt_DayOfOBflight_plus2_STR);
        aPlusMinus3daysDatesOB[4] = rt_DayOfOBflight_plus2_STR;

        DayOfOBflight_1.add(Calendar.DAY_OF_MONTH, 1);// day+3
        int DayOfOBflight_plus3_dayINT = DayOfOBflight_1
                .get(Calendar.DAY_OF_MONTH);
        String DayOfOBflight_plus3_daySTR = "";
        if (DayOfOBflight_plus3_dayINT < 10)
            DayOfOBflight_plus3_daySTR = "0" + DayOfOBflight_plus3_dayINT;
        else
            DayOfOBflight_plus3_daySTR = "" + DayOfOBflight_plus3_dayINT;

        int DayOfOBflight_plus3_monthINT = DayOfOBflight_1.get(Calendar.MONTH) + 1;
        String DayOfOBflight_plus3_monthSTR = "";
        if (DayOfOBflight_plus3_monthINT < 10)
            DayOfOBflight_plus3_monthSTR = "0" + DayOfOBflight_plus3_monthINT;
        else
            DayOfOBflight_plus3_monthSTR = "" + DayOfOBflight_plus3_monthINT;

        String rt_DayOfOBflight_plus3_STR = DayOfOBflight_1.get(Calendar.YEAR)
                + "-" + DayOfOBflight_plus3_monthSTR + "-"
                + DayOfOBflight_plus3_daySTR;
        System.out
                .println(" calculatePlusMinus3daysDatesOB   rt_DayOfOBflight_plus3_STR="
                        + rt_DayOfOBflight_plus3_STR);
        aPlusMinus3daysDatesOB[5] = rt_DayOfOBflight_plus3_STR;

        return aPlusMinus3daysDatesOB;
    }

    private String[] getRequestFlightData(String requestSTR, int rphNumber)
    {
        // TODO Auto-generated method stub
        String[] aRequestFlightData = new String[6];
        // //////////////////
        System.out.println("\n getRequestFlightData start ");

        char Codzyslow = '"';

        // RPH="1">
        /*
         * RPH="1">
         * <ns4:DepartureDateTime>2011-05-12T00:00:00</ns4:DepartureDateTime>
         * <ns4:OriginLocation LocationCode="ABC" /> <ns4:DestinationLocation
         * LocationCode="EWQ" /> <ns4:TPA_Extensions>
         */
        // requestContent
        System.out.println("\n Servlet::getRequestFlightData: requestSTR="
                + requestSTR);
        String RPH1Start_String = "RPH=" + Codzyslow + "1" + Codzyslow + ">";
        String RPH1End_String = ">";
        int StartRPH1 = 0;
        int EndRPH1 = 0;
        StartRPH1 = requestSTR.indexOf(RPH1Start_String);
        EndRPH1 = requestSTR.indexOf(RPH1End_String, StartRPH1);

        int StartRPH1_OriginAirport = 0;
        int EndRPH1_OriginAirport = 0;
        String RPH1_Start_OriginAirport = ":OriginLocation LocationCode=";
        String RPH1_End_OriginAirport = "/>";
        StartRPH1_OriginAirport = requestSTR.indexOf(RPH1_Start_OriginAirport,
                EndRPH1);
        EndRPH1_OriginAirport = requestSTR.indexOf(RPH1_End_OriginAirport,
                StartRPH1_OriginAirport);
        String RPH1_OriginAirport = requestSTR.substring(
                StartRPH1_OriginAirport + 30, EndRPH1_OriginAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: RPH1_OriginAirport="
                        + RPH1_OriginAirport + "<--");

        int StartRPH1_DestinationAirport = 0;
        int EndRPH1_DestinationAirport = 0;
        String RPH1_Start_DestinationAirport = ":DestinationLocation LocationCode=";
        String RPH1_End_DestinationAirport = "/>";
        StartRPH1_DestinationAirport = requestSTR.indexOf(
                RPH1_Start_DestinationAirport, EndRPH1_OriginAirport);
        EndRPH1_DestinationAirport = requestSTR.indexOf(
                RPH1_End_DestinationAirport, StartRPH1_DestinationAirport);
        String RPH1_DestinationAirport = requestSTR.substring(
                StartRPH1_DestinationAirport + 35,
                EndRPH1_DestinationAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: RPH1_DestinationAirport="
                        + RPH1_DestinationAirport + "<--");

        String DepartureDateTime_Start_String = ":DepartureDateTime>";
        String DepartureDateTime_End_String = "00</";
        int Start_DepartureDateTime = 0;
        int End_DepartureDateTime = 0;
        Start_DepartureDateTime = requestSTR.indexOf(
                DepartureDateTime_Start_String, EndRPH1);
        End_DepartureDateTime = requestSTR.indexOf(
                DepartureDateTime_End_String, Start_DepartureDateTime);
        String RPH1_DepartureDate = requestSTR.substring(
                Start_DepartureDateTime + 19, End_DepartureDateTime - 7);
        System.out.println("\n Servlet::getRequestFlightData: RPH1_DepartureDate=" + RPH1_DepartureDate);

        /*
         * RPH="2">
         * <ns4:DepartureDateTime>2010-05-31T00:00:00</ns4:DepartureDateTime>
         * <ns4:OriginLocation LocationCode="EWQ" /> <ns4:DestinationLocation
         * LocationCode="ABC" /> <ns4:TPA_Extensions>
         */

        String RPH2Start_String = "RPH=" + Codzyslow + "2" + Codzyslow;
        String RPH2End_String = ">";
        int StartRPH2 = 0;
        int EndRPH2 = 0;
        int StartRPH2_OriginAirport = 0;
        int EndRPH2_OriginAirport = 0;
        String RPH2_Start_OriginAirport = ":OriginLocation LocationCode=";
        String RPH2_End_OriginAirport = "/>";
        int StartRPH2_DestinationAirport = 0;
        int EndRPH2_DestinationAirport = 0;
        String RPH2_Start_DestinationAirport = ":DestinationLocation LocationCode=";
        String RPH2_End_DestinationAirport = "/>";
        String RPH2_DepartureDateTime_Start_String = ":DepartureDateTime>";
        String RPH2_DepartureDateTime_End_String = "00</";
        int RPH2_Start_DepartureDateTime = 0;
        int RPH2_End_DepartureDateTime = 0;
        String RPH2_OriginAirport = "";
        String RPH2_DestinationAirport = "";
        String RPH2_DepartureDate = "";

        if (rphNumber > 1)
        {
            System.out
                    .println("\n getRequestFlightData::   it is RT request   rphNumber="
                            + rphNumber);
            StartRPH2 = requestSTR.indexOf(RPH2Start_String);
            EndRPH2 = requestSTR.indexOf(RPH2End_String, StartRPH2);

            StartRPH2_OriginAirport = requestSTR.indexOf(
                    RPH2_Start_OriginAirport, EndRPH2);
            EndRPH2_OriginAirport = requestSTR.indexOf(RPH1_End_OriginAirport,
                    StartRPH2_OriginAirport);
            RPH2_OriginAirport = requestSTR.substring(
                    StartRPH2_OriginAirport + 30, EndRPH2_OriginAirport - 2);
            System.out
                    .println("\n Servlet::getRequestFlightData: RPH2_OriginAirport="
                            + RPH2_OriginAirport + "<--");

            StartRPH2_DestinationAirport = requestSTR.indexOf(
                    RPH2_Start_DestinationAirport, EndRPH2_OriginAirport);
            EndRPH2_DestinationAirport = requestSTR.indexOf(
                    RPH2_End_DestinationAirport, StartRPH2_DestinationAirport);
            RPH2_DestinationAirport = requestSTR.substring(
                    StartRPH2_DestinationAirport + 35,
                    EndRPH2_DestinationAirport - 2);
            System.out
                    .println("\n Servlet::getRequestFlightData: RPH2_DestinationAirport="
                            + RPH2_DestinationAirport + "<--");

            RPH2_Start_DepartureDateTime = requestSTR.indexOf(
                    RPH2_DepartureDateTime_Start_String, EndRPH2);
            RPH2_End_DepartureDateTime = requestSTR.indexOf(
                    RPH2_DepartureDateTime_End_String,
                    RPH2_Start_DepartureDateTime);
            RPH2_DepartureDate = requestSTR.substring(
                    RPH2_Start_DepartureDateTime + 19,
                    RPH2_End_DepartureDateTime - 7);
            System.out
                    .println("\n Servlet::getRequestFlightData: RPH2_DepartureDate="
                            + RPH2_DepartureDate);
        }// end if rphNumber

        aRequestFlightData[0] = RPH1_DepartureDate;
        aRequestFlightData[1] = RPH1_OriginAirport;
        aRequestFlightData[2] = RPH1_DestinationAirport;

        aRequestFlightData[3] = RPH2_DepartureDate;
        aRequestFlightData[4] = RPH2_OriginAirport;
        aRequestFlightData[5] = RPH2_DestinationAirport;

        // //////////////////
        return aRequestFlightData;
    }

    // MC RPH3
    private String[] getRequestFlightDataMCrph3(String requestSTR, int rphNumber)
    {
        // TODO Auto-generated method stub
        String[] aRequestFlightData = new String[3];
        // //////////////////
        System.out.println("\n getRequestFlightData start ");

        char Codzyslow = '"';

        // RPH="1">
        /*
         * RPH="1">
         * <ns4:DepartureDateTime>2011-05-12T00:00:00</ns4:DepartureDateTime>
         * <ns4:OriginLocation LocationCode="ABC" /> <ns4:DestinationLocation
         * LocationCode="EWQ" /> <ns4:TPA_Extensions>
         */
        /*
         * <ns4:OriginDestinationInformation RPH="3">
         * <ns4:DepartureDateTime>2011-07-03T00:00:00</ns4:DepartureDateTime>
         * <ns4:OriginLocation LocationCode="LED" /> <ns4:DestinationLocation
         * LocationCode="MOW" /> <ns4:TPA_Extensions> <ns4:SegmentType Code="O"
         * /> </ns4:TPA_Extensions> </ns4:OriginDestinationInformation>
         */

        // requestContent
        System.out
                .println("\n Servlet::getRequestFlightData MC 3rph: requestSTR="
                        + requestSTR);
        String RPH3Start_String = "RPH=" + Codzyslow + "3" + Codzyslow + ">";
        String RPH3End_String = ">";
        int StartRPH3 = 0;
        int EndRPH3 = 0;
        StartRPH3 = requestSTR.indexOf(RPH3Start_String);
        EndRPH3 = requestSTR.indexOf(RPH3End_String, StartRPH3);

        int StartRPH3_OriginAirport = 0;
        int EndRPH3_OriginAirport = 0;
        String RPH3_Start_OriginAirport = ":OriginLocation LocationCode=";
        String RPH3_End_OriginAirport = "/>";
        StartRPH3_OriginAirport = requestSTR.indexOf(RPH3_Start_OriginAirport,
                EndRPH3);
        EndRPH3_OriginAirport = requestSTR.indexOf(RPH3_End_OriginAirport,
                StartRPH3_OriginAirport);
        String RPH3_OriginAirport = requestSTR.substring(
                StartRPH3_OriginAirport + 30, EndRPH3_OriginAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: RPH3_OriginAirport="
                        + RPH3_OriginAirport + "<--");

        int StartRPH3_DestinationAirport = 0;
        int EndRPH3_DestinationAirport = 0;
        String RPH3_Start_DestinationAirport = ":DestinationLocation LocationCode=";
        String RPH3_End_DestinationAirport = "/>";
        StartRPH3_DestinationAirport = requestSTR.indexOf(
                RPH3_Start_DestinationAirport, EndRPH3_OriginAirport);
        EndRPH3_DestinationAirport = requestSTR.indexOf(
                RPH3_End_DestinationAirport, StartRPH3_DestinationAirport);
        String RPH3_DestinationAirport = requestSTR.substring(
                StartRPH3_DestinationAirport + 35,
                EndRPH3_DestinationAirport - 2);
        System.out
                .println("\n Servlet::getRequestFlightData: RPH3_DestinationAirport="
                        + RPH3_DestinationAirport + "<--");

        String DepartureDateTime_Start_String = ":DepartureDateTime>";
        String DepartureDateTime_End_String = "00</";
        int Start_DepartureDateTime = 0;
        int End_DepartureDateTime = 0;
        Start_DepartureDateTime = requestSTR.indexOf(
                DepartureDateTime_Start_String, EndRPH3);
        End_DepartureDateTime = requestSTR.indexOf(
                DepartureDateTime_End_String, Start_DepartureDateTime);
        String RPH3_DepartureDate = requestSTR.substring(
                Start_DepartureDateTime + 19, End_DepartureDateTime - 7);
        System.out
                .println("\n Servlet::getRequestFlightData: RPH3_DepartureDate="
                        + RPH3_DepartureDate);

        aRequestFlightData[0] = RPH3_DepartureDate;
        aRequestFlightData[1] = RPH3_OriginAirport;
        aRequestFlightData[2] = RPH3_DestinationAirport;

        // //////////////////
        return aRequestFlightData;
    }

    private String replaceDatesInOWresponse(String mockResponse,
            String[] requestFlightData)
    {
        // TODO Auto-generated method stub
        /*
         * 1.06.2011 <OneWayItineraries> <BrandedOneWayItineraries RPH="1">
         * <PricedItinerary SequenceNumber="1" CampaignID="65840"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-13T08:00:00"
         * ArrivalDateTime="2011-06-13T09:10:00" StopQuantity="0"
         * FlightNumber="903" ResBookDesigCode="N" ElapsedTime="70">
         */

        String mockResponseWithDatesReplacd = "";
        char Codzyslow = '"';
        String OneWayItineraries_String = "<OneWayItineraries>";
        String RPH1Start_String = "RPH=" + Codzyslow + "1" + Codzyslow + ">";
        String PricedItinerartSequence_String = "<PricedItinerary SequenceNumber=";
        String FlightSegmentDepartureDate_String = "<FlightSegment DepartureDateTime";

        int OneWayItinerariesStart_INT = 0;
        int RPH1StartStart_INT = 0;
        int PricedItinerartSequenceStart_INT = 0;
        int FlightSegmentDepartureDateStart_INT = 0;

        OneWayItinerariesStart_INT = mockResponse
                .indexOf(OneWayItineraries_String);
        if (OneWayItinerariesStart_INT > 0)
            RPH1StartStart_INT = mockResponse.indexOf(RPH1Start_String);
        if (RPH1StartStart_INT > 0)
            PricedItinerartSequenceStart_INT = mockResponse
                    .indexOf(PricedItinerartSequence_String);
        if (PricedItinerartSequenceStart_INT > 0)
            FlightSegmentDepartureDateStart_INT = mockResponse
                    .indexOf(FlightSegmentDepartureDate_String);

        String DepartureDateTimeRPH1 = "";
        if (PricedItinerartSequenceStart_INT > 0)
        {
            DepartureDateTimeRPH1 = mockResponse.substring(
                    FlightSegmentDepartureDateStart_INT + 34,
                    FlightSegmentDepartureDateStart_INT + 34 + 10);
        }
        System.out
                .println("\n replaceDatesInOWresponse::DepartureDateTimeRPH1="
                        + DepartureDateTimeRPH1);

        String[] aPlusMinus3daysDatesforResponseOW = new String[6];
        aPlusMinus3daysDatesforResponseOW = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH1);
        // request2=request2.replaceAll("StartAirport", StartAirport);
        // requestFlightData[0] - departureDate provided to WS -make all dates
        // reffere to this one as responseTargetDay0

        // request2=request2.replaceAll("StartAirport", StartAirport);
        mockResponseWithDatesReplacd = mockResponse.replaceAll(
                DepartureDateTimeRPH1, requestFlightData[0]);
        /*
         * calculatePlusMinus3daysDatesOB OBdate=20110613<--
         * DepartureDateTimeRPH1 calculatePlusMinus3daysDatesOB_calendar=Mon Jun
         * 13 00:00:00 PDT 2011 calculatePlusMinus3daysDatesOB
         * DayOfOBflight_1=Sun Jun 12 00:00:00 PDT 2011
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_1_STR=2011-06-12
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_2_STR=2011-06-11
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_3_STR=2011-06-10
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus1_STR=2011-06-14
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus2_STR=2011-06-15
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus3_STR=2011-06-16
         */

        String[] aPlusMinus3daysDatesfromRequestOW = new String[6];
        aPlusMinus3daysDatesfromRequestOW = calculatePlusMinus3daysDatesOB(requestFlightData[0]);// departure
                                                                                                 // date
                                                                                                 // provided
                                                                                                 // to
                                                                                                 // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[0],
                aPlusMinus3daysDatesfromRequestOW[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[1],
                aPlusMinus3daysDatesfromRequestOW[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[2],
                aPlusMinus3daysDatesfromRequestOW[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[3],
                aPlusMinus3daysDatesfromRequestOW[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[4],
                aPlusMinus3daysDatesfromRequestOW[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[5],
                aPlusMinus3daysDatesfromRequestOW[5]);

        return mockResponseWithDatesReplacd;
    }// end replaceDatesInOWresponse

    private String replaceDatesInRTresponse(String mockResponse,
            String[] requestFlightData)
    {
        // TODO Auto-generated method stub
        /*
         * 1.06.2011 <OneWayItineraries> <BrandedOneWayItineraries RPH="1">
         * <PricedItinerary SequenceNumber="1" CampaignID="65840"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-13T08:00:00"
         * ArrivalDateTime="2011-06-13T09:10:00" StopQuantity="0"
         * FlightNumber="903" ResBookDesigCode="N" ElapsedTime="70">
         */

        String mockResponseWithDatesReplacd = "";
        char Codzyslow = '"';
        String OneWayItineraries_String = "<OneWayItineraries>";
        String RPH1Start_String = "RPH=" + Codzyslow + "1" + Codzyslow + ">";
        String PricedItinerartSequence_String = "<PricedItinerary SequenceNumber=";
        String FlightSegmentDepartureDate_String = "<FlightSegment DepartureDateTime";

        int OneWayItinerariesStart_INT = 0;
        int RPH1StartStart_INT = 0;
        int PricedItinerartSequenceStart_INT = 0;
        int FlightSegmentDepartureDateStart_INT = 0;

        OneWayItinerariesStart_INT = mockResponse
                .indexOf(OneWayItineraries_String);
        if (OneWayItinerariesStart_INT > 0)
            RPH1StartStart_INT = mockResponse.indexOf(RPH1Start_String);
        if (RPH1StartStart_INT > 0)
            PricedItinerartSequenceStart_INT = mockResponse
                    .indexOf(PricedItinerartSequence_String);
        if (PricedItinerartSequenceStart_INT > 0)
            FlightSegmentDepartureDateStart_INT = mockResponse
                    .indexOf(FlightSegmentDepartureDate_String);

        String DepartureDateTimeRPH1 = "";
        if (PricedItinerartSequenceStart_INT > 0)
        {
            DepartureDateTimeRPH1 = mockResponse.substring(
                    FlightSegmentDepartureDateStart_INT + 34,
                    FlightSegmentDepartureDateStart_INT + 34 + 10);
        }
        System.out
                .println("\n replaceDatesInRTresponse::DepartureDateTimeRPH1="
                        + DepartureDateTimeRPH1);

        String[] aPlusMinus3daysDatesforResponseOW = new String[6];
        aPlusMinus3daysDatesforResponseOW = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH1);
        // requestFlightData[0] - departureDate provided to WS -make all dates
        // reffere to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponse.replaceAll(
                DepartureDateTimeRPH1, requestFlightData[0]);
        /*
         * calculatePlusMinus3daysDatesOB OBdate=20110613<--
         * DepartureDateTimeRPH1 calculatePlusMinus3daysDatesOB_calendar=Mon Jun
         * 13 00:00:00 PDT 2011 calculatePlusMinus3daysDatesOB
         * DayOfOBflight_1=Sun Jun 12 00:00:00 PDT 2011
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_1_STR=2011-06-12
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_2_STR=2011-06-11
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_3_STR=2011-06-10
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus1_STR=2011-06-14
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus2_STR=2011-06-15
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus3_STR=2011-06-16
         */

        String[] aPlusMinus3daysDatesfromRequestOW = new String[6];
        aPlusMinus3daysDatesfromRequestOW = calculatePlusMinus3daysDatesOB(requestFlightData[0]);// departure
                                                                                                 // date
                                                                                                 // provided
                                                                                                 // to
                                                                                                 // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[0],
                aPlusMinus3daysDatesfromRequestOW[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[1],
                aPlusMinus3daysDatesfromRequestOW[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[2],
                aPlusMinus3daysDatesfromRequestOW[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[3],
                aPlusMinus3daysDatesfromRequestOW[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[4],
                aPlusMinus3daysDatesfromRequestOW[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseOW[5],
                aPlusMinus3daysDatesfromRequestOW[5]);

        /*
         * RT RPH2 <BrandedOneWayItineraries RPH="2"> <PricedItinerary
         * SequenceNumber="1" CampaignID="66124"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-24T09:45:00"
         * ArrivalDateTime="2011-06-24T11:10:00" StopQuantity="0"
         * FlightNumber="924" ResBookDesigCode="X" ElapsedTime="85">
         * <DepartureAirport LocationCode="REP" /> <ArrivalAirport
         * LocationCode="BKK" /> <OperatingAirline Code="PG" FlightNumber="924"
         * /> <Equipment AirEquipType="AT7" /> <MarketingAirline Code="PG" />
         */
        String RPH2Start_String = "RPH=" + Codzyslow + "2" + Codzyslow + ">";
        String PricedItinerartSequenceRPH2_String = "<PricedItinerary SequenceNumber=";
        String FlightSegmentDepartureDateRPH2_String = "<FlightSegment DepartureDateTime";

        int RPH2StartStart_INT = 0;
        int PricedItinerartSequenceStartRPH2_INT = 0;
        int FlightSegmentDepartureDateStartRPH2_INT = 0;

        RPH2StartStart_INT = mockResponse.indexOf(RPH2Start_String,
                FlightSegmentDepartureDateStart_INT);
        if (RPH2StartStart_INT > 0)
            PricedItinerartSequenceStartRPH2_INT = mockResponse.indexOf(
                    PricedItinerartSequenceRPH2_String, RPH2StartStart_INT);
        if (PricedItinerartSequenceStartRPH2_INT > 0)
            FlightSegmentDepartureDateStartRPH2_INT = mockResponse.indexOf(
                    FlightSegmentDepartureDateRPH2_String,
                    PricedItinerartSequenceStartRPH2_INT);

        String DepartureDateTimeRPH2 = "";
        if (PricedItinerartSequenceStartRPH2_INT > 0)
        {
            DepartureDateTimeRPH2 = mockResponse.substring(
                    FlightSegmentDepartureDateStartRPH2_INT + 34,
                    FlightSegmentDepartureDateStartRPH2_INT + 34 + 10);
        }
        System.out
                .println("\n replaceDatesInRTresponse::DepartureDateTimeRPH2="
                        + DepartureDateTimeRPH2);

        String[] aPlusMinus3daysDatesforResponseRT_RPH2 = new String[6];
        aPlusMinus3daysDatesforResponseRT_RPH2 = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH2);
        // requestFlightData[3] - return Date provided to WS -make all dates
        // reference to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                DepartureDateTimeRPH2, requestFlightData[3]);

        String[] aPlusMinus3daysDatesfromRequestRT_RPH2 = new String[6];
        aPlusMinus3daysDatesfromRequestRT_RPH2 = calculatePlusMinus3daysDatesOB(requestFlightData[3]);// departure
                                                                                                      // date
                                                                                                      // provided
                                                                                                      // to
                                                                                                      // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[0],
                aPlusMinus3daysDatesfromRequestRT_RPH2[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[1],
                aPlusMinus3daysDatesfromRequestRT_RPH2[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[2],
                aPlusMinus3daysDatesfromRequestRT_RPH2[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[3],
                aPlusMinus3daysDatesfromRequestRT_RPH2[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[4],
                aPlusMinus3daysDatesfromRequestRT_RPH2[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(
                aPlusMinus3daysDatesforResponseRT_RPH2[5],
                aPlusMinus3daysDatesfromRequestRT_RPH2[5]);

        // RT RPH2 end

        return mockResponseWithDatesReplacd;
    }// end replaceDatesInRTresponse

    // RT LFS CALDATES
    // replaceDatesInRTresponseLFS
    private String replaceDatesInRTresponseLFS(String mockResponse, String[] requestFlightData)
    {

        System.out.println("\n BEFORE REPLACE replaceDatesInRTresponseLFS:: replaceDAtaInRTresponseLFS_CALDATES::=" + mockResponse);
        // TODO Auto-generated method stub
        /*
         * </Warnings> <OneWayItineraries> <SimpleOneWayItineraries RPH="1">
         * <TPA_Extensions> <ProcessingMessage PricingSource="CSH1"
         * Message="NO ITINS" DepartureDate="2011-07-06" /> <ProcessingMessage
         * PricingSource="CSH1" Message="NO ITINS" DepartureDate="2011-07-07" />
         * <ProcessingMessage PricingSource="CSH1" Message="NO SCHEDULES"
         * DepartureDate="2011-07-08" /> </TPA_Extensions> <PricedItinerary
         * SequenceNumber="1"> <AirItinerary DirectionInd="OneWay">
         * <OriginDestinationOptions> <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-07-09T08:00:00"
         * ArrivalDateTime="2011-07-09T09:10:00" StopQuantity="0"
         * FlightNumber="903" ResBookDesigCode="H" ElapsedTime="70">
         * <DepartureAirport LocationCode="BKK" /> <ArrivalAirport
         * LocationCode="REP" /> <OperatingAirline Code="PG" FlightNumber="903"
         * /> <Equipment AirEquipType="AT7" /> <MarketingAirline Code="PG" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="7" />
         * <ArrivalTimeZone GMTOffset="7" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> </OriginDestinationOptions>
         * </AirItinerary> <AirItineraryPricingInfo PricingSource="CSH1"
         * LastTicketDate="2011-07-08"> <ItinTotalFare> <BaseFare Amount="3500"
         * CurrencyCode="THB" DecimalPlaces="0" /> <EquivFare Amount="3500"
         * CurrencyCode="THB" DecimalPlaces="0" /> <Taxes> <Tax
         * TaxCode="TOTALTAX" Amount="1625" CurrencyCode="THB" DecimalPlaces="0"
         * /> </Taxes> <TotalFare Amount="5125" CurrencyCode="THB"
         * DecimalPlaces="0" /> <TotalMileage Amount="0" />
         */
        // <SimpleOneWayItineraries RPH="1">
        char codzyslow = '"';
        String preSearchLFS_RPH1 = "SimpleOneWayItineraries RPH=" + codzyslow + "1";
        int preSearchLFS_RPH1_INT = 0;
        preSearchLFS_RPH1_INT = mockResponse.indexOf(preSearchLFS_RPH1);
        // mockResponse.indexOf(string, int)
        if (preSearchLFS_RPH1_INT < 0)
            preSearchLFS_RPH1_INT = 0;

        String mockResponseWithDatesReplacd = "";
        char Codzyslow = '"';

        String PricedItinerartSequence_String = "<PricedItinerary SequenceNumber=";
        String FlightSegmentDepartureDate_String = "<FlightSegment DepartureDateTime";

        int PricedItinerartSequenceStart_INT = 0;
        int FlightSegmentDepartureDateStart_INT = 0;

        PricedItinerartSequenceStart_INT = mockResponse.indexOf(PricedItinerartSequence_String, preSearchLFS_RPH1_INT);
        if (PricedItinerartSequenceStart_INT > 0)
            FlightSegmentDepartureDateStart_INT = mockResponse.indexOf(FlightSegmentDepartureDate_String, PricedItinerartSequenceStart_INT);

        String DepartureDateTimeRPH1 = "";
        if (PricedItinerartSequenceStart_INT > 0)
        {
            DepartureDateTimeRPH1 = mockResponse.substring(FlightSegmentDepartureDateStart_INT + 34, FlightSegmentDepartureDateStart_INT + 34 + 10);
        }
        System.out.println("\n replaceDatesInRTresponse:: LFS DepartureDateTimeRPH1=" + DepartureDateTimeRPH1);

        String[] aPlusMinus3daysDatesforResponseOW = new String[6];
        aPlusMinus3daysDatesforResponseOW = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH1);
        // requestFlightData[0] - departureDate provided to WS -make all dates
        // reffere to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponse.replaceAll(DepartureDateTimeRPH1, requestFlightData[0]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file DepartureDateTimeRPH2=" + DepartureDateTimeRPH1 + "<-- will be replaced by " + requestFlightData[0] + "<--");

        /*
         * calculatePlusMinus3daysDatesOB OBdate=20110613<--
         * DepartureDateTimeRPH1 calculatePlusMinus3daysDatesOB_calendar=Mon Jun
         * 13 00:00:00 PDT 2011 calculatePlusMinus3daysDatesOB
         * DayOfOBflight_1=Sun Jun 12 00:00:00 PDT 2011
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_1_STR=2011-06-12
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_2_STR=2011-06-11
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_3_STR=2011-06-10
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus1_STR=2011-06-14
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus2_STR=2011-06-15
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus3_STR=2011-06-16
         */

        String[] aPlusMinus3daysDatesfromRequestOW = new String[6];
        aPlusMinus3daysDatesfromRequestOW = calculatePlusMinus3daysDatesOB(requestFlightData[0]);// departure
                                                                                                 // date
                                                                                                 // provided
                                                                                                 // to
                                                                                                 // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[0], aPlusMinus3daysDatesfromRequestOW[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[1], aPlusMinus3daysDatesfromRequestOW[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[2], aPlusMinus3daysDatesfromRequestOW[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[3], aPlusMinus3daysDatesfromRequestOW[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[4], aPlusMinus3daysDatesfromRequestOW[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[5], aPlusMinus3daysDatesfromRequestOW[5]);

        /*
         * RT RPH2
         * 
         * </SimpleOneWayItineraries> <SimpleOneWayItineraries RPH="2">
         * <PricedItinerary SequenceNumber="1"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-07-16T09:45:00"
         * ArrivalDateTime="2011-07-16T11:10:00" StopQuantity="0"
         * FlightNumber="924" ResBookDesigCode="H" ElapsedTime="85">
         * <DepartureAirport LocationCode="REP" /> <ArrivalAirport
         * LocationCode="BKK" /> <OperatingAirline Code="PG" FlightNumber="924"
         * /> <Equipment AirEquipType="AT7" /> <MarketingAirline Code="PG" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="7" />
         * <ArrivalTimeZone GMTOffset="7" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> </OriginDestinationOptions>
         * </AirItinerary> <AirItineraryPricingInfo PricingSource="CSH1"
         * LastTicketDate="2011-07-08"> <ItinTotalFare> <BaseFare Amount="3500"
         * CurrencyCode="THB" DecimalPlaces="0" />
         */

        // RPH pre processing

        String preSearchLFS_RPH2 = "SimpleOneWayItineraries RPH=" + codzyslow + "2";
        int preSearchLFS_RPH2_INT = 0;
        preSearchLFS_RPH2_INT = mockResponse.indexOf(preSearchLFS_RPH2);
        // mockResponse.indexOf(string, int)
        if (preSearchLFS_RPH2_INT < 0)
            preSearchLFS_RPH2_INT = 0;

        // <PricedItinerary SequenceNumber="1">
        String PricedItinerary_SequenceNumber1 = "<PricedItinerary SequenceNumber=" + codzyslow + "1";
        int PricedItinerary_SequenceNumber1INT = mockResponse.indexOf(PricedItinerary_SequenceNumber1, preSearchLFS_RPH2_INT);

        String PricedItinerartSequenceRPH2_String = "</OriginDestinationOption>";
        String FlightSegmentDepartureDateRPH2_String = "<FlightSegment DepartureDateTime";

        int PricedItinerartSequenceStartRPH2_INT = 0;
        int FlightSegmentDepartureDateStartRPH2_INT = 0;

        // PricedItinerartSequenceStartRPH2_INT=mockResponse.indexOf(PricedItinerartSequenceRPH2_String,FlightSegmentDepartureDateStart_INT);
        // PricedItinerartSequenceStartRPH2_INT=mockResponse.indexOf(PricedItinerartSequenceRPH2_String,preSearchLFS_RPH2_INT);
        PricedItinerartSequenceStartRPH2_INT = mockResponse.indexOf(PricedItinerartSequenceRPH2_String, PricedItinerary_SequenceNumber1INT);
        if (PricedItinerartSequenceStartRPH2_INT > 0)
            FlightSegmentDepartureDateStartRPH2_INT = mockResponse.indexOf(FlightSegmentDepartureDateRPH2_String, PricedItinerartSequenceStartRPH2_INT);

        String DepartureDateTimeRPH2 = "";
        if (PricedItinerartSequenceStartRPH2_INT > 0)
        {
            DepartureDateTimeRPH2 = mockResponse.substring(FlightSegmentDepartureDateStartRPH2_INT + 34, FlightSegmentDepartureDateStartRPH2_INT + 34 + 10);
        }
        System.out.println("\n replaceDatesInRTresponse:: LFS DepartureDateTimeRPH2=" + DepartureDateTimeRPH2);

        // Servlet:: RequestFlightData[3]=2011-07-31<--

        String[] aPlusMinus3daysDatesforResponseRT_RPH2 = new String[6];
        aPlusMinus3daysDatesforResponseRT_RPH2 = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH2);
        // requestFlightData[3] - return Date provided to WS -make all dates
        // reference to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(DepartureDateTimeRPH2, requestFlightData[3]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file DepartureDateTimeRPH2=" + DepartureDateTimeRPH2 + "<-- will be replaced by " + requestFlightData[3] + "<--");

        String[] aPlusMinus3daysDatesfromRequestRT_RPH2 = new String[6];
        aPlusMinus3daysDatesfromRequestRT_RPH2 = calculatePlusMinus3daysDatesOB(requestFlightData[3]);// departure
                                                                                                      // date
                                                                                                      // provided
                                                                                                      // to
                                                                                                      // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[0], aPlusMinus3daysDatesfromRequestRT_RPH2[0]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[0]=" + aPlusMinus3daysDatesforResponseRT_RPH2[0] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[0] + "<--");
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[1], aPlusMinus3daysDatesfromRequestRT_RPH2[1]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[1]=" + aPlusMinus3daysDatesforResponseRT_RPH2[1] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[1] + "<--");
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[2], aPlusMinus3daysDatesfromRequestRT_RPH2[2]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[2]=" + aPlusMinus3daysDatesforResponseRT_RPH2[2] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[2] + "<--");
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[3], aPlusMinus3daysDatesfromRequestRT_RPH2[3]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[3]=" + aPlusMinus3daysDatesforResponseRT_RPH2[3] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[3] + "<--");
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[4], aPlusMinus3daysDatesfromRequestRT_RPH2[4]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[4]=" + aPlusMinus3daysDatesforResponseRT_RPH2[4] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[4] + "<--");
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[5], aPlusMinus3daysDatesfromRequestRT_RPH2[5]);
        System.out.println("\n replaceDatesInRTresponse:: LFS file aPlusMinus3daysDatesforResponseRT_RPH2[5]=" + aPlusMinus3daysDatesforResponseRT_RPH2[5] + "<-- will be replaced by "
                + aPlusMinus3daysDatesfromRequestRT_RPH2[5] + "<--");

        // RT RPH2 MC LFS end
        return mockResponseWithDatesReplacd;
    }

    // end RT LFS CALDATES

    // ///////mc and LFS///////
    private String replaceDatesInMCresponseLFS(String mockResponse, String[] requestFlightData, String[] RequestFlightDataRPH3)
    {
        // TODO Auto-generated method stub
        /*
         * <PricedItinerary SequenceNumber="19"> <AirItinerary
         * DirectionInd="Other"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-20T00:10:00"
         * ArrivalDateTime="2011-06-20T01:40:00" StopQuantity="0"
         * FlightNumber="1401" ResBookDesigCode="T" ElapsedTime="90">
         * <DepartureAirport LocationCode="SVO" /> <ArrivalAirport
         * LocationCode="LED" /> <OperatingAirline Code="FV" FlightNumber="186"
         * /> <Equipment AirEquipType="A81" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="false" /> </TPA_Extensions> </FlightSegment> <FlightSegment
         * DepartureDateTime="2011-06-20T07:00:00"
         * ArrivalDateTime="2011-06-20T08:35:00" StopQuantity="0"
         * FlightNumber="1435" ResBookDesigCode="T" ElapsedTime="95">
         * <DepartureAirport LocationCode="LED" /> <ArrivalAirport
         * LocationCode="ARH" /> <OperatingAirline Code="FV" FlightNumber="301"
         * /> <Equipment AirEquipType="A81" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>I</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-29T07:30:00"
         * ArrivalDateTime="2011-06-29T09:00:00" StopQuantity="0"
         * FlightNumber="1672" ResBookDesigCode="T" ElapsedTime="90">
         * <DepartureAirport LocationCode="ARH" /> <ArrivalAirport
         * LocationCode="LED" /> <OperatingAirline Code="5N" FlightNumber="145"
         * /> <Equipment AirEquipType="735" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-07-03T06:15:00"
         * ArrivalDateTime="2011-07-03T07:30:00" StopQuantity="0"
         * FlightNumber="864" ResBookDesigCode="T" ElapsedTime="75">
         * <DepartureAirport LocationCode="LED" /> <ArrivalAirport
         * LocationCode="SVO" /> <OperatingAirline Code="SU" FlightNumber="864"
         * /> <Equipment AirEquipType="319" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> </OriginDestinationOptions>
         * </AirItinerary>
         */
        // //////old code ///////////////
        /*
         * 1.06.2011 <OneWayItineraries> <BrandedOneWayItineraries RPH="1">
         * <PricedItinerary SequenceNumber="1" CampaignID="65840"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-13T08:00:00"
         * ArrivalDateTime="2011-06-13T09:10:00" StopQuantity="0"
         * FlightNumber="903" ResBookDesigCode="N" ElapsedTime="70">
         * 
         * 
         * 
         * ////for MC LFS////////
         * 
         * <PricedItinerary SequenceNumber="19"> <AirItinerary
         * DirectionInd="Other"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-20T00:10:00"
         * ArrivalDateTime="2011-06-20T01:40:00" StopQuantity="0"
         * FlightNumber="1401" ResBookDesigCode="T" ElapsedTime="90">
         * <DepartureAirport LocationCode="SVO" /> <ArrivalAirport
         * LocationCode="LED" /> <OperatingAirline Code="FV" FlightNumber="186"
         * /> <Equipment AirEquipType="A81" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="false" /> </TPA_Extensions> </FlightSegment> <FlightSegment
         * DepartureDateTime="2011-06-20T07:00:00"
         */

        String mockResponseWithDatesReplacd = "";
        char Codzyslow = '"';

        String PricedItinerartSequence_String = "<PricedItinerary SequenceNumber=";
        String FlightSegmentDepartureDate_String = "<FlightSegment DepartureDateTime";

        int PricedItinerartSequenceStart_INT = 0;
        int FlightSegmentDepartureDateStart_INT = 0;

        PricedItinerartSequenceStart_INT = mockResponse.indexOf(PricedItinerartSequence_String);
        if (PricedItinerartSequenceStart_INT > 0)
            FlightSegmentDepartureDateStart_INT = mockResponse.indexOf(FlightSegmentDepartureDate_String);

        String DepartureDateTimeRPH1 = "";
        if (PricedItinerartSequenceStart_INT > 0)
        {
            DepartureDateTimeRPH1 = mockResponse.substring(FlightSegmentDepartureDateStart_INT + 34, FlightSegmentDepartureDateStart_INT + 34 + 10);
        }
        System.out.println("\n replaceDatesInRTresponse:: LFS DepartureDateTimeRPH1=" + DepartureDateTimeRPH1);

        String[] aPlusMinus3daysDatesforResponseOW = new String[6];
        aPlusMinus3daysDatesforResponseOW = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH1);
        // requestFlightData[0] - departureDate provided to WS -make all dates
        // reffere to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponse.replaceAll(DepartureDateTimeRPH1, requestFlightData[0]);
        /*
         * calculatePlusMinus3daysDatesOB OBdate=20110613<--
         * DepartureDateTimeRPH1 calculatePlusMinus3daysDatesOB_calendar=Mon Jun
         * 13 00:00:00 PDT 2011 calculatePlusMinus3daysDatesOB
         * DayOfOBflight_1=Sun Jun 12 00:00:00 PDT 2011
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_1_STR=2011-06-12
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_2_STR=2011-06-11
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_3_STR=2011-06-10
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus1_STR=2011-06-14
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus2_STR=2011-06-15
         * calculatePlusMinus3daysDatesOB rt_DayOfOBflight_plus3_STR=2011-06-16
         */

        String[] aPlusMinus3daysDatesfromRequestOW = new String[6];
        aPlusMinus3daysDatesfromRequestOW = calculatePlusMinus3daysDatesOB(requestFlightData[0]);// departure
                                                                                                 // date
                                                                                                 // provided
                                                                                                 // to
                                                                                                 // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[0], aPlusMinus3daysDatesfromRequestOW[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[1], aPlusMinus3daysDatesfromRequestOW[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[2], aPlusMinus3daysDatesfromRequestOW[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[3], aPlusMinus3daysDatesfromRequestOW[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[4], aPlusMinus3daysDatesfromRequestOW[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseOW[5], aPlusMinus3daysDatesfromRequestOW[5]);

        /*
         * RT RPH2 <BrandedOneWayItineraries RPH="2"> <PricedItinerary
         * SequenceNumber="1" CampaignID="66124"> <AirItinerary
         * DirectionInd="OneWay"> <OriginDestinationOptions>
         * <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-06-24T09:45:00"
         * ArrivalDateTime="2011-06-24T11:10:00" StopQuantity="0"
         * FlightNumber="924" ResBookDesigCode="X" ElapsedTime="85">
         * <DepartureAirport LocationCode="REP" /> <ArrivalAirport
         * LocationCode="BKK" /> <OperatingAirline Code="PG" FlightNumber="924"
         * /> <Equipment AirEquipType="AT7" /> <MarketingAirline Code="PG" />
         * 
         * /////////////mc
         * ///////////////////////////////////////////////////////
         * </FlightSegment> </OriginDestinationOption> <OriginDestinationOption>
         * <FlightSegment DepartureDateTime="2011-06-29T07:30:00"
         * ArrivalDateTime="2011-06-29T09:00:00" StopQuantity="0"
         * FlightNumber="1672" ResBookDesigCode="T" ElapsedTime="90">
         * <DepartureAirport LocationCode="ARH" /> <ArrivalAirport
         * LocationCode="LED" /> <OperatingAirline Code="5N" FlightNumber="145"
         * /> <Equipment AirEquipType="735" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-07-03T06:15:00"
         * 
         * /////////// MC 2nd leg start with </OriginDestinationOption>
         * **********
         */
        String PricedItinerartSequenceRPH2_String = "</OriginDestinationOption>";
        String FlightSegmentDepartureDateRPH2_String = "<FlightSegment DepartureDateTime";

        int PricedItinerartSequenceStartRPH2_INT = 0;
        int FlightSegmentDepartureDateStartRPH2_INT = 0;

        PricedItinerartSequenceStartRPH2_INT = mockResponse.indexOf(PricedItinerartSequenceRPH2_String, FlightSegmentDepartureDateStart_INT);
        if (PricedItinerartSequenceStartRPH2_INT > 0)
            FlightSegmentDepartureDateStartRPH2_INT = mockResponse.indexOf(FlightSegmentDepartureDateRPH2_String, PricedItinerartSequenceStartRPH2_INT);

        String DepartureDateTimeRPH2 = "";
        if (PricedItinerartSequenceStartRPH2_INT > 0)
        {
            DepartureDateTimeRPH2 = mockResponse.substring(FlightSegmentDepartureDateStartRPH2_INT + 34, FlightSegmentDepartureDateStartRPH2_INT + 34 + 10);
        }
        System.out.println("\n replaceDatesInRTresponse:: LFS DepartureDateTimeRPH2=" + DepartureDateTimeRPH2);

        String[] aPlusMinus3daysDatesforResponseRT_RPH2 = new String[6];
        aPlusMinus3daysDatesforResponseRT_RPH2 = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH2);
        // requestFlightData[3] - return Date provided to WS -make all dates
        // reference to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(DepartureDateTimeRPH2, requestFlightData[3]);

        String[] aPlusMinus3daysDatesfromRequestRT_RPH2 = new String[6];
        aPlusMinus3daysDatesfromRequestRT_RPH2 = calculatePlusMinus3daysDatesOB(requestFlightData[3]);// departure
                                                                                                      // date
                                                                                                      // provided
                                                                                                      // to
                                                                                                      // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[0], aPlusMinus3daysDatesfromRequestRT_RPH2[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[1], aPlusMinus3daysDatesfromRequestRT_RPH2[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[2], aPlusMinus3daysDatesfromRequestRT_RPH2[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[3], aPlusMinus3daysDatesfromRequestRT_RPH2[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[4], aPlusMinus3daysDatesfromRequestRT_RPH2[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH2[5], aPlusMinus3daysDatesfromRequestRT_RPH2[5]);

        // RT RPH2 MC LFS end

        // MC 3rd leg
        /*
         * <FlightSegment DepartureDateTime="2011-06-29T07:30:00"
         * ArrivalDateTime="2011-06-29T09:00:00" StopQuantity="0"
         * FlightNumber="1672" ResBookDesigCode="T" ElapsedTime="90">
         * <DepartureAirport LocationCode="ARH" /> <ArrivalAirport
         * LocationCode="LED" /> <OperatingAirline Code="5N" FlightNumber="145"
         * /> <Equipment AirEquipType="735" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> <OriginDestinationOption> <FlightSegment
         * DepartureDateTime="2011-07-03T06:15:00"
         * ArrivalDateTime="2011-07-03T07:30:00" StopQuantity="0"
         * FlightNumber="864" ResBookDesigCode="T" ElapsedTime="75">
         * <DepartureAirport LocationCode="LED" /> <ArrivalAirport
         * LocationCode="SVO" /> <OperatingAirline Code="SU" FlightNumber="864"
         * /> <Equipment AirEquipType="319" /> <MarketingAirline Code="SU" />
         * <MarriageGrp>O</MarriageGrp> <DepartureTimeZone GMTOffset="4" />
         * <ArrivalTimeZone GMTOffset="4" /> <TPA_Extensions> <eTicket
         * Ind="true" /> </TPA_Extensions> </FlightSegment>
         * </OriginDestinationOption> </OriginDestinationOptions>
         * </AirItinerary>
         */
        String PricedItinerartSequenceRPH3_String = "</OriginDestinationOption>";
        String FlightSegmentDepartureDateRPH3_String = "<FlightSegment DepartureDateTime";

        int PricedItinerartSequenceStartRPH3_INT = 0;
        int FlightSegmentDepartureDateStartRPH3_INT = 0;

        PricedItinerartSequenceStartRPH3_INT = mockResponse.indexOf(PricedItinerartSequenceRPH3_String, PricedItinerartSequenceStartRPH2_INT);
        if (PricedItinerartSequenceStartRPH3_INT > 0)
            FlightSegmentDepartureDateStartRPH3_INT = mockResponse.indexOf(FlightSegmentDepartureDateRPH3_String, PricedItinerartSequenceStartRPH3_INT);

        String DepartureDateTimeRPH3 = "";
        if (PricedItinerartSequenceStartRPH3_INT > 0)
        {
            DepartureDateTimeRPH3 = mockResponse.substring(FlightSegmentDepartureDateStartRPH3_INT + 34, FlightSegmentDepartureDateStartRPH3_INT + 34 + 10);
        }
        System.out.println("\n replaceDatesInRTresponse:: LFS LFS DepartureDateTimeRPH3=" + DepartureDateTimeRPH3);

        String[] aPlusMinus3daysDatesforResponseRT_RPH3 = new String[6];
        aPlusMinus3daysDatesforResponseRT_RPH3 = calculatePlusMinus3daysDatesOB(DepartureDateTimeRPH3);
        // requestFlightData[3] - return Date provided to WS -make all dates
        // reference to this one as responseTargetDay0

        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(DepartureDateTimeRPH3, RequestFlightDataRPH3[0]);

        String[] aPlusMinus3daysDatesfromRequestRT_RPH3 = new String[6];
        aPlusMinus3daysDatesfromRequestRT_RPH3 = calculatePlusMinus3daysDatesOB(RequestFlightDataRPH3[0]);// departure
                                                                                                          // date
                                                                                                          // provided
                                                                                                          // to
                                                                                                          // WS
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[0], aPlusMinus3daysDatesfromRequestRT_RPH3[0]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[1], aPlusMinus3daysDatesfromRequestRT_RPH3[1]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[2], aPlusMinus3daysDatesfromRequestRT_RPH3[2]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[3], aPlusMinus3daysDatesfromRequestRT_RPH3[3]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[4], aPlusMinus3daysDatesfromRequestRT_RPH3[4]);
        mockResponseWithDatesReplacd = mockResponseWithDatesReplacd.replaceAll(aPlusMinus3daysDatesforResponseRT_RPH3[5], aPlusMinus3daysDatesfromRequestRT_RPH3[5]);

        return mockResponseWithDatesReplacd;
    }

    // /// end MC and LFS //////////

    // ReadFileStart
    public static String readFile(String file1)// extends JPanel throws
                                               // Exception
    {// read start
        String FileContent = "";

        File file = new File(file1);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        // ////////file handle
        try
        {

            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            // //bis = new BufferedInputStream(fis);
            // //dis = new DataInputStream(bis);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String singleLine = "";
            // dis.available() returns 0 if the file does not have more lines.
            // //while (dis.available() != 0)
            while ((singleLine = br.readLine()) != null)
            {
                // FileContent = FileContent + dis.readLine();
                // FileContent = FileContent + singleLine;
                FileContent = FileContent + singleLine + "\n";

            }

            // dispose all the resources after using them.
            br.close();
            fis.close();
            // bis.close();
            // dis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return FileContent;
    }// read end

    public static String readFileNoCR(String file1)
    {// read start
        String FileContent = "";

        File file = new File(file1);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        // ////////file handle
        try
        {
            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            // //bis = new BufferedInputStream(fis);
            // //dis = new DataInputStream(bis);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String singleLine = "";
            // dis.available() returns 0 if the file does not have more lines.
            // //while (dis.available() != 0)
            while ((singleLine = br.readLine()) != null)
            {
                // FileContent = FileContent + dis.readLine();
                FileContent = FileContent + singleLine;
                // FileContent = FileContent + singleLine+"\n";
            }

            // dispose all the resources after using them.
            br.close();
            fis.close();
            // bis.close();
            // dis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return FileContent;
    }// read end

    // ReadFileEnd
    // readFile buffered
    String ReadFileBuf(String fileName)
    {
        System.out.println("\n REadFileBuf parm:" + fileName);
        FileInputStream fis;
        StringBuffer StrBufFileContent = null;
        String AllLines = "";

        try
        {
            fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis, 1024 * 1024);
            Reader streamReader = new InputStreamReader(bis);
            BufferedReader bufReader = new BufferedReader(streamReader);
            String line = null;

            while (line != null)
            {
                if ((line = bufReader.readLine()) != null)
                {
                    AllLines = AllLines + line;
                    // StrBufFileContent.append(line);
                    System.out.println("ReadFileBuf:: respnseLine=" + line);
                }
            }// while

        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("\n Servlet ReadFileBuf FileNotFound error"
                    + e.toString());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out
                    .println("\n Servlet ReadFileBuf IO error" + e.toString());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("\n Servlet ReadFileBuf Exception error"
                    + e.toString());
        }

        // String toBeReturned = new String(StrBufFileContent);

        // return StrBufFileContent.toString();
        return AllLines;

    }// read file

    boolean iSitRbeBookingREQ(String requestSTR)
    {
        System.out.println("\n check if  RBE request ");
        boolean toBeReturned = false;

        char Cozdzyslow = '"';

        try
        {
            System.out.println(" iSitRbeBookingREQ:: BufReadLine: "
                    + requestSTR);
            if (requestSTR.contains("AwardShopping Enable="))// + Cozdzyslow
                                                             // +" true"))
            {
                toBeReturned = true;
                System.out
                        .println("\n iSitRbeBookingREQ: request for RBE flights");
            }// if
             // else System.out.println("\n request for NON-RBE flights");
        }// try
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("\n Servlet ReadFileBuf Exception error"
                    + e.toString());
        }
        return toBeReturned;
    }

}
