/* Copyright 2009 EB2 International Limited */
package testproxy.servicemanagment;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager
{
    private List<ServiceNode> servicelist = new ArrayList<ServiceNode>();

    public List<ServiceNode> getServicelist()
    {
        return servicelist;
    }

    public ServiceNode getNodeByRequestType(String requestType)
    {
        for (ServiceNode node : servicelist)
        {
            if (node.getRequestType().equals(requestType))
                return node;
        }
        throw new RuntimeException("Node for request: " + requestType + " was not found!");
    }
}
