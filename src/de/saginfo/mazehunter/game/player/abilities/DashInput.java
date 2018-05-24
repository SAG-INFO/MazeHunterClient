/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.client.networkData.DashRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;
import static java.lang.Thread.sleep;

/**
 *
 * @author Karl Huber
 * 
 * Checks if the user pressed space as well as if the player is currently effected by cc Abilities and sends a dashrequest if needed.
 */
public class DashInput extends InputAdapter {
    
    boolean cooldownUp = true;
    boolean cancel;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SHIFT_LEFT) {
            if (cooldownUp && Status.canMove == 0 && Status.canUseAbilities == 0) {
                sendDashRequest();
            }
        }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    public void sendDashRequest() {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new DashRequest());
    }
    
    public DashInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}