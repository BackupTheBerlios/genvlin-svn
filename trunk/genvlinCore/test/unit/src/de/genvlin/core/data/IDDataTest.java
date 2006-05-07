/*
 * IDDataTest.java
 * JUnit based test
 *
 * Created on 18. März 2006, 15:09
 */

package de.genvlin.core.data;

import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class IDDataTest extends TestCase {
    
    public IDDataTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(IDDataTest.class);
        
        return suite;
    }

    /**
     * Test of getID method, of class de.genvlin.core.data.IDData.
     */
    public void testGetID() {
        System.out.println("getID is void");
        
    }
    
}
