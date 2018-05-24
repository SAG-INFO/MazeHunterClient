/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.MovementRequest;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class Status {
    // "mobility" type Abilities are also affected by canMove
    public static int canMove; // 0 == true
    public static int canUseAbilities; // 0 == true
    
    public void stun(float duration) {
        stopMovement();
        canMove++;
        canUseAbilities++;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task(){
            @Override
            public void run() {
                canMove--;
                canUseAbilities--;
            }
        }, duration);
    }
    
    public void root(float duration) {
        stopMovement();
        canMove++;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task(){
            @Override
            public void run() {
                canMove--;
            }
        }, duration);
    }
    
    public void silence(float duration) {
        canUseAbilities++;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task(){
            @Override
            public void run() {
                canUseAbilities--;
            }
        }, duration);
    }
    
    public void stopMovement(){
        MovementRequest mr = new MovementRequest(0,false);
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(mr);
    }
}
