import junit.framework.TestCase;

import junit.framework.*;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.assertEquals;
public class UtilsTest extends TestCase {

   @Test
   public void test() {	
	  Utils test = new Utils(); 
	  /* testing the email validator*/
	  assertEquals(true, test.validateEmail("blah@blah.com"));
      assertEquals(true, test.validateEmail("a@blah.com"));
      assertEquals(false, test.validateEmail("@blah.com"));
      assertEquals(false, test.validateEmail("blah@.com"));
      assertEquals(false, test.validateEmail("blah@blah."));
      assertEquals(false, test.validateEmail("@.blah"));
      assertEquals(false, test.validateEmail("blah.com"));
      assertEquals(false, test.validateEmail("blah@com"));
      assertEquals(false, test.validateEmail("blah@."));
      
      /*testing the phone validator*/
      assertEquals("1234567890", test.convertPhone("123-456-7890"));
      assertEquals(null, test.convertPhone("123"));
      assertEquals(null, test.convertPhone("123123123412"));
      assertEquals("1234567890", test.convertPhone("12a3dg45678sdf90"));
   }
}
