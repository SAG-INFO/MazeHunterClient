/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Map;

/**
 *
 * @author karl.huber
 */
public class SlideListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof SlideResponse) {

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    System.out.println("SlideResponse received.");

                    final Vector2 velocity = new Vector2(Map.blockbreite, 0);
                    int tmp = 0;

                    switch (((SlideResponse) object).direction) {
                        case 'N':
                            velocity.setAngle(90);
                            tmp = 1;
                            break;
                        case 'O':
                            velocity.setAngle(0);
                            tmp = 2;
                            break;
                        case 'S':
                            velocity.setAngle(180);
                            tmp = 3;
                            break;
                        case 'W':
                            velocity.setAngle(270);
                            tmp = 4;
                            break;
                    }

                    //GameScreen.GAMESCREEN_SINGLETON.game.world.map.movePlayers(velocity, ((SlideResponse) object).row);
                    int r = GameScreen.GAMESCREEN_SINGLETON.game.world.map.translateCoordinateToBlock(((SlideResponse) object).row);
                    System.out.println(" row:" + ((SlideResponse) object).row + "direction:" + r);
                    GameScreen.GAMESCREEN_SINGLETON.game.world.map.moveRow(r, tmp);
                }
            });
        }
    }

    public SlideListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
