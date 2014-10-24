/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;


import javax.servlet.http.HttpServletRequest;


import testproxy.connectors.AbstractConnector;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;

public class PayPalConnector extends AbstractConnector
{

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        String reqText = new String(((GenericRequestWrapper) request).getArrayByte());
        return reqText.matches(".*PayPal.*");
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return serviceManager.getNodeByRequestType("PayPal").getEndpoint();
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        String reqText = new String(((GenericRequestWrapper) request).getArrayByte());
        int start = reqText.indexOf("<PaymentAction>");
        if (start < 0)
        {
            return null;
        }
        start += 15;
        int end = reqText.indexOf("<", start);
        return "PAYPAL_" + reqText.substring(start, end);
    }

}
