
package BusinessLogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationTest {
    
    public AuthenticationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        System.out.println("* UtilsJUnit4Test: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
         System.out.println("* UtilsJUnit4Test: @AfterClass method");
    }
    
    @Before
    public void setUp() 
    {
        System.out.println("* UtilsJUnit4Test: @Before method");
    }
    
    @After
    public void tearDown() 
    {
        System.out.println("* UtilsJUnit4Test: @After method");
    }

    /**
     * Test of validate method, of class Authentication.
     */
    @Test
    public void testValidate() {
        System.out.println("validate test");
        String user1 = "teste";
        String pass1 = "teste";
        String user2 = "a";
        String pass2 = "a";
        Authentication instance = new Authentication();
        boolean expResult1 = true;
        boolean result1 = instance.validate(user1, pass1);
        boolean expResult2 = false;
        boolean result2 = instance.validate(user2, pass2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);

        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of emailcheck method, of class Authentication.
     */
    @Test
    public void testEmailcheck() {
        System.out.println("emailcheck");
        String email1 = "fgkjdj";
        String email2 = "teste@gmail.com";
        Authentication instance = new Authentication();
       boolean expResult1 = false;
        boolean result1 = instance.emailcheck(email1);
        boolean expResult2 = true;
        boolean result2 = instance.emailcheck(email1);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encrypt method, of class Authentication.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String str = "test";
        Authentication instance = new Authentication();
        String expResult = "098f6bcd4621d373cade4e832627b4f6";
        String result = instance.encrypt(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Authentication.
     */
    /*@Test
    public void testLogin() {
        System.out.println("login");
        String user = "";
        String pass = "";
        Authentication instance = new Authentication();
        String expResult = "";
        String result = instance.login(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class Authentication.
     */
    /*
    @Test
    public void testRegister() {
        System.out.println("register");
        String name = "";
        String user = "";
        String pass = "";
        String email = "";
        String question = "";
        String answer = "";
        Authentication instance = new Authentication();
        int expResult = 0;
        int result = instance.register(name, user, pass, email, question, answer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
