/*
 * PoolNode.java
 *
 * Created on 26. März 2006, 23:28
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

import de.genvlin.core.data.PoolInterface;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author Peter Karich
 */
public class PoolNode extends AbstractNode
{
    PoolInterface pool;
    
    /** Creates a new instance of PoolNode */
    public PoolNode(PoolInterface pool)
    {
        super(Children.LEAF);
        this.pool = pool;
        setName("id:"+pool.getTitle());
        setShortDescription(pool.getInfo());    
    }
    
    /*
    public Action[] getActions(boolean context) {
        return null;
    }*/
}
