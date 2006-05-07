/*
 * GTablePanelTest.java
 * JUnit based test
 *
 * Created on 25. April 2006, 13:24
 */

package de.genvlin.gui.table;

import javax.swing.JFrame;
import junit.framework.*;

/**
 * A simple class to test the TablePanel via GUI.
 * @author Peter Karich
 */
public class GTablePanelTest extends TestCase {
    
    public GTablePanelTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(GTablePanelTest.class);
        
        return suite;
    }        
    
    /**
     */
    public void testAll() {
        System.out.println("junit: test all");
        GTableModel m = new GTableModel();
        m.setValueAt(new Double(1),0,0);
        m.setValueAt(new Double(2),0,1);
        m.setValueAt(new Double(3),0,2);
        m.setValueAt(new Double(4),0,3);
        
        JFrame f = new JFrame();
        f.getContentPane().add(new GTablePanel(m).getComponent());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,600);
        f.setVisible(true);
        
        //HACK:enable it to test it in a GUI-matter - TODO: write abbot-test code
        /*try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }        */
    }
    
}
