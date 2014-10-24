package testproxy.utils;

import java.util.Random;

public class CreateSessionResponseCreator
{
    private static final String SESSION_CREATE_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap-env:Envelope "
            +
            "xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            +
            "<soap-env:Header><eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" "
            +
            "soap-env:mustUnderstand=\"1\"><eb:From><eb:PartyId eb:type=\"URI\">webservices.sabre.com</eb:PartyId></eb:From><eb:To>"
            +
            "<eb:PartyId eb:type=\"URI\">3509</eb:PartyId></eb:To><eb:CPAId>9W</eb:CPAId><eb:ConversationId>TEST_SESSION</eb:ConversationId>"
            +
            "<eb:Service eb:type=\"sabreXML\">Session</eb:Service><eb:Action>SessionCreateRS</eb:Action><eb:MessageData><eb:MessageId>"
            +
            "c1079f2a-34ea-4059-b11d-dc9c3f77a100@45</eb:MessageId><eb:Timestamp>2009-12-07T11:08:52</eb:Timestamp>"
            +
            "<eb:RefToMessageId>mid:20001209-133003-2333@clientofsabre.com</eb:RefToMessageId></eb:MessageData></eb:MessageHeader>"
            +
            "<wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">"
            +
            "<wsse:BinarySecurityToken EncodingType=\"wsse:Base64Binary\" valueType=\"String\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-~|~random~|~!1518410!0</wsse:BinarySecurityToken>"
            +
            "</wsse:Security></soap-env:Header><soap-env:Body><SessionCreateRS xmlns=\"http://www.opentravel.org/OTA/2002/11\" status=\"Approved\" version=\"1\">\t" +
            "<ConversationId>TEST_SESSION</ConversationId></SessionCreateRS></soap-env:Body></soap-env:Envelope>";

    private static final String SESSION_CLOSE_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            +
            "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            +
            "<soap-env:Header>"
            +
            "  <eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\">"
            +
            "   <eb:From>"
            +
            "    <eb:PartyId eb:type=\"URI\">123123</eb:PartyId>"
            +
            " </eb:From>"
            +
            "<eb:To>"
            +
            " <eb:PartyId eb:type=\"URI\">99999</eb:PartyId>"
            +
            "      </eb:To>"
            +
            "     <eb:CPAId>B6</eb:CPAId>"
            +
            "    <eb:ConversationId>TEST_SESSION</eb:ConversationId>"
            +
            "   <eb:Service eb:type=\"sabreXML\">Session</eb:Service>"
            +
            "  <eb:Action>SessionCloseRS</eb:Action>"
            +
            " <eb:MessageData>"
            +
            "  <eb:MessageId>b4a10bb6-e098-4283-a2bd-f1ff42cc7692@32</eb:MessageId>"
            +
            " <eb:Timestamp>2011-07-15T12:19:48</eb:Timestamp>"
            +
            "<eb:RefToMessageId>mid:2011-07-15T14:19:38@eb2.com</eb:RefToMessageId>"
            +
            "      </eb:MessageData>"
            +
            "   </eb:MessageHeader>"
            +
            "  <wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">"
            +
            "   <wsse:BinarySecurityToken EncodingType=\"wsse:Base64Binary\" valueType=\"String\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-4029969211149237504!1727562!0</wsse:BinarySecurityToken>"
            +
            "</wsse:Security>" +
            "  </soap-env:Header>" +
            " <soap-env:Body>" +
            "  <SessionCloseRS xmlns=\"http://www.opentravel.org/OTA/2002/11\" status=\"Approved\" version=\"1\" />" +
            "  </soap-env:Body>" +
            "</soap-env:Envelope>";

    public String getSessionCreateMessage()
    {

        Random generator = new Random();
        long randomLong = generator.nextLong();
        String response = SESSION_CREATE_RESPONSE.replaceAll("~\\|~random~\\|~", randomLong + "");
        return response;

    }

    public String getSessionCloseResponse()
    {
        return SESSION_CLOSE_RESPONSE;
    }
}
