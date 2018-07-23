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
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.projectiles.StunArrowEntity;

/**
 *
 * @author Karl Huber
 */
public class StunArrowListener extends Listener {

    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\abilities\\StunArrow\\stunArrowSound.mp3"));

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof StunArrowResponse) {
            StunArrowResponse data = (StunArrowResponse) object;
            
            Gdx.app.postRunnable(() -> {
                StunArrowEntity ball = new StunArrowEntity(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(data.connectionID).position.cpy(), data.projectileID);
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(ball);
                ball.shoot(data.velocity);
                sound.play(1.0f);
            });
        }
    }

    public StunArrowListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
