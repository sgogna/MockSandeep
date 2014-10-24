/* Copyright 2009 EB2 International Limited */
package it.webServiceServerSimulator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sabre.ssw.proxy.defines.ProxyMode;
import com.sabre.ssw.proxy.message.converter.JsonConverter;
import com.sabre.ssw.proxy.message.converter.JsonConverterInterface;

import fileManagers.MainFileManager;

public class SimpleJettyWebApplicationForIntegrationTests extends HttpServlet
{

    private static final long serialVersionUID = 1L;
    public static final String RESPONSE_PATH = "WSSimulatorResponse";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String testId = req.getHeader("testId");
        String fileName = req.getHeader("Filename");
        String WS_CALLS_PATH = System.getProperty("savePath");
        JsonConverterInterface convert = new JsonConverter();
        Map<String, String> properties;
        try
        {
            properties = (Map<String, String>) convert.getMapFromJSON(testId.split("@")[0]);
        }
        catch (Exception e)
        {
//            System.out.println("error");
            return;
        }

        String dirname = MainFileManager.getFullTestPath(WS_CALLS_PATH, ProxyMode.RECORD, RESPONSE_PATH + properties.get("airline"), testId, properties.get("testId"));
        File response = new File(dirname + File.separator + fileName + "_RS.xml");
        File headers = new File(dirname + File.separator + fileName + "_RS.zhd");

        try
        {
            String responseText = readFile(response);
            Map<String, String> headersMap = readHeaders(headers);
            Iterator< ? > it = headersMap.keySet().iterator();
            while (it.hasNext())
            {
                String str = (String) it.next();
                resp.addHeader(str, headersMap.get(str));
            }
            resp.getOutputStream().write(responseText.getBytes());
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String readFile(File file) throws Exception
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
        return bos.toString("UTF-8");
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

}
