/*
 * BSHScriptPlugin.java
 *
 * Created on 26. April 2006, 23:56
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

import bsh.EvalError;
import bsh.Interpreter;
import de.genvlin.core.plugin.Log;
import de.genvlin.core.plugin.RequestEvent;
import de.genvlin.core.plugin.ScriptPlugin;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Peter Karich
 */
public class BSHScriptPlugin implements ScriptPlugin {
    
    Interpreter interpreter;
    static BSHScriptPlugin util;
    
    /**
     * Creates a new instance of BSHScriptPlugin
     */
    private BSHScriptPlugin() {
    }
    
    private Interpreter getInterpreter() {
        if(interpreter == null) {
            interpreter = new Interpreter();
            //only useful if interactive
            //interpreter.setShowResults(true);//after pressing ENTER the values will printed
            //interpreter.setExitOnEOF(false);//so interpreter does not System.exit(-1) in while()-loop see beanshell sourcecode
            //new Thread(interpreter)
            
            interpreter.getNameSpace().importCommands("/script");
            //refreshNameSpace();
        }
        
        return interpreter;
    }
    
    public static synchronized BSHScriptPlugin getDefault() {
        if(util == null)
            util = new BSHScriptPlugin();
        return util;
    }
    
    //private boolean init = false;
    
    public void init() {
        /*if(!init) {
            PluginPool.getDefault().add(this);
            init=true;
        }*/
    }
    
    public void eval(String str) {
        try {
            getInterpreter().eval(str);
        } catch (EvalError ex) {
            Log.err("Error in line no:"+ex.getErrorLineNumber(), true);
            Log.err(ex, true);
        }
    }
    
    public void evalSource(final String fileName) {
        new Thread(){
            public void run(){
                try {
                    getInterpreter().source(fileName);
                } catch (FileNotFoundException ex) {
                    Log.err(ex, true);
                } catch (IOException ex) {
                    Log.err(ex, true);
                } catch (EvalError ex) {
                    Log.err("Error in line no:"+ex.getErrorLineNumber(), true);
                    Log.err(ex, true);
                }
            }
        }.start();        
    }
    
    final public static String name = "Beanshell interpreter";
    
    public String getName() {
        return name;
    }
    
    String s[] = {name};
    
    public String[] getActionContextReasons() {
        return s;
    }
    
    public void sendRequest(RequestEvent ri) {
    }
}
