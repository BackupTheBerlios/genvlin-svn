/*
 * TableDataLoader.java
 *
 * Created on 10. März 2006, 17:11
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

import de.genvlin.core.plugin.PluginPool;
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.FileEntry;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

/**
 *
 * @author Peter Karich
 */
public class TableDataLoader extends UniFileLoader {
    public static final String TABLE_MIME = "text/x-genvlin-table"; // NOI18N
    private static final long serialVersionUID = 31616457458349560L;
    
    public TableDataLoader() {
        super("de.genvlin.platform.gui.TableDataObject"); // NOI18N
    }
    
    protected void initialize() {
        super.initialize();
        getExtensions().addMimeType(TABLE_MIME);    
    }
    
    protected String defaultDisplayName() {
        return NbBundle.getMessage(TableDataLoader.class, "LBL_loaderName");
    }
    
    
    protected FileObject findPrimaryFile(FileObject fo) {
        if (fo.getMIMEType().equals(TABLE_MIME)) {
            return fo;
        } else {
            // Unrelated file.
            return null;
        }
    }
    
    protected MultiDataObject createMultiObject(FileObject primaryFile)
    throws DataObjectExistsException, IOException {
        return new TableDataObject(primaryFile, this);
    }
    
    /** This method will be called, if the system detects special MIME files
     and need a fileentry (we could make modifications on it before returning!)*/
    protected MultiDataObject.Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
        return new FileEntry(obj, primaryFile);
    }
    
    /** Will be called only one-time, to detect which actions are available.*/
    protected String actionsContext() {
        return "Loaders/"+TABLE_MIME+"/Actions";//see layer.xml which actions are added!
    }
}
