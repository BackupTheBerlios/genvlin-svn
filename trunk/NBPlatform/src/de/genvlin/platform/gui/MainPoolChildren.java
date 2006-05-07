/*
 * MainPoolChildren.java
 *
 * Created on 26. März 2006, 22:14
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

import de.genvlin.core.data.CollectionEvent;
import de.genvlin.core.data.CollectionListener;
import de.genvlin.core.data.ID;
import de.genvlin.core.data.IDData;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.PoolInterface;
import de.genvlin.core.data.VectorInterface;
import java.util.Collections;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Peter Karich
 */
public class MainPoolChildren extends Children.Keys
        implements CollectionListener{
    
    MainPool mp;
    
    public MainPoolChildren(MainPool mp) {
        this.mp = mp;
    }
    
    public MainPoolChildren() {
        mp = MainPool.getDefault();
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
        IDData data = mp.get((ID)key);
        
        if(data instanceof PoolInterface)
            return new Node[]{new PoolNode((PoolInterface)data)};
        
        return new Node[]{new VectorNode((VectorInterface)data)};
    }
    
    boolean added = false;
    
    protected void addNotify() {
        super.addNotify();
        mp.addVectorListener(this);
        added = true;
        vectorChanged(null);
    }
    protected void removeNotify() {
        mp.removeVectorListener(this);
        added = false;
        setKeys(Collections.EMPTY_SET);
        super.removeNotify();
    }
    
    public void vectorChanged(CollectionEvent e) {
        int size = mp.size();
        ID[] ids = new ID[size];
        
        for(int i=0; i< size; i++) {
            ids[i] = mp.get(i).getID();
        }
        
        setKeys(ids);        
    }
}

