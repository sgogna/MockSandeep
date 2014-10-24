/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

import testproxy.httpservletwrappers.GenericRequestWrapper;

public class TimeOut implements Callable<String>
{
    private boolean timeout = true;
    private HttpURLConnection connection;
    private GenericRequestWrapper request;
    private int returnCode;
    private ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private Exception e;

    public TimeOut(HttpURLConnection connection, GenericRequestWrapper request)
    {
        super();
        this.connection = connection;
        this.request = request;
    }

    @Override
    public String call() throws Exception
    {
        try
        {
            OutputStream out = connection.getOutputStream();
            out.write(((GenericRequestWrapper) request).getArrayByte());
            out.close();
            returnCode = connection.getResponseCode();
            InputStream connectionIn = null;
            if (returnCode == 200)
            {
                connectionIn = connection.getInputStream();
            }
            else
            {
                connectionIn = connection.getErrorStream();
            }

            int next = connectionIn.read();
            while (next > -1)
            {
                bos.write(next);
                next = connectionIn.read();
            }
            connectionIn.close();
            timeout = false;
        }
        catch (Exception e)
        {
            this.e = e;
        }
        return null;
    }

    public ByteArrayOutputStream getBos()
    {
        return bos;
    }

    public int getReturnCode()
    {
        return returnCode;
    }

    public boolean isTimeout()
    {
        return timeout;
    }

    public Exception getE()
    {
        return e;
    }
}