/*
 * TableGUISupport.java
 *
 * Created on 14. März 2006, 01:24
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
import de.genvlin.core.util.GTask;
import de.genvlin.core.util.ImportTask;
import de.genvlin.core.plugin.Log;
import de.genvlin.gui.table.GTablePanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataObject;
import org.openide.util.Cancellable;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;

/** This class provides opening support in the JTable-like manner.
 * It applies netbeans progressbar and requestprocessor.
 *
 * @author Peter Karich
 */
public class TableGUISupport implements TableCookie, Cancellable {
    
    private Task prepareTask;
    private ImportTask gTask;
    TableDataObject dObj;
    
    public TableGUISupport(DataObject dObj) {
        this.dObj = (TableDataObject)dObj;
    }
    
    public GTablePanel[] getOpenTables() {
        //TODO implement!
        return null;
    }
    
    protected boolean canClose() {
        TableOpenSupport tos = (TableOpenSupport)dObj.getCookie(TableOpenSupport.class);
                
        //&& tos.isOpen()
        if(tos != null && !dObj.isModified() ) {
            tos.close();
            return true;
        }        
        //else {            return super.canClose();        }
         
        return false;
    }
    
    public void setModified() {        
        if(dObj.getCookie(SaveCookie.class) == null) {
            dObj.addSaveCookie(new Save());
            dObj.setModified(true);
        }
    }
    
    private class Save implements SaveCookie {
        public void save() throws IOException {
            //saveDocument();
            
            SaveCookie save = (SaveCookie)dObj.getCookie(SaveCookie.class);
            if (save != null) {
                dObj.removeSaveCookie(save);                
            }
            dObj.setModified(false);
        }
    }
    
    /*
    public boolean isFinished() {
        return prepare().isFinished();
    }*/
    
    public synchronized Task prepare() {
        if (prepareTask == null) {
            prepareTask = RequestProcessor.getDefault().post(getGTask());
        }
        
        return prepareTask;
    }
    
    
    private ImportTask getGTask() {
        if(gTask==null) {
            try {
                gTask = new ImportTask(dObj.getPrimaryFile().getInputStream());
                
                //gTask.setInputStream();
            } catch(FileNotFoundException fne) {
                Log.err("Please report the following exception:", true);
                Log.err(fne, true);
            }
            
            gTask.addChangeListener(new MyListener());
        }
        return gTask;
    }
    
    
    
    
    
    
    
    /** This method interrupts the current thread via method interrupt().
     */
    public boolean cancel() {
        getGTask().interrupt();
        //return interrupted();
        return true;
    }
    
    public VectorPool getPool() {
        prepareTask.waitFinished();//blocking is better than NullPointerException.        
        return (VectorPool)getGTask().getResult();
    }
    
    public void setPool(VectorPool table) {
        getGTask().setPool(table);
    }
    
    public boolean isValid() {
        return getGTask().isValid();
    }
    
    public void setSeparator(String s) {
        getGTask().setSeparator(s);
    }
    
    public void addChangeListener(PropertyChangeListener cl) {
        getGTask().addChangeListener(cl);
    }
    
    public void removeChangeListener(PropertyChangeListener cl) {
        getGTask().removeChangeListener(cl);
    }
    
    ProgressHandle progress;
    
    class MyListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            String s = evt.getPropertyName();
            Object o = evt.getNewValue();
            
            if(s==GTask.STARTED) {
                progress = ProgressHandleFactory.
                        createHandle("Import",TableGUISupport.this);
                progress.start(getGTask().getInitSize());
            }
            
            //TODO I18N
            if(progress!=null) {
                if(s==GTask.STATUS && o instanceof Integer)
                    progress.progress("Importing. Please Wait!", ((Integer)o).intValue());
                
                if(s==GTask.FINISHED)   progress.finish();
            }
        }
    }
}
