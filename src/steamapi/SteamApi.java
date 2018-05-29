package steamapi;

import gamesapi.GamesAPIController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import ui.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class SteamApi {

    private static String user = "";
    private static GamesAPIController controller;
    
    public static void main(String[] args) throws Exception{

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }       

//        user = ler("Informe o usuário da Steam ou o Steam ID");
//        controller = new SteamAdapter(user);
        
//        ArrayList<String> gamesList = controller.getOwnedGamesNames();
//        ArrayList<Integer> playtimeList = controller.getOwnedGamesPlaytimeForever();
        
//        exibir(gamesList);
//        exibir(playtimeList);
        
//          exibirJList(gamesList);
//        exibir("Most played game: " + controller.getMostPlayedGame());
//          controller.getProfileImageUrl();
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }
    
    private static String ler(String msg){
        return JOptionPane.showInputDialog(msg);
    }
    
    private static void exibir(String texto){
        JOptionPane.showConfirmDialog(null, texto);
    }
        
    private static void exibir(ArrayList<?> games){
        JTextArea textArea = new JTextArea("", 20, 40);
        int i=1;
        for (Object game : games) {
            if(textArea.getText().isEmpty()){
                textArea.setText(i + " - " + game);
            }else{
                textArea.setText(textArea.getText() + "\n" + i + " - " + game);
            }
            i++;
        }
        textArea.setEditable(false);
        JScrollPane sp =new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, sp);
    }
    
    private static void exibirJList(ArrayList<?> games){
        DefaultListModel<String> model = new DefaultListModel();
        for (Object game : games) {
            model.addElement((String) game);
        }
        JList list = new JList(model);
        list.setBackground(Color.GRAY);
        list.setCellRenderer(new ImagesListRenderer());
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(800, 600));
        
        JFrame frame = new JFrame();
        frame.add(scroll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static class ImagesListRenderer extends DefaultListCellRenderer{
        Font font = new Font("helvetica", Font.BOLD, 12);
        
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
}
