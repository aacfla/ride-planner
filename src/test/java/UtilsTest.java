import junit.framework.TestCase;

import junit.framework.*;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.assertEquals;
public class UtilsTest extends TestCase {

    @Test
    public void testEmailValidator() {
    	
    	/* testing the email validator*/
    	assertEquals(true, Utils.validateEmail("blah@blah.com"));
    	assertEquals(true, Utils.validateEmail("a@blah.com"));
    	assertEquals(false, Utils.validateEmail("@blah.com"));
    	assertEquals(false, Utils.validateEmail("blah@.com"));
    	assertEquals(false, Utils.validateEmail("blah@blah."));
    	assertEquals(false, Utils.validateEmail("@.blah"));
    	assertEquals(false, Utils.validateEmail("blah.com"));
    	assertEquals(false, Utils.validateEmail("blah@com"));
    	assertEquals(false, Utils.validateEmail("blah@."));
    }
    
    public void testPhoneConverter() {
    	
    	/*testing the phone validator*/
    	assertEquals("1234567890", Utils.convertPhone("123-456-7890"));
    	assertEquals(null, Utils.convertPhone("123"));
    	assertEquals(null, Utils.convertPhone("123123123412"));
    	assertEquals("1234567890", Utils.convertPhone("12a3dg45678sdf90"));
    }
}
