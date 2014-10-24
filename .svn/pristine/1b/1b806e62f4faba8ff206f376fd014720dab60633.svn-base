package testproxy.jmx;

/**
 * Created with IntelliJ IDEA.
 * User: SG0962671
 * Date: 12/19/12
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.jmx.export.annotation.*;
import testproxy.servicemanagment.ServiceManager;
import testproxy.servicemanagment.ServiceNode;
import testproxy.utils.SpringBeanContainer;

import java.util.ArrayList;
import java.util.List;

@ManagedResource(description = "Configuration Service Registry parameters for the mock server")
public class ServiceRegistryMBean {
    private List<String> requestType = new ArrayList<String>();

    private ServiceManager getServiceManager(){
        return SpringBeanContainer.getServiceManager();
    }

    @ManagedAttribute(description = "Show which mode proxy is running.")
    public List<String> getServiceNameList() {
        for (ServiceNode node : getServiceManager().getServicelist())
            requestType.add(node.getRequestType());
        return requestType;
    }

    @ManagedOperation(description = "Show regular expression for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name to get regular expression for")})
    public String getRegularExpression(String requestNode) {
        return getServiceManager().getNodeByRequestType(requestNode).getRegex();
    }

    @ManagedOperation(description = "Set regular expression for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name"),
            @ManagedOperationParameter(name = "Regular Expression", description = "Regular expression to set for that service")})
    public void setRegularExpression(String requestType, String regex) {
        getServiceManager().getNodeByRequestType(requestType).setRegex(regex);
    }

    @ManagedOperation(description = "Show regular expression for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name to get Authentication Type for")})
    public String getAuthType(String requestNode) {
        return getServiceManager().getNodeByRequestType(requestNode).getAuthType();
    }

    @ManagedOperation(description = "Set authentication type for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name"),
            @ManagedOperationParameter(name = "Authentication Type", description = "We can set Basic authentication here to set for that service")})
    public void setAuthType(String requestType, String authType) {
        getServiceManager().getNodeByRequestType(requestType).setAuthType(authType);
    }

    @ManagedOperation(description = "Show end point for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name to get end point for")})
    public String getEndpoint(String requestNode) {
        return getServiceManager().getNodeByRequestType(requestNode).getEndpoint();
    }

    @ManagedOperation(description = "Set endpoint for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name"),
            @ManagedOperationParameter(name = "Endpoint", description = "We can set endpoint for particular service")})
    public void setEndpoint(String requestType, String endPoint) {
        getServiceManager().getNodeByRequestType(requestType).setAuthType(endPoint);
    }

    @ManagedOperation(description = "Show delay time for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name to get delay time for")})
    public Long getDelayTimeInMilliseconds(String requestNode) {
        return getServiceManager().getNodeByRequestType(requestNode).getDelayTimeInMilliseconds();
    }

    @ManagedOperation(description = "Set delay time in milliseconds for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Service Name", description = "Service Name"),
            @ManagedOperationParameter(name = "Delay Time", description = "We can set delay time in milliseconds for particular service")})
    public void setDelayTimeInMilliseconds(String requestType, String delayTime) {
        getServiceManager().getNodeByRequestType(requestType).setDelayTimeInMilliseconds(delayTime);
    }

}
