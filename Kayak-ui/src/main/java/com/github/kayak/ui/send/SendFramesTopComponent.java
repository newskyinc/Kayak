/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kayak.ui.send;

import com.github.kayak.core.Bus;
import com.github.kayak.ui.projects.Project;
import com.github.kayak.ui.projects.ProjectChangeListener;
import com.github.kayak.ui.projects.ProjectManagementListener;
import com.github.kayak.ui.projects.ProjectManager;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.github.kayak.ui.send//SendFrames//EN",
autostore = false)
@TopComponent.Description(preferredID = "SendFramesTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.github.kayak.ui.send.SendFramesTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SendFramesAction",
preferredID = "SendFramesTopComponent")
public final class SendFramesTopComponent extends TopComponent {

    private static final Logger logger = Logger.getLogger(SendFramesTopComponent.class.getCanonicalName());
    
    private SendFramesTableModel tableModel = new SendFramesTableModel();
    
    private ProjectManagementListener managementListener = new ProjectManagementListener() {

            @Override
            public void projectsUpdated() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void openProjectChanged(Project p) {
                p.addProjectChangeListener(projectListener);
                fillComboBox();
            }
        };
    
    private ProjectChangeListener projectListener = new ProjectChangeListener() {

        @Override
        public void projectNameChanged() {
            
        }

        @Override
        public void projectClosed() {
            
        }

        @Override
        public void projectOpened() {
            
        }

        @Override
        public void projectBusAdded(Bus bus) {
            fillComboBox();
        }

        @Override
        public void projectBusRemoved(Bus bus) {
            fillComboBox();
        }
    };

    public SendFramesTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(SendFramesTopComponent.class, "CTL_SendFramesTopComponent"));
        setToolTipText(NbBundle.getMessage(SendFramesTopComponent.class, "HINT_SendFramesTopComponent"));
        
        ProjectManager.getGlobalProjectManager().addListener(managementListener);
        Project p = ProjectManager.getGlobalProjectManager().getOpenedProject();
        if(p != null) {
            p.addProjectChangeListener(projectListener);
            fillComboBox();
        }
        
        Action send = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((SendFramesTableModel) table.getModel()).send(modelRow);
            }
        };
        
        Action sendInterval = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((SendFramesTableModel) table.getModel()).toggleSendInterval(modelRow);
            }
        };

        ButtonColumn bc = new ButtonColumn(jTable1, send, 4);
        CheckBoxColumn cbc = new CheckBoxColumn(jTable1, sendInterval, 6);
        
        jTable1.getColumn("Bus").setPreferredWidth(100);
        jTable1.getColumn("ID").setPreferredWidth(60);
        jTable1.getColumn("Length").setPreferredWidth(70);
        jTable1.getColumn("Data").setPreferredWidth(200);
        jTable1.getColumn("Send").setPreferredWidth(60);
        jTable1.getColumn("Interval (ms)").setPreferredWidth(100);
        jTable1.getColumn("Send interval").setPreferredWidth(100);
        jTable1.getColumn("Note").setPreferredWidth(150);
    }
    
    private void fillComboBox() {
        jComboBox1.removeAllItems();
        Project p = ProjectManager.getGlobalProjectManager().getOpenedProject();
        
        for(Bus b : p.getBusses()) {
            jComboBox1.addItem(b);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(tableModel);
        jScrollPane1.setViewportView(jTable1);

        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SendFramesTopComponent.class, "SendFramesTopComponent.jLabel1.text")); // NOI18N
        jToolBar1.add(jLabel1);

        jToolBar1.add(jComboBox1);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(SendFramesTopComponent.class, "SendFramesTopComponent.jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(SendFramesTopComponent.class, "SendFramesTopComponent.jButton2.text")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Bus b = (Bus) jComboBox1.getSelectedItem();
            tableModel.add(b);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "No bus was selected");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tableModel.remove(jTable1.getSelectedRow());
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
