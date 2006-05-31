package de.genvlin.plugins.bsh;

import de.genvlin.core.plugin.PluginPool;
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

public class BSHDataLoader extends UniFileLoader {
    
    public static final String BSH_MIME = "text/x-java-beanshell";
    
    private static final long serialVersionUID = 1L;
    
    public BSHDataLoader() {
        super("de.genvlin.plugins.bsh.BSHDataObject");
    }
    
    /*protected FileObject findPrimaryFile(FileObject fo) {
        if (fo.getMIMEType().equals(BSH_MIME)) return fo;
        else // Unrelated file.
            return null;
    }*/
    protected String defaultDisplayName() {
        return NbBundle.getMessage(BSHDataLoader.class, "LBL_BSH_loader_name");
    }
    
    protected void initialize() {
        super.initialize();        
        //TODO NOW Plugin init HACK:
        PluginPool.getDefault().add(BSHScriptPlugin.getDefault());        
        getExtensions().addMimeType(BSH_MIME);
    }
    
    /** This method will be called, if the system detects special MIME files
     and need a fileentry (we could make modifications on it before returning!)
     protected MultiDataObject.Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
     return new FileEntry(obj, primaryFile);
     }*/
    
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        return new BSHDataObject(primaryFile, this);
    }
    
    protected String actionsContext() {
        return "Loaders/" + BSH_MIME + "/Actions";
    }
    
}
