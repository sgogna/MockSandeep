/* Copyright 2009 EB2 International Limited */
package testproxy.connectors;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyAuthenticator extends Authenticator
{
    private static final Logger LOG = LoggerFactory.getLogger(ProxyAuthenticator.class);

    public PasswordAuthentication getPasswordAuthentication()
    {

        String proxyUser = System.getProperty("http.proxyUser");
        String proxyPassword = System.getProperty("http.proxyPassword");
        LOG.info("Autentication using username: " + proxyUser + " and password: " + generatePassword(proxyPassword.length()) + "(" + proxyPassword.length() + ")");
        // + " for " + this.getRequestingHost() + "; " + getRequestingScheme() +
        // "");
        return new PasswordAuthentication(proxyUser, proxyPassword.toCharArray());
    }

    private String generatePassword(int length)
    {
        String s = "";
        for (int i = 0; i < length; i++)
        {
            s += "*";
        }
        return s;
    }

}
