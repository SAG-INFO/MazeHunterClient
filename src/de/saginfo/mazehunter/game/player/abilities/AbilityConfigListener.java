/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.blizzard.BlizzardConfigResponse;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashConfigResponse;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealConfigResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class AbilityConfigListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof DashConfigResponse){
            GameScreen.GAMESCREEN_SINGLETON.dashConfig = ((DashConfigResponse)object).dashConfig;
        }
        if(object instanceof StandardHealConfigResponse){
            GameScreen.GAMESCREEN_SINGLETON.standardHealConfig = ((StandardHealConfigResponse)object).healConfig;
        }
        if(object instanceof BlizzardConfigResponse){
            GameScreen.GAMESCREEN_SINGLETON.blizzardConfig = ((BlizzardConfigResponse)object).blizzardConfig;
        }
    }

    public AbilityConfigListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}