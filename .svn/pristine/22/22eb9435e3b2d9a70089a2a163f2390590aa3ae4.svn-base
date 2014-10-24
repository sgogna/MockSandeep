/* Copyright 2009 EB2 International Limited */
package testproxy.servicemanagment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceNode
{
    private static final Logger LOG = LoggerFactory.getLogger(ServiceNode.class);
    private String requestType;
    private String endpoint;
    private String regex;
    private long delayTimeInMilliseconds = 0l;
    private int delayTimeInSeconds = 0;
    private String authType;
    private String username;
    private String password;
    private String mode;



    public ServiceNode()
    {
    }

    public ServiceNode(String requestType, String endpoint)
    {
        this.requestType = requestType;
        this.endpoint = endpoint;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public String getEndpoint()
    {
        return endpoint;
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }

 	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

 	public int getDelayTimeInSeconds() {
		return this.delayTimeInSeconds;
	}
        
 	protected void setDelayTimeInSeconds(String delayTimeInSeconds) {
            try {
                this.delayTimeInSeconds = Integer.parseInt(delayTimeInSeconds);
            } catch (NumberFormatException e) {
                LOG.warn("", e);
                this.delayTimeInSeconds = 0;
            }
	}
        
 	public long getDelayTimeInMilliseconds() {
            if(delayTimeInMilliseconds == 0){
                return getDelayTimeInSeconds()*1000;
            }
            return delayTimeInMilliseconds;
	}

	public void setDelayTimeInMilliseconds(String delayTimeInMilliseconds) {
            try {
                this.delayTimeInMilliseconds = Long.parseLong(delayTimeInMilliseconds);
            } catch (NumberFormatException e) {
                LOG.warn("", e);
                this.delayTimeInMilliseconds = 0l;
            }
	}

    public String getAuthType() {
        return authType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthType(String authType)
    {
        this.authType = authType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getServiceMode() {
        return mode;
    }
}
