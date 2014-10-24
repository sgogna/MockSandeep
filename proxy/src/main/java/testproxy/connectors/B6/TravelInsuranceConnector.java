/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;


import javax.servlet.http.HttpServletRequest;


import testproxy.connectors.AbstractConnector;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;

public class TravelInsuranceConnector extends AbstractConnector
{

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        String reqText = new String(((GenericRequestWrapper) request).getArrayByte());
        return reqText.matches(".*TravelInsurance.*");
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return serviceManager.getNodeByRequestType("TravelInsurance").getEndpoint();
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        return "INSURANCE_TravelInsurance";
    }

}
