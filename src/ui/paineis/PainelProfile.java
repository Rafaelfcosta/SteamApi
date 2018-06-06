package ui.paineis;

import gamesapi.GamesAPIController;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Rafael
 */
public class PainelProfile extends javax.swing.JPanel {
    String name; 
    private GamesAPIController controller;
    public PainelProfile(GamesAPIController controller, Icon icon) {
        initComponents();
        
        this.controller = controller;
        labelProfile.setIcon(icon);
        name = controller.getProfileName();
        String texto = "<html> " + name + "</html>";
        labelProfile.setText(texto);
        populateDetails();
    }

    private void populateDetails(){
        String mostPlayedGameName = controller.getMostPlayedGame();
        int timePlayed = controller.getOwnedGamesPlaytimeForever().get(mostPlayedGameName) / 60;
        LabelMostPlayedGame.setText("<html><center>" + name + "'s Most Played Game<br> <img src ='"+ controller.getGameImagesMap().get(mostPlayedGameName) + "'> <br><b>" +
                mostPlayedGameName + "</b> with <b>" + timePlayed +"</b> Hours <br><br><font size='20'> Total Games on account: <b>"+
                controller.GetTotalGamesCount() + "</font></b></html>");
        ArrayList<String> recent = controller.getRecentPlayedGames();
        String recentListHTML = "<html> Recent Played Games<br>";
        for (String recentgame : recent) {
            recentListHTML += "<img src='" + controller.getGameImagesMap().get(recentgame) + "'><br>";
        }
        recentListHTML += "</html>";
        System.out.println(recentListHTML);
        LabelRecentPlayedGames.setText(recentListHTML);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelProfile = new javax.swing.JLabel();
        painelProfileContent = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LabelMostPlayedGame = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LabelRecentPlayedGames = new javax.swing.JLabel();

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
        painelProfileContent.setLayout(new java.awt.BorderLayout());
        painelProfileContent.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(31, 50, 68));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(31, 50, 68));
        jPanel2.setLayout(new java.awt.BorderLayout());

        LabelMostPlayedGame.setBackground(new java.awt.Color(31, 50, 68));
        LabelMostPlayedGame.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        LabelMostPlayedGame.setForeground(new java.awt.Color(255, 255, 255));
        LabelMostPlayedGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelMostPlayedGame.setText("gameName");
        jPanel2.add(LabelMostPlayedGame, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(31, 50, 68));
        jPanel3.setLayout(new java.awt.BorderLayout());

        LabelRecentPlayedGames.setBackground(new java.awt.Color(31, 50, 68));
        LabelRecentPlayedGames.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        LabelRecentPlayedGames.setForeground(new java.awt.Color(255, 255, 255));
        LabelRecentPlayedGames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelRecentPlayedGames.setText("recentGames");
        jPanel3.add(LabelRecentPlayedGames, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_END);

        painelProfileContent.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(painelProfileContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelMostPlayedGame;
    private javax.swing.JLabel LabelRecentPlayedGames;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelProfile;
    private javax.swing.JPanel painelProfileContent;
    // End of variables declaration//GEN-END:variables
}
