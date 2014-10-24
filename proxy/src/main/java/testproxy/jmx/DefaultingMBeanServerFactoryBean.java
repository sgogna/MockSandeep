package testproxy.jmx;

import javax.management.MBeanServer;

import org.springframework.jmx.MBeanServerNotFoundException;
import org.springframework.jmx.support.JmxUtils;
import org.springframework.jmx.support.MBeanServerFactoryBean;

/**
 * Spring FactoryBean that returns a predefined MBeanServer if one is available,
 * otherwise creates one of its own using Springs MBeanServerFactoryBean. This
 * enables you to run an app that depends on an mbean server outside a container
 * seamlessly.
 *
 * @version $Revision: 360836 $
 */
public class DefaultingMBeanServerFactoryBean extends MBeanServerFactoryBean
{
    private MBeanServer defaultMBeanServer;

    /**
     * @throws MBeanServerNotFoundException
     */
    @Override
    public void afterPropertiesSet() throws MBeanServerNotFoundException
    {
        try
        {
            // Try to locate a pre-instantiated mbean server (perhaps provided
            // by the container).
            defaultMBeanServer = JmxUtils.locateMBeanServer();
        }
        catch (MBeanServerNotFoundException e)
        {
            // No existing mbean server. Create one.
            super.afterPropertiesSet();
        }
    }

    protected boolean isLocalMBeanServer()
    {
        return defaultMBeanServer == null;
    }

    @Override
    public void destroy()
    {
        if (isLocalMBeanServer())
        {
            super.destroy();
        }
    }

    @Override
    public MBeanServer getObject()
    {
        if (isLocalMBeanServer())
        {
            return super.getObject();
        }
        else
        {
            return defaultMBeanServer;
        }
    }
}
