/*
 * SeparatorPanelTest.java
 * JUnit based test
 *
 * Created on 13. März 2006, 22:25
 */

package de.genvlin.gui.util;

import de.genvlin.core.plugin.Platform;
import junit.framework.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author peter
 */
public class SeparatorPanelTest extends TestCase
{
    
    public SeparatorPanelTest(String testName)
    {
        super(testName);
    }
    
    protected void setUp() throws Exception
    {
    }
    
    protected void tearDown() throws Exception
    {
    }
    
    public static Test suite()
    {
        TestSuite suite = new TestSuite(SeparatorPanelTest.class);
        
        return suite;
    }
    
    /**
     * Test of getReturnValue method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testGetReturnValue()
    {
        System.out.println("getReturnValue");
        
        String expResult = SeparatorPanel.NEW_LINE;
        SeparatorPanel instance = new SeparatorPanel(expResult);
        String result = instance.getReturnValue();
        assertEquals(expResult, result);
        
        expResult = SeparatorPanel.TAB;
        instance = new SeparatorPanel(expResult);
        instance.actionPerformed(new ActionEvent(this,0, 
                instance.getTabString()));
        result = instance.getReturnValue();
        assertEquals(expResult, result);
        
        expResult = SeparatorPanel.SPACE;
        instance = new SeparatorPanel(expResult);
        instance.actionPerformed(new ActionEvent(this, 0, 
                instance.getTabString()));
        result = instance.getReturnValue();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of initRadioButton method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testInitRadioButton()
    {
        System.out.println("initRadioButton:is void");
    }
    
    /**
     * Test of setReturnValue method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testSetReturnValue()
    {
        System.out.println("setReturnValue:is void");
    }
    
    /**
     * Test of actionPerformed method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testActionPerformed()
    {
        System.out.println("actionPerformed:see getReturnValue");
        
    }
    
    /**
     * Test of keyPressed method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testKeyPressed()
    {
        System.out.println("keyPressed:is void");
    }
    
    /**
     * Test of keyReleased method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testKeyReleased()
    {
        System.out.println("keyReleased:is void");
    }
    
    /**
     * Test of keyTyped method, of class de.genvlin.util.SeparatorPanel.
     */
    public void testKeyTyped()
    {
        System.out.println("keyTyped:is void");
    }
    
}
