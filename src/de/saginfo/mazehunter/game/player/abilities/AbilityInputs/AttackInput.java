/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.abilities.FireballRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class AttackInput extends InputAdapter {
    
    public boolean canCollect = true;
    
    public int fireBallCharges;
    public boolean canUseFireball;
    
    public boolean blizzardCharges;
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Buttons.LEFT && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            if (fireBallCharges > 0 && canUseFireball) {
                useFireBall();
            } else if (blizzardCharges) {
                useBlizzard();
            }
        }
        return false;
    }
    
    public void useFireBall() {
        fireBallCharges--;
        if (fireBallCharges == 0) {
            canCollect = true;
        }
        
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new FireballRequest(getMouseAngle()));
        
        canUseFireball = false;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task(){
            @Override
            public void run() {
                canUseFireball = true;
            }
        }, GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_CD_BETWEEN_USES);
    }
    
    public void useBlizzard() {
        blizzardCharges = false;
        canCollect = true;
        //TODO: Request Blizzard
    }

    public void collectFireball() {
        if (canCollect) {
            canCollect = false;
            fireBallCharges = GameScreen.GAMESCREEN_SINGLETON.config.FIREBALL_CHARGES;
        }
    }
    
    public void collectBlizzard() {
        if (canCollect) {
            canCollect = false;
            blizzardCharges = true;
        }
    }
    
    public float getMouseAngle() {
        final Vector2 playerPos = new Vector2(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position);
        final Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        final Vector3 unpPlayerPos3 = new Vector3(GameScreen.GAMESCREEN_SINGLETON.camera.unproject(new Vector3(playerPos.x, playerPos.y, 0)));
        final Vector2 unpPlayerPos2 = new Vector2(unpPlayerPos3.x, unpPlayerPos3.y);
        final Vector2 playerToMouse = new Vector2(mousePos.sub(unpPlayerPos2));
        return playerToMouse.angle();
    }
    
    public AttackInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}

