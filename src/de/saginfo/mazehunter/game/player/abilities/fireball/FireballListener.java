/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.fireball;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.FireballResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.projectiles.FireballProjectile;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber
 */
public class FireballListener extends Listener{
    
    //TODO Animation
    
    public ArrayList<FireballProjectile> fireballs;
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof FireballResponse) {
            fireballs.add(new FireballProjectile(((FireballResponse)object).velocity, ((FireballResponse)object).position, GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_SIZE));
        }
    }

    public FireballListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}

