/*
 * PoolExplorer.java
 *
 * Created on 21. März 2006, 22:02
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

package de.genvlin.platform.gui;

import de.genvlin.core.data.MainPool;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import org.openide.ErrorManager;
import org.openide.explorer.ExplorerManager;//now we need Explorer API and Prop...
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.TreeTableView;
import org.openide.loaders.DataNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/** This is a explorer top component which allows the user a visual
 * representation of the available data in the MainPool.
 * (implementation via singleton)
 * @author Peter Karich
 */
public class PoolExplorer extends CloneableTopComponent
        implements ExplorerManager.Provider, Lookup.Provider, 
        PropertyChangeListener{
    private Node node;
    private ExplorerManager manager;
    private Lookup lookup;    
    
    private static final String PREFERRED_ID = "poolExplorer"; // NOI18N    
    /** The actual id of the singleton instance */
    private static String singletonID = PREFERRED_ID;    
    
    /** This component will live in explorermode. */
    //private static final String MODE = "explorer"; // NOI18N
    
    
    public static synchronized void activate() {
        TopComponent c = getInstance();
        c.open();
        c.requestActive();
    }
    
    public static synchronized PoolExplorer getInstance() {
        TopComponent c;
        c = WindowManager.getDefault().findTopComponent(singletonID);
        if (c == null) {
            c = new PoolExplorer();
            singletonID = WindowManager.getDefault().findTopComponentID(c);
        }
        return (PoolExplorer)c;
    }
    
    /** only public for serialization */
    public PoolExplorer() {
        init(new DataNode(null, new MainPoolChildren(MainPool.getDefault())));
    }
    
    public void init(Node node) {
        this.node = node;
        setDisplayName(NbBundle.getMessage(
                PoolExplorer.class, "PE_title"));
        
        setLayout(new BorderLayout());
               
        TreeTableView tv = new TreeTableView();
        tv.setDefaultActionAllowed(false);
        tv.setDragSource(false);
        tv.setDropTarget(false);
        tv.setPopupAllowed(true);
        tv.setRootVisible(false);
        tv.expandAll();
        add(tv, BorderLayout.CENTER);
        
        //org.openide.explorer.view.
        ActionMap map = getActionMap();
        manager = new ExplorerManager();
        manager.setRootContext(node);
        //        ExplorerUtils
        map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(manager));
        map.put(DefaultEditorKit.cutAction, ExplorerUtils.actionCut(manager));
        map.put(DefaultEditorKit.pasteAction, ExplorerUtils.actionPaste(manager));
        map.put("delete", ExplorerUtils.actionDelete(manager, true));
        
        InputMap keys = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        keys.put(KeyStroke.getKeyStroke("control C"), DefaultEditorKit.copyAction);
        keys.put(KeyStroke.getKeyStroke("control X"), DefaultEditorKit.cutAction);
        keys.put(KeyStroke.getKeyStroke("control V"), DefaultEditorKit.pasteAction);
        keys.put(KeyStroke.getKeyStroke("DELETE"), "delete");
        
        lookup = ExplorerUtils.createLookup(manager, map);
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        //if(evt.getPropertyName().equals(ExplorerManager.PROP_SELECTED_NODES))
        ErrorManager.getDefault().log("yeah node selected!"+ node.getName());
    }
    //protected void componentActivated() {    }
    public void addNotify() {
        super.addNotify();
        ExplorerUtils.activateActions(getExplorerManager(), true);
        getExplorerManager().addPropertyChangeListener(this);
    }
    
    //protected void componentDeactivated() {    }
    public void removeNotify() {
        getExplorerManager().removePropertyChangeListener(this);
        ExplorerUtils.activateActions(getExplorerManager(), false);
        super.removeNotify();
    }
    
    public ExplorerManager getExplorerManager() {
        return manager;
    }
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    public Lookup getLookup() {
        return lookup;
    }
    
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }
}