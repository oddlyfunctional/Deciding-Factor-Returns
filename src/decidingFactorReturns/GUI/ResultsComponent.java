/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultsComponent.java
 *
 * Created on Jul 13, 2011, 5:14:21 AM
 */
package decidingFactorReturns.GUI;

import decidingFactorReturns.exceptions.PolicyException;
import decidingFactorReturns.policies.Policy;
import decidingFactorReturns.structures.Node;
import decidingFactorReturns.utils.I18n;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author marcos
 */
public class ResultsComponent extends javax.swing.JPanel {

    private List<Node> nodes;
    private Policy policy;
    private ResultsComponent previousLevel;

    /** Creates new form ResultsComponent */
    public ResultsComponent(List<Node> nodes, Policy policy, ResultsComponent previousLevel) throws PolicyException {
        this.nodes = nodes;
        this.policy = policy;
        this.previousLevel = previousLevel;
        initComponents();
        resultsPanel.setLayout(new GridLayout(nodes.size(), 2, 1, 1));
        for (Node node : nodes) {
            resultsPanel.add(new JLabel(node.getName()), 0);
            resultsPanel.add(new JLabel(policy.importanceOfNode(node).toString()), 1);
        }
    }

    public ResultsComponent getPreviousLevel() {
        return previousLevel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousLevelButton = new javax.swing.JButton();
        finishConsultButton = new javax.swing.JButton();
        resultsScrollPane = new javax.swing.JScrollPane();
        resultsPanel = new javax.swing.JPanel();
        nextLevelButton = new javax.swing.JButton();

        previousLevelButton.setText(I18n.t("previous_level"));
        previousLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousLevelButtonActionPerformed(evt);
            }
        });

        finishConsultButton.setText(I18n.t("finish_consult"));
        finishConsultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishConsultButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        resultsScrollPane.setViewportView(resultsPanel);

        nextLevelButton.setText(I18n.t("next_level"));
        nextLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextLevelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resultsScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(finishConsultButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(previousLevelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextLevelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextLevelButton)
                    .addComponent(previousLevelButton)
                    .addComponent(finishConsultButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void finishConsultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishConsultButtonActionPerformed
        MainFrame.getInstance().menu();
    }//GEN-LAST:event_finishConsultButtonActionPerformed

    private void previousLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousLevelButtonActionPerformed
        if (previousLevel == null) {
            MainFrame.getInstance().showWarning(I18n.t("error_no_previous_level"));
        } else {
            MainFrame.getInstance().showPreviousResults();
        }
    }//GEN-LAST:event_previousLevelButtonActionPerformed

    private void nextLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextLevelButtonActionPerformed
        List<Node> nodes = new ArrayList<Node>();
        for (Node node : this.nodes) {
            nodes.addAll(node.getChildren());
        }
        if (nodes.size() == 0) {
            MainFrame.getInstance().showWarning(I18n.t("error_no_next_level"));
        } else {
            try {
                MainFrame.getInstance().showResults(nodes, policy, this);
            } catch (PolicyException e) {
                e.printStackTrace();
                MainFrame.getInstance().showWarning(e.getMessage());
            }
        }
    }//GEN-LAST:event_nextLevelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finishConsultButton;
    private javax.swing.JButton nextLevelButton;
    private javax.swing.JButton previousLevelButton;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JScrollPane resultsScrollPane;
    // End of variables declaration//GEN-END:variables
}
