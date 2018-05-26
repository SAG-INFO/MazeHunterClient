/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.configs.PushConfig;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class ConfigListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof PushConfig){
            GameScreen.GAMESCREEN_SINGLETON.config = (PushConfig)object;
            System.out.println(GameScreen.GAMESCREEN_SINGLETON.config.BLIZZARD_COOLDOWN);
        }
    }

    public ConfigListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}