package testproxy.beans;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import com.sabre.sabresonic.mockserver.core.beans.*;
/**
 * PassengerNameCommandTest
 */
public class PassengerNameCommandTest {
    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        assert "PassengerNameCommand".equalsIgnoreCase(new PassengerNameCommand("-1YXJNGTJFXF/AAAAAAAAA MR*ADT§").toString());
    }

    @Test
    public void testGetPassengerName1() throws Exception {
        PassengerNameCommand cmd = new PassengerNameCommand("-1YXJNGTJFXF/AAAAAAAAA MR*ADT§");
        assert "YXJNGTJFXF".equals(cmd.getPassengerName().last );
        assert "AAAAAAAAA".equals(cmd.getPassengerName().first );
        assert "MR".equals(cmd.getPassengerName().title );
        assert "ADT".equals(cmd.getPassengerName().type );
    }

    @Test
    public void testGetPassengerNameInLowerCase() throws Exception {
        PassengerNameCommand cmd = new PassengerNameCommand("-1thali/nilesh MR*ADT§");
        assert "THALI".equals(cmd.getPassengerName().last );
        assert "NILESH".equals(cmd.getPassengerName().first );
        assert "MR".equals(cmd.getPassengerName().title );
        assert "ADT".equals(cmd.getPassengerName().type );
    }

    @Test
    public void testGetPassengerName2() throws Exception {
        PassengerNameCommand cmd = new PassengerNameCommand("-1YXJNGTJFXF/AAAAAAAAA MR");
        assert "YXJNGTJFXF".equals(cmd.getPassengerName().last );
        assert "AAAAAAAAA".equals(cmd.getPassengerName().first );
        assert "MR".equals(cmd.getPassengerName().title );
    }
}
