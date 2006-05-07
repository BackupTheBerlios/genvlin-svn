/*
 * SaveSupport.java
 *
 * Created on 23. April 2006, 15:18
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

import de.genvlin.core.util.GTask;
import de.genvlin.core.plugin.Log;
import de.genvlin.core.util.SaveTask;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.JLabel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileLock;
import org.openide.loaders.DataObject;
import org.openide.loaders.MultiDataObject;

/** This class provides the save capability.
 *
 * @author Peter Karich
 */
public class SaveSupport implements SaveCookie {
    MultiDataObject.Entry entry;
    DataObject dObj;
    
    public SaveSupport(MultiDataObject.Entry entry) {
        this.entry = entry;
        dObj = entry.getDataObject();
    }
    
    public void save() throws IOException {
        String s = "Saving:"+entry.getDataObject().getName()+"?";
        DialogDescriptor diaDesc = new DialogDescriptor(
                new JLabel(s), s, true, DialogDescriptor.YES_NO_CANCEL_OPTION,
                null, null);
        DialogDisplayer.getDefault().createDialog(diaDesc).setVisible(true);
        
        if(diaDesc.getValue() == DialogDescriptor.CANCEL_OPTION)
            throw new IOException("User Canceled Saving!");
        
        if(diaDesc.getValue() == DialogDescriptor.YES_OPTION) {
            Log.log("Saving...", true);
            
            FileLock fl = entry.takeLock();//new FileLock(); will fail?
            SaveTask st = new SaveTask(dObj.getPrimaryFile().getOutputStream(fl));
            fl.releaseLock();            
            st.addChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent evt) {
                    if(evt.getPropertyName()==GTask.FINISHED)
                        Log.log("Saving finished!", true);
                }
            });
            TableGUISupport tgs=(TableGUISupport)dObj.getCookie(TableGUISupport.class);
            //shouldn't block...
            st.setPool(tgs.getPool());
            st.start();
        }
        else Log.log("Not Saved!", true);
    }
}