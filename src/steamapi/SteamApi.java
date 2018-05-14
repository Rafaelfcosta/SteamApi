package steamapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityURLRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Rafael
 */
public class SteamApi {

    private static final String key = "EBB5731E63AA94B36333E6EA4DA6652D";
    private static String profile_id;
    private static String user;
    
    public static void main(String[] args) throws SteamApiException{
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(key).build();        

        user = ler("Qual o nome de usuário da Steam?");
        
        ResolveVanityURLRequest idRequest = SteamWebApiRequestFactory.createGetPlayerId(user);
        
        ResolveVanityURL playerId = client.<ResolveVanityURL>processRequest(idRequest);
                
        profile_id = playerId.getResponse().getSteamId();
        if(playerId.getResponse().getSuccess() == 1){
            GetOwnedGamesRequest gamesReq = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();

            GetOwnedGames ownedGames = client.<GetOwnedGames>processRequest(gamesReq);
            System.out.println("Total Games: " + ownedGames.getResponse().getGameCount());
            exibir(ownedGames.getResponse().getGames());
            for (Game game : ownedGames.getResponse().getGames()) {
                if(game.getPlaytimeForever() > 2000){   
                    System.out.println("App id -> " + game.getAppid() + " " + game.getName() + " // Playtime ->" + game.getPlaytimeForever() );
                }
            }
        }else{
            System.out.println(playerId.getResponse().getMessage() + " for '" + user + "'");
        }
    }
    
    private static String ler(String msg){
        return JOptionPane.showInputDialog(msg);
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
