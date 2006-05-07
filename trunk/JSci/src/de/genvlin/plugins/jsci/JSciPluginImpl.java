/*
 * JSciPluginImpl.java
 *
 * Created on 25. April 2006, 22:45
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

package de.genvlin.plugins.jsci;

import JSci.awt.Graph2D;
import JSci.swing.JGraphLayout;
import JSci.swing.JLineGraph;
import de.genvlin.core.data.ID;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import de.genvlin.core.plugin.PlotPlugin;
import de.genvlin.core.plugin.PluginPool;
import de.genvlin.core.plugin.RequestEvent;
import de.genvlin.core.util.GTask;
import de.genvlin.gui.table.GTablePanel;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * This class enables jsci in genvlin.
 * @author Peter Karich
 */
public class JSciPluginImpl implements
        PlotPlugin, ActionListener{
    
    private String context[] = {PluginPool.TABLE, PluginPool.HEADER, PluginPool.SELECTED_COLS};
    private VectorInterface[] selected;
    
    
    public String getName() {
        return "JSci";
    }
    
    public String getMenuName() {
        return "JLineGraph";
    }
    public boolean plot(final XYPool xyVectorPool, final ID id) {
        final GTask t = new GTask() {
            
            JPanel panel;
            
            public void runTask() {
                XYVectorPool_Graph2DModelWrapper dataset = new XYVectorPool_Graph2DModelWrapper();
                
                for(int i=0; i<xyVectorPool.size(); i++)
                    dataset.add((XYInterface)xyVectorPool.get(i));
                
                final Font titleFont=new Font("Default",Font.BOLD,14);
                Label title;
                title = new Label("Line graph",Label.CENTER);
                title.setFont(titleFont);
                JLineGraph lineGraph = new JLineGraph(dataset);
                lineGraph.setGridLines(true);
                lineGraph.setMarker(new Graph2D.DataMarker.Circle(5));
                panel = new JPanel(new JGraphLayout());
                panel.add(title, JGraphLayout.TITLE);
                panel.add(lineGraph, JGraphLayout.GRAPH);
                panel.setName("plot:"+dataset.getID());
            }
            
            public Object getResult() {
                return panel;
            }
        };//GTask
        
        t.addChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName()==GTask.FINISHED)
                    showPanel((Component)t.getResult(), "east");
            }
        });
        t.start();
        return true;
    }
    
    public String[] getActionContextReasons() {
        return context;
    }
    
    public void sendRequest(RequestEvent ri) {
        if(ri.getActionContextReason()==PluginPool.SELECTED_COLS) {
            selected = (VectorInterface[])((GTablePanel)ri.getSource()).getSelectedVectors();
            JMenu menu = new JMenu(getName());
            JMenuItem item = new JMenuItem(getMenuName());
            menu.add(item);
            item.addActionListener(this);
            ((JPopupMenu)ri.getObject()).add(menu);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        XYPool pool = (XYPool)MainPool.getDefault().create(XYPool.class);
        
        for(int i=1; i < selected.length; i++)
            pool.add(selected[0], selected[i]);
        plot(pool, pool.getID());
    }
    
    protected boolean showPanel(Component panel, String position) {
        return PluginPool.getDefault().getPlatform().showPanel(panel, position);
    }
}
