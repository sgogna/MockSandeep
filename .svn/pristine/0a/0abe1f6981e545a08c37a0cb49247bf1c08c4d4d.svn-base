/* Copyright 2009 EB2 International Limited */
package testproxy.sessionsinfo;

import com.sabre.ssw.proxy.defines.ProxyMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.*;
import testproxy.beans.*;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@ManagedResource(description = "Set Session parameters for exclusive Mode for the mock server")
public class SessionsInfoManager
{
    private static final Logger LOG = LoggerFactory.getLogger(SessionsInfoManager.class);
    private static final SessionInfo NULL_SESSION_INFO = new SessionInfo() {
        @Override
        public void setPassengerName(PassengerName passengerName) { }
        @Override
        public void setOriginDestinations(String[] originDestinations) { }
    };
    /**
     * Map counting requests with the same name
     */
    private Map<String, SessionInfo> sessionCounterMap = new HashMap<String, SessionInfo>();
    private HashMap<String, Long> sessionLastUseMap = new HashMap<String, Long>();
    private int deleteOlderThan;

    public synchronized int getCount(String session, String request)
    {
        setCurrentTime(session);
        if (!sessionCounterMap.containsKey(session))
        {
            sessionCounterMap.put(session, new SessionInfo());
        }
        return sessionCounterMap.get(session).getNumber(request);
    }

    public void clearCount(String session, String request)
    {
         sessionCounterMap.get(session).clearMap(request);
    }

    private synchronized void setCurrentTime(String session)
    {

        sessionLastUseMap.put(session, System.currentTimeMillis());
    }

    public synchronized void cleanUpMap()
    {
        int count = 0;
        long currentTime = System.currentTimeMillis();

        Iterator<Entry<String, Long>> it = sessionLastUseMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String, Long> pairs = it.next();
            if (currentTime - pairs.getValue() > deleteOlderThan)
            {
                it.remove();
                sessionCounterMap.remove(pairs.getKey());
                count++;
            }
        }
        LOG.info("MapCleanUpTimer: Deleted " + count + " record(s). Left " + sessionCounterMap.size() + " record(s).");
    }

    // Used only via JMX
    @ManagedOperation(description = "Clean up all sessions")
    public synchronized void cleanUpSessions()
    {
        int count = 0;
        Iterator<Entry<String, SessionInfo>> it = sessionCounterMap.entrySet().iterator();
        while (it.hasNext())
        {
                Map.Entry<String, SessionInfo> pairs = it.next();
                it.remove();
                sessionCounterMap.remove(pairs.getKey());
                count++;
        }
        LOG.info("SessionCleanUpJMX: Deleted " + count + " record(s). Left " + sessionCounterMap.size() + " record(s).");
    }

    public int getDeleteOlderThan()
    {
        return deleteOlderThan;
    }

    public void setDeleteOlderThan(int deleteOlderThan)
    {
        this.deleteOlderThan = deleteOlderThan;
    }

   public synchronized ProxyMode getProxyModeForSession(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            return sessionCounter.getMode();
        }
        return null;
    }

    // this method is used by JMX bean and proxyhttpServlet.java
    @ManagedOperation(description = "Show regular expression for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Session Id", description = "Get the proxy mode for particular session id")})
    public synchronized String getProxyModeForSessions(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            return sessionCounter.getMode().toString();
        }
        return null;
    }

    // This method is only called via JMX bean
    @ManagedOperation(description = "Show regular expression for particular service.")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "Session Id", description = "Session Id or Conversation Id for which exclusive mode needs to be set"),
            @ManagedOperationParameter(name = "Proxy Mode", description = "REPLAY, RECORD or TRANSPARENT Mode can be set")})
    public synchronized void setProxyModeForSession(String session, String mode)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter == null)
        {
            sessionCounter = new SessionInfo();
            sessionCounterMap.put(session, sessionCounter);
        }
        sessionCounter.setMode(ProxyMode.valueOf(mode));
        LOG.info("JMX method called to : Set " + session + " SessionId to run in " + ProxyMode.valueOf(mode) + " MODE.");

    }

    public synchronized String getSuffixForSession(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter == null)
        {
            sessionCounterMap.put(session, new SessionInfo());
        }
        return sessionCounterMap.get(session).getSuffix();
    }

    public synchronized void setSuffixForSession(String session, String suffix)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            sessionCounter.setSuffix(suffix);
        }
    }

    public synchronized String getCreateTestTime(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            return sessionCounter.getCreateDate();
        }
        return "";
    }

    public synchronized void setCreateTestTime(String session, String date)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            sessionCounter.setCreateDate(date);
        }
    }
    
    public synchronized long getDiffTime(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            return sessionCounter.getTimeDiff();
        }
        return 0;
    }
    
    public synchronized void setDiffTime(String session, long diff)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            sessionCounter.setTimeDiff(diff);
        }
    }

    public synchronized void setPath(String session, String path)
    { 
//        System.out.println("Setting path: " + path);
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            sessionCounter.setPath(path);
//            System.out.println("path set");
        }
    }

    public synchronized String getPath(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            return sessionCounter.getPath();
        }
        return "";
    }
    
    public synchronized String getTestName(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            File f = new File(getPath(session));
            String suffixedName = f.getName();
            String suffix = getSuffixForSession(session);
            if (suffix.isEmpty())
                return suffixedName;
            else
                return suffixedName.replace(suffix, "");            
        }
        return "";
    }
    
    public synchronized String getAirline(String session)
    {
        SessionInfo sessionCounter = sessionCounterMap.get(session);
        if (sessionCounter != null)
        {
            File f = new File(getPath(session));
            
            File air = f.getParentFile().getParentFile();                        
            return  air.getName().substring(air.getName().indexOf("_") + 1);
        }
        return "";
    }


    public void addPassengerName(String sessionId, PassengerName passengerName) {
        SessionInfo sessionInfo = sessionCounterMap.get(sessionId);
        if ( sessionInfo == null ) {
            LOG.warn("Tried to add passenger name, but no session info object!!");
            return;
        }
        sessionInfo.setPassengerName(passengerName);
    }

    public PassengerName getPassengerName(String sessionId) {
        return getSession(sessionId).getPassengerName();
    }



    private SessionInfo getSession(String sessionId) {
        if ( sessionId == null ) {
            LOG.warn("Session id is null. Returning dummy object");
            return NULL_SESSION_INFO;
        }
        SessionInfo sessionInfo = sessionCounterMap.get(sessionId);
        if ( sessionInfo == null ) {
            LOG.warn("No session object associated with " + sessionId );
            return NULL_SESSION_INFO;
        }
        return sessionInfo;
    }

    public String[] getOriginDestinations(String sessionId) {
        return getSession(sessionId).getOriginDestinations();
    }


    public void setDepartureDates(String sessionId, String[] originDestinations) {
        getSession(sessionId).setOriginDestinations(originDestinations);
    }



    public void setVICounter(String sessionId) {
        SessionInfo sessionCounter = sessionCounterMap.get(sessionId);
        if (sessionCounter == null)
        {
            sessionCounter = new SessionInfo();
            sessionCounterMap.put(sessionId, sessionCounter);

        }
    }



    public void setExchangeStarted(String sessionId, boolean exchangeStarted) {
        SessionInfo sessionCounter = sessionCounterMap.get(sessionId);
        if (sessionCounter == null)
        {
            sessionCounter = new SessionInfo();
            sessionCounterMap.put(sessionId, sessionCounter);

        }
            sessionCounter.setExchangeStarted(exchangeStarted);
    }



    public boolean getExchangeStarted(String sessionId) {
        SessionInfo sessionCounter = sessionCounterMap.get(sessionId);
        if (sessionCounter != null)
        {
            return sessionCounter.getExchangeStarted();
        }
        return false;
    }

    public void setServiceCounter(String sessionId, int num) {
    SessionInfo sessionCounter = sessionCounterMap.get(sessionId);
    if (sessionCounter == null)
    {
        sessionCounter = new SessionInfo();
        sessionCounterMap.put(sessionId, sessionCounter);

    }
    sessionCounter.setServiceCounter(num);
}


    public int getServiceCounter(String sessionId) {
        SessionInfo sessionCounter = sessionCounterMap.get(sessionId);
        if (sessionCounter != null)
        {
            return sessionCounter.getServiceCounter();
        }
        return 0;
    }
}
