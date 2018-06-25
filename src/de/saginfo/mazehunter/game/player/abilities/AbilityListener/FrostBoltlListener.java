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
import de.saginfo.mazehunter.client.networkData.abilities.responses.FrostBoltResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FrostBoltShootResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityNotFoundException;
import de.saginfo.mazehunter.game.player.abilities.Entity.projectiles.FrostBoltEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karl Huber
 */
public class FrostBoltlListener extends Listener{
     
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\abilities\\Fireball\\airhorn.mp3"));
    
    @Override
    public void received(Connection connection, Object object) {
        
        if(object instanceof FrostBoltResponse) {
            Gdx.app.postRunnable(() -> {
                SpriteVisual visual = new SpriteVisual("assets\\abilities\\Fireball\\fireball.png");
                visual.rotate(((FrostBoltResponse) object).rotation);
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(new FrostBoltEntity(((FrostBoltResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((FrostBoltResponse)object).connectionID).position.cpy(), visual, ((FrostBoltResponse)object).entityID));
            });
            sound.play(1.0f);
        } else if (object instanceof FrostBoltShootResponse) {
            try {
                //shootanimation
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(((FrostBoltShootResponse) object).projectileID);
            } catch (EntityNotFoundException ex) {
                Logger.getLogger(FrostBoltlListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public FrostBoltlListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
