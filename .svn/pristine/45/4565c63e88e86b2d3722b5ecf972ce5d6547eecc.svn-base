/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import javax.servlet.http.HttpServletRequest;

import testproxy.servicemanagment.ServiceManager;

public class UnknownServiceConnector extends AbstractConnector
{

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        return true;
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        return null;
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        return "UNKNOWN_Unknown";
    }

}
