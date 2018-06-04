/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.client.networkData.abilities.StandardHealRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class UtilityInput extends InputAdapter {
    
    public boolean canCollect = true;
    
    public boolean standardHealCharges;
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            if (standardHealCharges) {
                useStandardHeal();
            }
        }
        return false;
    }
    
    public void useStandardHeal() {
        standardHealCharges = false;
        canCollect = true;
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new StandardHealRequest());
    }
    
    public UtilityInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
