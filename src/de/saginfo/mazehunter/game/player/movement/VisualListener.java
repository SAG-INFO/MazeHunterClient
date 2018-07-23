/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.movement;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.PlayerVisualUpdate;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;

/**
 *
 * @author arein
 */
public class VisualListener extends Listener{
    
    public VisualListener(){
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof PlayerVisualUpdate){
            PlayerVisualUpdate vu = (PlayerVisualUpdate)object;
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(vu.id);
            
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    player.visual.updateMovment(vu.direction, vu.moving);
                }
            });
        }
    }
}
