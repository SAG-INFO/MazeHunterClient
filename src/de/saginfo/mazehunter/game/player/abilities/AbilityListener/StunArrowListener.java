/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.projectiles.StunArrowProjectile;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class StunArrowListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object ) {
        if (object instanceof StunArrowResponse) {
            Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.projectiles.add(new StunArrowProjectile(((StunArrowResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((StunArrowResponse)object).id).position.cpy(), GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_SIZE, new SpriteVisual("assets\\abilities\\StunArrow\\stunArrow.png"), ((StunArrowResponse)object).id));
            }
        });
        }
    }
    
    public void StunArrowListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
