/* Copyright 2009 EB2 International Limited */
package testproxy.connectors.B6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testproxy.connectors.AbstractConnector;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.servicemanagment.ServiceManager;
import testproxy.utils.CreateSessionResponseCreator;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class GdsSabreConnector extends AbstractConnector
{
    private static final Logger LOG = LoggerFactory.getLogger(GdsSabreConnector.class);
    private CreateSessionResponseCreator creator = new CreateSessionResponseCreator();

    @Override
    public boolean isProperConnector(HttpServletRequest request)
    {
        return ((GenericRequestWrapper) request).getSoapAction() != null;
    }

    @Override
    public String getEndpoint(HttpServletRequest request, ServiceManager serviceManager)
    {
        String soapAction = ((GenericRequestWrapper) request).getSoapAction();
        String endpoint = serviceManager.getNodeByRequestType(soapAction).getEndpoint();
        if (endpoint != null)
        {
            return endpoint;
        }
        else
        {
            LOG.error("NO DEFINED ENDPOIND TO SOAPACTION: " + soapAction);
            return null;
        }
    }

    @Override
    public String getFileName(HttpServletRequest request)
    {
        String soapAction = ((GenericRequestWrapper) request).getSoapAction();
        if ("SessionCreate".equals(soapAction) || "SessionCreateATH".equals(soapAction) || "SessionClose".equals(soapAction))
        {
            return null;
        }
        return "SABREGDS_" + soapAction;
    }

    @Override
    public byte[] getSpecialResponse(HttpServletRequest request, Properties headers)
    {

        String soapAction = ((GenericRequestWrapper) request).getSoapAction();
        if ("SessionCreate".equals(soapAction) || "SessionCreateATH".equals(soapAction))
        {
            LOG.info("Generating SessionCreate request.");
            return creator.getSessionCreateMessage().getBytes();
        }
        else if (soapAction.equals("SessionClose"))
        {
            LOG.info("Generating SessionClose request.");
            return creator.getSessionCloseResponse().getBytes();
        }
        return new byte[0];
    }

    @Override
    public String getName()
    {
        return "GDSSabreConnector";
    }

    @Override
    public boolean isSpecialRequestAllowed()
    {
        return true;
    }

    @Override
    public String getSpecialFileName(HttpServletRequest request)
    {
        return "SABREGDS_SessionCreate";
    }

}
