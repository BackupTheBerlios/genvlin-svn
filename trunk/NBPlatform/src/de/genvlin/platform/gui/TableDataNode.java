/*
 * TableDataNode.java
 *
 * Created on 10. März 2006, 17:21
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

import java.awt.Image;
import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;

/**
 * @author Peter Karich
 */
public class TableDataNode extends DataNode {
    public TableDataNode(TableDataObject obj) {
        super(obj, Children.LEAF);
        //new TableChildren((TableCookie)obj.getCookie(TableCookie.class))); Table is not a leaf anymore!
    }
    
    public Image getIcon(int type) {
        //if (type == BeanInfo.ICON_COLOR_16x16 || type == BeanInfo.ICON_MONO_16x16)
        //Image icon =
        return Utilities.loadImage("de/genvlin/platform/resources/Table.png");
                /*
        ScoreCookie cookie = (ScoreCookie)getCookie(ScoreCookie.class);
        if (cookie != null) {
            if (inUse) {
                cookie.prepare();
            }
            if (!cookie.isValid()) {
                Image badge = Utilities.loadImage("org/netbeans/examples/modules/minicomposer/error-badge.gif");
                icon = Utilities.mergeImages(icon, badge, 8, 8);
            }
        }
        try {
            DataObject obj = getDataObject();
            icon = obj.getPrimaryFile().getFileSystem().getStatus().annotateIcon(icon, type, obj.files());
        } catch (FileStateInvalidException fsie) {
        }
        return icon;
                 */
    }
    
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    
}