/* Copyright 2009 EB2 International Limited */
package testproxy.utils;

import com.sabre.ssw.proxy.concurrent.fileManager.ConcurrentRequestsManager;
import org.springframework.context.ApplicationContext;
import testproxy.config.Configuration;
import testproxy.message.datagrabbers.DataGrabber;
import testproxy.message.replacers.DataReplacer;
import testproxy.servicemanagment.ServiceInfoReader;
import testproxy.servicemanagment.ServiceManager;
import testproxy.sessionsinfo.SessionsInfoManager;

public class SpringBeanContainer
{
    private static ApplicationContext applicationContext;
    private static Configuration configuration;
    private static SessionsInfoManager sessionsInfoManager;
    private static ServiceManager serviceManager;
    private static DataReplacer dataReplacer;
    private static DataGrabber dataGrabber;

    private SpringBeanContainer()
    {
    }

    public static ConcurrentRequestsManager getConcurrentRequestsManager()
    {
        return (ConcurrentRequestsManager) applicationContext.getBean("concurrentRequestsManager");
    }

   public static void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringBeanContainer.applicationContext = applicationContext;
        configuration = (Configuration) applicationContext.getBean("testProxyConfig");
        sessionsInfoManager = (SessionsInfoManager) applicationContext.getBean("sessionsInfoManager");
        ServiceInfoReader serviceInfoReader = (ServiceInfoReader) applicationContext.getBean("serviceInfoReader");
        if ( serviceInfoReader != null ) {
            serviceManager = serviceInfoReader.readServiceList();
        }
        dataGrabber = (DataGrabber) applicationContext.getBean("dataGrabber");
        dataReplacer = (DataReplacer) applicationContext.getBean("dataReplacer");
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static SessionsInfoManager getSessionsInfoManager() {
        return sessionsInfoManager;
    }

    public static ServiceManager getServiceManager() {
        return serviceManager;
    }

    public static DataReplacer getDataReplacer() {
        return dataReplacer;
    }

    public static DataGrabber getDataGrabber() {
        return dataGrabber;
    }
}
