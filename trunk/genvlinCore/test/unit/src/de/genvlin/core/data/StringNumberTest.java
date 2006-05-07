/*
 * StringNumberTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import junit.framework.*;

/**
 *
 * @author peter
 */
public class StringNumberTest extends TestCase {
    StringNumber instance;
    String str="hallo";
    
    
    public StringNumberTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        instance = new StringNumber(str);
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(StringNumberTest.class);
        
        return suite;
    }
    
    /**
     * Test of toString method, of class de.genvlin.core.data.StringNumber.
     */
    public void testToString() {
        System.out.println("toString");
        
        StringNumber instance = new StringNumber("hallo");
        
        String expResult = "hallo";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doubleValue method, of class de.genvlin.core.data.StringNumber.
     */
    public void testDoubleValue() {
        System.out.println("doubleValue");
        
        try {
            double result = instance.doubleValue();
            assertTrue(false);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    
    /**
     * Test of floatValue method, of class de.genvlin.core.data.StringNumber.
     */
    public void testFloatValue() {
        System.out.println("floatValue");
        
        try {
            double result = instance.floatValue();
            assertTrue(false);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
        
    }
    
    /**
     * Test of intValue method, of class de.genvlin.core.data.StringNumber.
     */
    public void testIntValue() {
        System.out.println("intValue");
        
        try {
            double result = instance.intValue();
            assertTrue(false);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    
    /**
     * Test of longValue method, of class de.genvlin.core.data.StringNumber.
     */
    public void testLongValue() {
        System.out.println("longValue");
        
        try {
            double result = instance.longValue();
            assertTrue(false);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    
}
