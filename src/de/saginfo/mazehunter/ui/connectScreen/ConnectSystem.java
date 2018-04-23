package de.saginfo.mazehunter.ui.connectScreen;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.LabyRindMain;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author sreis
 */
public class ConnectSystem extends Listener {

    public ConnectSystem() {
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof ConnectResponse) {
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    LabyRindMain.GAME_SINGLETON.setScreen(new GameScreen());
                }
            });
        }
    }
}
