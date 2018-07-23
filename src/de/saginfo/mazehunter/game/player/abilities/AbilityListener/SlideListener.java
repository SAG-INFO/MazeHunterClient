/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Block;
import de.saginfo.mazehunter.game.map.Map;
import static de.saginfo.mazehunter.game.map.Map.blockWorldwidth;
import de.saginfo.mazehunter.game.player.Player;

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
            Gdx.app.postRunnable(() -> {
                move(((SlideResponse) object).row, ((SlideResponse) object).direction);
                GameScreen.GAMESCREEN_SINGLETON.game.world.visionSystem.startVision();
            });
        }
    }

    private void move(int row, int direction) {
        switch (direction) {
            case 1:
                movePlayerVertical(row, direction);
                moveRowUp(row);
                break;
            case 2:
                movePlayerHorizontal(row, direction);
                moveRowRight(row);
                break;
            case 3:
                movePlayerVertical(row, direction);
                moveRowDown(row);
                break;
            case 4:
                movePlayerHorizontal(row, direction);
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
        Block in = map.blocklist[0][k];
        Block out = in.clone();

        in.setPosition(blockWorldwidth, k);
        in.animatePosition(blockWorldwidth - 1, in.getY());

        out.setPosition(0, out.getY());
        out.animatePosition(- 1, out.getY());
        out.disposeAfterDelay();

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[i][k];
            block.animatePosition(block.getX() - 1, block.getY());
            map.blocklist[i - 1][k] = map.blocklist[i][k];
        }
        map.blocklist[blockWorldwidth - 1][k] = in;
    }

    private void moveRowUp(int k) {
        Block in = map.blocklist[k][blockWorldwidth - 1];
        Block out = in.clone();

        in.setPosition(k, -1);
        in.animatePosition(in.getX(), 0);

        out.setPosition(out.getX(), blockWorldwidth - 1);
        out.animatePosition(out.getX(), blockWorldwidth);
        out.disposeAfterDelay();

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[k][i];
            block.animatePosition(block.getX(), block.getY() + 1);
            map.blocklist[k][i + 1] = block;
        }
        map.blocklist[k][0] = in;
    }

    private void moveRowDown(int k) {
        Block in = map.blocklist[k][0];
        Block out = in.clone();

        in.setPosition(k, blockWorldwidth);
        in.animatePosition(in.getX(), blockWorldwidth - 1);

        out.setPosition(out.getX(), 0);
        out.animatePosition(out.getX(), - 1);
        out.disposeAfterDelay();

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[k][i];
            block.animatePosition(block.getX(), block.getY() - 1);
            map.blocklist[k][i - 1] = block;
        }
        map.blocklist[k][blockWorldwidth - 1] = in;
    }

    private void movePlayerHorizontal(int row, int direction) {
        GameScreen.GAMESCREEN_SINGLETON.game.players.stream().filter((p) -> (map.translateCoordinateToBlock(p.position.y) == row)).forEachOrdered((p) -> {
            float relativeX = Map.blockbreite * (direction == 2 ? 1 : -1);
            p.visual.playSlideAnimation(relativeX, 0);
            
            if(p.isLocal())
                GameScreen.GAMESCREEN_SINGLETON.camera.slide(relativeX, 0);
        });
    }

    private void movePlayerVertical(int row, int direction) {
        GameScreen.GAMESCREEN_SINGLETON.game.players.stream().filter((p) -> (map.translateCoordinateToBlock(p.position.x) == row)).forEachOrdered((p) -> {
            float relativeY = Map.blockbreite * (direction == 1 ? 1 : -1);
            p.visual.playSlideAnimation(0, relativeY);
            if(p.isLocal())
                GameScreen.GAMESCREEN_SINGLETON.camera.slide(0, relativeY);
        });
    }

}
