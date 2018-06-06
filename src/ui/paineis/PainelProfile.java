package ui.paineis;

import javax.swing.Icon;

/**
 *
 * @author Rafael
 */
public class PainelProfile extends javax.swing.JPanel {

    public PainelProfile(Icon icon, String name) {
        initComponents();
        labelProfile.setIcon(icon);
        labelProfile.setText(name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelProfile = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        labelProfile.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons8-usuário-24.png"))); // NOI18N
        labelProfile.setText("name");
        labelProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 20, 1));
        add(labelProfile, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelProfile;
    // End of variables declaration//GEN-END:variables
}
