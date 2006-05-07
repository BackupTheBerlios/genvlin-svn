/*
 * Installer.java
 *
 * Created on 1. April 2006, 18:14
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

import de.genvlin.core.plugin.PluginPool;
import org.openide.modules.ModuleInstall;

/** This installer sets the netbeans platform as gui and register
 * the netbeans logger.
 */
public class Installer extends ModuleInstall {
    
    public void restored() {
        //TODO NOW Plugin init HACK:        
        PluginPool.getDefault().add(new NbPlatform());
        PluginPool.getDefault().add(new NbPlatform.NbLog());
        
        /*
        //NbPlatform.register();
        String fileName = (String)GProperties.getDefault().get("file.plugins");
        FileObject fo;
         
        //FileInputStream fInput = null;
        try {
            fo = NbDataFile.getFile(fileName, false);
            //fInput = new FileInputStream(fileName);
            ArrayList al = GFile.parseProperty(fo.getInputStream());
            for(int i=0; i<al.size(); i++) {
                GFile.Helper hel = ((GFile.Helper)al.get(i));
                //PlotPool.getDefault().add();
                try {
                    Log.log(Class.forName(hel.className).toString(), true);
                } catch(ClassNotFoundException cnfe) {
                    Log.log("Couldn't load plugin: "+hel.pluginName+"!", true);
                }
            }
        } catch (IOException ex) {
            Log.err("Couldn't found file:"+fileName, true);
            Log.err("So plugins won't work!", true);
        }*/
    }
    //public void uninstalled(
}
