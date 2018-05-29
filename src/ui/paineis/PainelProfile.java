package ui.paineis;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Rafael
 */
public class PainelProfile extends javax.swing.JPanel {

    public PainelProfile(Icon icon, String name) {
        initComponents();
        labelProfileIcon.setIcon(icon);
        labelProfileName.setText(name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelProfileIcon = new javax.swing.JLabel();
        labelProfileName = new javax.swing.JLabel();

        add(labelProfileIcon);

        labelProfileName.setText("name");
        add(labelProfileName);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelProfileIcon;
    private javax.swing.JLabel labelProfileName;
    // End of variables declaration//GEN-END:variables
}
