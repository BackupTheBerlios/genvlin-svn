/*
 * ID.java
 *
 * Created on 17. März 2006, 15:59
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

package de.genvlin.core.data;

/** This interface defines the ID abilities and is "<tt>Comparable</tt>".
 * Known subclasses {@link StringID} and {@link IntID}. If you subclass from 
 * here be sure that all classes are compatible with each other! (-> compareTo/equals)
 * @author Peter Karich
 */
public interface ID extends Comparable {
}
