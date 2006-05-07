/*
 * DoubleVectorTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import java.util.ArrayList;
import junit.framework.*;
import java.util.Collection;
import java.util.Arrays;

/**
 *
 * @author Peter Karich
 */
public class DoubleVectorTest extends TestCase {
    DoubleVector dv;
    double ep = 0.00001;
    
    public DoubleVectorTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        dv = (DoubleVector)MainPool.getDefault().create(DoubleVectorInterface.class);
        
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(DoubleVectorTest.class);
        
        return suite;
    }
    
    /**
     * Test of toArray method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testToArray() {
        System.out.println("toArray");
        
        for(double i=ep; i<3; i++) dv.addDouble(i);
        
        double[] expResult = new double[]{ep, 1+ep, 2+ep};
        double[] result = dv.toArray();
        assertTrue(expResult[1] == result[1]);
        
        dv.clear();
    }
    
    /**
     * Test of getDouble method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testGetDouble() {
        System.out.println("getDouble");
        
        for(double i=ep; i<200; i++) dv.addDouble(i);
        
        double expResult = 199+ep;
        double result = dv.getDouble(200-1);
        assertEquals(expResult, result, 0);
        dv.clear();
    }
    
    /**
     * Test of setDouble method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testSetDouble() {
        System.out.println("setDouble");
        
        for(double i=ep; i<200; i++) dv.addDouble(i);
        
        double d = 0.0;
        int i = 200-1;
        
        dv.setDouble(d, i);
        
        double expResult = d;
        double result = dv.getDouble(i);
        assertEquals(expResult, result,0);
        dv.clear();
    }
    
    /**
     * Test of addDouble method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testAddDouble() {
        System.out.println("addDouble");
        
        double value = ep;
        DoubleVector instance = null;
        
        assertTrue(0 == dv.size());
        dv.addDouble(value);
        assertTrue(1 == dv.size());
        
    }
    
    /**
     * Test of insert method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testInsert() {
        System.out.println("insert");
        
        double value = ep;
        int at = 10;
        
        for(double i=ep; i<200; i++) dv.addDouble(i);
        
        dv.insert(at, value);
        
        assertTrue(value == dv.getDouble(at));
        
        dv.clear();
    }
    
    /**
     * Test of addAll method, of class de.genvlin.core.data.DoubleVector.
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
        
        
        double test[] = {12.2, 234.4, 32.3};
        dv.addAll(test);
        
        assertEquals(32.3d, dv.getDouble(2), 1e-10);
    }
    
    /**
     * Test of clear method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testClearAndAdd() {
        System.out.println("clear and add");
        
        dv.add(new Integer(0));
        
        assertTrue(1 == dv.size());
        dv.clear();
        assertTrue(0 == dv.size());
    }
    
    /**
     * Test of size method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testSize() {
        System.out.println("size");
        
        for(double i=0.0001; i<100; i++) dv.addDouble(i);
        
        int expResult = 100;
        int result = dv.size();
        assertEquals(expResult, result);        
    }
    
    /**
     * Test of get method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testGet() {
        System.out.println("get");
        
        dv.addDouble(23.78);
        
        Number expResult = new Double(23.78);
        Number result = dv.get(0);
        assertEquals(expResult, result);
        
        dv.clear();        
    }
    
    /**
     * Test of getRawArray method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testGetRawArray() {
        System.out.println("getRawArray: should not used directly!");
        
    }
    
    /**
     * Test of clear method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testClear() {
        System.out.println("clear: see testClearAndAdd");
    }
    
    /**
     * Test of add method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testAdd() {
        System.out.println("add: see testClearAndAdd");
    }
}
