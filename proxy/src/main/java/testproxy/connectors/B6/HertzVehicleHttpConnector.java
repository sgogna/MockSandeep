/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testproxy.connectors.AbstractConnector;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;

public class HertzVehicleHttpConnector extends AbstractConnector
{
    private static final Logger LOG = LoggerFactory.getLogger(HertzVehicleHttpConnector.class);

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        String requestText = new String(((GenericRequestWrapper) request).getArrayByte());
        return requestText.matches(".*OTA_VehAvailRateRQ.*") || requestText.matches(".*OTA_VehResRQ.*");
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        String endpoint = serviceManager.getNodeByRequestType("VehicleHertz").getEndpoint();
        if (endpoint != null)
        {
            return endpoint;
        }
        else
        {
            LOG.error("NO DEFINED ENDPOINT TO HERTZ VEHICLE SERVICE");
            return null;
        }
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        String requestText = new String(((GenericRequestWrapper) request).getArrayByte());
        return requestText.matches(".*OTA_VehAvailRateRQ.*") ? "HERTZVEHICLE_OTA_VehAvailRateRQ" : "HERTZVEHICLE_OTA_VehResRQ";
    }
}
