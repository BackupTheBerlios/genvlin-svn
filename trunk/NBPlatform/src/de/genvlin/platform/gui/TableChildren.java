/*
 * TableChildren.java
 *
 * Created on 20. März 2006, 23:39
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

import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.ID;
import de.genvlin.core.data.VectorPool;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Task;
import org.openide.util.TaskListener;

/**
 *
 * @author Peter Karich
 */
public class TableChildren extends Children.Keys
        implements PropertyChangeListener, TaskListener{
    
    TableCookie tc;
    VectorPool vp;
    
    public TableChildren(TableCookie tc) {
        this.tc = tc;        
    }
    
    /** <p>Typical usage:
     * <ol>
     * <li>Subclass.
     * <li>Decide what type your key should be.
     * <li>Implement {@link #createNodes} to create some nodes
     * (usually exactly one) per key.
     * <li>Override {@link Children#addNotify} to compute a set of keys
     * and set it using {@link #setKeys(Collection)}.
     * The collection may be ordered.
     * <li>Override {@link Children#removeNotify} to just call
     * <code>setKeys</code> on {@link Collections#EMPTY_SET}.
     * <li>When your model changes, call <code>setKeys</code>
     * with the new set of keys. <code>Children.Keys</code> will
     * be smart and calculate exactly what it needs to do effficiently.
     * <li><i>(Optional)</i> if your notion of what the node for a
     * given key changes (but the key stays the same), you can
     * call {@link #refreshKey}. Usually this is not necessary.
     * </ol>
     */
    void test(){}
    protected Node[] createNodes(Object key) {
        return new Node[]{new VectorNode((VectorInterface)vp.get((ID)key))};
    }
    
    boolean added = false;

    /*
    protected void addNotify() {
        if(vp==null) return;
        
        if(!added) {
            tc.addChangeListener(this);
            added = true;
        }
        setKeys(ids);
    }
    
    protected void removeNotify() {
        setKeys(Collections.EMPTY_SET);
        tc.removeChangeListener(this);
    }
*/    
    protected void addNotify() {
        super.addNotify();
        tc.addChangeListener(this);
        added = true;
        propertyChange(null);
    }
    protected void removeNotify() {
        tc.removeChangeListener(this);
        added = false;
        setKeys(Collections.EMPTY_SET);
        super.removeNotify();
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        if (added) {
        //    tc.prepare().addTaskListener(this);
        }
    }
    
    public void taskFinished(Task task) {
        if(tc.isValid()) {
            vp = tc.getPool();
            int size = vp.size();
            ID[] ids = new ID[size];
            
            for(int i=0; i< size; i++) {
                ids[i] = vp.get(i).getID();
            }
            
            setKeys(ids);
        }
    }
}
