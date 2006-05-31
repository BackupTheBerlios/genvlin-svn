/*
 * XYVectorTest.java
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
public class XYVectorTest extends TestCase
{
    XYPool vp;
    XYVectorInterface xyV;
    
    public XYVectorTest(String testName)
    {
        super(testName);
    }
    
    protected void setUp() throws Exception
    {
        vp = (XYPool)MainPool.getDefault().create(XYPool.class);
    }
    
    protected void tearDown() throws Exception
    {
    }
    
    public static Test suite()
    {
        TestSuite suite = new TestSuite(XYVectorTest.class);
        
        return suite;
    }
    
    public XYVectorInterface initData()
    {
        return vp.create(VectorInterface.class, VectorInterface.class);
    }
    
    public XYVectorInterface initDouble()
    {
        return vp.create(VectorInterface.class, VectorInterface.class);
    }
    
    public XYVectorInterface initDataDouble()
    {
        return vp.create(VectorInterface.class, VectorInterface.class);
    }
    
    public XYVectorInterface initDataDoubleDiffSize()
    {
        VectorInterface x = (VectorInterface)MainPool.getDefault().create(VectorInterface.class);
        VectorInterface y = (VectorInterface)MainPool.getDefault().create(DoubleVectorInterface.class);
        
        x.add(new Double(49.2345d));
        
        XYVectorInterface tmp = (XYVectorInterface)MainPool.getDefault().create(x, y);
        
        return tmp;
    }
    
    /**
     * Test of add method, of class de.genvlin.core.data.XYVector.
     */
    public void testAddAndGet()
    {
        System.out.println("add");
        
        
        startAllCombinations(new RunTest()
        {
            public void run(Number xNum, Number yNum)
            {
                xyV.add(xNum, yNum);
                
                Number resultX = xyV.getX(0);
                Number resultY = xyV.getY(0);
                
                /*if(resultX instanceof Double)
                    assertEquals(xNum.doubleValue(), 
                            resultX.doubleValue(),1e-10);
                else if(resultX instanceof Float)
                    assertEquals(((Number)xNum).floatValue(), 
                            ((Number)resultX).floatValue(),1e-10);
                else
                    assertEquals(xNum, resultX);
                
                if(resultY instanceof Double)
                    assertEquals(((Number)yNum).doubleValue(), 
                            ((Number)resultY).doubleValue(),1e-10);
                else if(resultY instanceof Float)
                    assertEquals(((Number)yNum).floatValue(), 
                            ((Number)resultY).floatValue(),1e-10);
                else
                    assertEquals(yNum, resultY);
                */
                assertEquals(yNum.doubleValue(), resultY.doubleValue(), 1e-10);

            }
            
        });
    }
    
    /**
     * Test of size method, of class de.genvlin.core.data.XYVector.
     */
    public void testSize()
    {
        System.out.println("size");
        
        startAllCombinations(new RunTest()
        {
            public void run(Number xNum, Number yNum)
            {
                xyV.add(xNum, yNum);
                xyV.add(xNum, yNum);
                xyV.add(xNum, yNum);
                assertTrue(3 == xyV.size());
                vp.clear();
            }
        });
    }
    
    private void startAllCombinations(RunTest r)
    {
        Number xNum;
        Number yNum;
        
        for(int n=0; n<3; n++)
        {
            if(n==0)
            {
                xNum = new Integer(17);
                yNum = new Integer(45);
            }
            else if(n==1)
            {
                xNum = new Double(23452.23453);
                yNum = new Integer(23452*23);
                
            }
            else //if(n==2)
            {
                xNum = new Double(23452.23453);
                yNum = new Double(23452.23453*23);
                
            }
            
            for(int i=0; i<3; i++)
            {
                switch(i)
                {
                    case 0: xyV = initData(); break;
                    case 1: xyV = initDouble(); break;
                    case 2: xyV = initDataDouble(); break;
                    case 3: xyV = initDataDoubleDiffSize(); break;
                    default:
                        xyV = initData();
                }
                System.out.println("'number'Loop="+n+"  'initData'Loop="+i);
                r.run(xNum, yNum);
            }
        }
    }
    
    interface RunTest
    {
        public void run(Number xNum, Number yNum);
    }



    /**
     * Test of add method, of class de.genvlin.core.data.XYVector.
     */
    public void testAdd()
    {
        System.out.println("add: see testAddAndGet");
        
    }

    /**
     * Test of getY method, of class de.genvlin.core.data.XYVector.
     */
    public void testGetY()
    {
        System.out.println("getY: see testAddAndGet");
        
    }
    
        /**
     * Test of getX method, of class de.genvlin.core.data.XYVector.
     */
    public void testGetX()
    {
        System.out.println("getX: see initDataDoubleDiffSize");
        
    }

    /**
     * Test of getID method, of class de.genvlin.core.data.XYVector.
     */
    public void testGetID() {
        System.out.println("getID");
        
        XYVectorInterface instance = (XYVectorInterface)vp.create(VectorInterface.class, DoubleVectorInterface.class);        
        ID result = instance.getID();
        assertNotNull(result);
        
        XYVectorInterface instance2 = (XYVectorInterface)vp.create(VectorInterface.class, DoubleVectorInterface.class);
        ID result2 = instance2.getID();
        
        assertNotNull(result2);
        
        assertTrue(result.compareTo(result2)<0);
    }

    
}
