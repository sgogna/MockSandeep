package testproxy.httpservletwrappers;

import testproxy.servlets.HttpServletRequestMock;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * GenericRequestWrapperMock
 */
public class GenericRequestWrapperMock extends GenericRequestWrapper {
    public String mockRequest;

    public GenericRequestWrapperMock(String requestString) {
        super(new HttpServletRequestMock());
        this.mockRequest = requestString;
    }

    @Override
    void readDataFromReader() throws IOException {
        reqBytes = mockRequest.getBytes("UTF-8");
    }
}