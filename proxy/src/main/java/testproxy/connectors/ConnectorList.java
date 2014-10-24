/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import java.util.List;

public class ConnectorList
{
    private List<Connector> connectors;
    private Connector defaultConnector;

    public List<Connector> getConnectors()
    {
        return connectors;
    }

    public void setConnectors(List<Connector> connectors)
    {
        this.connectors = connectors;
    }

    public Connector getDefaultConnector()
    {
        return defaultConnector;
    }

    public void setDefaultConnector(Connector defaultConnector)
    {
        this.defaultConnector = defaultConnector;
    }

}
