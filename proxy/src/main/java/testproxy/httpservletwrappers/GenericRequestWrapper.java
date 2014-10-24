/* Copyright 2009 EB2 International Limited */
package testproxy.httpservletwrappers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testproxy.beans.HostCommand;
import testproxy.beans.HostCommandFactory;
import testproxy.utils.XmlUtil;

public class GenericRequestWrapper extends HttpServletRequestWrapper implements GenericWrapper
{

    HttpServletRequest origRequest;
    byte[] reqBytes;
    boolean firstTime = true;
    private String sessionId;
    private String customerID;
    private String uniqeId;
    private String pnr;
    private HostCommand hostCommand;
    private String requestString;
    private String responseString;
    private static final Logger LOG = LoggerFactory.getLogger(GenericRequestWrapper.class);
    public GenericRequestWrapper(HttpServletRequest arg0)
    {
        super(arg0);
        origRequest = arg0;
    }

    @Override
    public BufferedReader getReader() throws IOException
    {

        if (firstTime)
        {
            readDataFromReader();
        }

        InputStreamReader dave = new InputStreamReader(new ByteArrayInputStream(reqBytes), "UTF-8");
        BufferedReader br = new BufferedReader(dave);
        return br;

    }

    void readDataFromReader() throws IOException
    {
        firstTime = false;
        ServletInputStream inputStream = origRequest.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next = inputStream.read();
        while (next > -1)
        {
            bos.write(next);
            next = inputStream.read();
        }
        bos.flush();
        bos.close();

        reqBytes = bos.toByteArray();
    }

    public byte[] getArrayByte()
    {
        if (firstTime)
        {
            try
            {
                readDataFromReader();
            }
            catch (IOException e)
            {
                LOG.error(e.getMessage());
            }
        }

        return reqBytes;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {

        if (firstTime)
        {
            readDataFromReader();
        }

        ServletInputStream sis = new ServletInputStream()
                        {
            private int i;

            @Override
            public int read() throws IOException
                            {
                byte b;
                if (reqBytes.length > i)
                    b = reqBytes[i++];
                else
                    b = -1;

                return b;
            }
        };

        return sis;
    }

    public String getSoapAction()
    {
        String s = new String(getArrayByte());
        int start = s.indexOf(":Action>");
        if (start < 0)
        {
            return getActionNameAfterBodyTag(s);
        }
        else
        {
            start += 8;
            int end = s.indexOf("<", start) - 2;
            return s.substring(start, end);
        }
    }

    private String getActionNameAfterBodyTag(String s)
    {
        int body = s.indexOf(":Body>");
        if (body > 0)
        {
            int endOfBody = body + 6;
            int maxCharOfActionName = 100;
            int canDidateActionNameEndIndex = endOfBody + maxCharOfActionName;
            String candidateActionName = s.substring(endOfBody, canDidateActionNameEndIndex).trim();
            Pattern pattern = Pattern.compile("[\\S]+");
            Matcher matcher = pattern.matcher(candidateActionName.replaceAll("<", ""));
            if (matcher.find())
            {
                String group = matcher.group();
                if (group.contains(":"))
                {
                    return group.substring(group.indexOf(":") + 1, group.length() - 2);
                }
                return group.substring(0, group.length() - 2);
            }
        }

        return null;
    }

    public String getConversationId()
    {
        if (sessionId == null)
        {
            String rqnow = getRequestString();
            String SessionIDStart_STR = ":ConversationId>";
            String SessionIDEnd_STR = "</";
            int SessionIDStart_INT;
            int SessionIDEnd_INT;
            String currentSessionID = "";

            SessionIDStart_INT = rqnow.indexOf(SessionIDStart_STR);
            if (SessionIDStart_INT > 0)
            {
                SessionIDEnd_INT = rqnow.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
                sessionId = rqnow.substring(SessionIDStart_INT + 16, SessionIDEnd_INT);
                LOG.debug("\n this is what comes from request ::::::  getConversationId=" + currentSessionID + "<--");
            }
        }
        return sessionId;
    }

    public String getCustomerID()
    {
        if (customerID == null)
        {
            String rqnow = getRequestString();
            String SessionIDStart_STR = ":CustomerID>";
            String SessionIDEnd_STR = "</";
            int SessionIDStart_INT;
            int SessionIDEnd_INT;
            String currentSessionID = "";

            SessionIDStart_INT = rqnow.indexOf(SessionIDStart_STR);
            if (SessionIDStart_INT > 0)
            {
                SessionIDEnd_INT = rqnow.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
                customerID = rqnow.substring(SessionIDStart_INT + 12, SessionIDEnd_INT);
                LOG.debug("\n this is what comes from request ::::::  getCustomerID=" + currentSessionID + "<--");
            }
            else
            {
                customerID =  getCPAID();
            }
        }
        return customerID;
    }

    private String getCPAID()
    {
        if (customerID == null)
        {
            String rqnow = getRequestString();
            String SessionIDStart_STR = ":CPAId>";
            String SessionIDEnd_STR = "</";
            int SessionIDStart_INT;
            int SessionIDEnd_INT;
            String currentSessionID = "";

            SessionIDStart_INT = rqnow.indexOf(SessionIDStart_STR);
            if (SessionIDStart_INT > 0)
            {
                SessionIDEnd_INT = rqnow.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
                customerID = rqnow.substring(SessionIDStart_INT + 7, SessionIDEnd_INT);
                LOG.debug("\n this is what comes from request ::::::  getcpaId=" + currentSessionID + "<--");
            }
        }
        return customerID;
    }

    public String getPNR()
    {
        if (pnr == null)
        {
            String rqnow = getRequestString();
            String SessionIDStart_STR = "PNRLocator>";
            String SessionIDEnd_STR = "</";
            int SessionIDStart_INT;
            int SessionIDEnd_INT;
            String currentSessionID = "";

            SessionIDStart_INT = rqnow.indexOf(SessionIDStart_STR);
            if (SessionIDStart_INT > 0)
            {
                SessionIDEnd_INT = rqnow.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
                pnr = rqnow.substring(SessionIDStart_INT + 11, SessionIDEnd_INT);
                LOG.debug("\n this is what comes from request ::::::  pnr=" + pnr + "<--");
            }
        }
        return pnr;
    }

    public String getUniqeId()
    {
        if (uniqeId == null)
        {
            String rqnow = getRequestString();
            String SessionIDStart_STR = ":UniqueID ID=";
            String SessionIDEnd_STR = "</";
            int SessionIDStart_INT;
            int SessionIDEnd_INT;
            String currentSessionID = "";

            SessionIDStart_INT = rqnow.indexOf(SessionIDStart_STR);
            if (SessionIDStart_INT > 0)
            {
                SessionIDEnd_INT = rqnow.indexOf(SessionIDEnd_STR, SessionIDStart_INT);
                uniqeId = rqnow.substring(SessionIDStart_INT + 14, SessionIDEnd_INT-3);
                LOG.debug("\n this is what comes from request ::::::  UniqueID=" + uniqeId + "<--");
            }
        }
        return uniqeId;
    }

    public HostCommand getHostCommand()
    {
        if (hostCommand == null)
        {
            String requestContent = XmlUtil.prettyFormat(getRequestString(), 2);
            hostCommand = HostCommandFactory.create(requestContent);
        }
        return hostCommand;
    }

    public String getRequestString()
    {
        if (requestString == null)
        {
            requestString = new String(getArrayByte());
        }
        return requestString;
    }

    public String getResponseString()
    {
        if (responseString == null)
        {
            responseString = new String(getArrayByte());
        }
        return responseString;
    }
}