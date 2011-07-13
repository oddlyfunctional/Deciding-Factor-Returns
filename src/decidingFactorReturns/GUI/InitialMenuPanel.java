/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InitialMenuPanel.java
 *
 * Created on Jul 12, 2011, 3:47:16 PM
 */
package decidingFactorReturns.GUI;

import decidingFactorReturns.controllers.Edit;
import decidingFactorReturns.utils.I18n;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author marcos
 */
public class InitialMenuPanel extends javax.swing.JPanel {

    /** Creates new form InitialMenuPanel */
    public InitialMenuPanel() {
        initComponents();
        MainFrame.getInstance().setTitle(I18n.t("menu_title"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        createTreeButton = new javax.swing.JButton();
        consultTreeButton = new javax.swing.JButton();
        editTreeButton = new javax.swing.JButton();

        exitButton.setText(I18n.t("exit"));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        createTreeButton.setText(I18n.t("create_tree"));
        createTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTreeButtonActionPerformed(evt);
            }
        });

        consultTreeButton.setText(I18n.t("consult_tree"));

        editTreeButton.setText(I18n.t("edit_tree"));
        editTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTreeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(editTreeButton)
                        .addComponent(createTreeButton)
                        .addComponent(consultTreeButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {consultTreeButton, createTreeButton, editTreeButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createTreeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editTreeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultTreeButton)
                .addGap(18, 18, 18)
                .addComponent(exitButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void createTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTreeButtonActionPerformed
        Edit.getInstance().newTree();
        MainFrame.getInstance().changePanel(new EditNodePanel(null));
    }//GEN-LAST:event_createTreeButtonActionPerformed

    private void editTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTreeButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Edit.getInstance().loadTree(fc.getSelectedFile());
                MainFrame.getInstance().pushNode(Edit.getInstance().getRoot());
                MainFrame.getInstance().downTree();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(this, I18n.t("error_io"), I18n.t("warning"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_editTreeButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultTreeButton;
    private javax.swing.JButton createTreeButton;
    private javax.swing.JButton editTreeButton;
    private javax.swing.JButton exitButton;
    // End of variables declaration//GEN-END:variables
}
