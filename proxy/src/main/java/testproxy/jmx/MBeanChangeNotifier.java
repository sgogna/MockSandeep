package testproxy.jmx;

public interface MBeanChangeNotifier
{
    void registerMBeanChangeNotificationListener(String beanKey, MBeanChangeNotificationListener listener);
}
