package gamesapi;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import java.util.ArrayList;

/**
 *
 * @author Rafael F
 */
public interface GamesAPIController {
    ArrayList<String> getOwnedGamesNames(String usernameOrId) throws SteamApiException;
    ArrayList<Integer> getOwnedGamesPlaytimeForever(String usernameOrId) throws SteamApiException;
    String getMostPlayedGame(String usernameOrId) throws SteamApiException;
    
}
