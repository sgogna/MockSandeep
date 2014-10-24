package testproxy.message.replacers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testproxy.TestHelper;
import testproxy.utils.SpringBeanContainer;

/**
 * IA_DepartureDatesReplacerTest
 */
public class IA_DepartureDatesReplacerTest {
    static final String IA_RESPONSE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap-env:Header><eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\"><eb:From><eb:PartyId eb:type=\"URI\">123123</eb:PartyId></eb:From><eb:To><eb:PartyId eb:type=\"URI\">99999</eb:PartyId></eb:To><eb:CPAId>2P</eb:CPAId><eb:ConversationId>A3OE_D12B234FD110F2C4901D6664C149C202</eb:ConversationId><eb:Service>SabreCommand</eb:Service><eb:Action>SabreCommandLLSRS</eb:Action><eb:MessageData><eb:MessageId>1977c57f-0795-4eb0-ba42-8c8c03d113ed@32</eb:MessageId><eb:Timestamp>2012-08-02T19:27:22</eb:Timestamp><eb:RefToMessageId>mid:2012-08-02T19:27:22@eb2.com</eb:RefToMessageId></eb:MessageData></eb:MessageHeader><wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\"><wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3893965734719988479!1555162!0</wsse:BinarySecurityToken></wsse:Security></soap-env:Header><soap-env:Body><SabreCommandLLSRS xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" EchoToken=\"String\" TimeStamp=\"2012-08-02T19:27:22\" Target=\"Production\" Version=\"2003A.TsabreXML1.6.1\" SequenceNmbr=\"1\" PrimaryLangID=\"en-us\" AltLangID=\"en-us\">\n" +
                    " <Response><![CDATA[ 1 2P 737E 09AUG Q CRKSIN SS1   815P 1150P /E]]><![CDATA[\n" +
                    " 2 2P 738T 21SEP F SINCRK SS1  1220A  400A /E]]></Response>\n" +
                    "</SabreCommandLLSRS></soap-env:Body></soap-env:Envelope>";
    static final String IA_EXPECTED_MODIFIED_RESPONSE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap-env:Header><eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\"><eb:From><eb:PartyId eb:type=\"URI\">123123</eb:PartyId></eb:From><eb:To><eb:PartyId eb:type=\"URI\">99999</eb:PartyId></eb:To><eb:CPAId>2P</eb:CPAId><eb:ConversationId>A3OE_D12B234FD110F2C4901D6664C149C202</eb:ConversationId><eb:Service>SabreCommand</eb:Service><eb:Action>SabreCommandLLSRS</eb:Action><eb:MessageData><eb:MessageId>1977c57f-0795-4eb0-ba42-8c8c03d113ed@32</eb:MessageId><eb:Timestamp>2012-08-02T19:27:22</eb:Timestamp><eb:RefToMessageId>mid:2012-08-02T19:27:22@eb2.com</eb:RefToMessageId></eb:MessageData></eb:MessageHeader><wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\"><wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3893965734719988479!1555162!0</wsse:BinarySecurityToken></wsse:Security></soap-env:Header><soap-env:Body><SabreCommandLLSRS xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" EchoToken=\"String\" TimeStamp=\"2012-08-02T19:27:22\" Target=\"Production\" Version=\"2003A.TsabreXML1.6.1\" SequenceNmbr=\"1\" PrimaryLangID=\"en-us\" AltLangID=\"en-us\">\n" +
                    " <Response><![CDATA[ 1 2P 737E 02SEP Q CRKSIN SS1   815P 1150P /E]]><![CDATA[\n" +
                    " 2 2P 738T 17OCT F SINCRK SS1  1220A  400A /E]]></Response>\n" +
                    "</SabreCommandLLSRS></soap-env:Body></soap-env:Envelope>";
    private static final String SESSION_ID = "A3OE_D12B234FD110F2C4901D6664C149C202";

    @BeforeMethod
    public void setUp() throws Exception {
        TestHelper.setupConsoleLogging();
        TestHelper.setupSessionsInfoManager(SESSION_ID, "OTA_AirBookLLS");
    }

    @Test
    public void testReplace() throws Exception {
        IA_DepartureDatesReplacer originDestinationReplacer = new IA_DepartureDatesReplacer();
        SpringBeanContainer.getSessionsInfoManager().setDepartureDates(SESSION_ID, new String[]{"02SEP", "17OCT"});
        String modifiedResponse = originDestinationReplacer.replace(SESSION_ID, IA_RESPONSE);
        assert IA_EXPECTED_MODIFIED_RESPONSE.equals(modifiedResponse);
        assert !modifiedResponse.contains("09AUG");
        assert !modifiedResponse.contains("21SEP");
        assert modifiedResponse.contains("02SEP");
        assert modifiedResponse.contains("17OCT");
    }
}
