/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.entity.DisposeNonMoving;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 */
public class NonMovingListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DisposeNonMoving) {
            System.out.println("DisposeNonMoving received.");
            GameScreen.GAMESCREEN_SINGLETON.game.nonMovingManager.disposeNonMoving(((DisposeNonMoving) object).entityID);
        }
    }

    public NonMovingListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
