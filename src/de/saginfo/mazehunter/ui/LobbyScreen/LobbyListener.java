package de.saginfo.mazehunter.ui.LobbyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.client.networkData.lobby.LobbyUpdate;
import de.saginfo.mazehunter.client.networkData.lobby.PlayerLobby;
import de.saginfo.mazehunter.client.networkData.StartGameResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 * A NetworkListener that waits for messages from the server.
 * Adds and Removes Players from the Lobby (when they connect or disconnect).
 * Starts the Game when a player decides to do so. 
 * At the moment also responsible for creating Players after the Game is started.
 * @author sreis
 */
public class LobbyListener extends Listener{
    private final LobbyScreen lobbyScreen;

    public LobbyListener(LobbyScreen lobbyScreen) {
        this.lobbyScreen = lobbyScreen;
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof LobbyUpdate){
            LobbyUpdate update = (LobbyUpdate) object;
            lobbyScreen.updatePlayers(update.nutten);
        }
        
        else if(object instanceof StartGameResponse){
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    startGame();
                }
            });
        }
    }
    
    private void startGame(){
        GameScreen gameScreen = new GameScreen(lobbyScreen.client);
        MazeHunterMain.MAIN_SINGLETON.setScreen(gameScreen);
    }
}
