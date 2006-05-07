/*
 * LinearFunctionTest.java
 * JUnit based test
 *
 * Created on 1. Mai 2006, 22:49
 */

package de.genvlin.core.math;

import de.genvlin.core.data.MainPool;
import java.util.Random;
import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class LinearFunctionTest extends TestCase {
    
    LinearFunction lf;
    double delta = 1e-19;
    int SIZE = 2000;
    double MINX=10, MINY=10, MAXX=20, MAXY=20;
    
    public LinearFunctionTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        lf = (LinearFunction)MainPool.getDefault().create(LinearFunction.class);
        lf.setM(1);
        lf.setN(1);
        lf.setRange(MINX,MINY,MAXX,MAXY);
        lf.setSize(SIZE);
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(LinearFunctionTest.class);
        
        return suite;
    }
    
    /**
     * Test of setRange method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testSetRange() {
        System.out.println("setRange");
        
        
    }
    
    /**
     * Test of getMaxX method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetMaxX() {
        System.out.println("getMaxX");
    }
    
    /**
     * Test of getMinX method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetMinX() {
        System.out.println("getMinX");
    }
    
    /**
     * Test of getMaxY method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetMaxY() {
        System.out.println("getMaxY");
    }
    
    /**
     * Test of getMinY method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetMinY() {
        System.out.println("getMinY");
    }
    
    /**
     * Test of setSize method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testSetSize() {
        System.out.println("setSize");
        
        //int size = 2000;
        //lf.setSize(size);
        //assertEquals(lf.size(), size);
    }
    
    /**
     * Test of at method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testAt() {
        System.out.println("at");
        
        double x = 10.0;
        
        double expResult = 11.0;
        double result = lf.at(x);
        assertEquals(expResult, result, delta);
    }
    
    /**
     * Test of getM method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetM() {
        System.out.println("getM");
    }
    
    /**
     * Test of getN method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetN() {
        System.out.println("getN");
    }
    
    /**
     * Test of setM method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testSetM() {
        System.out.println("setM");
    }
    
    /**
     * Test of setN method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testSetN() {
        System.out.println("setN");
    }
    
    /**
     * Test of derivativeAt method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testDerivativeAt() {
        System.out.println("derivativeAt");
        
        double x = 10.0;
        
        double expResult = 1.0;
        double result = lf.derivativeAt(x);
        assertEquals(expResult, result, delta);
        
    }
    
    /**
     * Test of getX method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetX() {
        System.out.println("getX");
        
        double expResult = MINX;
        double result = lf.getX(0).doubleValue();
        assertEquals(expResult, result, delta);
        
        int index = 333;
        expResult = MINX + index*(MAXX - MINX)/SIZE;
        result = lf.getX(index).doubleValue();
        assertEquals(expResult, result, delta);
        
        Random r = new Random(12345);
        int tmp;
        double xTmp, yTmp;
        double maxX, maxY;
        
        for(int n=0; n< 10; n++) {
            maxX = Math.abs(r.nextDouble() * 1000)+1;
            maxY = Math.abs(r.nextDouble() * 1000)+1;
            lf.setRange(-maxX, -maxY, maxX, maxY);
            for(int i=0; i< 10; i++) {
                tmp = r.nextInt(lf.size());
                xTmp = lf.getX(tmp).doubleValue();
                yTmp = lf.getY(tmp).doubleValue();
                expResult = lf.at(xTmp);
                result = yTmp;
                assertEquals(expResult, result, delta);
            }
        }
    }
    
    /**
     * Test of getY method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetY() {
        System.out.println("getY");
        
        double expResult = lf.at(MINY);
        double result = lf.getY(0).doubleValue();
        assertEquals(expResult, result, delta);
        
        int index = 333;
        expResult = lf.at(MINY + index*(MAXY - MINY)/SIZE);
        result = lf.getY(index).doubleValue();
    }
    
    /**
     * Test of getXDouble method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetXDouble() {
        System.out.println("getXDouble");
    }
    
    /**
     * Test of getYDouble method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testGetYDouble() {
        System.out.println("getYDouble");
    }
    
    /**
     * Test of size method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testSize() {
        System.out.println("size");
        
        int expResult = SIZE;
        int result = lf.size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of clear method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testClear() {
        System.out.println("clear");
    }
    
    /**
     * Test of iterator method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testIterator() {
        System.out.println("iterator");
    }
    
    /**
     * Test of remove method, of class de.genvlin.core.math.LinearFunction.
     */
    public void testRemove() {
        System.out.println("remove");
    }
}
