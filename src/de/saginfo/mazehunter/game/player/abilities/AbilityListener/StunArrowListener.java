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
import de.saginfo.mazehunter.game.player.abilities.projectiles.StunArrowProjectile;
import de.saginfo.mazehunter.grafik.SpriteVisual;

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
                GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.projectiles.add(new StunArrowProjectile(((StunArrowResponse)object).velocity, GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((StunArrowResponse)object).connectionID).position.cpy(), GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_SIZE, visual, ((StunArrowResponse)object).projectileID));
            });} else if (object instanceof StunArrowShootResponse) {
            //shootanimation
            if (GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((StunArrowShootResponse) object).playerID) == GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer()) {
                Status.stun(((StunArrowShootResponse) object).stunDuration);
            }
            GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.disposeProjectile(((StunArrowShootResponse) object).projectileID);
        }
    }
    
    public StunArrowListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
