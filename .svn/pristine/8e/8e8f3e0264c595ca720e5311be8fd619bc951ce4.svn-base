package testproxy.httpservletwrappers;

import testproxy.servlets.HttpServletResponseMock;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * GenericResponseWrapperMock
 */
public class GenericResponseWrapperMock extends GenericResponseWrapper {
    private String responseString;

    public GenericResponseWrapperMock() {
        super(new HttpServletResponseMock());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        return new ServletOutputStream() {
            private StringBuilder sb = new StringBuilder();
            @Override
            public void write(int b) throws IOException {
                sb.append((char)b);
            }

            @Override
            public void flush() throws IOException {
                responseString = sb.toString();
            }
        };
    }

    public String getResponseString() {
        return responseString;
    }
}
