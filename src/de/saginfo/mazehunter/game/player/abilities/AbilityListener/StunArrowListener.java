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
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowShootResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityNotFoundException;
import de.saginfo.mazehunter.game.player.abilities.Entity.projectiles.StunArrowEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karl Huber
 */
public class StunArrowListener extends Listener{
    
    Sound sound = Gdx.audio.newSound(Gdx.files.local("assets\\abilities\\StunArrow\\stunArrowSound.mp3"));
    
    @Override
    public void received(Connection connection, Object object ) {
        if (object instanceof StunArrowResponse) {
            Gdx.app.postRunnable(() -> {
                sound.play(10.0f);
                System.out.println("StunArrowResponse received.");
                SpriteVisual visual = (new SpriteVisual("assets\\abilities\\StunArrow\\stunArrow.png"));
                visual.rotate(((StunArrowResponse) object).rotation);

                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(new StunArrowEntity(((StunArrowResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((StunArrowResponse)object).connectionID).position.cpy(), visual, ((StunArrowResponse)object).entityID));

            });} else if (object instanceof StunArrowShootResponse) {
            //shootanimation
            if (GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((StunArrowShootResponse) object).playerID) == GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer()) {
                //update hp bar
            }
            try {
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(((StunArrowShootResponse) object).entityID);
            } catch (EntityNotFoundException ex) {
                Logger.getLogger(StunArrowListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public StunArrowListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
