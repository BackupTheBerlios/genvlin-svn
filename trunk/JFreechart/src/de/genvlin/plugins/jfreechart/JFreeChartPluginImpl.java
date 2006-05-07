/*
 * JFreeChartPluginImpl.java
 *
 * Created on 5. April 2006, 23:17
 *
 * genvlin project.
 * Copyright (C) 2005, 2006 Peter Karich.
 *
 * This project is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * version 2.1 of the License.
 *
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this project; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * or look at http://www.gnu.org
 */

package de.genvlin.plugins.jfreechart;

import de.genvlin.core.data.ID;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import de.genvlin.core.math.Function;
import de.genvlin.core.math.LinearFunction;
import de.genvlin.core.plugin.FitPlugin;
import de.genvlin.core.plugin.Log;
import de.genvlin.core.plugin.PlotPlugin;
import de.genvlin.core.plugin.PluginPool;
import de.genvlin.core.plugin.RequestEvent;
import de.genvlin.core.util.GTask;
import de.genvlin.gui.table.GTablePanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.Regression;

/**
 * JFreeChart acts as provider for the PluginSPI interface.
 *
 * @author Peter Karich
 */
public class JFreeChartPluginImpl implements
        PlotPlugin, ActionListener, FitPlugin {
    
    private String context[] = {PluginPool.TABLE, PluginPool.HEADER, PluginPool.SELECTED_COLS};
    private VectorInterface[] selected;
    
    private String LINEAR_REG = "Linear Regression";
    
    public String getName() {
        return "JFreechart";
    }
    
    protected String getMenuName() {
        return "XYLineChart";
    }
    
    public boolean plot(final XYPool xyVectorPool, final ID id) {
        final GTask t = new GTask() {
            ChartPanel panel;
            
            public void runTask() {
                XYVectorPoolWrapper dataset = new XYVectorPoolWrapper(xyVectorPool);
                
                JFreeChart chart = ChartFactory.createXYLineChart(
                        "JFreechart Plot Panel.",      // chart title
                        "X Title",                      // x axis label
                        "Y Title",                      // y axis label
                        dataset,                  // data
                        PlotOrientation.VERTICAL,
                        true,                     // include legend
                        true,                     // tooltips
                        false                     // urls
                        );
                
                panel = new ChartPanel(chart);
                panel.setName("plot:"+dataset.getID());
            }
            
            public Object getResult() {
                return panel;
            }
        };//GTask
        
        t.addChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName()==GTask.FINISHED) {
                    showPanel((ChartPanel)t.getResult(), "east");
                }
            }
        });
        t.start();
        return true;
    }
    
    public Function fit(XYInterface xyVector) {
        XYVectorPoolWrapper dataset = new XYVectorPoolWrapper();
        dataset.add(xyVector);
        
        double res[] = Regression.getOLSRegression(dataset, 0);
        //y = a+ b*x; res[0]-> a, res[1]->b
        
        LinearFunction lf = (LinearFunction)MainPool.getDefault().
                create(LinearFunction.class);
        double m = res[1];
        double n = res[0];
        lf.setM(m);
        lf.setN(n);
        lf.setRange(xyVector.getMinX(), xyVector.getMinY(),
                xyVector.getMaxX(), xyVector.getMaxY());
        Log.log("\n"+lf.toString(), true);
        
        return lf;
    }
    
    public String[] getActionContextReasons() {
        return context;
    }
    
    public void sendRequest(RequestEvent ri) {
        if(ri.getActionContextReason()==PluginPool.SELECTED_COLS) {
            selected = (VectorInterface[])((GTablePanel)ri.getSource()).getSelectedVectors();
            JMenu menu = new JMenu(getName());
            JMenuItem item = new JMenuItem(getMenuName());
            item.setActionCommand(getMenuName());
            menu.add(item);
            item.addActionListener(this);
            
            item = new JMenuItem(LINEAR_REG);
            item.setActionCommand(LINEAR_REG);
            menu.add(item);
            item.addActionListener(this);
            
            ((JPopupMenu)ri.getObject()).add(menu);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        XYPool pool = (XYPool)MainPool.getDefault().create(XYPool.class);
        XYInterface xyi;
        String ac = e.getActionCommand();
        
        for(int i=1; i < selected.length; i++) {
            xyi = pool.add(selected[0], selected[i]);
            if(ac == LINEAR_REG) {
                //make data to function
                xyi = fit(xyi);
            }
            //add the function
            pool.add(xyi);
        }
        if(pool.size()==0) {
            Log.log("Nothing to plot??", true);
            return;
        }
        //plot the fitted functions and its data OR:
        //only the selected XYVectoritnerface (the raw data)
        plot(pool, pool.getID());
    }
    
    protected boolean showPanel(Component panel, String position) {
        return PluginPool.getDefault().getPlatform().showPanel(panel, position);
    }
}