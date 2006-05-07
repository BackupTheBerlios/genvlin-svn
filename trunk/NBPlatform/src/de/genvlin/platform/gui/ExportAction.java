package de.genvlin.platform.gui;

import de.genvlin.core.plugin.Log;
import java.io.IOException;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

/**unused class
 */
public final class ExportAction extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        for(int i=0; i<activatedNodes.length; i++) {
            SaveCookie c = (SaveCookie) activatedNodes[i].getCookie(SaveCookie.class);
            if(c!=null) {
                try{
                    c.save();
                } catch(IOException ioe) {
                    Log.log("Saving failed!("+ioe.getMessage()+")", true);
                }
            }
        }
    }
    
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(ExportAction.class, "CTL_ExportAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    protected String iconResource() {
        return "de/genvlin/platform/resources/Export.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}

