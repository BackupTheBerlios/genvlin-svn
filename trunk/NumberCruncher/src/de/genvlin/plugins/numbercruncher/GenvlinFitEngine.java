/*
 * GenvlinFitEngine.java
 *
 * Created on 1. Mai 2006, 21:10
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

package de.genvlin.plugins.numbercruncher;

import de.genvlin.core.data.ID;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import de.genvlin.core.math.*;
import de.genvlin.core.plugin.FitPlugin;
import de.genvlin.core.plugin.PluginPool;
import de.genvlin.core.plugin.Log;
import de.genvlin.core.plugin.RequestEvent;
import de.genvlin.gui.table.GTablePanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import numbercruncher.mathutils.DataPoint;
import numbercruncher.mathutils.RegressionLine;

/**
 *
 *
 * @author Peter Karich
 */
public class GenvlinFitEngine implements FitPlugin, ActionListener {
    
    private String context[] = {PluginPool.TABLE, PluginPool.HEADER, PluginPool.SELECTED_COLS};
    private VectorInterface[] selected;
    private String LINEAR_REG = "Linear Regression";
    
    /** Creates a new instance of GenvlinFitEngine */
    public GenvlinFitEngine() {
    }
    
    public String getName() {
        return "NumberCruncher";
    }
    
    public Function fit(XYInterface xyVector) {
        RegressionLine line = new RegressionLine();
        double tmpX, tmpY;
        for(int i=0; i < xyVector.size(); i++)            {
            tmpX=xyVector.getXDouble(i);
            tmpY=xyVector.getYDouble(i);
            line.addDataPoint(tmpX, tmpY);
        }
        
        LinearFunction lf = (LinearFunction)MainPool.getDefault().create(LinearFunction.class);
        lf.setM(line.getM());
        lf.setN(line.getN());
        lf.setRange(xyVector.getMinX(), xyVector.getMinY(),
                xyVector.getMaxX(), xyVector.getMaxY());
        Log.log("\n"+lf.toString(), true);
        Log.log("r="+line.getCorrelationCoeff(), true);
        Log.log("y = mx+n; err(m)="+line.getMError()+"; err(n)="
                +line.getNError(), true);
        
        return lf;
    }
    
    public String[] getActionContextReasons() {
        return context;
    }
    
    public void sendRequest(RequestEvent ri) {
        if(ri.getActionContextReason()==PluginPool.SELECTED_COLS) {
            selected = (VectorInterface[])((GTablePanel)ri.getSource()).getSelectedVectors();
            JMenu menu = new JMenu(getName());
            JMenuItem item = new JMenuItem(LINEAR_REG);
            item.setActionCommand(LINEAR_REG);
            menu.add(item);
            item.addActionListener(this);
            
            ((JPopupMenu)ri.getObject()).add(menu);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        XYPool pool = (XYPool)MainPool.getDefault().create(XYPool.class);
        XYInterface xyi;
        
        for(int i=1; i < selected.length; i++) {
            xyi = pool.add(selected[0], selected[i]);
            //make data to function
            xyi = fit(xyi);
            //add the function
            pool.add(xyi);
        }
        if(pool.size()==0) {
            Log.log("Nothing to plot??", true);
            return;
        }
        //plot the fitted functions and its data:
        plot(pool, pool.getID());
    }
    
    public boolean plot(final XYPool xyVectorPool, final ID id) {
        return PluginPool.getDefault().getPlotEngine().plot(xyVectorPool, id);
    }
}
