package de.genvlin.plugins.bsh;

import de.genvlin.core.plugin.PluginPool;
import java.io.File;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

public final class RunAction extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        //EditorCookie cookie = (EditorCookie) activatedNodes[0].getCookie(EditorCookie.class);
        
          /*try {
                Log.log(cookie.getDocument().getText(0, cookie.getDocument().getEndPosition().getOffset()));
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }*/
        /*try {
            InputStream is =  ((CloneableEditorSupport)cookie).getInputStream();
            Interpreter interpreter = new Interpreter(new InputSource(is));
         
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        for(int i=0; i<activatedNodes.length; i++) {            
            BSHDataObject dObj = (BSHDataObject) activatedNodes[i].getCookie(BSHDataObject.class);
            File file = FileUtil.toFile(dObj.getPrimaryFile());
            PluginPool.getDefault().getScriptEngine().evalSource(file.getAbsolutePath());
        }
    }
    
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    public String getName() {
        return NbBundle.getMessage(RunAction.class, "CTL_RunAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            EditorCookie.class
        };
    }
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}

