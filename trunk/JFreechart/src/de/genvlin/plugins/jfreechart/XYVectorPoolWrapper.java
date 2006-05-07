/*
 * XYVectorPoolWrapper.java
 *
 * Created on 7. April 2006, 19:30
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

import de.genvlin.core.data.AbstractCollection;
import de.genvlin.core.data.CollectionEvent;
import de.genvlin.core.data.IDData;
import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.PoolInterface;
import de.genvlin.core.data.VectorInterface;
import de.genvlin.core.data.XYInterface;
import de.genvlin.core.data.XYPool;
import java.util.HashSet;
import java.util.Iterator;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Peter Karich
 */
public class XYVectorPoolWrapper extends AbstractCollection
        implements XYDataset, PoolInterface {//,
    
    private XYPool pool;
    
    public XYVectorPoolWrapper() {
        this((XYPool)MainPool.getDefault().create(XYPool.class));
    }
    
    public XYVectorPoolWrapper(XYPool pool) {
        super(pool.getID());
        this.pool = pool;
    }
    
    public void add(XYInterface xyVector) {
        pool.add(xyVector);
    }
    
    public void add(VectorInterface x, VectorInterface y) {
        pool.add(x,y);
    }
    
    public DomainOrder getDomainOrder() {
        return DomainOrder.ASCENDING;
    }
    
    public int getItemCount(int series) {
        return ((XYInterface)pool.get(series)).size();
    }
    
    final static Double nan = new Double(Double.NaN);
    
    public Number getX(int series, int item) {
        Number n = ((XYInterface)pool.get(series)).getX(item);
        if(n == null) return nan;
        return n;
    }
    
    public double getXValue(int series, int item) {
        return getX(series, item).doubleValue();
    }
    
    public Number getY(int series, int item) {
        //cheating: JFreechart don't want null as x so we set y=NaN
        //if(((XYInterface)pool.get(series)).getX(item)== null)
        //    return nan;
        
        Number n = ((XYInterface)pool.get(series)).getY(item);
        if(n == null) return nan;
        return n;
    }
    
    public double getYValue(int series, int item) {
        return getY(series, item).doubleValue();
    }
    
    public int getSeriesCount() {
        return pool.size();
    }
    
    public Comparable getSeriesKey(int serie) {
        return pool.get(serie).getID();
    }
    
    public int indexOf(Comparable comparable) {
        return pool.indexOf(comparable);
    }
    
    private HashSet datasetListeners;
    
    private HashSet getDSListeners() {
        if(datasetListeners == null)
            datasetListeners = new HashSet();
        
        return datasetListeners;
    }
    
    
    public void addChangeListener(DatasetChangeListener datasetChangeListener) {
        getDSListeners().add(datasetChangeListener);
    }
    
    public void removeChangeListener(DatasetChangeListener datasetChangeListener) {
        getDSListeners().remove(datasetChangeListener);
    }
    
    DatasetGroup group;
    
    public DatasetGroup getGroup() {
        if(group == null)
            group = new DatasetGroup(pool.getID().toString());
        
        return group;
    }
    
    public void setGroup(DatasetGroup datasetGroup) {
        if(datasetGroup != null)
            group = datasetGroup;
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
    
    protected void fireEvent(CollectionEvent evt) {
        super.fireEvent(evt);
        fireDSEvent(evt);
    }
    
    protected void fireDSEvent(CollectionEvent evt) {
        HashSet pcl = getDSListeners();
        DatasetChangeEvent dsce = new DatasetChangeEvent(this, this);
        Iterator iter = pcl.iterator();
        while(iter.hasNext()) {
            ((DatasetChangeListener)iter.next()).datasetChanged(dsce);
        }        
    }
}