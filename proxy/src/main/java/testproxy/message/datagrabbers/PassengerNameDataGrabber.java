package testproxy.message.datagrabbers;

//import com.sabre.sabresonic.mockserver.core.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testproxy.beans.HostCommand;
import testproxy.beans.PassengerNameCommand;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.utils.SpringBeanContainer;

/**
 * PassengerNameDataGrabber
 */
public class PassengerNameDataGrabber extends SabreCommandLLSDataGrabber {
    private static final Logger logger = LoggerFactory.getLogger(PassengerNameDataGrabber.class);

    @Override
    public void grab(GenericRequestWrapper request) {
        HostCommand hostCommand = request.getHostCommand();
        if ( hostCommand.isPassengerNameCommand()) {
            String sessionId = request.getConversationId();
            SpringBeanContainer.getSessionsInfoManager().addPassengerName(sessionId, ((PassengerNameCommand) hostCommand).getPassengerName());
            logger.info("Received and stored passenger name: [" + sessionId + ":" + ((PassengerNameCommand) hostCommand).getPassengerName());
        }
    }
}
