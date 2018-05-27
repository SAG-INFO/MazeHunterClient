 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.abilities.StandardHealRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class StandardHealInput extends InputAdapter {
    
    boolean cooldownUp = true;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F) {
            if (cooldownUp && Status.canMove == 0 && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON != null) {
                sendHealRequest();
                startCooldown();
            }
        }
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
        }, GameScreen.GAMESCREEN_SINGLETON.config.STANDARDHEAL_COOLDOWN);
    }
    
    public void sendHealRequest() {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new StandardHealRequest());
    }
    
    public StandardHealInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
