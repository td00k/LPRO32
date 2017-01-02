
package DataAccess;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JDBCHandlerTest {
    
    public JDBCHandlerTest() {
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
     * Test of run method, of class JDBCHandler.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        int type = 0;
        String query = "";
        String[] args = null;
        JDBCHandler instance = null;
        String[] expResult = null;
        String[] result = instance.run(type, query, args);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of open method, of class JDBCHandler.
     */
    @Test
    public void testOpen() {
        System.out.println("open");
        JDBCHandler instance = null;
        int expResult = 0;
        int result = instance.open();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class JDBCHandler.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        JDBCHandler instance = null;
        int expResult = 0;
        int result = instance.close();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
