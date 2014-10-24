package testproxy.utils;

import junit.framework.TestCase;

/**
 * HostCommandUtilsTest
 */
public class HostCommandUtilsTest extends TestCase {

    public void testGetHostCommandForHostCommandEntry() throws Exception {
        String requestStr = ("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "\t<SOAP-ENV:Header>\n" +
                "\t\t<eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\">\n" +
                "\t\t\t<eb:From>\n" +
                "\t\t\t\t<eb:PartyId eb:type=\"URI\">usgtestclient.sabre.com</eb:PartyId>\n" +
                "\t\t\t</eb:From>\n" +
                "\t\t\t<eb:To>\n" +
                "\t\t\t\t<eb:PartyId eb:type=\"URI\">webservices.sabre.com</eb:PartyId>\n" +
                "\t\t\t</eb:To>\n" +
                "\t\t\t<eb:CPAId>YV</eb:CPAId>\n" +
                "\t\t\t<eb:ConversationId>123456@usgperftesting.sabre.com</eb:ConversationId>\n" +
                "\t\t\t<eb:Service eb:type=\"\">Test</eb:Service>\n" +
                "\t\t\t<eb:Action>SabreCommandLLSRQ</eb:Action>\n" +
                "\t\t\t<eb:MessageData>\n" +
                "\t\t\t\t<eb:MessageId>cc716a50-eca4-40d1-9226-d99408a26e35</eb:MessageId>\n" +
                "\t\t\t\t<eb:Timestamp>2012-07-16T18:53:01</eb:Timestamp>\n" +
                "\t\t\t</eb:MessageData>\n" +
                "\t\t</eb:MessageHeader>\n" +
                "\t\t<wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">\n" +
                "\t\t\t<wsse:BinarySecurityToken EncodingType=\"wsse:Base64Binary\" valueType=\"String\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3899992360602755196!1644474!0</wsse:BinarySecurityToken>\n" +
                "\t\t</wsse:Security>\n" +
                "\t</SOAP-ENV:Header>\n" +
                "\t<SOAP-ENV:Body>\n" +
                "\t\t<SabreCommandLLSRQ xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.3.1\">\n" +
                "\t\t\t<HostCommand>4G1/2A-1.1</HostCommand>\n" +
                "\t\t</SabreCommandLLSRQ>\n" +
                "\t</SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>");
        assertEquals( "4G12A-1.1", HostCommandUtils.getHostCommand(requestStr).sanitized() );
    }

    public void testGetHostCommandForRequestEntry() throws Exception {
        final String requestStr = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "\t<SOAP-ENV:Header>\n" +
                "\t\t<eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\">\n" +
                "\t\t\t<eb:From>\n" +
                "\t\t\t\t<eb:PartyId eb:type=\"URI\">usgtestclient.sabre.com</eb:PartyId>\n" +
                "\t\t\t</eb:From>\n" +
                "\t\t\t<eb:To>\n" +
                "\t\t\t\t<eb:PartyId eb:type=\"URI\">webservices.sabre.com</eb:PartyId>\n" +
                "\t\t\t</eb:To>\n" +
                "\t\t\t<eb:CPAId>YV</eb:CPAId>\n" +
                "\t\t\t<eb:ConversationId>123456@usgperftesting.sabre.com</eb:ConversationId>\n" +
                "\t\t\t<eb:Service eb:type=\"\">Test</eb:Service>\n" +
                "\t\t\t<eb:Action>SabreCommandLLSRQ</eb:Action>\n" +
                "\t\t\t<eb:MessageData>\n" +
                "\t\t\t\t<eb:MessageId>cc716a50-eca4-40d1-9226-d99408a26e35</eb:MessageId>\n" +
                "\t\t\t\t<eb:Timestamp>2012-07-16T18:53:01</eb:Timestamp>\n" +
                "\t\t\t</eb:MessageData>\n" +
                "\t\t</eb:MessageHeader>\n" +
                "\t\t<wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">\n" +
                "\t\t\t<wsse:BinarySecurityToken EncodingType=\"wsse:Base64Binary\" valueType=\"String\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3899992360602755196!1644474!0</wsse:BinarySecurityToken>\n" +
                "\t\t</wsse:Security>\n" +
                "\t</SOAP-ENV:Header>\n" +
                "\t<SOAP-ENV:Body>\n" +
                "\t\t<SabreCommandLLSRQ xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.3.1\">\n" +
                "\t\t\t<Request>4G1/2A-1.1</Request>\n" +
                "\t\t</SabreCommandLLSRQ>\n" +
                "\t</SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";
        assertEquals("4G12A-1.1", HostCommandUtils.getHostCommand(requestStr).sanitized() );
    }

    public void testGetHostCommand() throws Exception {
        assertEquals( "WPMEUR", HostCommandUtils.getHostCommand("<ns10:HostCommand>WPMEUR<U+0087>P1ADT</ns10:HostCommand>").sanitized());
    }
}
