/*
 * DoubleVectorTest.java
 * JUnit based test
 *
 * Created on 9. März 2006, 00:33
 */

package de.genvlin.core.data;

import de.genvlin.core.plugin.Log;
import java.util.ArrayList;
import junit.framework.*;
import java.util.Collection;

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
    
    public void mySetUp() {
        try {
            for(int i=0; i<10; i++) dv.addDouble(i);
        } catch(Exception e) {
            Log.err("\nClear-exception while calling setUp():", false);
            Log.err(e, false);
        }
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
        dv.clear();
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
        dv.clear();
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
        dv.clear();
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
    
    /**
     * Test of remove method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testRemove() {
        System.out.println("remove");
        
        /*for(int i=0; i<34; i++)
            dv.addDouble(i);
         
        boolean expResult = true;
        boolean result = dv.remove(23);
        assertEquals(expResult, result);
         
        dv.clear();*/
    }
    
    /**
     * Test of set method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testSet() {
        System.out.println("set");
        
        for(int i=0; i<34; i++)
            dv.add(new Double(i));
        
        int ind = 23;
        Number expResult = new Double(ind);
        Number setted = new Double(2345);
        Number result = dv.set(ind, setted);
        assertEquals(expResult, result);
        assertEquals(setted, dv.get(ind));
        
        dv.clear();
    }
    
    /**
     * Test of iterator method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testIterator() {
        System.out.println("iterator");
        
        /*Iterator result = instance.iterator();
        assertEquals(expResult, result);
         */
    }
    
    /**
     * Test of trimToSize method, of class de.genvlin.core.data.DoubleVector.
     */
    public void testTrimToSize() {
        System.out.println("trimToSize");
        
        for(int i=0; i<25; i++)
            dv.addDouble(i);
        
        int start;
        for(int i=0; i<20; i++) {
            start = dv.getRawArray().length - i;
            dv.trimToSize(start);
            for(int n=0; n<dv.size(); n++) {
                try {
                    dv.set(n, new Double(23));
                }catch(Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
                }
            }
            for(int n=dv.size(); n<dv.size()+10; n++) {
                try {
                    dv.set(n, new Double(23));
                    assertTrue(false);
                }catch(Exception e) {
                }
            }
        }
        dv.clear();
    }
}
