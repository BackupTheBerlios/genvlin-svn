/*
 * TableTopComponent.java
 *
 * Created on 14. März 2006, 13:03
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
import de.genvlin.gui.table.GTablePanel;
import de.genvlin.gui.util.ImportFilePanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.openide.ErrorManager;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.MultiDataObject;
import org.openide.util.Task;
import org.openide.util.TaskListener;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.TopComponent;

/** This class shows the GTablePanel in netbeans.
 *
 * @author Peter Karich
 */
public class TableTopComponent extends CloneableTopComponent
        implements ActionListener {//PropertyChangeListener
    
    private static final long serialVersionUID = 37623723544473895L;
    private static String PREFERRED_ID = "TableTopComponent";
    
    private MultiDataObject.Entry entry;
    private DataObject dObj;
    private GTablePanel table;
    private TableCookie tCookie;
    private TableOpenSupport  openSupport;
    private JButton cancelButton;
    private JButton doImportButton;
    private JPanel northPanel;
    private ImportFilePanel importPanel;
    //boolean added = false;
    
    
    public TableTopComponent(MultiDataObject.Entry entry) {
        this.entry = entry;
        init(null);
    }
    
    //if we declare a method readResolve we need this here (for serialisation):
    private TableTopComponent() {
        //init();-> this will cause a NullPointerExc, cause we have not init the entry!
    }
    
    /** Initialization - run this only from EDT*/
    private void init(final GTablePanel tablePanel) {
        //WindowsAPI is required to be called from EDT (AWT thread) only
        //see http://www.netbeans.org/download/dev/javadoc/OpenAPIs/org/openide/doc-files/threading.html
        if(!SwingUtilities.isEventDispatchThread()) { //==EventQueue.isDispatchThread();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    init(tablePanel);
                }
            });
            return;
        }
        //now we know, WE are the current AWT EventQueue's dispatch thread
        
        
        table = tablePanel;
        removeAll();
        setLayout(new BorderLayout());
        dObj = entry.getDataObject();
        tCookie = (TableCookie)dObj.getCookie(TableCookie.class);
        if(tCookie == null) {
            ErrorManager.getDefault().log("Error while importing");
            return;
        }
        //to cancel the closing if modified && !saved
        openSupport = (TableOpenSupport)dObj.getCookie(TableOpenSupport.class);
        
        if(table != null) {
            setDisplayName(table.getModel().getID()+":"+entry.getFile().getName());
            add(tablePanel.getComponent(), BorderLayout.CENTER);
            //if(added) table.addFocusListener(popupSupport);
        } else {
            JPanel buttonPanel;
            try {
                northPanel = new JPanel(new BorderLayout());
                
                doImportButton = new JButton("Do Import");
                doImportButton.addActionListener(this);
                doImportButton.setActionCommand("Do Import");
                
                cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(this);
                cancelButton.setActionCommand("Cancel");
                
                buttonPanel = new JPanel();
                buttonPanel.add(doImportButton);
                buttonPanel.add(cancelButton);
                importPanel = new ImportFilePanel(dObj.getPrimaryFile().getInputStream());
                northPanel.add(importPanel, BorderLayout.CENTER);
                northPanel.add(buttonPanel, BorderLayout.NORTH);
                add(northPanel, BorderLayout.NORTH);
            } catch (FileNotFoundException ex) {
                Log.err(ex, true);
            }
        }
        
        TableTopComponent.this.revalidate();
    }
    
    public void actionPerformed(ActionEvent ae) {
        String comm = ae.getActionCommand();
        if(comm.equalsIgnoreCase("cancel")) {
            Log.log("cancel",true);
            close();
            return;
        }
        //else:
        tCookie.setSeparator(importPanel.getSeparatorValue());
        tCookie.prepare().addTaskListener(new InitStep2Thread());
    }
    
    class InitStep2Thread implements TaskListener, Runnable {
        
        public void taskFinished(Task task) {
            SwingUtilities.invokeLater(this);//to call run() from EventDispatcherQueueThread = EDT
            //Log.log("taskFinished!", true);
        }
        
        public void run() {
            //Log.log("taskFinished! - And run", true);
            
            //now this shouldn't block the EDT, because we have already prepared
            init(new GTablePanel(tCookie.getPool()));
        }
    }
    
    //to provide docking anywhere:
    public List availableModes(List modes) {
        return null;
    }    
    
    //ObjectInputStream ois.defaultReadObject ();
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        /*entry = (MultiDataObject.Entry)oi.readObject();
        init(new GTablePanel((VectorPool)oi.readObject()));*/
    }
    
    public void writeExternal(ObjectOutput oo) throws java.io.IOException {
        super.writeExternal(oo);
        /*oo.writeObject(entry);
        if(tCookie.isValid())
            //storing table.getModel() will fail, cause of missing serialVersionUID's in AbstractTableModel, etc.!!!???
            oo.writeObject(tCookie.getPool());
         */
        if(dObj!=null) {
            SaveCookie c = (SaveCookie)dObj.getCookie(SaveCookie.class);
            if(c!=null) {
                try{
                    c.save();
                } catch(IOException ioe) {
                    Log.log("Saving failed!("+ioe.getMessage()+")", true);
                }
            }
        }
    }
    
    protected boolean closeLast() {
        if (openSupport== null) {
            // Should not happen.
            Log.log("WARNING: no TableOpenSupport, will just close! Not save!", true);
            return true;
        }
        boolean ok = openSupport.canClose();
        return ok;
    }
    
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ONLY_OPENED;//PERSISTENCE_NEVER//
    }
    
    //protected void componentActivated() {    }
    public void addNotify() {
        super.addNotify();
        //added = true;
        //if(table != null) table.addFocusListener(popupSupport);
    }
    
    //protected void componentDeactivated() {    }
    public void removeNotify() {
        //if(added && table != null) table.removeFocusListener(popupSupport);
        super.removeNotify();
    }
    
    public void componentOpened() {
        Log.log("componentOpened", false);
    }
    
    public void componentClosed() {
        Log.log("componentClosed", false);
    }
    
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
