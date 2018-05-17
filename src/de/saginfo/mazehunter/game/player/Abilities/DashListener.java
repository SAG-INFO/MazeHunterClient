/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.Abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.DashResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;

/**
 *
 * @author karl.huber
 * 
 * Listens for the serverresponse to the dashrequest to then render the dash animation and update the position.
 */
public class DashListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object) {
        
        //TODO Animation
        
        if(object instanceof DashResponse){
            
            System.out.println("response empfangen");
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((DashResponse) object).id);
            player.position.set(((DashResponse) object).position);
            player.velocity.set(((DashResponse) object).velocity);
        }
    }

    public DashListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
