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
    Map<String, Integer> getOwnedGamesPlaytimeForever();
    String getMostPlayedGame();
    Map<String, ImageIcon> getGameImagesMap();
    String getProfileImageUrlMedium();
    String getProfileImageUrlFull();
    String getProfileName();
}
