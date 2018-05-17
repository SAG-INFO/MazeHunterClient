/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.client.networkData.DashRequest;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class DashInput extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SPACE) {
            sendDashRequest();
        }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    public void sendDashRequest() {
        DashRequest dashRequest = new DashRequest();
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(dashRequest);
    }
    
    public DashInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}