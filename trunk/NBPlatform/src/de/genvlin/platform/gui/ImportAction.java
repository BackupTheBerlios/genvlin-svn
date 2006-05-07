/*
 * VectorEditorSupport.java
 *
 * Created on 10. März 2006, 16:57
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

import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;
import org.openide.windows.CloneableTopComponent;

public final class ImportAction extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        
        for(int i=0; i<activatedNodes.length; i++) {
            TableOpenSupport c = (TableOpenSupport) activatedNodes[i].getCookie(TableOpenSupport.class);
            if(c == null) return;
            
            CloneableTopComponent ctc = c.createCloneableTopComponent();
            ctc.open();
            if(i==0) ctc.requestActive();
        }
    }
    
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(ImportAction.class, "CTL_ImportAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class//, EditCookie.class
        };
    }
    
    protected String iconResource() {
        return "de/genvlin/platform/resources/Import.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
}
