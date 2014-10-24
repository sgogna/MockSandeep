package testproxy.beans;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import com.sabre.sabresonic.mockserver.core.beans.*;
/**
 * HostCommandFactoryTest
 */
public class HostCommandFactoryTest {
    private static final String SABRE_COMMAND_LLS_PREFIX =
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "\t<soap:Header>\n" +
                    "\t\t<ns5:MessageHeader xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\">\n" +
                    "\t\t\t<ns5:From>\n" +
                    "\t\t\t\t<ns5:PartyId>99999</ns5:PartyId>\n" +
                    "\t\t\t</ns5:From>\n" +
                    "\t\t\t<ns5:To>\n" +
                    "\t\t\t\t<ns5:PartyId>123123</ns5:PartyId>\n" +
                    "\t\t\t</ns5:To>\n" +
                    "\t\t\t<ns5:CPAId>MN</ns5:CPAId>\n" +
                    "\t\t\t<ns5:ConversationId>E6IE_F3E8C6F863A8C8B0DEB0C97D7E0301AE</ns5:ConversationId>\n" +
                    "\t\t\t<ns5:Service>SabreCommand</ns5:Service>\n" +
                    "\t\t\t<ns5:Action>SabreCommandLLSRQ</ns5:Action>\n" +
                    "\t\t\t<ns5:MessageData>\n" +
                    "\t\t\t\t<ns5:MessageId>mid:2012-08-10T18:47:35@eb2.com</ns5:MessageId>\n" +
                    "\t\t\t\t<ns5:Timestamp>2012-08-10T18:47:35</ns5:Timestamp>\n" +
                    "\t\t\t</ns5:MessageData>\n" +
                    "\t\t</ns5:MessageHeader>\n" +
                    "\t\t<ns7:Security xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\">\n" +
                    "\t\t\t<ns7:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3891070541832470272!503498!0</ns7:BinarySecurityToken>\n" +
                    "\t\t</ns7:Security>\n" +
                    "\t</soap:Header>\n" +
                    "\t<soap:Body>\n" +
                    "\t\t<ns4:SabreCommandLLSRQ xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.6.1\">\n" +
                    "\t\t\t<ns4:Request Output=\"SCREEN\">\n" +
                    "\t\t\t\t<ns4:HostCommand>";
    private static final String SABRE_COMMAND_LLS_SUFFIX =
            "</ns4:HostCommand>\n" +
                    "\t\t\t</ns4:Request>\n" +
                    "\t\t</ns4:SabreCommandLLSRQ>\n" +
                    "\t</soap:Body>\n" +
                    "</soap:Envelope>";

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {
        assert HostCommandFactory.create(SABRE_COMMAND_LLS_PREFIX + "-1YXJNGTJFXF/YXJNGTJFXF MR*ADT§" + SABRE_COMMAND_LLS_SUFFIX) instanceof PassengerNameCommand;
        assert HostCommandFactory.create(SABRE_COMMAND_LLS_PREFIX + "5-TBM*AX3782100113571009�05/99" + SABRE_COMMAND_LLS_SUFFIX) instanceof FOPEntryCommand;
    }
}
