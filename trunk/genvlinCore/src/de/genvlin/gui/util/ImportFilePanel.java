/*
 * ImportFilePanel.java
 *
 * Created on 13. März 2006, 19:08
 */

package de.genvlin.gui.util;

import de.genvlin.core.plugin.Log;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Peter Karich
 */
public class ImportFilePanel extends javax.swing.JPanel {
    //static ResourceBundle bundle = ResourceBundle.getBundle("de.genvlin.gui.util.ImportFilePanel");
    //static private String IMPORT = bundle.getString( "IFP_IMPORT"), CANCEL = bundle.getString("IFP_CANCEL");;
    
    public ImportFilePanel(InputStream is) {
        this();
        setInputStream(is);
    }
    
    public ImportFilePanel() {
        initComponents();
    }
    
    public void setInputStream(InputStream is) {
        textArea.setText(getFirstChars(is, 500));
        textArea.setCaretPosition(0);
    }
    
    /**  This method returns the first characters of a file, also okay if
     * the file is not a text file.
     */
    public String getFirstChars(InputStream is, int chars) {
        if(chars <= 0) return "";
        String string = null;
        
        try {
            InputStreamReader iStream = new InputStreamReader(is);
            char[] cBuf= new char[chars];
            
            iStream.read(cBuf);
            iStream.close();
            
            string = new String(cBuf);
            
            /*WARNING: if we read lines then in bytefiles we get one very big line!!
            for(int lineNo = 0; ((line = in.readLine()) != null
                    || lineNo < lines); lineNo++)
            {
                sb.append(line+lineSeparator);
            }*/
        } catch(Exception exc) {
            Log.err(exc, false);
        }
        return string;
    }
    
    public String getSeparatorValue() {
        return separatorPanel1.getReturnValue();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        separatorPanel1 = new de.genvlin.gui.util.SeparatorPanel();

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(separatorPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(separatorPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private de.genvlin.gui.util.SeparatorPanel separatorPanel1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
    
}
