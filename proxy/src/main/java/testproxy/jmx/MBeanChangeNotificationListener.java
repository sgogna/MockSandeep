package testproxy.jmx;

public interface MBeanChangeNotificationListener
{
    void onChange(String name, Object bean);
}
