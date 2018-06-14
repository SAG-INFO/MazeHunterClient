/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowShootResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapShootResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;
import de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving.TrapNonMoving;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class TrapListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object ) {
        if (object instanceof TrapResponse) {
            Gdx.app.postRunnable(() -> {
                System.out.println("TrapResponse received.");
                SpriteVisual visual = (new SpriteVisual("assets\\abilities\\Trap\\trap.png"));
                GameScreen.GAMESCREEN_SINGLETON.game.nonMovingManager.nonMoving.add(new TrapNonMoving(((TrapResponse) object).position, ((TrapResponse)object).entityID, visual));
            });} else if (object instanceof TrapShootResponse) {
            //shootanimation
            if (GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((TrapShootResponse) object).connectionID) == GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer()) {
                Status.root(GameScreen.GAMESCREEN_SINGLETON.config.TRAP_ROOTDURATION);
            }
            GameScreen.GAMESCREEN_SINGLETON.game.nonMovingManager.disposeNonMoving(((TrapShootResponse) object).entityID);
        }
    }
    
    public TrapListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}