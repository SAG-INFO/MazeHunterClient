/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.DashResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;

/**
 *
 * @author Karl Huber
 */
public class DashListener extends Listener {
    
    //TODO Animation
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\sounds\\Fart.mp3"));
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof DashResponse){
            
            Player player = GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((DashResponse) object).id);
            player.position.set(((DashResponse) object).position);
            player.velocity.set(((DashResponse) object).velocity);
            
            sound.play(1.0f);
        }
    }

    public DashListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
