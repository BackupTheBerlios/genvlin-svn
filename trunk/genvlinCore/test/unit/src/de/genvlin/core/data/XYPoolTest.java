/*
 * XYPoolTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import junit.framework.*;
import java.util.Comparator;

/**
 *
 * @author Peter Karich
 */
public class XYPoolTest extends TestCase {
    XYPool vp;
    
    public XYPoolTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        vp = (XYPool)MainPool.getDefault().create(XYPool.class);
    }
    
    protected void tearDown() throws Exception {
        vp.clear();
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(XYPoolTest.class);
        
        return suite;
    }
    
    /**
     * Test of create method, of class de.genvlin.core.data.XYPool.
     */
    public void testCreate() {
        System.out.println("createVector");
        
        XYInterface expResult = vp.create(VectorInterface.class,
                VectorInterface.class);
        
        IDData result = vp.get(expResult.getID());
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of add method, of class de.genvlin.core.data.XYPool.
     */
    public void testAdd() {
        System.out.println("add");
        
        VectorInterface x = (VectorInterface)MainPool.getDefault().create(VectorInterface.class);
        VectorInterface y = (VectorInterface)MainPool.getDefault().create(DoubleVectorInterface.class);
        
        XYInterface result = vp.add(x, y);
        
        assertNotNull(result);
        
    }
    
}