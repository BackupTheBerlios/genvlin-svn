package de.genvlin.plugins.bsh;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.text.DataEditorSupport;

public class BSHDataObject extends MultiDataObject {
    
    public BSHDataObject(FileObject pf, BSHDataLoader loader) 
    throws DataObjectExistsException, IOException {
        super(pf, loader);
        CookieSet cookies = getCookieSet();
        cookies.add((Node.Cookie) DataEditorSupport.
                create(this, getPrimaryEntry(), cookies));
        //cookies.add(new BSHDataEditorSupport(this));
    }
    
    protected Node createNodeDelegate() {
        return new BSHDataNode(this);
    }
    
}
