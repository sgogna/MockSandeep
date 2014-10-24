/* Copyright 2009 EB2 International Limited */
package testproxy.httpservletwrappers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GenericResponseWrapper extends HttpServletResponseWrapper implements GenericWrapper
{
    private ServletOutputStream outputStream;
    private PrintWriter writer;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
//    private Map<String, String> headers = new HashMap<String, String>();
    private Properties headers = new Properties();

    public GenericResponseWrapper(HttpServletResponse resp)
    {
        super(resp);
    }

    public void putHeader(String name, String value)
    {
        headers.setProperty(name, value);
    }

    public Properties getHeaders()
    {
        return headers;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        if (outputStream == null)
        {
            ServletOutputStream sos = super.getOutputStream();
            outputStream = new MyServletOutputStream(sos, output);
        }
        return outputStream;

    }

    @Override
    public PrintWriter getWriter() throws IOException
    {
        if (writer != null)
            return writer;

        writer = new PrintWriter(getOutputStream());
        return writer;
    }

    public byte[] getArrayByte()
    {
        return output.toByteArray();
    }

    public String getSoapAction()
    {
        String s = new String(output.toByteArray());
        int start = s.indexOf(":Action>") + 8;
        int end = s.indexOf("<", start);
        return s.substring(start, end - 2);
    }

}

class MyServletOutputStream extends ServletOutputStream
{

    protected ServletOutputStream ob;
    private ByteArrayOutputStream output;

    public MyServletOutputStream(ServletOutputStream ob, ByteArrayOutputStream output)
    {
        this.ob = ob;
        this.output = output;
    }

    @Override
    public void write(int arg0) throws IOException
    {
        this.output.write(arg0);
        this.ob.write(arg0);
    }
    
    @Override
    public void flush() throws IOException {
        this.ob.flush();
        this.output.flush();
    }

    @Override
    public void close() throws IOException {
        this.ob.close();
        this.output.close();
    }


}
