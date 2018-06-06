package ui.paineis;

import gamesapi.GamesAPIController;
import javax.swing.Icon;

/**
 *
 * @author Rafael
 */
public class PainelProfile extends javax.swing.JPanel {

    private GamesAPIController controller;
    public PainelProfile(GamesAPIController controller, Icon icon) {
        initComponents();
        
        this.controller = controller;
        labelProfile.setIcon(icon);
        String name = controller.getProfileName();
        String texto = "<html> " + name + "</html>";
        labelProfile.setText(texto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelProfile = new javax.swing.JLabel();
        painelProfileContent = new javax.swing.JPanel();

        setBackground(new java.awt.Color(31, 50, 68));
        setLayout(new java.awt.BorderLayout());

        labelProfile.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        labelProfile.setForeground(new java.awt.Color(255, 255, 255));
        labelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons8-usuário-24.png"))); // NOI18N
        labelProfile.setText("name");
        labelProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 20, 1));
        add(labelProfile, java.awt.BorderLayout.PAGE_START);

        painelProfileContent.setBackground(new java.awt.Color(31, 50, 68));
        add(painelProfileContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelProfile;
    private javax.swing.JPanel painelProfileContent;
    // End of variables declaration//GEN-END:variables
}
