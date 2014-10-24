/* Copyright 2009 EB2 International Limited */
package it.testBase;

import it.webServiceServerSimulator.SimpleJettyWebApplicationForIntegrationTests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sabre.ssw.proxy.defines.CommonTestProxyConstants;
import com.sabre.ssw.proxy.defines.ProxyMode;
import com.sabre.ssw.proxy.message.ProxyCommandCreator;

import fileManagers.MainFileManager;

public class IntegrationTestBase
{

    private String testId;
    private String airline;
    private String runId;
    private String WS_CALLS_PATH;
    private String sessionId;
    private String originalHost;
    protected ProxyMode proxyMode;
    private String endpoint;

    @BeforeClass
    public void setUp() throws Exception
    {
        WS_CALLS_PATH = System.getProperty("savePath");
        endpoint = "http://localhost:871/testproxy/s";
        proxyMode = ProxyMode.REPLAY;

    }

    @Test
    public void test() throws Exception
    {
        String response = getResponse("ShoppingApi_purchase__SABREGDS_EndTransactionLLS_4");

        assert response != null : "Response is null";
    }

    private Map<String, String> readHeaders(File headerFile)
    {
        Map<String, String> headers = new HashMap<String, String>();
        Properties properties = new Properties();
        MainFileManager.readHeaderFile(headerFile, properties);
        for (String prop : properties.stringPropertyNames())
        {
            if (!prop.equals("testId") && !prop.equals("Host") && !prop.equals("host"))
            {
                headers.put(prop, properties.getProperty(prop));
            }
        }
        return headers;
    }

    protected String readFile(File file) throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Reader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        int next = in.read();
        while (next > -1)
        {
            bos.write(next);
            next = in.read();
        }
        bos.flush();
        in.close();
        return bos.toString();
    }

    protected String sendRequest(String filename, String request, Map<String, String> headers) throws Exception
    {
        prepareHeaders(headers, filename);
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        for (String header : headers.keySet())
        {
            connection.setRequestProperty(header, headers.get(header));
        }

        String response = null;
        OutputStream out = null;
        InputStream in = null;

        out = connection.getOutputStream();
        out.write(request.getBytes());
        in = connection.getInputStream();
        response = getStringFromInputStream(in);
        connection.disconnect();
        return response;
    }

    private String getStringFromInputStream(InputStream inp) throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Reader in = new InputStreamReader(inp, "UTF-8");
        int next = in.read();
        while (next > -1)
        {
            bos.write(next);
            next = in.read();
        }
        bos.flush();
        in.close();
        return bos.toString();
    }

    protected String getResponse(String filename) throws Exception
    {
        return getResponse(filename, false);
    }

    protected String getResponse(String filename, Boolean changeAirline) throws Exception
    {
        if (testId == null)
        {
            throw new NullPointerException("You need to set testId");
        }
        if (airline == null)
        {
            throw new NullPointerException("You need to set airline");
        }
        if (sessionId == null)
        {
            throw new NullPointerException("You need to set sessionId");
        }

        String airline = (changeAirline ? SimpleJettyWebApplicationForIntegrationTests.RESPONSE_PATH : "") + this.airline;

        String dirname = MainFileManager.getFullTestPath(WS_CALLS_PATH, ProxyMode.RECORD, airline, runId, testId);
        String requestName1 = filename + "_RQ.xml";
        String requestHeaderName1 = filename + "_RQ.zhd";
        File requestFile = new File(dirname + File.separator + requestName1);
        File headerFile = new File(dirname + File.separator + requestHeaderName1);
        Map<String, String> headers = readHeaders(headerFile);
        String request = readFile(requestFile);
        //
        String response = sendRequest(filename, request, headers);
        return response;
    }

    protected String getPath()
    {
        return MainFileManager.getFullTestPath(WS_CALLS_PATH, proxyMode, airline, runId, testId);
    }

    protected String getBasicTestPath()
    {
        return MainFileManager.getFullTestPath(WS_CALLS_PATH, ProxyMode.RECORD, airline, runId, testId);
    }

    protected String readFileFromTestFolder(String filename) throws Exception
    {
        if (testId == null)
        {
            throw new NullPointerException("You need to set testId");
        }
        if (airline == null)
        {
            throw new NullPointerException("You need to set airline");
        }
        if (sessionId == null)
        {
            throw new NullPointerException("You need to set sessionId");
        }

        String dirname = MainFileManager.getFullTestPath(WS_CALLS_PATH, ProxyMode.RECORD, airline, runId, testId);
        File requestFile = new File(dirname + File.separator + filename);
        return readFile(requestFile);
    }

    protected void prepareHeaders(Map<String, String> headers, String filename)
    {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("testId", testId);
        if (originalHost != null)
        {
            propertiesMap.put("original_host", originalHost);
        }
        propertiesMap.put("airline", airline);
        if (runId != null)
        {
            propertiesMap.put("runId", runId);
        }
        propertiesMap.put("mode", proxyMode.toString());
        if (filename != null)
        {
            propertiesMap.put("T1T2Transaction", filename.substring(0, filename.indexOf("__")));
        }
        headers.put(CommonTestProxyConstants.TEST_PROXY_COMMAND,
                ProxyCommandCreator.getDirectProxyCommand(propertiesMap, sessionId));
    }

    protected void setTestId(String testId)
    {
        this.testId = testId;
    }

    protected void setAirline(String airline)
    {
        this.airline = airline;
    }

    protected void setRunId(String runId)
    {
        this.runId = runId;
    }

    protected void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    protected void setOriginalHost(String originalHost)
    {
        this.originalHost = originalHost;
    }

    protected void setProxyMode(ProxyMode proxyMode)
    {
        this.proxyMode = proxyMode;
    }

    protected String generateSessionId() throws InterruptedException
    {
        Random random = new Random();
        long l = random.nextLong();
        l = (l < 0) ? -1 * l : l;
        return new String(String.valueOf(l));
    }



}
