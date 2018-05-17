/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.DashResponse;
import de.saginfo.mazehunter.client.networkData.MovementResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 */
public class DashListener extends Listener{
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof DashResponse){
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((MovementResponse) object).id);
            player.position.set(((MovementResponse) object).position);
            player.velocity.set(((MovementResponse) object).velocity);
        }
    }

    public DashListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
