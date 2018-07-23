/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map.camera;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.VectorAcessor;

/**
 *
 * @author arein
 */
public class MazeCam extends OrthographicCamera {

    private Shake shake;

    private final float cameraSmooth = 0.3f;
    private final Vector3 tmpVec3 = new Vector3();
    private final Vector2 tmpVec2 = new Vector2();
    
    private boolean slideInProgress;
    
    public MazeCam() {
        shake = new Shake();
        super.zoom = 0.95f;
    }

    public void update(float delta) {
        super.update();
        if (GameScreen.GAMESCREEN_SINGLETON.game.localPlayerExists() && !slideInProgress) {
            GameScreen.GAMESCREEN_SINGLETON.camera.position.lerp(tmpVec3.set(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position, 0), cameraSmooth);
        }
        shake.update(delta, this, tmpVec2);
    }

    public void slide(float x, float y) {
        shake.shake(1);
        slideInProgress = true;
        tmpVec2.set(position.x, position.y);
        Tween.to(tmpVec2, VectorAcessor.POSITION, 1).targetRelative(x, y).setCallback((int type, BaseTween<?> source) -> {
            slideInProgress = false;
        }).start(GameScreen.GAMESCREEN_SINGLETON.tweenManager);
    }
}
