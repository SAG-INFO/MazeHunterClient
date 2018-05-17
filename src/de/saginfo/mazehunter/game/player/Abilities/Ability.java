/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.Abilities;

import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author Karl Huber
 */
public abstract class Ability extends Listener{
    public int cooldown;
    public boolean canuse;
    
    public void useAbility() {
        canuse = false;
        Timer cd = new Timer();
        cd.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                canuse = true;
            }
        }, cooldown);
    }
}