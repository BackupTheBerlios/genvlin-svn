/*
 * RegressionLineTest.java
 * JUnit based test
 *
 * Created on 2. Mai 2006, 00:41
 */

package numbercruncher.mathutils;

import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class RegressionLineTest extends TestCase {
    
    public RegressionLineTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(RegressionLineTest.class);
        
        return suite;
    }
    
    public void testAll() {
        System.out.println("all:get, at, ...");
        
        RegressionLine rl = new RegressionLine();
        rl.addDataPoint(new DataPoint(20f, 59.4f));
        rl.addDataPoint(new DataPoint(406f, 661.6f));
        rl.addDataPoint(new DataPoint(733f, 1173.2f));
        rl.addDataPoint(new DataPoint(835f, 1332.5f));
        double delta = 0.01;
        
        assertEquals(1399750, rl.getSumXX(), delta);
        assertEquals(2242390.656, rl.getSumXY(), delta);
        assertEquals(3593197.266, rl.getSumYY(), delta);
        assertEquals(1994, rl.getSumX(), delta);
        assertEquals(3226.7, rl.getSumY(), delta);
        
        assertEquals(1.56228, rl.getM(), delta);
        assertEquals(27.8787, rl.getN(), delta);
        
        delta = 1e-8;
        
        assertEquals(0.14575771049415684, rl.getVariance(), delta);        
        assertEquals(0.0005993649209, rl.getMError(), delta);        
        assertEquals(0.3545574079, rl.getNError(), delta);        
        assertEquals(0.9999997792, rl.getCorrelationCoeff(), delta);         
    }
    
    /**
     * Test of size method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetDataPointCount() {
        System.out.println("getDataPointCount");
    }
    
    /**
     * Test of getN method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetN() {
        System.out.println("getN");
    }
    
    /**
     * Test of getM method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetM() {
        System.out.println("getM");
    }
    
    /**
     * Test of getMError method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetMError() {
        System.out.println("getMError");
    }
    
    /**
     * Test of getNError method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetNError() {
        System.out.println("getNError");
    }
    
    /**
     * Test of getCorrelationCoeff method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetCorrelationCoeff() {
        System.out.println("getCorrelationCoeff");
    }
    
    /**
     * Test of getSumX method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetSumX() {
        System.out.println("getSumX");
    }
    
    /**
     * Test of getSumY method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetSumY() {
        System.out.println("getSumY");
    }
    
    /**
     * Test of getSumXX method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetSumXX() {
        System.out.println("getSumXX");
    }
    
    /**
     * Test of getSumXY method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testGetSumXY() {
        System.out.println("getSumXY");
    }
    
    /**
     * Test of addDataPoint method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testAddDataPoint() {
        System.out.println("addDataPoint");
    }
    
    /**
     * Test of at method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testAt() {
        System.out.println("at");
    }
    
    /**
     * Test of reset method, of class numbercruncher.mathutils.RegressionLine.
     */
    public void testReset() {
        System.out.println("reset");
    }
    
}
