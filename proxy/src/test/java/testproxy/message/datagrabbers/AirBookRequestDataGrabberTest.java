package testproxy.message.datagrabbers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testproxy.TestHelper;
import testproxy.httpservletwrappers.GenericRequestWrapperMock;
import testproxy.utils.SpringBeanContainer;

import java.util.Arrays;

/**
 * AirBookRequestDataGrabberTest
 */
public class AirBookRequestDataGrabberTest {
    private static final String SESSION_ID = "A3OE_951658ACFB3489F0420482DB0DCDF9AF";

    @BeforeMethod
    public void setUp() throws Exception {
        TestHelper.setupConsoleLogging();
        TestHelper.setupSessionsInfoManager(SESSION_ID, "OTA_AirBookLLS");
    }

    @Test
    public void testGrab() throws Exception {
        new AirBookRequestDataGrabber().grab(new GenericRequestWrapperMock(TestHelper.AIR_BOOK_REQUEST));
        String[] dptrDates = SpringBeanContainer.getSessionsInfoManager().getOriginDestinations(SESSION_ID);
        assert Arrays.equals(new String[] { "02SEP", "17OCT" }, dptrDates);
    }
}
