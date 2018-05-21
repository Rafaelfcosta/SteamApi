package steamapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityURLRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import gamesapi.GamesAPIController;
import gamesapi.SteamAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Rafael
 */
public class SteamApi {

    private static String user = "";
    private static GamesAPIController controller = new SteamAdapter();
    
    public static void main(String[] args) throws SteamApiException{

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }       

        user = ler("Informe o usuário da Steam ou o Steam ID");
        ArrayList<String> gamesList = controller.getOwnedGamesNames(user);
        ArrayList<Integer> playtimeList = controller.getOwnedGamesPlaytimeForever(user);
        
        exibir(gamesList);
        
        exibir(controller.getMostPlayedGame(user));
    }
    
    private static String ler(String msg){
        return JOptionPane.showInputDialog(msg);
    }
    
    private static void exibir(String texto){
        JOptionPane.showConfirmDialog(null, texto);
    }
        
    private static void exibir(ArrayList<String> games){
        JTextArea textArea = new JTextArea("", 20, 40);
        int i=1;
        for (String game : games) {
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
}
