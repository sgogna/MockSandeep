package testproxy.message.datagrabbers;

import org.springframework.beans.factory.annotation.Autowired;
import testproxy.httpservletwrappers.GenericRequestWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * DataGrabberComposite
 */
public class DataGrabberComposite implements DataGrabber {
    @Autowired
    private List<DataGrabber> dataGrabbers;

    @Override
    public void grab(GenericRequestWrapper request) {
        for (DataGrabber dataGrabber: dataGrabbers)
            dataGrabber.grab(request);
    }

    public void setDataGrabbers(List<DataGrabber> dataGrabbers) {
        this.dataGrabbers = dataGrabbers;
    }

    /**
     * Only used by unit test. application uses spring to set dataGrabbers
     * @param dataGrabber
     */
    public void add(DataGrabber dataGrabber) {
        if ( dataGrabbers == null )
            dataGrabbers = new ArrayList<DataGrabber>();
        dataGrabbers.add(dataGrabber);
    }
}
