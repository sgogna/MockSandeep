package testproxy.jmx;

import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.jmx.support.ObjectNameManager;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * Naming stategy for where beans will be displayed in the JMX console treeview
 *
 * @version $Revision: $
 */
public class TestProxyNamingStategy implements ObjectNamingStrategy
{
    private String contextName = "testproxy";
    private String contextPath;

    public ObjectName getObjectName(Object managedBean, String beanKey) throws MalformedObjectNameException
    {
        StringBuffer buf = new StringBuffer(256);
        buf.append(contextName).append(":");

        if (contextPath != null)
        {
            buf.append("path=").append(contextPath).append(",");
        }

        buf.append("name=").append(beanKey);

        return ObjectNameManager.getInstance(buf.toString());
    }


    public void setContextPath(String contextPath)
    {
        this.contextPath = contextPath;
    }

    public void setContextName(String contextName)
    {
        this.contextName = contextName;
    }
}
