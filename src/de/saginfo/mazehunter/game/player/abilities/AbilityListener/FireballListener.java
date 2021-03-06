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
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballDispose;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityNotFoundException;
import de.saginfo.mazehunter.game.player.abilities.Entity.projectiles.FireballEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karl Huber
 */
public class FireballListener extends Listener{
     
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\abilities\\Fireball\\airhorn.mp3"));
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof FireballResponse) {
            FireballResponse data = (FireballResponse) object;
            
            Gdx.app.postRunnable(() -> {
                FireballEntity ball = new FireballEntity(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(data.connectionID).position.cpy(), data.projectileID);
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(ball);
                ball.shoot(data.velocity);
                sound.play(1.0f);
            });
            
        } else if (object instanceof FireballDispose) {
            Gdx.app.postRunnable(() -> {
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(((FireballDispose) object).projectileID);
            });
        }
    }

    public FireballListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
