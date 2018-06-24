/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.CanMoveUpdate;
import de.saginfo.mazehunter.client.networkData.CanUseAbilitiesUpdate;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class StatusListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof CanMoveUpdate) {
            Status.canMove = ((CanMoveUpdate) object).canMove;
        }
        if(object instanceof CanUseAbilitiesUpdate) {
            Status.canUseAbilities = ((CanUseAbilitiesUpdate) object).canUseAbilities;
        }
    }

    public StatusListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
