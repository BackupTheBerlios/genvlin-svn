/*
 * StringIDTest.java
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
public class StringIDTest extends TestCase {
    
    public StringIDTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(StringIDTest.class);
        
        return suite;
    }
    
    /**
     * Test of compareTo method, of class de.genvlin.core.data.StringID.
     */
    public void testCompareTo() {
        System.out.println("compareTo");
        
        Object o = new StringID("test");
        StringID instance = new StringID("test");
        int result = instance.compareTo(o);
        assertTrue(result == 0);
        
        o = new StringID("atest");
        result = instance.compareTo(o);
        assertTrue(result>0);
        
        o = new StringID("utes");
        result = instance.compareTo(o);
        assertTrue(result<0);
    }
    
}
