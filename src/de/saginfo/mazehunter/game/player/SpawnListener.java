package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.SpawnPlayer;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author arein
 */
public class SpawnListener extends Listener{
    
    public SpawnListener(){
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SpawnPlayer){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    SpawnPlayer si = (SpawnPlayer)object;
                    GameScreen.GAMESCREEN_SINGLETON.game.createPlayer(si.id, si.name, si.position, si.hunter);
                }
            });
        }
    }
}
