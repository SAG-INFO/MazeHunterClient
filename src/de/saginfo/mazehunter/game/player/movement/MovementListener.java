/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.movement;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.MovementResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;

/**
 *
 * @author karl.huber
 * 
 * Listens for movementresponses from the server and updates the position and the velocity.
 */
public class MovementListener extends Listener{
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementResponse) {
            MovementResponse resp = (MovementResponse)object;
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(resp.id);
            player.position.set(resp.position);
            player.velocity.set(resp.velocity);
        }
    }

    public MovementListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
