/*
 * IDTest.java
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
public class IDTest extends TestCase {
    
    public IDTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(IDTest.class);
        
        return suite;
    }
    
    public void testCheck() {
    }
}
