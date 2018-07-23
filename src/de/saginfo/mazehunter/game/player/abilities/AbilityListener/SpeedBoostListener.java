/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SpeedBoostResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.grafik.AnimationVisual;

/**
 *
 * @author arein
 */
public class SpeedBoostListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SpeedBoostResponse){
            Player p = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((SpeedBoostResponse) object).playerId);
            p.visual.fps = 25;
            
            Gdx.app.postRunnable(() ->{
                if(p.visual.moving)
                    ((AnimationVisual)p.visual.currentVisual).setFps(25);
            });
            
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    p.visual.fps = 10;
                }
            }, 5);
        }
    }
}
