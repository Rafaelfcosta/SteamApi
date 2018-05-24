package gamesapi;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Rafael F
 */
public interface GamesAPIController {
    ArrayList<String> getOwnedGamesNames();
    ArrayList<Integer> getOwnedGamesPlaytimeForever();
    String getMostPlayedGame();
    Map<String, ImageIcon> getGameImagesMap();
}
