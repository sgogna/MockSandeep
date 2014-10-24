package testproxy.utils;

import testproxy.beans.HostCommand;
import testproxy.beans.HostCommandFactory;

//import com.sabre.sabresonic.mockserver.core.beans.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HostCommandUtils
 * @deprecated Use HostCommandFactory directly
 */
public class HostCommandUtils {

    public static HostCommand getHostCommand(String requestContent) {
        return HostCommandFactory.create(requestContent);
    }
}
