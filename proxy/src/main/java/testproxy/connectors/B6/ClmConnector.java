/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;


import javax.servlet.http.HttpServletRequest;

import testproxy.connectors.AbstractConnector;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;

public class ClmConnector extends AbstractConnector
{

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        String reqText = new String(((GenericRequestWrapper) request).getArrayByte());
        return reqText.matches(".*comarch.*");
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return serviceManager.getNodeByRequestType("Comarch").getEndpoint();
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        String reqText = new String(((GenericRequestWrapper) request).getArrayByte());
        int start = reqText.indexOf("comarch");
        start = reqText.indexOf("<", start)+1;
        int end = reqText.indexOf(">", start);
        
        return "CLM_" + reqText.substring(start, end);
    }

}
