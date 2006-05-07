/*
 * TableDataObject.java
 *
 * Created on 10. März 2006, 17:02
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

import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;

/**
 *
 * @author Peter Karich
 */
public class TableDataObject extends MultiDataObject {
    
    /** Creates a new instance of TableDataObject */
    public TableDataObject(FileObject pf, TableDataLoader loader)
    throws DataObjectExistsException {
        super(pf, loader);
        
        CookieSet cookies = getCookieSet();
        
        cookies.add(new TableEditorSupport(this));
        cookies.add(new TableGUISupport(this));
        
        //for ImportAction:
        cookies.add(new TableOpenSupport(getPrimaryEntry()));
        
        cookies.add(new SaveSupport(getPrimaryEntry()));
    }
    
    /** Display another name in prop window:
     public String getName() {
     return "<"+getPrimaryFile().getName()+">";
     }*/
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected Node createNodeDelegate() {
        return new TableDataNode(this);
    }
    
    void addSaveCookie(SaveCookie save) {
        getCookieSet().add(save);
    }
    
    void removeSaveCookie(SaveCookie save) {
        getCookieSet().remove(save);
    }
}
