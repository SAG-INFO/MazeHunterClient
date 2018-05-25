/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 * 
 * Checks if the user pressed space as well as if the player is currently effected by cc Abilities and sends a dashrequest if needed.
 */
public class DashInput extends InputAdapter {
    
    boolean cooldownUp = true;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SHIFT_LEFT) {
            if (cooldownUp && Status.canMove == 0 && Status.canUseAbilities == 0) {
                sendDashRequest();
                startCooldown();
            }
        }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    public void startCooldown() {
        cooldownUp = false;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task(){
            @Override
            public void run() {
                cooldownUp = true;
            }
        }, GameScreen.GAMESCREEN_SINGLETON.dashConfig.DASH_COOLDOWN);
    }
    
    public void sendDashRequest() {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new DashRequest());
    }
    
    public DashInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}