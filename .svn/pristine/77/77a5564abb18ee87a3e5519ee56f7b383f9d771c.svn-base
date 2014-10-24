package testproxy.logs2mock;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testproxy.config.Configuration;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.httpservletwrappers.GenericResponseWrapper;

/**
 *
 * @author SG0218182
 */
public class LogReader {

    private static final Logger LOG = LoggerFactory.getLogger(LogReader.class);
    public static final String DEFAULT_BASE_DIR = "prodlogs";
    private final Configuration configuration;
    private final GenericRequestWrapper request;
    private final GenericResponseWrapper response;

    public LogReader(final Configuration configuration, final GenericRequestWrapper req, final GenericResponseWrapper resp) {
        this.configuration = configuration;
        this.request = req;
        this.response = resp;
    }

    public byte[] read() {
        byte[] result = null;
        String fileId = getFileId();
        if (fileId != null) {
            try {
                result = readFile(fileId);
                if (result != null) {
                    response.setContentType("application/soap+xml;charset=utf-8");
                    response.putHeader("Content-Length", String.valueOf(result.length));
                    response.setCharacterEncoding("UTF-8");
                }
            } catch (IOException ex) {
                LOG.warn("", ex);
            }
        }
        simulateTimeout();
        return result;
    }

    protected byte[] readFile(String fileId) throws IOException {
        String path = FilenameUtils.concat(getBaseDir(), fileId + ".response");
        File file = new File(path);
        if (file.exists()) {
            return FileUtils.readFileToByteArray(file);
        }
        LOG.warn("File not found: " + file.getAbsolutePath());
        return null;
    }

    protected String getFileId() {
        return getParameterValue(configuration.getProdLogsResponseId());
    }

    protected void simulateTimeout() {
        String stringValue = getParameterValue(configuration.getProdLogsTimeout());
        if (stringValue != null) {
            try {
                long timeout = Long.parseLong(stringValue);
                Thread.sleep(timeout);
            } catch (Exception ex) {
                LOG.warn("", ex);
            }
        }
    }

    protected String getBaseDir() {
        String baseDir = configuration.getProdLogsBaseDir();
        if (StringUtils.isBlank(baseDir)) {
            baseDir = FilenameUtils.concat(configuration.getSavePath(), DEFAULT_BASE_DIR);
        }
        return baseDir;
    }

    protected String getParameterValue(String name) {
        String value = request.getParameter(name);
        if (value == null) {
            value = request.getHeader(name);
        }
        return value;
    }

}
