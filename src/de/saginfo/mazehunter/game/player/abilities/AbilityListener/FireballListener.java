/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.StandardHealResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class FireballListener extends Listener{
    
    //TODO Animation
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof StandardHealResponse) {
            
        }
    }

    public FireballListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}

