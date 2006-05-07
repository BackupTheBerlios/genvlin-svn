/*
 * NbPlatform.java
 *
 * Created on 7. April 2006, 16:22
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

import de.genvlin.core.data.MainPool;
import de.genvlin.core.data.PoolInterface;
import de.genvlin.core.plugin.LogPlugin;
import de.genvlin.core.plugin.Platform;
import de.genvlin.core.plugin.PluginPool;
import de.genvlin.core.plugin.PluginSPI;
import de.genvlin.core.plugin.RequestEvent;
import de.genvlin.platform.plot.PlotTopComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import org.openide.ErrorManager;
import org.openide.util.Utilities;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.Mode;
import org.openide.windows.OutputWriter;
import org.openide.windows.WindowManager;

/**
 *
 * @author Peter Karich
 */
public class NbPlatform implements Platform.SPI {
    
    private PoolInterface pool;
    
    NbPlatform() {
        pool = (PoolInterface)MainPool.getDefault().create(PoolInterface.class);
    }
    
    /*class PanelPool extends Pool {
        PanelPool() {
            super(new IntID(1234));//TODO NOW remove this hack!
        }
        public boolean add(Comparable panel, ID id) {
            getColl().put(id, panel);
        }
    }*/
    
    public boolean showPanel(Component panel, String position) {
        PlotTopComponent tc = new PlotTopComponent(panel);
        //pool.
        String MODE = "properties";
        
        if(position.equals("center"))
            MODE = "editor";
        
        Mode m = WindowManager.getDefault().findMode(MODE);
        m.dockInto(tc);
        tc.open();
        tc.requestActive();
        return true;
    }
    
    /*
    public void registerLog() {
        NbLog.register();
    }*/
    
    /*
    public String getMessage(String className, String messageKey) {
        try {
            return NbBundle.getMessage(Class.forName(className), messageKey);
        } catch (ClassNotFoundException ex) {
            Log.log("Resource for class "+className+" not found!!", true);
        }
        return "missing resource!";
    } */
    
    static private final String PLATFORM_NAME = "Netbeans Platform";
    public String getName() {
        return PLATFORM_NAME;
    }
    
    public void showPopup(Platform.PopupSupport s){
        
        if (!s.isPopupAllowed()) return;
        
        JPopupMenu popup = s.createPopup();
        
        if ((popup != null)){// && (popup.getSubElements().length > 0)) {
            Point p = new Point();//s.getViewPosition());
            
            /*
            if(me.getComponent() == table.getTable()) {
                int yOffset = table.getTableHeader().getHeight();
                p.y -= yOffset;
            }*/
            MouseEvent me = s.getMouseEvent();
            p.x = me.getX();// - p.x;
            p.y = me.getY();// - p.y;
            
            SwingUtilities.convertPointToScreen(p, me.getComponent());
            
            Dimension popupSize = popup.getPreferredSize();
            Rectangle screenBounds = Utilities.getUsableScreenBounds(me.getComponent().getGraphicsConfiguration());//me.getComponent().getGraphicsConfiguration().getBounds();//
            
            if ((p.x + popupSize.width) > (screenBounds.x + screenBounds.width))
                p.x = (screenBounds.x + screenBounds.width) - popupSize.width;
            
            if ((p.y + popupSize.height) > (screenBounds.y + screenBounds.height))
                p.y = (screenBounds.y + screenBounds.height) - popupSize.height;
            
            SwingUtilities.convertPointFromScreen(p, me.getComponent());
            popup.show(me.getComponent(), p.x, p.y);
        }
    }
    
    String s[] = {PLATFORM_NAME};
    
    public String[] getActionContextReasons() {
        return s;
    }
    
    public void sendRequest(RequestEvent ri) {
    }
    
    static class NbLog implements LogPlugin {
        
        private static NbLog instance;
        private static InputOutput io;
        static private final String LOGGER_NAME = PLATFORM_NAME+ " Logger";//"Netbean Logger";
        public NbLog() {
            io = IOProvider.getDefault().getIO(LOGGER_NAME,false);
        }
        
        /*
        static public void register() {
            if(instance == null) {
                PluginPool.getDefault().add(instance = new NbLog());
                //io = IOProvider.getDefault().getIO(LOGGER_NAME,false);
            }
        }*/
        
/*        static public void unregister() {
            if(instance != null) PluginPool.getDefault().remove(instance);
        }
  */      
        public String getName() {
            return LOGGER_NAME;
        }
        
        public void err(String s, boolean notify) {
            ErrorManager.getDefault().log(ErrorManager.ERROR, s);
            if(notify) notify(s);
        }
        
        public void err(Throwable t, boolean notify) {
            ErrorManager.getDefault().log(ErrorManager.ERROR, t.getLocalizedMessage());
            if(notify) notify(t.getMessage());
        }
        
        public void log(String s, boolean notify) {
            ErrorManager.getDefault().log(ErrorManager.INFORMATIONAL, s);
            if(notify) notify(s);
        }
        
        private void notify(String s) {
            //io.setOutputVisible(true);
            //io.setInputVisible(true);
            
            //TODO avoid the exception
            io.select();//this will create the editor pane => exception!! for the first time!
            OutputWriter writer = io.getOut();
            writer.println(s);
            writer.flush();
            writer.close();
        }
        
        String s[] = {LOGGER_NAME};
        
        public String[] getActionContextReasons() {
            return s;
        }
        
        public void sendRequest(RequestEvent ri) {
        }
    }
    
}
