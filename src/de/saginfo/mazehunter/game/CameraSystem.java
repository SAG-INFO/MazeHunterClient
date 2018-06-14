/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game;

import static com.badlogic.gdx.math.Rectangle.tmp;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author s.reiser
 */
public class CameraSystem {
    
    private final Vector3 tmp = new Vector3();
    private final float alpha = 0.5f;

    public CameraSystem() {
        GameScreen.GAMESCREEN_SINGLETON.camera.zoom = 0.5f;
    }
    
    public void update(){
        tmp.set(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position, 0);
        GameScreen.GAMESCREEN_SINGLETON.camera.position.lerp(tmp, alpha);
    }
}
