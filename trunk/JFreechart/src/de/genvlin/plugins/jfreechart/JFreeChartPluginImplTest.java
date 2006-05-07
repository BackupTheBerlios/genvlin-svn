/*
 * JFreeChartPluginImplTest.java
 * JUnit based test
 *
 * Created on 9. April 2006, 10:45
 */

package de.genvlin.plugins.jfreechart;

import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.MainPool;
//import junit.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 *
 * @author Peter Karich
 */
public class JFreeChartPluginImplTest { //extends TestCase {
    
    public JFreeChartPluginImplTest(String testName) {
        //super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    /*
    public static Test suite() {
        TestSuite suite = new TestSuite(JFreeChartPluginImplTest.class);
        
        return suite;
    }*/
    
    /**
     * Test of getName method, of class de.genvlin.plugins.jfreechart.JFreeChartPluginImpl.
     */
    public void testGetName() {
        System.out.println("getName:nothing todo..");
    }
    
    /**
     * Test of actionPerformed method, of class de.genvlin.plugins.jfreechart.JFreeChartPluginImpl.
     */
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        
        ActionEvent e = null;
        //JFreeChartPluginImpl instance = new JFreeChartPluginImpl();
        
        JPanel panel = new JPanel();
        XYVectorPoolWrapper dataset = new XYVectorPoolWrapper();
        
        VectorInterface dv1 = (VectorInterface)MainPool.getDefault().create(VectorInterface.class);
        VectorInterface dv2 = (VectorInterface)MainPool.getDefault().create(VectorInterface.class);
        
        dv1.add(new Double(1));
        dv1.add(new Double(2));
        dv1.add(new Double(3));
        dv1.add(new Double(4));
        dv1.add(new Double(5));
        dv1.add(new Double(6));
        
        dv2.add(new Double(0));
        dv2.add(new Double(3));
        dv2.add(new Double(2));
        dv2.add(new Double(3));
        dv2.add(new Double(7));
        dv2.add(new Double(5));
        
        dataset.add(dv1, dv2);
        /*
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "JFreechart Plot Panel.",      // chart title
                "X Title",                      // x axis label
                "Y Title",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
                );
        
        new Thread( new Runnable(){
            public void run() {
                
                JFrame f = new JFrame();
                f.setSize(400,400);
                f.setContentPane(new ChartPanel(chart));
                f.setVisible(true);
            }}).start();*/
    }
    
    /**
     * Test of getComponentClass method, of class de.genvlin.plugins.jfreechart.JFreeChartPluginImpl.
     */
    public void testGetComponentClass() {
        System.out.println("getComponentClass: nothing todo..");
    }
    
    /**
     * Test of getPopup method, of class de.genvlin.plugins.jfreechart.JFreeChartPluginImpl.
     */
    public void testGetPopup() {
        System.out.println("getPopup:nothing todo..");
    }
    
}
