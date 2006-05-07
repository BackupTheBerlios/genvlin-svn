/*
 * MainPoolTest.java
 * JUnit based test
 *
 * Created on 17. März 2006, 21:38
 */

package de.genvlin.core.data;

import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class MainPoolTest extends TestCase {
    
    public MainPoolTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(MainPoolTest.class);
        
        return suite;
    }
    
    /**
     * Test of getDefault method, of class de.genvlin.core.data.MainPool.
     */
    public void testGetDefault() {
        System.out.println("getDefault: is void");
        
    }
    
    /**
     * Test of importPool method, of class de.genvlin.core.data.MainPool.
     */
    public void testImportPool() {
        System.out.println("importPool: not yet implemented!");
    }
    
    /**
     * Test of create method, of class de.genvlin.core.data.MainPool.
     */
    public void testCreate() {
        System.out.println("create");
        
        MainPool instance = MainPool.getDefault();
        IDData result;
        Class clazzes[] = { DoubleVectorInterface.class, XYPool.class,
        VectorPool.class, VectorInterface.class};
        
        for(int i=0; i<clazzes.length; i++)
            try {
                result = instance.create(clazzes[i]);
                assertNotNull(result);
                //assertTrue(result instanceof DataVector);
            } catch(Exception e) {
                assertTrue(false);
            }
        
        Class[] notThisClazzes = { String.class, MainPool.class};
        
        for(int i=0; i<notThisClazzes.length; i++)
            try {
                result = instance.create(notThisClazzes[i]);
                assertTrue(false);
            } catch(Exception e) {
                assertTrue(true);
            }
        
    }
}
