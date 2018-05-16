package steamapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityURLRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import java.rmi.server.UID;
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

    private static final String key = "EBB5731E63AA94B36333E6EA4DA6652D";
    private static String profile_id = "";
    private static String user = "";
    
    public static void main(String[] args) throws SteamApiException{
        
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(key).build();        

        user = ler("Informe o usuário da Steam ou o Steam ID");
        
        ResolveVanityURL playerId = null;
        boolean byId = false;
        int success = 0;
        if(!user.isEmpty()){
            if(!user.matches("[0-9]+") && user.length() > 2){
                ResolveVanityURLRequest idRequest = SteamWebApiRequestFactory.createGetPlayerId(user);
                playerId = client.<ResolveVanityURL>processRequest(idRequest);
                success = playerId.getResponse().getSuccess();
                profile_id = playerId.getResponse().getSteamId();
            }else{
                profile_id = user;
                byId = true;
            }
        }
        if(success == 1 || byId){
            GetOwnedGamesRequest gamesReq = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();

            GetOwnedGames ownedGames = client.<GetOwnedGames>processRequest(gamesReq);
            System.out.println("Total Games: " + ownedGames.getResponse().getGameCount());
            //exibir(ownedGames.getResponse().getGames());
            int mais = 0;
            String gameName = "";
            for (Game game : ownedGames.getResponse().getGames()) {
                int tempo = game.getPlaytimeForever();
                if(tempo > mais){
                    mais  = tempo;
                    gameName = game.getName();
                }
                //if(tempo > 2000){   
                    System.out.println("App id -> " + game.getAppid() + " " + game.getName() + " // Playtime ->" + game.getPlaytimeForever() );
                //}
            }
            //exibir(gameName + " -> " + mais);
            System.out.println("Most played Game: " + gameName + " -> " + mais);
        }else{
            System.out.println("No username/ID entered");
        }
    }
    
    private static String ler(String msg){
        return JOptionPane.showInputDialog(msg);
    }
    
    private static void exibir(String texto){
        JOptionPane.showConfirmDialog(null, texto);
    }
    
    private static void exibir(List<Game> games){
        JTextArea textArea = new JTextArea("", 20, 40);
        int i=1;
        for (Game game : games) {
            if(textArea.getText().isEmpty()){
                textArea.setText(i + " - " + game.getName());
            }else{
                textArea.setText(textArea.getText() + "\n" + i + " - " + game.getName());
            }
            i++;
        }
        textArea.setEditable(false);
        JScrollPane sp =new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, sp);
    }    
}
