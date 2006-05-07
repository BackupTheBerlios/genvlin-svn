/*
 * PoolInterfaceTest.java
 * JUnit based test
 *
 * Created on 18. März 2006, 15:09
 */

package de.genvlin.core.data;

import junit.framework.*;
import java.util.Iterator;

/**
 *
 * @author Peter Karich
 */
public class PoolInterfaceTest extends TestCase {
    
    public PoolInterfaceTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(PoolInterfaceTest.class);
        
        return suite;
    }

    
    /**
     * Test of get method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testGet() {
        System.out.println("get:is void");
    }

    /**
     * Test of iterator method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testIterator() {
        System.out.println("iterator:is void");
        
        }

    /**
     * Test of remove method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testRemove() {
        System.out.println("remove:is void");
        
    }

    /**
     * Test of create method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testCreate() {
            System.out.println("create:is void");
    
        }

    /**
     * Test of size method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testSize() {
            System.out.println("size:is void");
    
        }

    /**
     * Test of clear method, of class de.genvlin.core.data.PoolInterface.
     */
    public void testClear() {
            System.out.println("clear:is void");
    
            }
}
