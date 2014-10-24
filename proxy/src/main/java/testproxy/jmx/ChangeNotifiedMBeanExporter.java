package testproxy.jmx;

import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.springframework.jmx.export.MBeanExporter;


public class ChangeNotifiedMBeanExporter extends MBeanExporter implements MBeanChangeNotificationListener
{
	 private static final Logger log = Logger.getLogger(ChangeNotifiedMBeanExporter.class);
    @Override
    protected void onRegister(ObjectName objectName, Object mbean)
    {
        if (mbean instanceof MBeanChangeNotifier)
        {
            ((MBeanChangeNotifier) mbean).registerMBeanChangeNotificationListener(objectName.getKeyProperty("name"), this);
        }
        super.onRegister(objectName, mbean);
    }

    public void onChange(String name, Object bean)
    {
        try
        {
            ObjectName objectName = getObjectName(bean, name);
            server.unregisterMBean(objectName);
            server.registerMBean(bean, objectName);
        }
        catch (Exception e)
        {
            // TODO: handle exception
        	log.debug(e);
        }
    }
}
