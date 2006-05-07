/*
 * TableOpenSupport.java
 *
 * Created on 14. März 2006, 13:01
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

import de.genvlin.core.plugin.Log;
import java.io.IOException;
import org.openide.cookies.CloseCookie;
import org.openide.cookies.OpenCookie;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;

/**
 *
 * @author Peter Karich
 */
public class TableOpenSupport extends OpenSupport
        implements OpenCookie, CloseCookie {
    
    public TableOpenSupport(MultiDataObject.Entry entry) {
        super(entry);
    }
    
    /*public boolean isOpen()
    {
        return !allEditors.isEmpty();
    }*/
    
    
    public boolean canClose() {
        if (!super.canClose()) {
            return false;
        }
        
        DataObject dob = entry.getDataObject();
        //TODO: add listeners(GTablePanel) to all its vectors
        //if (!dob.isModified()) {            return true;        }
        
        /**TableGUISupport tgs = (TableGUISupport)dob.getCookie(TableGUISupport.class);
        if (tgs == null) {
            // Should not happen.
            Log.log("Closing modified file, cannot save!",true);
            return true;
        }
         TODO: implement getOpenTables:
        if(tgs.getOpenTables() != null) {
            return true;
        }*/
        //boolean ret = tgs.superCanClose();
        //if(ret) tgs.close();
        //return ret;
        SaveCookie c = (SaveCookie)dob.getCookie(SaveCookie.class);
        if(c!=null) {
            try{
                c.save();
                return true;
            } catch(IOException ioe) {
                Log.log("Saving failed!("+ioe.getMessage()+")", true);
                return false;
            }
        } else {
            //shoud not happen
            return false;
        }
    }
    
    protected CloneableTopComponent createCloneableTopComponent() {
        return new TableTopComponent(entry);
    }
}
