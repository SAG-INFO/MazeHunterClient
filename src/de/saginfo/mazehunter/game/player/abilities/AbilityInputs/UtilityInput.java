/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.client.networkData.abilities.UtilityRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class UtilityInput extends InputAdapter {
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new UtilityRequest());
        }
        return false;
    }
    
    public UtilityInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
