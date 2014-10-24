package testproxy.message.datagrabbers;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testproxy.TestHelper;
import testproxy.beans.ApplicationContextMock;
import testproxy.beans.PassengerName;
//import com.sabre.sabresonic.mockserver.core.beans.*;
import testproxy.httpservletwrappers.GenericRequestWrapperMock;
import testproxy.sessionsinfo.SessionsInfoManager;
import testproxy.utils.SpringBeanContainer;

/**
 * PassengerNameDataGrabberTest
 */
public class PassengerNameDataGrabberTest {
    static final String SABRE_COMMAND_LLS_PASSENGER_NAME_REQUEST =
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header><ns5:MessageHeader xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns5:From><ns5:PartyId>99999</ns5:PartyId></ns5:From><ns5:To><ns5:PartyId>123123</ns5:PartyId></ns5:To><ns5:CPAId>CY</ns5:CPAId><ns5:ConversationId>CYCY_2B14831F2FC2323C43B866149627DCA4</ns5:ConversationId><ns5:Service>SabreCommand</ns5:Service><ns5:Action>SabreCommandLLSRQ</ns5:Action><ns5:MessageData><ns5:MessageId>mid:2012-08-02T14:52:58@eb2.com</ns5:MessageId><ns5:Timestamp>2012-08-02T14:52:58</ns5:Timestamp></ns5:MessageData></ns5:MessageHeader><ns7:Security xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns7:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3893959604573827195!1925531!0</ns7:BinarySecurityToken></ns7:Security></soap:Header><soap:Body><ns4:SabreCommandLLSRQ xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.6.1\"><ns4:Request Output=\"SCREEN\"><ns4:HostCommand>-1THALI/NILESH MR*ADT§</ns4:HostCommand></ns4:Request></ns4:SabreCommandLLSRQ></soap:Body></soap:Envelope>";
    private SessionsInfoManager sessionsInfoManager;
    private String SESSION_ID = "CYCY_2B14831F2FC2323C43B866149627DCA4";;


    @BeforeMethod
    public void setUp() {
        TestHelper.setupConsoleLogging();
        sessionsInfoManager = new SessionsInfoManager();
        sessionsInfoManager.getCount(SESSION_ID, "SabreCommandLLSRQ");
        SpringBeanContainer.setApplicationContext(new ApplicationContextMock().add("sessionsInfoManager", sessionsInfoManager));
    }

    @Test
    public void testGrab() throws Exception {
        new PassengerNameDataGrabber().grab(new GenericRequestWrapperMock(SABRE_COMMAND_LLS_PASSENGER_NAME_REQUEST));
        PassengerName passengerName = sessionsInfoManager.getPassengerName(SESSION_ID);
        assert "THALI".equals(passengerName.last);
        assert "NILESH".equals(passengerName.first);
    }
}
