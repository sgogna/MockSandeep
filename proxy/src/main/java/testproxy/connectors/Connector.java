/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import java.util.Properties;

import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testproxy.servicemanagment.ServiceManager;

import com.sabre.ssw.proxy.defines.ProxyMode;

public interface Connector
{
    /**
     * Call real service
     * @param request HTTP request
     * @param response HTTP response
     * @param service service name
     * @param testId test ID
     * @param sessionId session ID
     * @param connectTimeout connection timeout
     * @param filename response file name 
     * @return HTTP response code or error code (error code < 0) <code>-1</code> means no response available. Mostly it will be HTTP response code.
     */
    public int callRealService(HttpServletRequest request, HttpServletResponse response, String service, String testId, String sessionId, int connectTimeout, String filename);

    public boolean isProperConnector(HttpServletRequest request);

    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager);

    public String getFileName(HttpServletRequest request);

    SSLSocketFactory createHttpsConnection();

    public byte[] getSpecialResponse(HttpServletRequest request, Properties headers);

    public String getName();

    public String getSpecialFileName(HttpServletRequest request);

    public boolean isSpecialRequestAllowed();

    public String getRequestFullName(String session, String requestName, ProxyMode mode);
}
