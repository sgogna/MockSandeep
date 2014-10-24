package testproxy.message.datagrabbers;

import testproxy.httpservletwrappers.GenericRequestWrapper;

/**
 * DataGrabber
 */
public interface DataGrabber {
    public void grab(GenericRequestWrapper request);
}
