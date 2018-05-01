package de.saginfo.mazehunter.ui.connectScreen;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.ui.LobbyScreen.LobbyScreen;

/**
 *
 * @author sreis
 */
public class ConnectSystem extends Listener {

    private ConnectScreen screen;

    public ConnectSystem(ConnectScreen screen) {
        this.screen = screen;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof ConnectResponse) {
            if(((ConnectResponse) object).connectionAccepted)
                joinLobby();
            else
                screen.displayError(((ConnectResponse) object).msg);
        }
    }

    private void joinLobby() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                LobbyScreen lbb = new LobbyScreen(screen.client);
                MazeHunterMain.MAIN_SINGLETON.setScreen(lbb);
            }
        });

    }
}
