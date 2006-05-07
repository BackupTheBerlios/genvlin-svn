/*
 * VectorPoolTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class VectorPoolTest extends TestCase {
    VectorPool vp;
    
    public VectorPoolTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        vp =(VectorPool) MainPool.getDefault().create(VectorPool.class);
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(VectorPoolTest.class);
        
        return suite;
    }
    
    /**
     * Test of create method, of class de.genvlin.core.data.VectorPool.
     */
    public void testCreate() {
        System.out.println("create");
        
        Class clazz = VectorInterface.class;
        VectorInterface expResult = (VectorInterface)vp.create(clazz);
        assertNotNull(expResult);
        
        IDData result = vp.get(expResult.getID());
        assertNotNull(result);
        
        assertEquals(expResult, result);
        clazz = VectorInterface.class;
        expResult = vp.create(clazz);
        result = vp.get(expResult.getID());
        assertEquals(expResult, result);
        
        vp.clear();
        expResult = vp.create(clazz);
        result = vp.get(expResult.getID());
        assertEquals(expResult, result);
        
        vp.clear();
    }
    
    /**
     * Test of add method, of class de.genvlin.core.data.VectorPool.
     */
    public void testAdd() {
        System.out.println("add");
        
        VectorInterface av = (VectorInterface)vp.create(VectorInterface.class);
        
        boolean expResult = false;
        boolean result = vp.add(av);
        assertEquals(expResult, result);
        
    }
    
}
