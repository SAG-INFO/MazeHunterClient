/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.AbilityConfigResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class AbilityConfigListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof AbilityConfigResponse){
            GameScreen.GAMESCREEN_SINGLETON.abiliyConfig = ((AbilityConfigResponse) object).abilityConfig;
        }
    }

    public AbilityConfigListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}