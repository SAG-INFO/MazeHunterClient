/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Block;
import de.saginfo.mazehunter.game.map.Map;
import static de.saginfo.mazehunter.game.map.Map.blockWorldwidth;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.grafik.VectorAcessor;

/**
 * @author paul kuschfeldt, julian mittermeier, seamuel reiser, karl huber
 */
public class SlideListener extends Listener {

    private final Map map;

    public SlideListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
        this.map = GameScreen.GAMESCREEN_SINGLETON.game.world.map;
    }

    @Override
    public void received(Connection connection, final Object object) {
        if (object instanceof SlideResponse) {
            final SlideResponse rp = (SlideResponse) object;
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    move(((SlideResponse) object).row, ((SlideResponse) object).direction);
                }
            });

            Timer.schedule(new Timer.Task() {
                public void run() {
                    GameScreen.GAMESCREEN_SINGLETON.game.world.visionSystem.startVision();
                }
            }, 0.75f);
        }
    }

    private void move(int row, int direction) {
        switch (direction) {
            case 1:
                doRowY(row, direction);
                moveRowUp(row);
                break;
            case 2:
                doRowX(row, direction);
                moveRowRight(row);
                break;
            case 3:
                doRowY(row, direction);
                moveRowDown(row);
                break;
            case 4:
                doRowX(row, direction);
                moveRowLeft(row);
                break;
        }
    }

    private void moveRowRight(int k) {
        Block in = map.blocklist[blockWorldwidth - 1][k];
        Block out = in.clone();

        in.setPosition(-1, in.getY());
        in.animatePosition(0, in.getY());

        out.setPosition(blockWorldwidth - 1, out.getY());
        out.animatePosition(blockWorldwidth, out.getY());
        out.disposeAfterDelay();

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[i][k];
            block.animatePosition(block.getX() + 1, block.getY());
            map.blocklist[i + 1][k] = map.blocklist[i][k];
        }

        map.blocklist[0][k] = in;
    }

    private void moveRowLeft(int k) {
        Block out = map.blocklist[0][k];
        out.animatePosition(-1, out.getY());
        out.disposeAfterDelay();

        Block in = out.clone();
        in.setPosition(blockWorldwidth, in.getY());
        in.animatePosition(blockWorldwidth - 1, in.getY());

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[i][k];
            block.animatePosition(block.getX() - 1, block.getY());
            map.blocklist[i - 1][k] = map.blocklist[i][k];
        }
        map.blocklist[blockWorldwidth - 1][k] = in;
    }

    private void moveRowUp(int k) {
        Block out = map.blocklist[k][blockWorldwidth - 1];
        out.animatePosition(out.getX(), blockWorldwidth);
        out.disposeAfterDelay();

        Block in = out.clone();
        in.setPosition(in.getX(), -1);
        in.animatePosition(in.getX(), 0);

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[k][i];
            block.animatePosition(block.getX(), block.getY() + 1);
            map.blocklist[k][i + 1] = block;
        }
        map.blocklist[k][0] = in;
    }

    private void moveRowDown(int k) {
        Block out = map.blocklist[k][0];
        out.animatePosition(out.getX(), -1);
        out.disposeAfterDelay();

        Block in = out.clone();
        in.setPosition(in.getX(), blockWorldwidth);
        in.animatePosition(in.getX(), blockWorldwidth - 1);

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[k][i];
            block.animatePosition(block.getX(), block.getY() - 1);
            map.blocklist[k][i - 1] = block;
        }
        map.blocklist[k][blockWorldwidth - 1] = in;
    }

        private void doRowX(int row, int direction) {
        for (Player p : GameScreen.GAMESCREEN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.position.y) == row) {
                float targetX = map.boundPosition(p.position.x+(Map.blockbreite*(direction==2?1:-1)));
                Tween.to(p.position, VectorAcessor.POSITION_X, 1).target(targetX).start(GameScreen.GAMESCREEN_SINGLETON.tweenManager);
            }
        }
    }

    private void doRowY(int row, int direction) {
        for (Player p : GameScreen.GAMESCREEN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.position.x) == row) {
                float targetY = map.boundPosition(p.position.y+(Map.blockbreite*(direction==1?1:-1)));
                Tween.to(p.position, VectorAcessor.POSITION_Y, 1).target(targetY).start(GameScreen.GAMESCREEN_SINGLETON.tweenManager);
            }
        }
    }
    
}
