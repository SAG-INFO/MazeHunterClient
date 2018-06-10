/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.HealthUpdate;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class HealthUpdateListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof HealthUpdate) {
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((HealthUpdate) object).id);
            player.health += ((HealthUpdate) object).change;
        }
    }

    public HealthUpdateListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}