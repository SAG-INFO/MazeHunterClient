/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.DashResponse;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 * 
 * Listens for the serverresponse to the dashrequest therefore renders the dash animation and updates the position.
 */
public class DashListener extends Listener{
    
    //TODO Animation
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof DashResponse){
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((DashResponse) object).id);
            player.position.set(((DashResponse) object).position);
        }
    }

    public DashListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
