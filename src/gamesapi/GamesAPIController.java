package gamesapi;

import java.util.ArrayList;

/**
 *
 * @author Rafael F
 */
public interface GamesAPIController {
    ArrayList<Object> getOwnedGamesNames();
    ArrayList<Object> getOwnedGamesPlaytimeForever();
}
