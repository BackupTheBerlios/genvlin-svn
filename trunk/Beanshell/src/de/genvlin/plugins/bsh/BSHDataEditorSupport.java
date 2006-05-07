/*
 * BSHDataEditorSupport.java
 *
 * Created on 26. April 2006, 15:36
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

package de.genvlin.plugins.bsh;

import java.io.IOException;
import org.openide.cookies.CloseCookie;
import org.openide.cookies.EditCookie;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.PrintCookie;
import org.openide.cookies.ViewCookie;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.text.DataEditorSupport;
import org.openide.windows.CloneableOpenSupport;

/**
 * This class provides the special buttons for bsh script windows:
 * run, save
 * @author Peter Karich
 */
public class BSHDataEditorSupport extends DataEditorSupport 
        implements EditorCookie, EditCookie, 
        ViewCookie, PrintCookie, CloseCookie {
    
    BSHDataEditorSupport(BSHDataObject bdo) {
        super(bdo, new BSHEnv(bdo));        
    }  
    
    static private class BSHEnv extends DataEditorSupport.Env {
        public BSHEnv(BSHDataObject bdo) {
            super(bdo);
        }
        
        protected FileObject getFile() {
            return getDataObject().getPrimaryFile();
        }
        
        protected FileLock takeLock() throws IOException {
            return ((BSHDataObject)getDataObject())
            .getPrimaryEntry().takeLock();
        }
        public CloneableOpenSupport findCloneableOpenSupport() {
            return (BSHDataEditorSupport)getDataObject().getCookie(BSHDataEditorSupport.class);
        }
    }
}
