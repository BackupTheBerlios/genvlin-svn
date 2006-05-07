package de.genvlin.plugins.numbercruncher;

import de.genvlin.core.plugin.PluginPool;
import org.openide.modules.ModuleInstall;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {
    
    public void restored() {
        //TODO NOW Plugin init HACK:
        PluginPool.getDefault().add(new GenvlinFitEngine());
    }
    
}
