package testproxy;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import testproxy.beans.ApplicationContextMock;
import testproxy.sessionsinfo.SessionsInfoManager;
import testproxy.utils.SpringBeanContainer;

import java.io.PrintWriter;

/**
 * testproxy.TestHelper
 */
public class TestHelper {
    public static final String TRAVEL_ITIN_READ_REQUEST =
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "\t<soap:Header>\n" +
                    "\t\t<ns4:MessageHeader xmlns:ns14=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns11=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns10=\"http://services.sabre.com/STL/v01\" xmlns:ns9=\"http://www.w3.org/1999/xlink\" xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns5=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns4=\"http://www.ebxml.org/namespaces/messageHeader\">\n" +
                    "\t\t\t<ns4:From>\n" +
                    "\t\t\t\t<ns4:PartyId>99999</ns4:PartyId>\n" +
                    "\t\t\t</ns4:From>\n" +
                    "\t\t\t<ns4:To>\n" +
                    "\t\t\t\t<ns4:PartyId>123123</ns4:PartyId>\n" +
                    "\t\t\t</ns4:To>\n" +
                    "\t\t\t<ns4:CPAId>F7</ns4:CPAId>\n" +
                    "\t\t\t<ns4:ConversationId>F7F7_5D78A3E61207FB986DFA73C3D16C0010</ns4:ConversationId>\n" +
                    "\t\t\t<ns4:Service>TravelItineraryReadLLSRQ</ns4:Service>\n" +
                    "\t\t\t<ns4:Action>TravelItineraryReadLLSRQ</ns4:Action>\n" +
                    "\t\t\t<ns4:MessageData>\n" +
                    "\t\t\t\t<ns4:MessageId>mid:2012-06-11T05:49:05@eb2.com</ns4:MessageId>\n" +
                    "\t\t\t\t<ns4:Timestamp>2012-06-11T05:49:05</ns4:Timestamp>\n" +
                    "\t\t\t</ns4:MessageData>\n" +
                    "\t\t</ns4:MessageHeader>\n" +
                    "\t\t<ns7:Security xmlns:ns14=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns11=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns10=\"http://services.sabre.com/STL/v01\" xmlns:ns9=\"http://www.w3.org/1999/xlink\" xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns5=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns4=\"http://www.ebxml.org/namespaces/messageHeader\">\n" +
                    "\t\t\t<ns7:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/STSB!ICESMSLB\\/STS.LB!-391249040359481087!1023785!0</ns7:BinarySecurityToken>\n" +
                    "\t\t</ns7:Security>\n" +
                    "\t</soap:Header>\n" +
                    "\t<soap:Body>\n" +
                    "\t\t<ns11:TravelItineraryReadRQ xmlns:ns14=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns11=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns10=\"http://services.sabre.com/STL/v01\" xmlns:ns9=\"http://www.w3.org/1999/xlink\" xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns5=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns4=\"http://www.ebxml.org/namespaces/messageHeader\" ReturnHostCommand=\"true\" Version=\"2.0.0\">\n" +
                    "\t\t\t<ns11:MessagingDetails>\n" +
                    "\t\t\t\t<ns11:Transaction Code=\"PNR\"/>\n" +
                    "\t\t\t</ns11:MessagingDetails>\n" +
                    "\t\t\t<ns11:UniqueID ID=\"KTXLLD\"/>\n" +
                    "\t\t</ns11:TravelItineraryReadRQ>\n" +
                    "\t</soap:Body>\n" +
                    "</soap:Envelope>";

    public static final String SABRE_COMMAND_LLS_4DOCS_REQUEST =
                            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header><ns5:MessageHeader xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns5:From><ns5:PartyId>99999</ns5:PartyId></ns5:From><ns5:To><ns5:PartyId>123123</ns5:PartyId></ns5:To><ns5:CPAId>CY</ns5:CPAId><ns5:ConversationId>CYCY_2B14831F2FC2323C43B866149627DCA4</ns5:ConversationId><ns5:Service>SabreCommand</ns5:Service><ns5:Action>SabreCommandLLSRQ</ns5:Action><ns5:MessageData><ns5:MessageId>mid:2012-08-02T14:52:58@eb2.com</ns5:MessageId><ns5:Timestamp>2012-08-02T14:52:58</ns5:Timestamp></ns5:MessageData></ns5:MessageHeader><ns7:Security xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns7:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3893959604573827195!1925531!0</ns7:BinarySecurityToken></ns7:Security></soap:Header><soap:Body><ns4:SabreCommandLLSRQ xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.6.1\"><ns4:Request Output=\"SCREEN\"><ns4:HostCommand>4DOCS/DB/01JAN80/M/THALI/NILESH-1.1</ns4:HostCommand></ns4:Request></ns4:SabreCommandLLSRQ></soap:Body></soap:Envelope>";

    public static final String SABRE_COMMAND_LLS_IA_REQUEST =
                            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header><ns5:MessageHeader xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns5:From><ns5:PartyId>99999</ns5:PartyId></ns5:From><ns5:To><ns5:PartyId>123123</ns5:PartyId></ns5:To><ns5:CPAId>CY</ns5:CPAId><ns5:ConversationId>A3OE_951658ACFB3489F0420482DB0DCDF9AF</ns5:ConversationId><ns5:Service>SabreCommand</ns5:Service><ns5:Action>SabreCommandLLSRQ</ns5:Action><ns5:MessageData><ns5:MessageId>mid:2012-08-02T14:52:58@eb2.com</ns5:MessageId><ns5:Timestamp>2012-08-02T14:52:58</ns5:Timestamp></ns5:MessageData></ns5:MessageHeader><ns7:Security xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\"><ns7:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3893959604573827195!1925531!0</ns7:BinarySecurityToken></ns7:Security></soap:Header><soap:Body><ns4:SabreCommandLLSRQ xmlns:ns8=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns7=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://webservices.sabre.com/sabreXML/2003/07\" Version=\"2003A.TsabreXML1.6.1\"><ns4:Request Output=\"SCREEN\"><ns4:HostCommand>IA</ns4:HostCommand></ns4:Request></ns4:SabreCommandLLSRQ></soap:Body></soap:Envelope>";

    public static final String SABRE_COMMAND_LLS_IA_RESPONSE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap-env:Header><eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\"><eb:From><eb:PartyId eb:type=\"URI\">123123</eb:PartyId></eb:From><eb:To><eb:PartyId eb:type=\"URI\">99999</eb:PartyId></eb:To><eb:CPAId>2P</eb:CPAId><eb:ConversationId>A3OE_D12B234FD110F2C4901D6664C149C202</eb:ConversationId><eb:Service>SabreCommand</eb:Service><eb:Action>SabreCommandLLSRS</eb:Action><eb:MessageData><eb:MessageId>1977c57f-0795-4eb0-ba42-8c8c03d113ed@32</eb:MessageId><eb:Timestamp>2012-08-02T19:27:22</eb:Timestamp><eb:RefToMessageId>mid:2012-08-02T19:27:22@eb2.com</eb:RefToMessageId></eb:MessageData></eb:MessageHeader><wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\"><wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3893965734719988479!1555162!0</wsse:BinarySecurityToken></wsse:Security></soap-env:Header><soap-env:Body><SabreCommandLLSRS xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" EchoToken=\"String\" TimeStamp=\"2012-08-02T19:27:22\" Target=\"Production\" Version=\"2003A.TsabreXML1.6.1\" SequenceNmbr=\"1\" PrimaryLangID=\"en-us\" AltLangID=\"en-us\">\n" +
                    " <Response><![CDATA[ 1 2P 737E 09AUG Q CRKSIN SS1   815P 1150P /E]]>" +
                    "<![CDATA[ 2 2P 738T 21SEP F SINCRK SS1  1220A  400A /E]]></Response>\n" +
                    "</SabreCommandLLSRS></soap-env:Body></soap-env:Envelope>";

    public static final String AIR_BOOK_REQUEST =
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "\t<soap:Header>\n" +
                    "\t\t<ns5:MessageHeader xmlns:ns16=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns15=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns14=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns11=\"http://services.sabre.com/STL/v01\" xmlns:ns10=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns9=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns8=\"http://www.opentravel.org/OTA/2002/11\" xmlns:ns7=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://www.opentravel.org/OTA/2003/05\">\n" +
                    "\t\t\t<ns5:From>\n" +
                    "\t\t\t\t<ns5:PartyId>99999</ns5:PartyId>\n" +
                    "\t\t\t</ns5:From>\n" +
                    "\t\t\t<ns5:To>\n" +
                    "\t\t\t\t<ns5:PartyId>123123</ns5:PartyId>\n" +
                    "\t\t\t</ns5:To>\n" +
                    "\t\t\t<ns5:CPAId>2P</ns5:CPAId>\n" +
                    "\t\t\t<ns5:ConversationId>A3OE_951658ACFB3489F0420482DB0DCDF9AF</ns5:ConversationId>\n" +
                    "\t\t\t<ns5:Service>AirBook</ns5:Service>\n" +
                    "\t\t\t<ns5:Action>OTA_AirBookLLSRQ</ns5:Action>\n" +
                    "\t\t\t<ns5:MessageData>\n" +
                    "\t\t\t\t<ns5:MessageId>mid:2012-08-03T21:57:41@eb2.com</ns5:MessageId>\n" +
                    "\t\t\t\t<ns5:Timestamp>2012-08-03T21:57:41</ns5:Timestamp>\n" +
                    "\t\t\t</ns5:MessageData>\n" +
                    "\t\t</ns5:MessageHeader>\n" +
                    "\t\t<ns15:Security xmlns:ns16=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns15=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns14=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns11=\"http://services.sabre.com/STL/v01\" xmlns:ns10=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns9=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns8=\"http://www.opentravel.org/OTA/2002/11\" xmlns:ns7=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://www.opentravel.org/OTA/2003/05\">\n" +
                    "\t\t\t<ns15:BinarySecurityToken>Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3893576218705306622!113874!0</ns15:BinarySecurityToken>\n" +
                    "\t\t</ns15:Security>\n" +
                    "\t</soap:Header>\n" +
                    "\t<soap:Body>\n" +
                    "\t\t<ns10:OTA_AirBookRQ xmlns:ns16=\"http://www.sabre.com/ns/Ticketing/misc/1.0\" xmlns:ns15=\"http://schemas.xmlsoap.org/ws/2002/12/secext\" xmlns:ns14=\"http://webservices.sabre.com/sabreXML/2003/07/request\" xmlns:ns13=\"http://webservices.sabre.com/sabreXML/2003/07/response\" xmlns:ns12=\"http://services.sabre.com/STL_Header/v120\" xmlns:ns11=\"http://services.sabre.com/STL/v01\" xmlns:ns10=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:ns9=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:ns8=\"http://www.opentravel.org/OTA/2002/11\" xmlns:ns7=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns6=\"http://www.w3.org/1999/xlink\" xmlns:ns5=\"http://www.ebxml.org/namespaces/messageHeader\" xmlns:ns4=\"http://www.opentravel.org/OTA/2003/05\" Version=\"2003A.TsabreXML1.6.1\">\n" +
                    "\t\t\t<ns10:POS>\n" +
                    "\t\t\t\t<ns10:Source/>\n" +
                    "\t\t\t</ns10:POS>\n" +
                    "\t\t\t<ns10:TPA_Extensions>\n" +
                    "\t\t\t\t<ns10:MessagingDetails>\n" +
                    "\t\t\t\t\t<ns10:MDRSubset Code=\"AD01\"/>\n" +
                    "\t\t\t\t</ns10:MessagingDetails>\n" +
                    "\t\t\t</ns10:TPA_Extensions>\n" +
                    "\t\t\t<ns10:AirItinerary>\n" +
                    "\t\t\t\t<ns10:OriginDestinationOptions>\n" +
                    "\t\t\t\t\t<ns10:OriginDestinationOption>\n" +
                    "\t\t\t\t\t\t<ns10:FlightSegment DepartureDateTime=\"2012-09-02T04:45:00\" FlightNumber=\"945\" ArrivalDateTime=\"2012-09-02T05:50:00\" ResBookDesigCode=\"W\" ActionCode=\"NN\" BrandID=\"EF\" NumberInParty=\"1\">\n" +
                    "\t\t\t\t\t\t\t<ns10:DepartureAirport LocationCode=\"MNL\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:ArrivalAirport LocationCode=\"ILO\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:OperatingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:MarketingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:MarriageGrp Ind=\"false\"/>\n" +
                    "\t\t\t\t\t\t</ns10:FlightSegment>\n" +
                    "\t\t\t\t\t</ns10:OriginDestinationOption>\n" +
                    "\t\t\t\t\t<ns10:OriginDestinationOption>\n" +
                    "\t\t\t\t\t\t<ns10:FlightSegment DepartureDateTime=\"2012-10-17T07:05:00\" FlightNumber=\"381\" ArrivalDateTime=\"2012-10-17T07:50:00\" ResBookDesigCode=\"W\" ActionCode=\"NN\" BrandID=\"EF\" NumberInParty=\"1\">\n" +
                    "\t\t\t\t\t\t\t<ns10:DepartureAirport LocationCode=\"ILO\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:ArrivalAirport LocationCode=\"MNL\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:OperatingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:MarketingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t<ns10:MarriageGrp Ind=\"false\"/>\n" +
                    "\t\t\t\t\t\t</ns10:FlightSegment>\n" +
                    "\t\t\t\t\t</ns10:OriginDestinationOption>\n" +
                    "\t\t\t\t</ns10:OriginDestinationOptions>\n" +
                    "\t\t\t</ns10:AirItinerary>\n" +
                    "\t\t</ns10:OTA_AirBookRQ>\n" +
                    "\t</soap:Body>\n" +
                    "</soap:Envelope>";

    public static final String AIR_BOOK_RESPONSE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "\t<soap-env:Header>\n" +
                    "\t\t<eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\">\n" +
                    "\t\t\t<eb:From>\n" +
                    "\t\t\t\t<eb:PartyId eb:type=\"URI\">123123</eb:PartyId>\n" +
                    "\t\t\t</eb:From>\n" +
                    "\t\t\t<eb:To>\n" +
                    "\t\t\t\t<eb:PartyId eb:type=\"URI\">99999</eb:PartyId>\n" +
                    "\t\t\t</eb:To>\n" +
                    "\t\t\t<eb:CPAId>CY</eb:CPAId>\n" +
                    "\t\t\t<eb:ConversationId>A3OE_951658ACFB3489F0420482DB0DCDF9AF</eb:ConversationId>\n" +
                    "\t\t\t<eb:Service>AirBook</eb:Service>\n" +
                    "\t\t\t<eb:Action>OTA_AirBookLLSRS</eb:Action>\n" +
                    "\t\t\t<eb:MessageData>\n" +
                    "\t\t\t\t<eb:MessageId>addb332d-095a-43ae-abae-d9743ff7f0b8@176</eb:MessageId>\n" +
                    "\t\t\t\t<eb:Timestamp>2012-08-02T20:02:06</eb:Timestamp>\n" +
                    "\t\t\t\t<eb:RefToMessageId>mid:2012-08-02T15:01:47@eb2.com</eb:RefToMessageId>\n" +
                    "\t\t\t</eb:MessageData>\n" +
                    "\t\t</eb:MessageHeader>\n" +
                    "\t\t<wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">\n" +
                    "\t\t\t<wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3893957428374291836!1576642!0</wsse:BinarySecurityToken>\n" +
                    "\t\t</wsse:Security>\n" +
                    "\t</soap-env:Header>\n" +
                    "\t<soap-env:Body>\n" +
                    "\t\t<OTA_AirBookRS xmlns=\"http://webservices.sabre.com/sabreXML/2003/07\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" TimeStamp=\"2012-08-02T20:02:06\" AltLangID=\"en-us\" Version=\"2003A.TsabreXML1.6.1\" PrimaryLangID=\"en-us\" EchoToken=\"String\" SequenceNmbr=\"1\" Target=\"Production\">\n" +
                    "\t\t\t<Success/>\n" +
                    "\t\t\t<AirReservation>\n" +
                    "\t\t\t\t<AirItinerary>\n" +
                    "\t\t\t\t\t<OriginDestinationOptions>\n" +
                    "\t\t\t\t\t\t<OriginDestinationOption>\n" +
                    "\t\t\t\t\t\t\t<FlightSegment Status=\"SS\" NumberInParty=\"001\" FlightNumber=\"945\" DepartureDateTime=\"2012-09-05T09:25:00\" ArrivalDateTime=\"2012-09-05T12:05:00\" ResBookDesigCode=\"B\" eTicket=\"ET\">\n" +
                    "\t\t\t\t\t\t\t\t<DepartureAirport LocationCode=\"MNL\" CodeContext=\"IATA\"/>\n" +
                    "\t\t\t\t\t\t\t\t<ArrivalAirport LocationCode=\"ILO\" CodeContext=\"IATA\"/>\n" +
                    "\t\t\t\t\t\t\t\t<OperatingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t</FlightSegment>\n" +
                    "\t\t\t\t\t\t\t<FlightSegment Status=\"SS\" NumberInParty=\"001\" FlightNumber=\"381\" DepartureDateTime=\"2012-09-12T15:00:00\" ArrivalDateTime=\"2012-09-12T19:30:00\" ResBookDesigCode=\"B\" eTicket=\"ET\">\n" +
                    "\t\t\t\t\t\t\t\t<DepartureAirport LocationCode=\"ILO\" CodeContext=\"IATA\"/>\n" +
                    "\t\t\t\t\t\t\t\t<ArrivalAirport LocationCode=\"MNL\" CodeContext=\"IATA\"/>\n" +
                    "\t\t\t\t\t\t\t\t<OperatingAirline Code=\"2P\"/>\n" +
                    "\t\t\t\t\t\t\t</FlightSegment>\n" +
                    "\t\t\t\t\t\t</OriginDestinationOption>\n" +
                    "\t\t\t\t\t</OriginDestinationOptions>\n" +
                    "\t\t\t\t</AirItinerary>\n" +
                    "\t\t\t</AirReservation>\n" +
                    "\t\t\t<TPA_Extensions>\n" +
                    "\t\t\t\t<HostCommand>\u0082\u0086\u0082\u0086\u0082\u0086A\u0082\u0086\u0082\u0086RS01S093\u0082\u0086JA200002160048QH 01SBS                                  DT0064FL 0101   MNL  ILOEC201209050925201209051205   CY 1793B    00016SP 0101001NN0064FL 0201   ILO  MNLEC201209121500201209121930   CY 1794B    00016SP 0202001NN</HostCommand>\n" +
                    "\t\t\t</TPA_Extensions>\n" +
                    "\t\t</OTA_AirBookRS>\n" +
                    "\t</soap-env:Body>\n" +
                    "</soap-env:Envelope>";

    public static final String TRAVEL_ITINERARY_READ_MOCK_RESPONSE =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    "<soap-env:Header><eb:MessageHeader xmlns:eb=\"http://www.ebxml.org/namespaces/messageHeader\" eb:version=\"1.0\" soap-env:mustUnderstand=\"1\"><eb:From><eb:PartyId eb:type=\"URI\">123123</eb:PartyId></eb:From><eb:To><eb:PartyId eb:type=\"URI\">99999</eb:PartyId></eb:To><eb:CPAId>F7</eb:CPAId><eb:ConversationId>F7F7_5D78A3E61207FB986DFA73C3D16C0010</eb:ConversationId><eb:Service>TravelItineraryReadLLSRQ</eb:Service><eb:Action>TravelItineraryReadLLSRS</eb:Action><eb:MessageData><eb:MessageId>1e544b7b-399b-4bf8-8334-d3477f6056bc@176</eb:MessageId><eb:Timestamp>2012-06-25T10:29:44</eb:Timestamp><eb:RefToMessageId>mid:2012-06-25T05:30:20@eb2.com</eb:RefToMessageId></eb:MessageData></eb:MessageHeader><wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\"><wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3907547879922531708!1252467!0</wsse:BinarySecurityToken></wsse:Security></soap-env:Header><soap-env:Body><TravelItineraryReadRS xmlns=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:stl=\"http://services.sabre.com/STL/v01\" Version=\"2.0.0\">\n" +
                    " <stl:ApplicationResults status=\"Complete\">\n" +
                    "  <stl:Success timeStamp=\"2012-06-25T05:29:44-05:00\">\n" +
                    "   <stl:SystemSpecificResults>\n" +
                    "    <stl:GenericHostCommand LNIATA=\"3DE369\">JX PNR*NBJYUF</stl:GenericHostCommand>\n" +
                    "   </stl:SystemSpecificResults>\n" +
                    "  </stl:Success>\n" +
                    " </stl:ApplicationResults>\n" +
                    " <TravelItinerary>\n" +
                    "  <CustomerInfo>\n" +
                    "   <ContactNumbers>\n" +
                    "    <ContactNumber LocationCode=\"ZDH\" Phone=\"69-378650535-M-1.1\" RPH=\"001\"/>\n" +
                    "    <ContactNumber LocationCode=\"ZDH\" Phone=\"23-723507805-H-1.1\" RPH=\"002\"/>\n" +
                    "   </ContactNumbers>\n" +
                    "   <PersonName Infant=\"false\" NameNumber=\"01.01\" NameReference=\"ADT\" RPH=\"1\">\n" +
                    "    <Email>\u0087NMVBPIVF@SABRE.COM\u0087¤E</Email>\n" +
                    "    <Email>\u0087LINKAHH@SABRE.COM\u0087¤E</Email>\n" +
                    "    <GivenName>E MR</GivenName>\n" +
                    "    <Surname>RRPKROWATRBCGCGLEBFBRTWUGC</Surname>\n" +
                    "   </PersonName>\n" +
                    "  </CustomerInfo>\n" +
                    "  <ItineraryInfo>\n" +
                    "   <ItineraryPricing>\n" +
                    "    <PriceQuote RPH=\"1\">\n" +
                    "     <MiscInformation>\n" +
                    "      <SignatureLine Source=\"SYS\" Status=\"ACTIVE\">\n" +
                    "       <Text>ZDH  LXF 5DIE 0529/25JUN</Text>\n" +
                    "      </SignatureLine>\n" +
                    "     </MiscInformation>\n" +
                    "     <PricedItinerary InputMessage=\"WPRQ\u0087MUSD\u0087P1ADT\" RPH=\"1\" StatusCode=\"A\" TaxExempt=\"false\" ValidatingCarrier=\"F7\">\n" +
                    "      <AirItineraryPricingInfo>\n" +
                    "       <ItinTotalFare>\n" +
                    "        <BaseFare Amount=\"294.00\" CurrencyCode=\"CHF\"/>\n" +
                    "        <EquivFare Amount=\"305.00\" CurrencyCode=\"USD\"/>\n" +
                    "        <Taxes>\n" +
                    "         <Tax Amount=\"87.40\" TaxCode=\"XT\"/>\n" +
                    "         <TaxBreakdownCode>59.10YQ</TaxBreakdownCode>\n" +
                    "         <TaxBreakdownCode>5.20YR</TaxBreakdownCode>\n" +
                    "         <TaxBreakdownCode>1.00YR</TaxBreakdownCode>\n" +
                    "         <TaxBreakdownCode>22.10CH</TaxBreakdownCode>\n" +
                    "        </Taxes>\n" +
                    "        <TotalFare Amount=\"392.40\" CurrencyCode=\"USD\"/>\n" +
                    "        <Totals>\n" +
                    "         <BaseFare Amount=\"294.00\"/>\n" +
                    "         <EquivFare Amount=\"305.00\"/>\n" +
                    "         <Taxes>\n" +
                    "          <Tax Amount=\"87.40\"/>\n" +
                    "         </Taxes>\n" +
                    "         <TotalFare Amount=\"392.40\"/>\n" +
                    "        </Totals>\n" +
                    "       </ItinTotalFare>\n" +
                    "       <PassengerTypeQuantity Code=\"ADT\" Quantity=\"01\"/>\n" +
                    "       <PTC_FareBreakdown>\n" +
                    "        <Endorsements>\n" +
                    "         <Text>F7 ONLY/RESTRICTIONS APPLY</Text>\n" +
                    "        </Endorsements>\n" +
                    "        <FareBasis Code=\"DPREMIUM\"/>\n" +
                    "        <FareCalculation>\n" +
                    "         <Text>GVA F7 ROM321.52DPREMIUM NUC321.52END ROE0.91438</Text>\n" +
                    "        </FareCalculation>\n" +
                    "        <FlightSegment ConnectionInd=\"O\" DepartureDateTime=\"07-03T07:25\" FlightNumber=\"154\" ResBookDesigCode=\"D\" SegmentNumber=\"1\" Status=\"OK\">\n" +
                    "         <BaggageAllowance Number=\"02P\"/>\n" +
                    "         <FareBasis Code=\"DPREMIUM\"/>\n" +
                    "         <MarketingAirline Code=\"F7\" FlightNumber=\"154\"/>\n" +
                    "         <OriginLocation LocationCode=\"GVA\"/>\n" +
                    "         <ValidityDates>\n" +
                    "          <NotValidAfter>07-03</NotValidAfter>\n" +
                    "         </ValidityDates>\n" +
                    "        </FlightSegment>\n" +
                    "        <FlightSegment>\n" +
                    "         <OriginLocation LocationCode=\"FCO\"/>\n" +
                    "        </FlightSegment>\n" +
                    "        <ResTicketingRestrictions>LAST DAY TO PURCHASE 28JUN/2359</ResTicketingRestrictions>\n" +
                    "        <ResTicketingRestrictions>GUARANTEED FARE APPL IF PURCHASED BEFORE 28JUN</ResTicketingRestrictions>\n" +
                    "       </PTC_FareBreakdown>\n" +
                    "      </AirItineraryPricingInfo>\n" +
                    "     </PricedItinerary>\n" +
                    "     <ResponseHeader>\n" +
                    "      <Text>FARE - PRICE RETAINED</Text>\n" +
                    "      <Text>FARE USED TO CALCULATE DISCOUNT</Text>\n" +
                    "     </ResponseHeader>\n" +
                    "    </PriceQuote>\n" +
                    "   </ItineraryPricing>\n" +
                    "   <ReservationItems>\n" +
                    "    <Item RPH=\"1\">\n" +
                    "     <FlightSegment AirMilesFlown=\"0439\" ArrivalDateTime=\"07-03T09:05\" DepartureDateTime=\"2012-07-03T07:25\" ElapsedTime=\"01.40\" FlightNumber=\"0154\" NumberInParty=\"01\" ResBookDesigCode=\"D\" SegmentNumber=\"0001\" SmokingAllowed=\"false\" SpecialMeal=\"false\" Status=\"HK\" StopQuantity=\"00\" eTicket=\"true\">\n" +
                    "      <DestinationLocation LocationCode=\"FCO\" Terminal=\"TERMINAL 1\" TerminalCode=\"1\"/>\n" +
                    "      <Equipment AirEquipType=\"S20\"/>\n" +
                    "      <MarketingAirline Code=\"F7\" FlightNumber=\"0154\"/>\n" +
                    "      <OriginLocation LocationCode=\"GVA\" Terminal=\"MAIN TERMINAL\" TerminalCode=\"M\"/>\n" +
                    "      <UpdatedArrivalTime>07-03T09:05</UpdatedArrivalTime>\n" +
                    "      <UpdatedDepartureTime>07-03T07:25</UpdatedDepartureTime>\n" +
                    "     </FlightSegment>\n" +
                    "    </Item>\n" +
                    "   </ReservationItems>\n" +
                    "   <Ticketing RPH=\"01\" TicketTimeLimit=\"T-\"/>\n" +
                    "  </ItineraryInfo>\n" +
                    "  <ItineraryRef AirExtras=\"false\" ID=\"NBJYUF\" InhibitCode=\"U\" PartitionID=\"F7\" PrimeHostID=\"F7\">\n" +
                    "   <Source AAAPseudoCityCode=\"ZDH\" CreateDateTime=\"2012-06-25T05:29\" CreationAgent=\"DIE\" HomePseudoCityCode=\"LXF\" ReceivedFrom=\"SSW\"/>\n" +
                    "  </ItineraryRef>\n" +
                    "  <SpecialServiceInfo RPH=\"001\" Type=\"AFX\">\n" +
                    "   <Service SSR_Code=\"SSR\" SSR_Type=\"BRND\">\n" +
                    "    <Text>F7 NN1 GVAFCO0154D03JUL/BS</Text>\n" +
                    "   </Service>\n" +
                    "  </SpecialServiceInfo>\n" +
                    "  <SpecialServiceInfo RPH=\"002\" Type=\"AFX\">\n" +
                    "   <Service SSR_Code=\"SSR\" SSR_Type=\"DOCS\">\n" +
                    "    <PersonName NameNumber=\"01.01\">RRPKROWATRBCGCGLEBFBRTWUGC/E MR</PersonName>\n" +
                    "    <Text>F7 HK1/DB/01MAR76/M/RRPKROWATRBCGCGLEBFBRTWUGC/EJPUSHTRRFXUYTIQQQNCMUSIML</Text>\n" +
                    "   </Service>\n" +
                    "  </SpecialServiceInfo>\n" +
                    " </TravelItinerary>\n" +
                    "</TravelItineraryReadRS></soap-env:Body></soap-env:Envelope>";
    public static final String OTA_VEHLOCSEARCH_RQ =
            "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "\t<soap:Body>\n" +
                    "\t\t<OTA_VehLocSearchRQ xmlns:ns2=\"http://ey.webservices.ae/wsrv/namespaces/messageHeader\" xmlns=\"http://www.opentravel.org/OTA/2003/05\" Version=\"2.0\" PrimaryLangID=\"en\">\n" +
                    "\t\t\t<POS>\n" +
                    "\t\t\t\t<Source AgentSine=\"ABC\" PseudoCityCode=\"SABRE\" ISOCountry=\"AE\" ISOCurrency=\"AED\" FirstDepartPoint=\"AUH\" TerminalID=\"192.168.2.147\"/>\n" +
                    "\t\t\t</POS>\n" +
                    "\t\t\t<VehLocSearchCriterion>\n" +
                    "\t\t\t\t<RefPoint CodeContext=\"LocationId\">CITY_VCE_IT</RefPoint>\n" +
                    "\t\t\t</VehLocSearchCriterion>\n" +
                    "\t\t</OTA_VehLocSearchRQ>\n" +
                    "\t</soap:Body>\n" +
                    "</soap:Envelope>";

    public static void setupConsoleLogging() {
        ConsoleAppender console = new ConsoleAppender();
        console.setWriter(new PrintWriter(System.out));
        console.setLayout(new SimpleLayout());
        Logger.getRootLogger().addAppender(console);
    }

    public static void setupSessionsInfoManager(String SESSION_ID, String request) {
        SessionsInfoManager sessionsInfoManager = new SessionsInfoManager();
        sessionsInfoManager.getCount(SESSION_ID, request);
        SpringBeanContainer.setApplicationContext(new ApplicationContextMock().add("sessionsInfoManager", sessionsInfoManager));
    }
}
