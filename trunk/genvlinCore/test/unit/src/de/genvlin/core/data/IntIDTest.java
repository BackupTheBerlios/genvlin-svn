/*
 * IntIDTest.java
 * JUnit based test
 *
 * Created on 17. März 2006, 21:38
 */

package de.genvlin.core.data;

import junit.framework.*;

/**
 *
 * @author peter
 */
public class IntIDTest extends TestCase {
    
    public IntIDTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(IntIDTest.class);
        
        return suite;
    }

    /**
     * Test of compareTo method, of class de.genvlin.core.data.IntID.
     */
    public void testCompareTo() {
        System.out.println("compareTo");
        
        IntID instance = new IntID(3);
        Object o = new IntID(2);
        
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        
        instance = new IntID(3);
        o = new IntID(4);
        
        expResult = -1;
        result = instance.compareTo(o);
        assertEquals(expResult, result);
        
        instance = new IntID(3);
        o = new IntID(3);
        
        expResult = 0;
        result = instance.compareTo(o);
        assertEquals(expResult, result);
        
        assertTrue(instance.equals(o));
        
    }
    
}
