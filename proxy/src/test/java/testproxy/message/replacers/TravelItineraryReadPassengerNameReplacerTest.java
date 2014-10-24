package testproxy.message.replacers;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testproxy.TestHelper;
import testproxy.beans.PassengerName;
//import com.sabre.sabresonic.mockserver.core.beans.*;
import testproxy.utils.SpringBeanContainer;

/**
 * TravelItineraryReadPassengerNameReplacerTest
 */
public class TravelItineraryReadPassengerNameReplacerTest {
    static final String MOCK_RESPONSE =
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
                    "    <Email>Â\u0087NMVBPIVF@SABRE.COMÂ\u0087Â¤E</Email>\n" +
                    "    <Email>Â\u0087LINKAHH@SABRE.COMÂ\u0087Â¤E</Email>\n" +
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
                    "     <PricedItinerary InputMessage=\"WPRQÂ\u0087MUSDÂ\u0087P1ADT\" RPH=\"1\" StatusCode=\"A\" TaxExempt=\"false\" ValidatingCarrier=\"F7\">\n" +
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

    static final String MOCK_RESPONSE_2 =
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
                    "\t\t\t<eb:ConversationId>F7F7_5D78A3E61207FB986DFA73C3D16C0010</eb:ConversationId>\n" +
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
                    "\t\t\t\t\t\t<Email>Ã‚Â‡WALDEMAR.SOJKA@SABRE.COMÃ‚Â‡Ã‚Â¤E</Email>\n" +
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
                    "\t\t\t\t\t\t\t<PricedItinerary InputMessage=\"WPRQÃ‚Â‡MCHFÃ‚Â‡P1ADT\" RPH=\"1\" StatusCode=\"A\" TaxExempt=\"false\" ValidatingCarrier=\"F7\">\n" +
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
    private static final String SESSION_ID = "F7F7_5D78A3E61207FB986DFA73C3D16C0010";

    @BeforeMethod
    public void setUp() throws Exception {
        TestHelper.setupConsoleLogging();
        TestHelper.setupSessionsInfoManager(SESSION_ID, "TravelItineraryReadLLS");
    }

    @Test
    public void testReplace() throws Exception {
        PassengerName passengerName = new PassengerName("thali", "nilesh", "mr");
        SpringBeanContainer.getSessionsInfoManager().addPassengerName(SESSION_ID, passengerName);
        final String ACTUAL_RESULT = new TravelItineraryReadPassengerNameReplacer().replace(SESSION_ID, MOCK_RESPONSE);
        assert !ACTUAL_RESULT.contains("<GivenName>E MR</GivenName>");
        assert !ACTUAL_RESULT.contains("<Surname>RRPKROWATRBCGCGLEBFBRTWUGC</Surname>");
        assert ACTUAL_RESULT.contains("<GivenName>nilesh mr</GivenName>");
        assert ACTUAL_RESULT.contains("<Surname>thali</Surname>");
    }

    @Test
    public void testReplace2() throws Exception {
        SpringBeanContainer.getSessionsInfoManager().addPassengerName(SESSION_ID, new PassengerName("YXJNGTJFXFYXJNGTJFXFMR*ADTÃ‚Â§", null));
        final String ACTUAL_RESULT = new TravelItineraryReadPassengerNameReplacer().replace(SESSION_ID, MOCK_RESPONSE_2);
        assert !ACTUAL_RESULT.contains("<GivenName>QPDSVRBITV MR</GivenName>");
        assert !ACTUAL_RESULT.contains("<Surname>QPDSVRBITV</Surname>");
        assert ACTUAL_RESULT.contains("<GivenName>null null</GivenName>");
        assert ACTUAL_RESULT.contains("<Surname>YXJNGTJFXFYXJNGTJFXFMR*ADT");
    }
}
