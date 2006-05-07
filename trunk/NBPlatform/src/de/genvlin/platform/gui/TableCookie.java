/*
 * TableCookie.java
 *
 * Created on 14. März 2006, 01:00
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

import de.genvlin.core.data.VectorPool;
import de.genvlin.gui.table.GTablePanel;
import java.beans.PropertyChangeListener;
import org.openide.nodes.Node;
import org.openide.util.Task;

/** This interface defines important methods to recieve
 * a pool from file (or elsewhere).
 *
 * @author Peter Karich
 */
public interface TableCookie extends Node.Cookie
{
    //TODO: use Weakhashmap (AbstracVector.Set) see Line.Set???
    public VectorPool getPool();
    
    public void setPool(VectorPool table);
    
    /** May be we cannot parse the table data file correctly */
    public boolean isValid();
    
    /** @param obj specifies the column separator.<br>
     * If obj is null the separator from GProperties will be used.     
     */
    public void setSeparator(String s);
    
    /** Prepars the pool from file.
     */
    public Task prepare();
    public void addChangeListener(PropertyChangeListener cl);
    public void removeChangeListener(PropertyChangeListener cl);
    
    public GTablePanel[] getOpenTables();
}