package testproxy.servlets;

import com.sabre.ssw.proxy.defines.ProxyMode;
import fileManagers.MainFileManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testproxy.TestHelper;
import testproxy.beans.PassengerName;
//import com.sabre.sabresonic.mockserver.core.beans.*;
import testproxy.config.Configuration;
import testproxy.connectors.B6.GdsSabreConnector;
import testproxy.connectors.Connector;
import testproxy.httpservletwrappers.GenericRequestWrapperMock;
import testproxy.httpservletwrappers.GenericResponseWrapper;
import testproxy.httpservletwrappers.GenericResponseWrapperMock;
import testproxy.httpservletwrappers.GenericWrapper;
import testproxy.message.datagrabbers.AirBookRequestDataGrabber;
import testproxy.message.datagrabbers.DataGrabberComposite;
import testproxy.message.datagrabbers.PassengerNameDataGrabber;
import testproxy.message.replacers.DataReplacerComposite;
import testproxy.message.replacers.IA_DepartureDatesReplacer;
import testproxy.message.replacers.TravelItineraryReadPassengerNameReplacer;
import testproxy.servicemanagment.ServiceManager;
import testproxy.servicemanagment.ServiceNode;
import testproxy.utils.SpringBeanContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * ProxyHttpServletTest
 */
public class ProxyHttpServletTest {

    public static final String SESSION_ID = "F7F7_5D78A3E61207FB986DFA73C3D16C0010";
    private ProxyHttpServlet phs;
    private GenericRequestWrapperMock travelItinReadRequestWrapper = new GenericRequestWrapperMock(TestHelper.TRAVEL_ITIN_READ_REQUEST);
    private GenericRequestWrapperMock sabreCommandLLSRequestWrapper = new GenericRequestWrapperMock(TestHelper.SABRE_COMMAND_LLS_4DOCS_REQUEST);
    private GenericRequestWrapperMock airBookRequestWrapper = new GenericRequestWrapperMock(TestHelper.AIR_BOOK_REQUEST);

    // set by each test
    private String mockResponse;
    private boolean saveMessageCalled;

    @BeforeMethod
    public void setUp() {
        TestHelper.setupConsoleLogging();

        phs = new ProxyHttpServlet() {
            @Override
            Connector getConnector(HttpServletRequest req) {
                return new GdsSabreConnector();
            }

            @Override
            void setPath(String sessionId, String path) {
                // completely copied from parent and then deleted the concurrent requests manager thingy
                if (sessionsInfoManager.getPath(sessionId).isEmpty())
                {
                    sessionsInfoManager.setPath(sessionId, path);
                }
            }

            @Override
            Properties getHeadersMap(HttpServletRequest request) {
                return new Properties();
            }

            @Override
            byte[] getRecordedMessage(String requestName, String pathToRecord, Properties headers, boolean isRequest, String rqMessage, Properties rqHeaders) {
                try {
                    return mockResponse.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return new byte[0];
                }
            }

            @Override
            void saveMessage(GenericWrapper message, String requestName, boolean isRequest, String path, Properties headers) {
                saveMessageCalled = true;
            }
        };
        phs.configuration = new Configuration();
        phs.configuration.setMode(ProxyMode.REPLAY.name());
        phs.configuration.setFixResponse(true);
        phs.dataGrabberComposite = new DataGrabberComposite();
        ((DataGrabberComposite)phs.dataGrabberComposite).add(new PassengerNameDataGrabber());
        ((DataGrabberComposite)phs.dataGrabberComposite).add(new AirBookRequestDataGrabber());
        phs.dataReplacerComposite = new DataReplacerComposite();
        ((DataReplacerComposite)phs.dataReplacerComposite).add(new TravelItineraryReadPassengerNameReplacer());
        ((DataReplacerComposite)phs.dataReplacerComposite).add(new IA_DepartureDatesReplacer());
        TestHelper.setupSessionsInfoManager(SESSION_ID, "TravelItineraryReadLLS");
        phs.sessionsInfoManager = SpringBeanContainer.getSessionsInfoManager();
        phs.mainFileManager = new MainFileManager();
        phs.serviceManager = new ServiceManager();
    }

    @Test
    public void testProcessRequestForNameReplacementInTravelItineraryReadRequest() throws Exception {
        createServiceNode("TravelItineraryReadLLS", ".*Transaction Code=\\\"([a-zA-Z]{0,9}[0-9]{0,9}).*#.*UniqueID ID=.([a-zA-Z]{0,9}).*/#");
        phs.sessionsInfoManager.getCount(SESSION_ID, "TravelItineraryReadLLS");
        PassengerName passengerName = new PassengerName("thali", "nilesh", "mr");
        phs.sessionsInfoManager.addPassengerName(SESSION_ID, passengerName);

        this.mockResponse = TestHelper.TRAVEL_ITINERARY_READ_MOCK_RESPONSE;
        GenericResponseWrapper responseWrapper = new GenericResponseWrapperMock();
        phs.processRequest(travelItinReadRequestWrapper, responseWrapper, "F7");
        String responseString = ((GenericResponseWrapperMock) responseWrapper).getResponseString();
//        assert responseString.contains("<GivenName>nilesh mr</GivenName>");
 //       assert responseString.contains("<Surname>thali</Surname>");
    }

    @Test
    public void testGetCommandNameForSabreCommandLLS() throws Exception {
        String testId = phs.getCommandName(sabreCommandLLSRequestWrapper);
        assert "SecureFlight".equals(testId) : "Expected 4DOCSDB01JAN80MTHALINILESH-1.1: Got " + testId;
    }

    @Test
    public void testGetCommandNameForNonSabreCommandLLS() throws Exception {
        createServiceNode("OTA_AirBookLLS", ".*LocationCode=.([a-zA-Z]{0,3}).*/#");
        String testId = phs.getCommandName(airBookRequestWrapper);
        assert "MNLILOILOMNL_".equals(testId);
    }

    /**
     * This is more of an integration/acceptance test that simulates multiple requests
     * @throws Exception
     */
    @Test
    public void testProcessRequestAirBookFollowedByIAReplacesOriginDestinationsInIAResponse() throws Exception {
        TestHelper.setupSessionsInfoManager("A3OE_951658ACFB3489F0420482DB0DCDF9AF", "OTA_AirBookLLS");
        // AirBook request
        createServiceNode("OTA_AirBookLLS", ".*LocationCode=.([a-zA-Z]{0,3}).*/#");
        mockResponse = TestHelper.AIR_BOOK_RESPONSE;
        phs.processRequest(new GenericRequestWrapperMock(TestHelper.AIR_BOOK_REQUEST),
                new GenericResponseWrapperMock(), "2P");
        // IA request
        createServiceNode("SabreCommandLLS", "0");
        mockResponse = TestHelper.SABRE_COMMAND_LLS_IA_RESPONSE;
        GenericResponseWrapperMock iaResponse = new GenericResponseWrapperMock();
        phs.processRequest(new GenericRequestWrapperMock(TestHelper.SABRE_COMMAND_LLS_IA_REQUEST),
                iaResponse, "2P");
        String iaResponseString = iaResponse.getResponseString();
//        assert iaResponseString.contains("02SEP");
//        assert iaResponseString.contains("17OCT");
    }

    @Test
    public void testProcessRequestForOTA_VehLocSearchRQ_WhichDoesntHaveSessionOrConversationId() throws Exception {
        phs.configuration.setMode(ProxyMode.RECORD.name());
        createServiceNode("OTA_VehLocSearch", "0");
        try {
        phs.processRequest(new GenericRequestWrapperMock(TestHelper.OTA_VEHLOCSEARCH_RQ),
                new GenericResponseWrapperMock(), "EY");
        }
        catch (NullPointerException npe) {

        }
    }

    private void createServiceNode(String serviceName, String regex) {
        ServiceNode travelItineraryReadServiceNode = new ServiceNode(serviceName, null);
        travelItineraryReadServiceNode.setRegex(regex);
        phs.serviceManager.getServicelist().add(travelItineraryReadServiceNode);
    }
}
