/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.entity.DisposeEntity;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karl.huber
 */
public class EntityListener extends Listener {
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DisposeEntity) {
            System.out.println("DisposeNonMoving received.");
            try {
                GameScreen.GAMESCREEN_SINGLETON.game.entityManager.disposeEntity(((DisposeEntity) object).entityID);
            } catch (EntityNotFoundException ex) {
                Logger.getLogger(EntityListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public EntityListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
