/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.projectiles.FireballProjectile;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber
 */
public class FireballListener extends Listener{
     
    //TODO Animation
    
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof FireballResponse) {
            GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.projectiles.add(new FireballProjectile(((FireballResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((FireballResponse)object).id).position, GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_SIZE, new SpriteVisual("assets\\img\\player\\fireball.png"), ((FireballResponse)object).id));
        }
    }

    public FireballListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
