/*
 * XYVectorPool_Graph2DModelWrapper.java
 *
 * Created on 25. April 2006, 22:55
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

import JSci.awt.AbstractGraphModel;
import JSci.awt.Graph2DModel;
import de.genvlin.core.data.CollectionListener;
import de.genvlin.core.data.ID;
import de.genvlin.core.data.IDData;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.PoolInterface;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import java.util.Iterator;

/**
 *
 * @author Peter Karich
 */
public class XYVectorPool_Graph2DModelWrapper extends AbstractGraphModel
        implements Graph2DModel, PoolInterface {
    
    private XYPool pool;
    private XYInterface currentVector;
    private Iterator iter;
    
    public XYVectorPool_Graph2DModelWrapper() {
        this((XYPool)MainPool.getDefault().create(XYPool.class));
    }
    
    public XYVectorPool_Graph2DModelWrapper(XYPool pool) {
        this.pool = pool;
    }       
    
    public float getXCoord(int i) {
        return currentVector.getX(i).floatValue();
    }
    
    public float getYCoord(int i) {
        return currentVector.getY(i).floatValue();
    }
    
    public int seriesLength() {
        return currentVector.size();
    }
    
    public void firstSeries() {
        iter = pool.iterator();
        iter.hasNext();
        currentVector = (XYInterface)iter.next();
    }
    
    public boolean nextSeries() {
        if(iter.hasNext()) {
            currentVector = (XYInterface)iter.next();
            return true;
        }
        return false;
    }
    
    public void add(XYInterface xyVector) {
        pool.add(xyVector);
    }
    
    public void add(VectorInterface x, VectorInterface y) {
        pool.add(x,y);
    }
    
    public boolean remove(Comparable id) {
        return pool.remove(id);
    }
    
    public IDData get(Comparable id) {
        return pool.get(id);
    }
    
    public IDData get(int index) {
        return pool.get(index);
    }
    
    public void addVectorListener(CollectionListener vl) {
        pool.addVectorListener(vl);
    }
    
    public void removeVectorListener(CollectionListener vl) {
        pool.removeVectorListener(vl);
    }
    
    public int size() {
        return pool.size();
    }
    
    public void clear() {
        pool.clear();
    }
    
    public Iterator iterator() {
        return pool.iterator();
    }
    
    public boolean remove(int index) {
        return pool.remove(index);
    }
    
    public String getInfo() {
        return pool.getInfo();
    }
    
    public String getTitle() {
        return pool.getTitle();
    }
    
    public void setInfo(String info) {
        pool.setInfo(info);
    }
    
    public void setTitle(String title) {
        pool.setTitle(title);
    }
    
    public ID getID() {
        return pool.getID();
    }
    
    public int compareTo(Object o) {
        return pool.compareTo(o);
    }

    public int indexOf(Comparable id) {
        return pool.indexOf(id);
    }

    public void update()
    {
        pool.update();
    }
}
