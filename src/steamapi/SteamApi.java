package steamapi;


import com.alee.laf.WebLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import ui.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class SteamApi {
    
    public static void main(String[] args){

        try {
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            WebLookAndFeel.install();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        
        TelaPrincipal tela;
        try {
            tela = new TelaPrincipal();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(SteamApi.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }
        
    }
}
