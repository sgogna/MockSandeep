/* Copyright 2009 EB2 International Limited */
package testproxy.keys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.thoughtworks.xstream.XStream;

public class SecurityKeyReader
{
    private Resource keyInfoFile;
    static XStream xstream = new XStream();
    private static final Logger LOG = LoggerFactory.getLogger(SecurityKeyReader.class);
    private SecurityKeyManager keyManager;

    public Resource getServiceInfoFile()
    {
        return keyInfoFile;
    }

    public void setServiceInfoFile(Resource keyInfoFile)
    {
        this.keyInfoFile = keyInfoFile;
    }

    public void init()
    {
        keyManager = readServiceList();
    }

    private SecurityKeyManager readServiceList()
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(keyInfoFile.getFile());
            return (SecurityKeyManager) xstream.fromXML(fis);
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

    public SecurityKeyManager getKeyManager()
    {
        return keyManager;
    }
}
