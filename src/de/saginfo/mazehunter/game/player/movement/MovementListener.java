/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\sounds\\walking.mp3"));
    private boolean soundIsPlaying;
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof MovementResponse) {
            
            if(((MovementResponse) object).velocity.isZero()) {
                sound.stop();
                soundIsPlaying = false;
            } else if(soundIsPlaying==false) {
                sound.loop(1.0f);
                soundIsPlaying = true;
            } else {
                
            }
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((MovementResponse) object).id);
            player.position.set(((MovementResponse) object).position);
            player.velocity.set(((MovementResponse) object).velocity);
        }
    }

    public MovementListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
