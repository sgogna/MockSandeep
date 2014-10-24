package testproxy.httpservletwrappers;

import org.testng.annotations.Test;
import testproxy.servlets.HttpServletRequestMock;

import java.io.UnsupportedEncodingException;

/**
 * GenericRequestWrapperTest
 */
public class GenericRequestWrapperTest
{
    static final String MOCK_RESPONSE =
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
                    "\t\t\t<eb:CPAId>F7</eb:CPAId>\n" +
                    "\t\t\t<eb:ConversationId>F7F7_F1591142CCEE8A04C8121A1C71B1EF23</eb:ConversationId>\n" +
                    "\t\t\t<eb:Service>TravelItineraryReadLLSRQ</eb:Service>\n" +
                    "\t\t\t<eb:Action>TravelItineraryReadLLSRS</eb:Action>\n" +
                    "\t\t\t<eb:MessageData>\n" +
                    "\t\t\t\t<eb:MessageId>a69e0654-9992-4342-baf5-ae55020dacdf@31</eb:MessageId>\n" +
                    "\t\t\t\t<eb:Timestamp>2012-07-20T21:12:35</eb:Timestamp>\n" +
                    "\t\t\t\t<eb:RefToMessageId>mid:2012-07-20T21:12:35@eb2.com</eb:RefToMessageId>\n" +
                    "\t\t\t</eb:MessageData>\n" +
                    "\t\t</eb:MessageHeader>\n" +
                    "\t\t<wsse:Security xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2002/12/secext\">\n" +
                    "\t\t\t<wsse:BinarySecurityToken valueType=\"String\" EncodingType=\"wsse:Base64Binary\">Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTC!ICESMSLB\\/CRT.LB!-3898541219377465088!1352682!0</wsse:BinarySecurityToken>\n" +
                    "\t\t</wsse:Security>\n" +
                    "\t</soap-env:Header>\n" +
                    "\t<soap-env:Body>\n" +
                    "\t\t<TravelItineraryReadRS xmlns=\"http://webservices.sabre.com/sabreXML/2011/10\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:stl=\"http://services.sabre.com/STL/v01\" Version=\"2.0.0\">\n" +
                    "\t\t\t<stl:ApplicationResults status=\"Complete\">\n" +
                    "\t\t\t\t<stl:Success timeStamp=\"2012-07-20T16:12:35-05:00\">\n" +
                    "\t\t\t\t\t<stl:SystemSpecificResults>\n" +
                    "\t\t\t\t\t\t<stl:GenericHostCommand LNIATA=\"563A91\">JX PNR*DZSKQH</stl:GenericHostCommand>\n" +
                    "\t\t\t\t\t</stl:SystemSpecificResults>\n" +
                    "\t\t\t\t</stl:Success>\n" +
                    "\t\t\t</stl:ApplicationResults>\n" +
                    "\t\t\t<TravelItinerary>\n" +
                    "\t\t\t\t<CustomerInfo>\n" +
                    "\t\t\t\t\t<ContactNumbers>\n" +
                    "\t\t\t\t\t\t<ContactNumber LocationCode=\"ZDH\" Phone=\"12-12-35428742-M-1.1\" RPH=\"001\"/>\n" +
                    "\t\t\t\t\t\t<ContactNumber LocationCode=\"ZDH\" Phone=\"12-12-70794177-H-1.1\" RPH=\"002\"/>\n" +
                    "\t\t\t\t\t</ContactNumbers>\n" +
                    "\t\t\t\t\t<PersonName Infant=\"false\" NameNumber=\"01.01\" NameReference=\"ADT\" RPH=\"1\">\n" +
                    "\t\t\t\t\t\t<Email>ÂWALDEMAR.SOJKA@SABRE.COMÂÂ¤E</Email>\n" +
                    "\t\t\t\t\t\t<GivenName>QPDSVRBITV MR</GivenName>\n" +
                    "\t\t\t\t\t\t<Surname>QPDSVRBITV</Surname>\n" +
                    "\t\t\t\t\t</PersonName>\n" +
                    "\t\t\t\t</CustomerInfo>\n" +
                    "\t\t\t\t<ItineraryInfo>\n" +
                    "\t\t\t\t\t<ItineraryPricing>\n" +
                    "\t\t\t\t\t\t<PriceQuote RPH=\"1\">\n" +
                    "\t\t\t\t\t\t\t<MiscInformation>\n" +
                    "\t\t\t\t\t\t\t\t<SignatureLine Source=\"SYS\" Status=\"ACTIVE\">\n" +
                    "\t\t\t\t\t\t\t\t\t<Text>ZDH  LXF 5DIE 1612/20JUL</Text>\n" +
                    "\t\t\t\t\t\t\t\t</SignatureLine>\n" +
                    "\t\t\t\t\t\t\t</MiscInformation>\n" +
                    "\t\t\t\t\t\t\t<PricedItinerary InputMessage=\"WPRQÂMCHFÂP1ADT\" RPH=\"1\" StatusCode=\"A\" TaxExempt=\"false\" ValidatingCarrier=\"F7\">\n" +
                    "\t\t\t\t\t\t\t\t<AirItineraryPricingInfo>\n" +
                    "\t\t\t\t\t\t\t\t\t<ItinTotalFare>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<BaseFare Amount=\"463.00\" CurrencyCode=\"CHF\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<Taxes>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Tax Amount=\"89.35\" TaxCode=\"XT\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TaxBreakdownCode>57.00YQ</TaxBreakdownCode>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TaxBreakdownCode>1.00YQ</TaxBreakdownCode>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TaxBreakdownCode>5.00YR</TaxBreakdownCode>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TaxBreakdownCode>5.00YR</TaxBreakdownCode>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TaxBreakdownCode>21.35CH</TaxBreakdownCode>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</Taxes>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<TotalFare Amount=\"552.35\" CurrencyCode=\"CHF\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<Totals>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<BaseFare Amount=\"463.00\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Taxes>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Tax Amount=\"89.35\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</Taxes>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<TotalFare Amount=\"552.35\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</Totals>\n" +
                    "\t\t\t\t\t\t\t\t\t</ItinTotalFare>\n" +
                    "\t\t\t\t\t\t\t\t\t<PassengerTypeQuantity Code=\"ADT\" Quantity=\"01\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t<PTC_FareBreakdown>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<Endorsements>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Text>F7 ONLY/RESTRICTIONS APPLY</Text>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</Endorsements>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<FareBasis Code=\"XTWIST\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<FareCalculation>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Text>GVA F7 ROM481.19XTWIST NUC481.19END ROE0.96218</Text>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</FareCalculation>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<FlightSegment ConnectionInd=\"O\" DepartureDateTime=\"08-16T18:40\" FlightNumber=\"1123\" ResBookDesigCode=\"X\" SegmentNumber=\"1\" Status=\"OK\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<BaggageAllowance Number=\"01P\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<FareBasis Code=\"XTWIST\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<MarketingAirline FlightNumber=\"1123\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<OriginLocation LocationCode=\"GVA\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<ValidityDates>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<NotValidAfter>08-16</NotValidAfter>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<NotValidBefore>08-16</NotValidBefore>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</ValidityDates>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</FlightSegment>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<FlightSegment>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<OriginLocation LocationCode=\"FCO\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</FlightSegment>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<ResTicketingRestrictions>LAST DAY TO PURCHASE 26JUL/2359</ResTicketingRestrictions>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<ResTicketingRestrictions>GUARANTEED FARE APPL IF PURCHASED BEFORE 26JUL</ResTicketingRestrictions>\n" +
                    "\t\t\t\t\t\t\t\t\t</PTC_FareBreakdown>\n" +
                    "\t\t\t\t\t\t\t\t</AirItineraryPricingInfo>\n" +
                    "\t\t\t\t\t\t\t</PricedItinerary>\n" +
                    "\t\t\t\t\t\t\t<ResponseHeader>\n" +
                    "\t\t\t\t\t\t\t\t<Text>FARE - PRICE RETAINED</Text>\n" +
                    "\t\t\t\t\t\t\t\t<Text>FARE USED TO CALCULATE DISCOUNT</Text>\n" +
                    "\t\t\t\t\t\t\t</ResponseHeader>\n" +
                    "\t\t\t\t\t\t</PriceQuote>\n" +
                    "\t\t\t\t\t</ItineraryPricing>\n" +
                    "\t\t\t\t\t<ReservationItems>\n" +
                    "\t\t\t\t\t\t<Item RPH=\"1\">\n" +
                    "\t\t\t\t\t\t\t<FlightSegment AirMilesFlown=\"0439\" ArrivalDateTime=\"08-16T20:05\" DepartureDateTime=\"2012-08-16T18:40\" ElapsedTime=\"01.25\" FlightNumber=\"1123\" NumberInParty=\"01\" ResBookDesigCode=\"X\" SegmentNumber=\"0001\" SmokingAllowed=\"false\" SpecialMeal=\"false\" Status=\"HK\" StopQuantity=\"00\" eTicket=\"true\">\n" +
                    "\t\t\t\t\t\t\t\t<DestinationLocation LocationCode=\"FCO\" Terminal=\"TERMINAL 1\" TerminalCode=\"1\"/>\n" +
                    "\t\t\t\t\t\t\t\t<Equipment AirEquipType=\"32S\"/>\n" +
                    "\t\t\t\t\t\t\t\t<MarketingAirline Code=\"F7\" FlightNumber=\"1123\"/>\n" +
                    "\t\t\t\t\t\t\t\t<Meal Code=\"S\"/>\n" +
                    "\t\t\t\t\t\t\t\t<OperatingAirline Code=\"AZ\" CompanyShortName=\"ALITALIA C.A. I S.P.A.\"/>\n" +
                    "\t\t\t\t\t\t\t\t<OperatingAirline CompanyShortName=\"ALITALIA C.A. I S.P.A.\"/>\n" +
                    "\t\t\t\t\t\t\t\t<OriginLocation LocationCode=\"GVA\" Terminal=\"MAIN TERMINAL\" TerminalCode=\"M\"/>\n" +
                    "\t\t\t\t\t\t\t\t<PartnerCarrier Code=\"AZ\" FlightNumber=\"0577\" ResBookDesigCode=\"M\">\n" +
                    "\t\t\t\t\t\t\t\t\t<Banner>OPERATED BY ALITALIA C.A. I S.P.A.</Banner>\n" +
                    "\t\t\t\t\t\t\t\t</PartnerCarrier>\n" +
                    "\t\t\t\t\t\t\t\t<Text>OPERATED BY ALITALIA C.A. I S.P.A.</Text>\n" +
                    "\t\t\t\t\t\t\t\t<UpdatedArrivalTime>08-16T20:05</UpdatedArrivalTime>\n" +
                    "\t\t\t\t\t\t\t\t<UpdatedDepartureTime>08-16T18:40</UpdatedDepartureTime>\n" +
                    "\t\t\t\t\t\t\t</FlightSegment>\n" +
                    "\t\t\t\t\t\t</Item>\n" +
                    "\t\t\t\t\t</ReservationItems>\n" +
                    "\t\t\t\t\t<Ticketing RPH=\"01\" TicketTimeLimit=\"T-\"/>\n" +
                    "\t\t\t\t</ItineraryInfo>\n" +
                    "\t\t\t\t<ItineraryRef AirExtras=\"false\" ID=\"DZSKQH\" InhibitCode=\"U\" PartitionID=\"F7\" PrimeHostID=\"F7\">\n" +
                    "\t\t\t\t\t<Source AAAPseudoCityCode=\"ZDH\" CreateDateTime=\"2012-07-20T16:12\" CreationAgent=\"DIE\" HomePseudoCityCode=\"LXF\" ReceivedFrom=\"SSW\"/>\n" +
                    "\t\t\t\t</ItineraryRef>\n" +
                    "\t\t\t\t<SpecialServiceInfo RPH=\"001\" Type=\"AFX\">\n" +
                    "\t\t\t\t\t<Service SSR_Code=\"SSR\" SSR_Type=\"BRND\">\n" +
                    "\t\t\t\t\t\t<Text>F7 NN1 GVAFCO1123X16AUG/BT</Text>\n" +
                    "\t\t\t\t\t</Service>\n" +
                    "\t\t\t\t</SpecialServiceInfo>\n" +
                    "\t\t\t\t<SpecialServiceInfo RPH=\"002\" Type=\"AFX\">\n" +
                    "\t\t\t\t\t<Service SSR_Code=\"SSR\" SSR_Type=\"DOCS\">\n" +
                    "\t\t\t\t\t\t<PersonName NameNumber=\"01.01\">QPDSVRBITV/QPDSVRBITV MR</PersonName>\n" +
                    "\t\t\t\t\t\t<Text>F7 HK1/DB/03JUL64/M/QPDSVRBITV/QPDSVRBITV</Text>\n" +
                    "\t\t\t\t\t</Service>\n" +
                    "\t\t\t\t</SpecialServiceInfo>\n" +
                    "\t\t\t</TravelItinerary>\n" +
                    "\t\t</TravelItineraryReadRS>\n" +
                    "\t</soap-env:Body>\n" +
                    "</soap-env:Envelope>\n";

    @Test
    public void testGetSoapAction() throws Exception
    {
        assert "TravelItineraryReadLLS".equals(new GenericRequestWrapper(new HttpServletRequestMock())
                        {
            @Override
            public byte[] getArrayByte()
                            {
                try
                {
                    return MOCK_RESPONSE.getBytes("UTF-8");
                }
                catch (UnsupportedEncodingException e)
                                {
                    e.printStackTrace();
                    return new byte[0];
                }
            }
        }.getSoapAction());
    }

    public static String HOTEL_MOCK_RESPONSE =
            "<soapenv:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"> xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\">\n" +
            "\t<soapenv:Header/>\n" +
            "\t\t<soapenv:Body>\n" +
            "\t\t\t<ns:OTA_HotelAvailRQ PrimaryLangID=\"fr\" Version=\"2.0\" >\n" +
            "\t\t\t\t<ns:POS>\n" +
            "\t\t\t\t\t\t<ns:Source ISOCountry=\"AE\" ISOCurrency=\"AED\" PseudoCityCode=\"SABRE\" TerminalID=\"192.168.2.147\" FirstDepartPoint=\"AUH\" AgentSine=\"12313213213\"/>\n" +
            "\t\t\t\t</ns:POS>\n" +
            "\t\t\t\t<ns:AvailRequestSegments>\n" +
            "\t\t\t\t<ns:AvailRequestSegment>\n" +
            "\t\t\t\t<ns:HotelSearchCriteria>\n" +
            "\t\t\t\t\t<ns:Criterion ExactMatch=\"true\">\n" +
            "\t\t\t\t\t\t<ns:HotelRef HotelCityCode=\"CITY_BCN_ES\" HotelCodeContext=\"LocationId\"/>\n" +
            "\t\t\t\t\t</ns:Criterion>\n" +
            "\t\t\t\t</ns:HotelSearchCriteria>\n" +
            "\t\t\t\t<ns:StayDateRange End=\"2012-09-25T00:00:00\" Start=\"2012-09-18T00:00:00\"/>\n" +
            "\t\t\t\t<ns:RoomStayCandidates>\n" +
            "\t\t\t\t\t<ns:RoomStayCandidate Quantity=\"1\" RPH=\"1\">\n" +
            "\t\t\t\t\t<ns:GuestCounts>\n" +
            "\t\t\t\t\t\t<ns:GuestCount AgeQualifyingCode=\"10\" Count=\"2\"/>\n" +
            "\t\t\t\t\t</ns:GuestCounts>\n" +
            "\t\t\t\t\t</ns:RoomStayCandidate>\n" +
            "\t\t\t\t</ns:RoomStayCandidates>\n" +
            "\t\t\t\t</ns:AvailRequestSegment>\n" +
            "\t\t\t\t</ns:AvailRequestSegments>\n" +
            "\t\t\t</ns:OTA_HotelAvailRQ>\n" +
            "\t\t</soapenv:Body>\n" +
            "</soapenv:Envelope>\n";

    @Test
    public void testGetSoapActionWithoutHeaders() throws Exception
    {
        assert "OTA_HotelAvail".equals(new GenericRequestWrapper(new HttpServletRequestMock())
                        {
            @Override
            public byte[] getArrayByte()
                            {
                try
                {
                    return HOTEL_MOCK_RESPONSE.getBytes("UTF-8");
                }
                catch (UnsupportedEncodingException e)
                                {
                    e.printStackTrace();
                    return new byte[0];
                }
            }
        }.getSoapAction());
    }
}
