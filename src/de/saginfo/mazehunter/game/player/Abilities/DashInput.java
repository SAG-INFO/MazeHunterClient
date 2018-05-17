/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.Abilities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.DashRequest;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 * 
 * Checks if the Client pressed space and sends a dashrequest if done so.
 */
public class DashInput extends InputAdapter {
    public int cooldown = 5;
    public boolean canuse = true;
    
    Timer cdtimer = new Timer();
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SHIFT_LEFT && canuse == true) {
                sendDashRequest(); 
                canuse = false;
                cdtimer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        canuse = true;
                    }
                }, cooldown);
                
        }
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