/*
 * PlotTopComponent.java
 *
 * Created on 1. April 2006, 17:30
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

package de.genvlin.platform.plot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.TopComponent;

/**
 *
 * @author Peter Karich
 */
public class PlotTopComponent extends CloneableTopComponent {
    
    private static final long serialVersionUID = 23423451261346L;

    final static private String idPreferred = "PlotTopComponent";
    
    private Component wrappedPanel;
    
    public PlotTopComponent(Component panel) {
        wrappedPanel = panel;
        setDisplayName(wrappedPanel.getName());
        setLayout(new BorderLayout());
        add(wrappedPanel, BorderLayout.CENTER);
    }        
    
    //to provide docking anywhere:(TODO: does not work?)
    public List availableModes(List modes) {
        return null;
    }
    
    protected String preferredID() {
        return idPreferred;
    }
    
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;//PERSISTENCE_ONLY_OPENED; //
    }
}
