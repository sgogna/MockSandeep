/* Copyright 2009 EB2 International Limited */
package it.testBase;

import java.util.Map;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.sabre.ssw.proxy.defines.ProxyMode;

public class IntegrationCompareTestBase extends IntegrationTestBase
{

    private Server server;
    private static final int SERVER_PORT = 8888;
    private static final String CONTEXT_PATH = "/wssimulator";
    private static final String INTEGRATION_SERVER_PORT = "proxy.test.integration.serverPort";

    @BeforeClass
    public void startServer() throws Exception
    {
        //super.setUp();
        String port = System.getProperty(INTEGRATION_SERVER_PORT);
        if (port != null)
        {
            server = new Server(Integer.parseInt(port));
        }
        else
        {
            server = new Server(SERVER_PORT);
        }
        server.addHandler(new WebAppContext("src/test/webapp", CONTEXT_PATH));
        server.start();
        setOriginalHost("http://localhost:" + SERVER_PORT + CONTEXT_PATH + "/Simulator");
        setProxyMode(ProxyMode.COMPARE);
    }

    @Override
    protected void prepareHeaders(Map<String, String> headers, String filename)
    {
        super.prepareHeaders(headers, filename);
        headers.put("Filename", filename);
    }

    @AfterClass
    public void stopServer()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
