package testproxy.message.datagrabbers;

import org.testng.annotations.Test;
import testproxy.httpservletwrappers.GenericRequestWrapper;
import testproxy.httpservletwrappers.GenericRequestWrapperMock;

/**
 * DataGrabberCompositeTest
 */
public class DataGrabberCompositeTest {
    private int count;

    @Test
    public void testGrab() throws Exception {
        DataGrabberComposite dataGrabber = new DataGrabberComposite();
        dataGrabber.add(new DataGrabberMock());
        dataGrabber.add(new DataGrabberMock());
        assert 0 == count;
        dataGrabber.grab(new GenericRequestWrapperMock("request"));
        assert 2 == count;
    }

    class DataGrabberMock implements DataGrabber {
        @Override
        public void grab(GenericRequestWrapper request) {
            count++;
        }
    }
}
