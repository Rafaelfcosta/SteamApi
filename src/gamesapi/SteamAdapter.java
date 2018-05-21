package gamesapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityURLRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import java.util.ArrayList;

/**
 *
 * @author Rafael F
 */
public class SteamAdapter implements GamesAPIController{
    
    private static final String key = "EBB5731E63AA94B36333E6EA4DA6652D";
    private static String profile_id = "";
    
    private SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(key).build();
    
    @Override
    public ArrayList<String> getOwnedGamesNames(String usernameOrId) throws SteamApiException{
        ArrayList<String> gamesList = new ArrayList<>();
        
        ResolveVanityURL playerId = null;
        boolean byId = false;
        int success = 0;
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
            GetOwnedGamesRequest gamesReq = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();
            GetOwnedGames ownedGames = client.<GetOwnedGames>processRequest(gamesReq);
//            System.out.println("Total Games: " + ownedGames.getResponse().getGameCount());
            int mais = 0;
            String gameName = "";
            for (Game game : ownedGames.getResponse().getGames()) {
                int tempo = game.getPlaytimeForever();
                if(tempo > mais){
                    mais  = tempo;
                    gameName = game.getName();
                }
                gamesList.add(game.getName());
            }
//            System.out.println("Most played Game: " + gameName + " -> " + mais);
        }else{
            System.out.println("No username/ID entered");
        }
        
        return gamesList;
    }

    @Override
    public ArrayList<Integer> getOwnedGamesPlaytimeForever(String usernameOrId) throws SteamApiException{
        ArrayList<Integer> playtimeList = new ArrayList<>();
        
        ResolveVanityURL playerId = null;
        boolean byId = false;
        int success = 0;
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
            GetOwnedGamesRequest gamesReq = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();
            GetOwnedGames ownedGames = client.<GetOwnedGames>processRequest(gamesReq);
            for (Game game : ownedGames.getResponse().getGames()) {
                playtimeList.add(game.getPlaytimeForever());
            }
        }else{
            System.out.println("No username/ID entered");
        }
        
        return playtimeList;
    }

    @Override
    public String getMostPlayedGame(String usernameOrId) throws SteamApiException {
        String gameName = "";
        ResolveVanityURL playerId = null;
        boolean byId = false;
        int success = 0;
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
            GetOwnedGamesRequest gamesReq = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(profile_id).includeAppInfo(true).includePlayedFreeGames(true).buildRequest();
            GetOwnedGames ownedGames = client.<GetOwnedGames>processRequest(gamesReq);
            int mais = 0;  
            for (Game game : ownedGames.getResponse().getGames()) {
                int tempo = game.getPlaytimeForever();
                if(tempo > mais){
                    mais  = tempo;
                    gameName = game.getName();
                }
            }
        }
        return gameName;
    }
}
