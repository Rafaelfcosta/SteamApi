package gamesapi;

import gamesapi.adapter.SteamAdapter;

/**
 *
 * @author rafae
 */
public class GamesAPIControllerFactory {
    
    public static GamesAPIController create(String user)throws Exception{
        return new SteamAdapter(user);
    } 
}
