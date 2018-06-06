package gamesapi.adapter;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityURLRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import gamesapi.GamesAPIController;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Rafael F
 */
public class SteamAdapter implements GamesAPIController{
    
    private static final String key = "EBB5731E63AA94B36333E6EA4DA6652D";
    private String profile_id = "";
    private ResolveVanityURL playerId = null;
    private boolean byId = false;
    private int success = 0;
    
    private SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(key).build();
    private GetOwnedGamesRequest gamesRequest;
    private GetOwnedGames ownedGames;
    private GetPlayerSummariesRequest playerSumariesRequest;
    private GetPlayerSummaries playerSumaries;

    public SteamAdapter(String usernameOrId) throws SteamApiException{
        if(!usernameOrId.isEmpty()){
            if(!usernameOrId.matches("[0-9]+") && usernameOrId.length() > 2){
                ResolveVanityURLRequest idRequest = SteamWebApiRequestFactory.createGetPlayerId(usernameOrId);
                playerId = client.<ResolveVanityURL>processRequest(idRequest);
                success = playerId.getResponse().getSuccess();
                profile_id = playerId.getResponse().getSteamId();
            }else{
                profile_id = usernameOrId;
                byId = true;
            }
        }
        if(success == 1 || byId){
            gamesRequest = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();
            ownedGames = client.<GetOwnedGames>processRequest(gamesRequest);
            
            List<String> ids = new ArrayList<>();
            ids.add(profile_id);
            playerSumariesRequest = new GetPlayerSummariesRequest.GetPlayerSummariesRequestBuilder(ids).buildRequest();
            playerSumaries = client.<GetPlayerSummaries>processRequest(playerSumariesRequest);
            
        
        }else{
            System.out.println("No username/ID entered or not find");
        }
    }
    
    
    
    @Override
    public ArrayList<String> getOwnedGamesNames(){
        ArrayList<String> gamesList = new ArrayList<>();
//          System.out.println("Total Games: " + ownedGames.getResponse().getGameCount());
        
        for (Game game : ownedGames.getResponse().getGames()) {
            if(game.getPlaytimeForever() > 0){
                Object a = ownedGames.getResponse().getAdditionalProperties().get(game.getName());
                gamesList.add(game.getName());
            }
        }
        
        return gamesList;
    }

//    @Override
//    public ArrayList<Integer> getOwnedGamesPlaytimeForever(){
//        ArrayList<Integer> playtimeList = new ArrayList<>();
//        for (Game game : ownedGames.getResponse().getGames()) {
//            playtimeList.add(game.getPlaytimeForever());
//        }
//                
//        return playtimeList;
//    }

    @Override
    public Map<String, Integer> getOwnedGamesPlaytimeForever() {
        Map<String, Integer> map = new HashMap<>();
        for (Game game : ownedGames.getResponse().getGames()) {
            map.put(game.getName(), game.getPlaytimeForever());
        }
        return map;
    }
    
    

    @Override
    public String getMostPlayedGame(){
        String gameName = "";
        int mais = 0;
        for (Game game : ownedGames.getResponse().getGames()) {
            int tempo = game.getPlaytimeForever();
            if(tempo > mais){
                mais  = tempo;
                gameName = game.getName();
            }
        }
        
        return gameName;
    }
    
    @Override
    public String getProfileImageUrlMedium(){
       return playerSumaries.getResponse().getPlayers().get(0).getAvatarmedium();
    }
    
    @Override
    public String getProfileImageUrlFull(){
       return playerSumaries.getResponse().getPlayers().get(0).getAvatarfull();
    }

    @Override
    public Map<String, ImageIcon> getGameImagesMap(){
        
        Map<String, ImageIcon> map = new HashMap<>();
        try {
            for (Game game : ownedGames.getResponse().getGames()) {
                if(game.getPlaytimeForever() > 0){
                    map.put(game.getName(), new ImageIcon(new URL("http://media.steampowered.com/steamcommunity/public/images/apps/" + game.getAppid() + "/"  + game.getImgLogoUrl() + ".jpg" )));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    @Override
    public String getProfileName() {
//        System.out.println(playerSumaries.getResponse().getPlayers().get(0).getProfileurl());
        return playerSumaries.getResponse().getPlayers().get(0).getPersonaname();
    }

    @Override
    public Integer getPersonState() {
        return playerSumaries.getResponse().getPlayers().get(0).getPersonastate();
    }
    
}
