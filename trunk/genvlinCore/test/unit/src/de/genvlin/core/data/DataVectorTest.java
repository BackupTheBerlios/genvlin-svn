/*
 * DataVectorTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import de.genvlin.core.plugin.Log;
import junit.framework.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Peter Karich
 */
public class DataVectorTest extends TestCase {
    DataVector dv;
    
    public DataVectorTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        dv = (DataVector)MainPool.getDefault().create(VectorInterface.class);
    }
    
    public void mySetUp() {
        try {
            for(int i=0; i<10; i++) dv.add(new Integer(i));
        } catch(Exception e) {
            Log.err("\nClear-exception while calling setUp():", false);
            Log.err(e, false);
        }
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(DataVectorTest.class);
        
        return suite;
    }
    
    /**
     * Test of getDouble method, of class de.genvlin.core.data.DataVector.
     */
    public void testGetDouble() {
        System.out.println("getDouble");
        
        mySetUp();
        double expResult = 0.0;
        double result;
        
        for(int i=0; i<10; i++) {
            result = dv.getDouble(i);
            expResult = i;
            assertEquals(expResult, result, 0);
        }
        dv.clear();
    }
    
    /**
     * Test of iterator method, of class de.genvlin.core.data.DataVector.
     */
    public void testIterator() {
        System.out.println("iterator");
        
        mySetUp();
        
        Iterator result = dv.iterator();
        
        assertNotNull(result);
        
        assertTrue(result.hasNext());
        
        for(int i=0; result.hasNext(); i++) {
            assertEquals(new Integer(i), result.next());
        }
        dv.clear();
    }
    
    /**
     * Test of size method, of class de.genvlin.core.data.DataVector.
     */
    public void testSize() {
        System.out.println("size");
        mySetUp();
        int expResult = 10;
        int result = dv.size();
        assertEquals(expResult, result);
        dv.clear();
    }
    
    /**
     * Test of add method, of class de.genvlin.core.data.DataVector.
     */
    public void testAdd() {
        System.out.println("add");
        
        mySetUp();
        assertEquals(10, dv.size());
        
        Number n = new Double(1.34d);
        
        boolean expResult = true;
        boolean result = dv.add(n);
        
        assertEquals(expResult, result);
        
        assertTrue(11 == dv.size());
        
        assertEquals(n, dv.get(10));
        dv.clear();
    }
    
    /**
     * Test of get method, of class de.genvlin.core.data.DataVector.
     */
    public void testGet() {
        System.out.println("get");
        
        mySetUp();
        Number expResult = new Integer(3);
        Number result = dv.get(3);
        assertEquals(expResult, result);
        dv.clear();
    }
    
    /**
     * Test of clear method, of class de.genvlin.core.data.DataVector.
     */
    public void testClear() {
        System.out.println("clear");
        mySetUp();
        assertTrue(10 == dv.size());
        dv.clear();
        assertTrue(0 == dv.size());        
    }
    
     /**
     * Test of clear method, of class de.genvlin.core.data.DataVector.
     */
    public void testEquals() {
        System.out.println("equals");
        mySetUp();
        assertTrue(10 == dv.size());
        dv.clear();
        assertTrue(0 == dv.size());        
    }
    
    /**
     * Test of addAll method, of class de.genvlin.core.data.DataVector.
     */
    public void testAddAll() {
        System.out.println("addAll");
        
        Collection coll = new ArrayList();
        
        coll.add(new Double(12.3));
        coll.add(new Double(22.3));
        coll.add(new Double(32.3));
        coll.add(new Double(42.3));
        
        
        dv.addAll(coll);
        
        
        assertEquals(32.3d, dv.getDouble(2), 1e-10);
        dv.clear();
    }    
}
