package ui;

import gamesapi.GamesAPIController;
import gamesapi.GamesAPIControllerFactory;
import gamesapi.GamesAPIListener;
import gamesapi.adapter.SteamAdapter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ui.paineis.Loading;
import ui.paineis.PainelMyGames;
import ui.paineis.PainelProfile;

public class TelaPrincipal extends javax.swing.JFrame {
   
    private GamesAPIController controller;
    private String user;
    private PainelMyGames painelMyGames;
    private PainelProfile painelProfile;
    private Loading painelLoading = new Loading();
    
   
    public TelaPrincipal() throws Exception {
        initComponents();
        ConfigurarPaineis();
        user = ler("Informe o usuário da Steam ou o Steam ID");
        controller = GamesAPIControllerFactory.create(user);
        setGlassPane(painelLoading);
        
        ConfigurarAvatarUser();
        CarregarProfileInicial();
        
        atualizarPainel();
    }
    
    private static String ler(String msg){
        return JOptionPane.showInputDialog(msg);
    }
    
    private void ConfigurarAvatarUser() throws MalformedURLException{
        String status;
        switch (controller.getPersonState()){
            case 0:
                status = "<font color='red'> Offline </font>";
                break;
            case 1:
                status = "<font color='green'> Online </font>";
                break;
            case 2:
                status = "Busy";
                break;
            case 3:
                status = "Away";
                break;
            case 4:
                status = "Snooze";
                break;
            case 5:
                status = "Looking to trade";
                break;
            case 6:
                status = "Looking to play";
                break;
            default:
                status = "Undefined";
        }
        this.labelImgUsuario.setIcon(new ImageIcon(new URL(controller.getProfileImageUrlMedium())));
        
        String name = controller.getProfileName();
        String texto = "<html> " + name + "<br>" + status + "</html>";
        labelNomeUsuario.setText(texto);
    }
    
    private void CarregarProfileInicial(){
        GamesAPIListener listener = new GamesAPIListener() {
            @Override
            public void GettingData() {
                painelLoading.setVisible(true);
            }

            
            @Override
            public void DataArrived() {
                painelLoading.setVisible(false);
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                listener.GettingData();
                try {
                    painelProfile = new PainelProfile(controller, new ImageIcon(new URL(controller.getProfileImageUrlFull())));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                painelConteudo.add(painelProfile);
                listener.DataArrived();
            }
        });
        thread.start();
    }
    
    private void ConfigurarPaineis(){
        MouseListener listener = new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent me) {
                JPanel painel = (JPanel) me.getSource();
            }
            
            @Override
            public void mouseEntered(MouseEvent me) {
                JPanel painel = (JPanel) me.getSource();
                painel.setBackground(Color.GRAY);
                painel.setOpaque(true);
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                JPanel painel = (JPanel) me.getSource();
                painel.setBackground(Color.darkGray);
                painel.setOpaque(false);
            }
            
        };        
        painelMeusGames.addMouseListener(listener);
        painelMeusGames.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        painelPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public class ImagesListRenderer extends DefaultListCellRenderer{
        Font font = new Font("consolas", Font.BOLD, 12);
        
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index, 
                boolean isSelected, boolean cellHasFocus){
            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(controller.getGameImagesMap().get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDireita = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        painelConteudo = new javax.swing.JPanel();
        painelMenu = new javax.swing.JPanel();
        painelPerfil = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelImgUsuario = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        painelMeusGames = new javax.swing.JPanel();
        labelIconGames = new javax.swing.JLabel();
        labelGames = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelDireita.setLayout(new java.awt.BorderLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        painelDireita.add(jSeparator1, java.awt.BorderLayout.WEST);

        painelConteudo.setBackground(new java.awt.Color(102, 102, 102));
        painelConteudo.setPreferredSize(new java.awt.Dimension(800, 600));
        painelConteudo.setLayout(new java.awt.BorderLayout());
        painelDireita.add(painelConteudo, java.awt.BorderLayout.CENTER);

        getContentPane().add(painelDireita, java.awt.BorderLayout.CENTER);

        painelMenu.setBackground(new java.awt.Color(15, 22, 31));
        painelMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        painelMenu.setForeground(new java.awt.Color(255, 255, 255));
        painelMenu.setLayout(new java.awt.GridLayout(5, 0, 5, 0));

        painelPerfil.setBackground(new java.awt.Color(31, 50, 68));
        painelPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelPerfilMouseClicked(evt);
            }
        });
        painelPerfil.setLayout(new java.awt.BorderLayout());

        labelNomeUsuario.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeUsuario.setText("nome");
        labelNomeUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        painelPerfil.add(labelNomeUsuario, java.awt.BorderLayout.CENTER);

        labelImgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons8-usuário-24.png"))); // NOI18N
        labelImgUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        painelPerfil.add(labelImgUsuario, java.awt.BorderLayout.WEST);
        painelPerfil.add(jSeparator2, java.awt.BorderLayout.SOUTH);

        painelMenu.add(painelPerfil);

        painelMeusGames.setBackground(new java.awt.Color(15, 22, 31));
        painelMeusGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelMeusGamesMouseClicked(evt);
            }
        });
        painelMeusGames.setLayout(new java.awt.BorderLayout());

        labelIconGames.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        labelIconGames.setForeground(new java.awt.Color(255, 255, 255));
        labelIconGames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIconGames.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons8-joystick-48.png"))); // NOI18N
        labelIconGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        painelMeusGames.add(labelIconGames, java.awt.BorderLayout.WEST);

        labelGames.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        labelGames.setForeground(new java.awt.Color(255, 255, 255));
        labelGames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGames.setText("Games");
        labelGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        painelMeusGames.add(labelGames, java.awt.BorderLayout.CENTER);

        painelMenu.add(painelMeusGames);

        getContentPane().add(painelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void painelMeusGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMeusGamesMouseClicked
        GamesAPIListener listener = new GamesAPIListener() {
            @Override
            public void GettingData() {
                painelLoading.setVisible(true);
            }

            
            @Override
            public void DataArrived() {
                painelLoading.setVisible(false);
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                listener.GettingData();
                painelMyGames = new PainelMyGames(controller);
                painelConteudo.removeAll();
                painelConteudo.add(painelMyGames);
                atualizarPainel();
                listener.DataArrived();
            }
        });
        thread.start();
    }//GEN-LAST:event_painelMeusGamesMouseClicked

    private void painelPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelPerfilMouseClicked
        painelConteudo.removeAll();
        painelConteudo.add(painelProfile);
        atualizarPainel();
    }//GEN-LAST:event_painelPerfilMouseClicked
    
    private void atualizarPainel(){
        painelConteudo.repaint();
        painelConteudo.revalidate();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                      new TelaPrincipal().setVisible(true);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelGames;
    private javax.swing.JLabel labelIconGames;
    private javax.swing.JLabel labelImgUsuario;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelDireita;
    private javax.swing.JPanel painelMenu;
    private javax.swing.JPanel painelMeusGames;
    private javax.swing.JPanel painelPerfil;
    // End of variables declaration//GEN-END:variables
}
