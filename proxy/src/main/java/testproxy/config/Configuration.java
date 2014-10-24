package testproxy.config;

import com.sabre.ssw.proxy.defines.ProxyMode;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "Configuration parameters for the mock server")
public class Configuration
{
    private String mode;
    private String savePath;
    private String originalHost;
    private boolean generateCreateSession;
    private boolean fixResponse;
    private boolean recordFixedResponses;
    private int connectTimeout;
    private boolean useSharedCache;
    private boolean skipTimeoutResponse;
    private boolean generateFakeResponses;
    private boolean prodLogsEnabled;
    private String prodLogsBaseDir;
    private String prodLogsResponseId;
    private String prodLogsTimeout;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath)
    {
        this.savePath = savePath;
    }

    @ManagedAttribute(description = "Show which mode proxy is running.")
    public String getMode()
    {
        return mode;
    }

    @ManagedAttribute(description = "Set proxy mode to RECORD or REPLAY")
    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public boolean isGenerateCreateSession()
    {
        return generateCreateSession;
    }

    public void setGenerateCreateSession(boolean generateCreateSession)
    {
        this.generateCreateSession = generateCreateSession;
    }

    public boolean isFixResponse()
    {
        return fixResponse;
    }

    public void setFixResponse(boolean fixResponse)
    {
        this.fixResponse = fixResponse;
    }

    public int getConnectTimeout()
    {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }

    public boolean isRecordFixedResponses()
    {
        return recordFixedResponses;
    }

    public void setRecordFixedResponses(boolean recordFixedResponses)
    {
        this.recordFixedResponses = recordFixedResponses;
    }

    @ManagedAttribute(description = "Show which web service url proxy is connected to")
    public String getOriginalHost() {
		return originalHost;
	}

    @ManagedAttribute(description = "Set sabre web services url")
    public void setOriginalHost(String originalHost) {
		this.originalHost = originalHost;
	}

    @ManagedAttribute(description = "Is SharedCache being used?")
    public boolean isUseSharedCache() {
        return useSharedCache;
    }

    @ManagedAttribute(description = "set whether to use shared cache or not")
    public void setUseSharedCache(boolean useSharedCache) {
        this.useSharedCache = useSharedCache;
    }

    @ManagedAttribute(description = "Is timeouts will not saved as pattern?")
    public boolean isSkipTimeoutResponse() {
        return this.skipTimeoutResponse;
    }

    @ManagedAttribute(description = "Skip saving timeouts as pattern?")
    public void setSkipTimeouts(boolean skipTimeoutResponse) {
        this.skipTimeoutResponse = skipTimeoutResponse;
    }
    
    @ManagedAttribute
    public void setGenerateFakeResponses(boolean generateFakeResponses) {
        this.generateFakeResponses = generateFakeResponses;
    }
    
    @ManagedAttribute
    public boolean isGenerateFakeResponses() {
        return generateFakeResponses;
    }

    public String getProdLogsBaseDir() {
        return prodLogsBaseDir;
    }

    public void setProdLogsBaseDir(String prodLogsBaseDir) {
        this.prodLogsBaseDir = prodLogsBaseDir;
    }

    public boolean isProdLogsEnabled() {
        return prodLogsEnabled;
    }

    public void setProdLogsEnabled(boolean prodLogsEnabled) {
        this.prodLogsEnabled = prodLogsEnabled;
    }

    public String getProdLogsResponseId() {
        return prodLogsResponseId;
    }

    public void setProdLogsResponseId(String prodLogsResponseId) {
        this.prodLogsResponseId = prodLogsResponseId;
    }

    public String getProdLogsTimeout() {
        return prodLogsTimeout;
    }

    public void setProdLogsTimeout(String prodLogsTimeout) {
        this.prodLogsTimeout = prodLogsTimeout;
    }
    
    
}