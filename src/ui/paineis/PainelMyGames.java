package ui.paineis;

import gamesapi.GamesAPIController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Rafael
 */
public class PainelMyGames extends JPanel {

    private static GamesAPIController controller;
    
    public PainelMyGames(GamesAPIController controller) {
        initComponents();
        
        PainelMyGames.controller = controller;
        
        ArrayList<String> games = controller.getOwnedGamesNames();
        Map<String, Integer> playtimeMap = controller.getOwnedGamesPlaytimeForever();
        Map<String, ImageIcon> imagesMap = controller.getGameImagesMap();
        
        
        DefaultListModel<String> model = new DefaultListModel();
        for (Object game : games) {
            model.addElement((String) game);
        }
        JList list = new JList(model);
        list.setBackground(Color.GRAY);
        list.setCellRenderer(new ImagesListRenderer());
        
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                JList list = (JList) evt.getSource();
                if(evt.getClickCount() == 2){
                    JLabel label = new JLabel();
                    label.setFont(new Font("consolas", Font.PLAIN, 20));
                    int playtime = playtimeMap.get(games.get(list.getSelectedIndex()));
                    String gameName = games.get(list.getSelectedIndex());
                    
                    if(playtime < 60){
                        String texto = "<html><center>" + gameName + "</center><br>Playtime: " + String.valueOf(playtime) +
                                " Mins </html>";
                        label.setText(texto);
                        exibir(label, imagesMap.get(gameName));
                        
                    }else{
                        playtime /= 60;
                        String texto = "<html><center>" + gameName + "</center><br>Playtime: " + String.valueOf(playtime) + 
                                " Hours </html>";
                        label.setText(texto);
                        exibir(label, imagesMap.get(gameName));
                    }
                    
                }
            }
        });
        
        JScrollPane scroll = new JScrollPane(list);       
        this.add(scroll);
    }
    
    private static void exibir(Object msg, Icon icon){
        JOptionPane.showMessageDialog(null, msg, "Details", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    
    public static class ImagesListRenderer extends DefaultListCellRenderer{
        Font font = new Font("consolas", Font.PLAIN, 20);
        
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

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
