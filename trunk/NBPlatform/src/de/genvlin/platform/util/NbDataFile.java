/*
 * NbDataFile.java
 *
 * Created on 25. März 2006, 12:31
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

package de.genvlin.platform.util;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;

/**
 *
 * @author Peter Karich
 */
public class NbDataFile {
    
    private static final String ROOT = "genvlin-root"; //NOI18N
    private static FileObject root;
    
    /** Retrieve file or folder relative to genvlin-root, with a given relative path.
     * This method isn't final since revision 1.93.
     * @param relativePath is just basename of the file or (since 4.16)
     *        the relative path delimited by '/'
     *  @param create specifiy wether file (or folder) should created or not if not available.
     */
    public static FileObject getFile(String filename, boolean create) throws IOException {
        FileSystem sysFs = Repository.getDefault().getDefaultFileSystem();
        if (root == null) {
            root = sysFs.findResource(ROOT);
            if(root == null)  root = sysFs.getRoot().createFolder(ROOT);
        }
        
        FileObject fo = root.getFileObject(filename);
        if (fo == null) {
            if (create) fo = root.createData(filename);
            else        return null;
        }
        
        return fo;
    }
    
}
