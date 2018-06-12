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
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballShootResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.projectiles.FireballProjectile;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class FireballListener extends Listener{
     
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\abilities\\Fireball\\airhorn.mp3"));
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof FireballResponse) {
            Gdx.app.postRunnable(() -> {
                SpriteVisual visual = new SpriteVisual("assets\\abilities\\Fireball\\fireball.png");
                visual.rotate(((FireballResponse) object).rotation);
                GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.projectiles.add(new FireballProjectile(((FireballResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((FireballResponse)object).connectionID).position.cpy(), GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_SIZE, visual, ((FireballResponse)object).projectileID));
            });
            sound.play(1.0f);
        } else if (object instanceof FireballShootResponse) {
            //shootanimation
            GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.disposeProjectile(((FireballShootResponse) object).projectileID);
        }
    }

    public FireballListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
