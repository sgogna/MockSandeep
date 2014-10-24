/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testproxy.config.Configuration;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.httpservletwrappers.GenericResponseWrapper;
import testproxy.keys.KeyInfo;
import testproxy.keys.SecurityKeyReader;
import testproxy.servicemanagment.ServiceManager;
import testproxy.sessionsinfo.SessionsInfoManager;

import com.sabre.ssw.proxy.defines.ProxyMode;

import fileManagers.InfoFileManager;
import testproxy.utils.SpringBeanContainer;

public abstract class AbstractConnector implements Connector
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractConnector.class);
    private SessionsInfoManager sessionsInfoManager;
    private static SSLSocketFactory sslSocketFactory = null;
    private SecurityKeyReader keyReader;
    ServiceManager serviceManager;
	Configuration configuration;
	
	final int DEFAULT_SUCCESS_CODE = 200;
	final int DEFAULT_TIMEOUT_CODE = 404;
	final int DEFAULT_INTERNALL_ERROR_CODE = 500;
	final int OTHER_ERROR_CODE = 0;
	final int NO_RESPONSE_CODE = -1;
    
    @Override
    public int callRealService(HttpServletRequest request, HttpServletResponse response, String service, String testId, String sessionId, int connectTimeout, String filename)
    {
    	int result = DEFAULT_SUCCESS_CODE;
        URL url;
        String requestName = this.getFileName(request) != null ? this.getFileName(request) : this.getSpecialFileName(request);
        try
        {
            url = new URL(service);
            LOG.info("[" + requestName + "] calls " + service);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection instanceof HttpsURLConnection)
            {
                ((HttpsURLConnection) connection).setSSLSocketFactory(createHttpsConnection());
                //temp. fix not to check the host name hotel and car was having problems to Ethiad certification is not proper.
                ((HttpsURLConnection) connection).setHostnameVerifier(new HostnameVerifier()
                {
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                });
            }
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            Enumeration< ? > en = request.getHeaderNames();
            while (en.hasMoreElements())
            {
                String name = (String) en.nextElement();
                if (!name.toLowerCase().equals("host"))
                {
                    connection.setRequestProperty(name, request.getHeader(name));
                }
            }
            serviceManager = SpringBeanContainer.getServiceManager();
            configuration = SpringBeanContainer.getConfiguration();
            String authType = serviceManager.getNodeByRequestType(((GenericRequestWrapper)request).getSoapAction()).getAuthType();
            if (authType != null && authType.equalsIgnoreCase("Basic")){
                String username = serviceManager.getNodeByRequestType(((GenericRequestWrapper)request).getSoapAction()).getUsername();
                String password = serviceManager.getNodeByRequestType(((GenericRequestWrapper)request).getSoapAction()).getPassword();
                String authString = username + ":" + password;
                byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
                String authStringEnc = new String(authEncBytes);
                LOG.info("Base64 encoded auth string: " + authStringEnc);
                connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
                connection.setRequestProperty("accept-encoding", "false");
                }
            int addedlenght = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int returnCode = DEFAULT_SUCCESS_CODE;
            try
            {

                ExecutorService executor = Executors.newCachedThreadPool();
                TimeOut timeOut = new TimeOut(connection, (GenericRequestWrapper) request);
                try
                {
                    executor.invokeAll(Arrays.asList(timeOut), connectTimeout, TimeUnit.MILLISECONDS);

                }
                catch (InterruptedException e)
                {
                    // e.printStackTrace();
                }
                if (timeOut.isTimeout())
                {
                    if (timeOut.getE() == null)
                    {
                        throw new SocketTimeoutException();
                    }
                    else
                    {
                        throw timeOut.getE();
                    }
                }
                else
                {
                    bos = timeOut.getBos();
                    returnCode = timeOut.getReturnCode();
                }

                if (returnCode != DEFAULT_SUCCESS_CODE)
                {
                    LOG.error("[" + requestName + "]. Service: " + service + " returned with error code: " + returnCode);
                    LOG.error("[" + requestName + "] \nERROR MESSAGE: " + new String(bos.toByteArray()));
                    String path = sessionsInfoManager.getPath(sessionId);
                    if (path != null)
                    {
                        InfoFileManager.setErrorFile(path, filename);
                    }
                }
                // bos.close();
                response.setStatus(returnCode);
                Map<String, List<String>> m = connection.getHeaderFields();
                Iterator< ? > it = m.keySet().iterator();
                while (it.hasNext())
                {
                    String str = (String) it.next();
                    if (str != null)
                    {
                        String value = "";

                        if (str.equals("Content-Length"))
                        {
                            Integer newLength = Integer.parseInt((String) ((List< ? >) m.get(str)).get(0)) + addedlenght;
                            value = newLength.toString();
                        }
                        else
                        {
                            value = (String) ((List< ? >) m.get(str)).get(0);
                        }
                        if (!str.equals("Transfer-Encoding"))
                        response.addHeader(str, value);
                        try
                        {
                            if (!str.equals("Transfer-Encoding"))
                            ((GenericResponseWrapper) response).putHeader(str, value);
                        }
                        catch (Exception e)
                        {

                        }
                    }

                }
            }
            catch (SocketTimeoutException e)
            {
            	
            	String path = sessionsInfoManager.getPath(sessionId);
            	if (path != null)
            	{
            		InfoFileManager.setErrorFile(path, filename);
            	}
            	connection.disconnect();

                if (configuration.isSkipTimeoutResponse()) {
                    LOG.error("[" + requestName + "] return with exception:\n", e);
                    String errorMsg = "TIMEOUT : SKIP TIMEOUT RESPONSE enabled - no data recorded\nThe Testproxy did not receive a timely response from the server: "
                            + service + "\nTimeOut: " + connectTimeout;
                    addedlenght = errorMsg.length();
                    bos.write(errorMsg.getBytes(), 0, addedlenght);
                    response.setStatus(DEFAULT_INTERNALL_ERROR_CODE);
                    // //////////////////////////////////////
                    result = NO_RESPONSE_CODE;
                    // //////////////////////////////////////

                } else {
                    LOG.error("[" + requestName + "] return with exception:\n", e);
                    String errorMsg = "TIMEOUT\nThe Testproxy did not receive a timely response from the server: "
                            + service + "\nTimeOut: " + connectTimeout;
                    addedlenght = errorMsg.length();
                    bos.write(errorMsg.getBytes(), 0, addedlenght);
                    response.setStatus(DEFAULT_TIMEOUT_CODE);
                    result = DEFAULT_TIMEOUT_CODE;
                }

            }
            catch (Exception e)
            {
                String path = sessionsInfoManager.getPath(sessionId);
                if (path != null)
                {
                    InfoFileManager.setErrorFile(path, filename);
                }
                connection.disconnect();
                LOG.error("[" + requestName + "] return with exception:\n", e);

                final Writer resultWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(resultWriter);
                e.printStackTrace(printWriter);
                String errorMsg = resultWriter.toString();
                addedlenght = errorMsg.length();
                bos.write(errorMsg.getBytes(), 0, addedlenght);
                response.setStatus(DEFAULT_TIMEOUT_CODE);
                result = OTHER_ERROR_CODE;
            }

            response.getOutputStream().write(bos.toByteArray());

        }
        catch (MalformedURLException e)
        {
            LOG.error("[" + requestName + "] Bad url", e);
            ////////////////////////////////////////
            result = NO_RESPONSE_CODE;
            ////////////////////////////////////////
        }
        catch (IOException e)
        {
            LOG.error("[" + requestName + "] IO problem", e);
            ////////////////////////////////////////
            result = NO_RESPONSE_CODE;
            ////////////////////////////////////////
        }
        catch (Exception e)
        {
            LOG.error("[" + requestName + "] Error with connection:\n" + e.getMessage());
            ////////////////////////////////////////
            result = NO_RESPONSE_CODE;
            ////////////////////////////////////////
        }
        
        return result;
        
    }

    @Override
    public boolean isSpecialRequestAllowed()
    {
        return false;
    }

    @Override
    public SSLSocketFactory createHttpsConnection()
    {
        if (sslSocketFactory != null)
        {
            return sslSocketFactory;
        }
        try
        {
            KeyManagerFactory factory = KeyManagerFactory.getInstance("SunX509");
            for (KeyInfo keyInfo : keyReader.getKeyManager().getKeys())
            {
                KeyStore ks = KeyStore.getInstance("JKS");
                InputStream inputStream = new FileInputStream(new File(keyReader.getServiceInfoFile().getFile().getParentFile().getAbsolutePath() + File.separator + keyInfo.getKeyPath()));

                ks.load(inputStream, keyInfo.getPassword().toCharArray());
                factory.init(ks, keyInfo.getPassword().toCharArray());
            }

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(factory.getKeyManagers(), new TrustManager[] { new X509TrustManager()
            {

                @Override
                public X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                {
                    return;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                {
                    return;
                }
            } }, null);
            sslSocketFactory = context.getSocketFactory();
            return sslSocketFactory;
        }
        catch (KeyStoreException e)
        {
            LOG.error("", e);
        }
        catch (NoSuchAlgorithmException e)
        {
            LOG.error("", e);
        }
        catch (KeyManagementException e)
        {
            LOG.error("", e);
        }
        catch (FileNotFoundException e)
        {
            LOG.error("", e);
        }
        catch (CertificateException e)
        {
            LOG.error("", e);
        }
        catch (IOException e)
        {
            LOG.error("", e);
        }
        catch (UnrecoverableKeyException e)
        {
            LOG.error("", e);
        }
        return null;
    }

    @Override
    public byte[] getSpecialResponse(HttpServletRequest request, Properties headers)
    {
        return new byte[0];
    }

    @Override
    public String getName()
    {
        return "";
    }

    @Override
    public String getSpecialFileName(HttpServletRequest request)
    {
        return "";
    }

    public SessionsInfoManager getSessionsInfoManager()
    {
        return sessionsInfoManager;
    }

    public void setSessionsInfoManager(SessionsInfoManager sessionsInfoManager)
    {
        this.sessionsInfoManager = sessionsInfoManager;
    }

    @Override
    public String getRequestFullName(String session, String requestName, ProxyMode mode)
    {
        String number = String.valueOf(sessionsInfoManager.getCount(session, requestName));
        requestName += "_" + number;
        return requestName;
    }

    public SecurityKeyReader getKeyReader()
    {
        return keyReader;
    }

    public void setKeyReader(SecurityKeyReader keyReader)
    {
        this.keyReader = keyReader;
    }

}
