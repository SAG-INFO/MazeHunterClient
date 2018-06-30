package de.saginfo.mazehunter.ui.gameoverscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.client.networkData.Gameover;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author sreis
 */
public class GameoverListener extends Listener {

    public GameoverListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof Gameover) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    exitGame();
                }
            });
        }
    }

    private void exitGame() {
        GameScreen.GAMESCREEN_SINGLETON.game.close();
        GameScreen.GAMESCREEN_SINGLETON.dispose();
        GameoverScreen gameoverScreen = new GameoverScreen();
        MazeHunterMain.MAIN_SINGLETON.setScreen(gameoverScreen);
    }
}
