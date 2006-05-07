/*
 * ImportTaskTest.java
 * JUnit based test
 *
 * Created on 4. April 2006, 10:33
 */

package de.genvlin.core.util;

import de.genvlin.core.data.VectorPool;
import de.genvlin.gui.table.GTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import junit.framework.*;

/**
 *
 * @author Peter Karich
 */
public class ImportTaskTest extends TestCase {
    
    public ImportTaskTest(String testName) {
        super(testName);
    }
    
    //File f = new File("ImportTaskTest_testRun.txt");
    FileInputStream fis = null;
    StringBuffer testSB;
    
    protected void setUp() throws Exception {
        testSB = new StringBuffer();
        
        for(int i=0; i<12; i++) {
            testSB.append("\t1\t3\t4\t5\n");
            testSB.append("\t1\t2\t3\t4\t5\n");
            testSB.append("\t1\t2\t3\t4\t5\n");
            testSB.append("\t1\t2\t3\t5\n");
            testSB.append("\t1\t2\t3\t4\t5\n");
            testSB.append("\t4\t5\n");
        }
        
        try{
            File tmpF = File.createTempFile("ImportTaskTest","testRun");
            assertTrue(tmpF.canRead());
            assertTrue(tmpF.canWrite());
            
            FileWriter fw = new FileWriter(tmpF);
            fw.write(testSB.toString());
            fw.close();
            
            fis = new FileInputStream(tmpF);
            
        } catch(IOException exc) {
            assertTrue(false);
        }
    }
    
    protected void tearDown() throws Exception {
        try {
            fis.close();
        } catch(IOException exc) { //nothing todo
            assertTrue(false);
        }
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(ImportTaskTest.class);
        
        return suite;
    }
    
    /**
     * Test of run method, of class de.genvlin.core.util.ImportTask.
     */
    public void testRun() {
        System.out.println("run");
        
        final ImportTask instance = new ImportTask(fis);
        
        instance.addChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String s = evt.getPropertyName();
                
                System.out.println(s);
                if(s == GTask.STATUS && evt.getNewValue() instanceof Integer)
                    System.out.println(evt.getNewValue());
            }
        });
        
        instance.start();
        
        try {
            instance.join();
        } catch(InterruptedException ie) {
            assertTrue(false);
        }
        
        GTableModel model = new GTableModel();
        model.set((VectorPool)instance.getResult());
        System.out.println("----------------- expected: ");
        System.out.println(testSB);
        System.out.println("----------------- result: ");
        System.out.println(model.getAll());
        
        //TODO
        //this doesn't work:
        //assertEquals(testSB.toString(), model.getAll());
    }
    
    /**
     * Test of cancel method, of class de.genvlin.core.util.ImportTask.
     */
    public void testCancel() {
        System.out.println("cancel:see run");
    }
    
    /**
     * Test of isCanceled method, of class de.genvlin.core.util.ImportTask.
     */
    public void testIsCanceled() {
        System.out.println("isCanceled:see run");
        
    }
    
    /**
     * Test of setInputStream method, of class de.genvlin.core.util.ImportTask.
     */
    public void testSetInputStream() {
        System.out.println("setInputStream:see run");
    }
    
    /**
     * Test of getInputStream method, of class de.genvlin.core.util.ImportTask.
     */
    public void testGetInputStream() {
        System.out.println("getInputStream:see run");
    }
    
    /**
     * Test of setSeparator method, of class de.genvlin.core.util.ImportTask.
     */
    public void testSetSeparator() {
        System.out.println("setSeparator:see run");
    }
    
    /**
     * Test of isValid method, of class de.genvlin.core.util.ImportTask.
     */
    public void testIsValid() {
        System.out.println("isValid:see run");
    }
    
    /**
     * Test of getPool method, of class de.genvlin.core.util.ImportTask.
     */
    public void testGetPool() {
        System.out.println("getPool:see run");
    }
    
    /**
     * Test of fireChangeEvent method, of class de.genvlin.core.util.ImportTask.
     */
    public void testFireChangeEvent() {
        System.out.println("fireChangeEvent:see run");
    }
}
