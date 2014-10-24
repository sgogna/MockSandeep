/* Copyright 2009 EB2 International Limited */
package testproxy.servicemanagment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.thoughtworks.xstream.XStream;

public class ServiceInfoReader
{
    private Resource serviceInfoFile;
    private static final Logger LOG = LoggerFactory.getLogger(ServiceInfoReader.class);

    public Resource getServiceInfoFile()
    {
        return serviceInfoFile;
    }

    public void setServiceInfoFile(Resource serviceInfoFile)
    {
        this.serviceInfoFile = serviceInfoFile;
    }

    static XStream xstream = new XStream();

    public void createTestFile()
    {
        ServiceManager sm = new ServiceManager();
        sm.getServicelist().add(new ServiceNode("request", "gds"));
        sm.getServicelist().add(new ServiceNode("request1", "gds1"));
        System.out.println(xstream.toXML(sm));

    }

    public ServiceManager readServiceList()
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(serviceInfoFile.getFile());
            return (ServiceManager) xstream.fromXML(fis);
        }
        catch (FileNotFoundException e)
        {
            LOG.error(e.getMessage());
        }
        catch (IOException e)
        {
            LOG.error(e.getMessage());
        }
        return null;
    }

}
