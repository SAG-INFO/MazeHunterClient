/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.AnimationVisual;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.Visual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author sreis
 */
public class PlayerVisual {

    //TODO the other 3 directions ... 
    public final SpriteVisual[] idles;
    public final AnimationVisual[] runs;

    public Visual currentVisual;

    public int fps = 10;

    private final float offsetX = -25;
    private final float offsetY = -20;

    public int direction = 1;
    public boolean moving;

    private boolean animationInProgress;
    
    public PlayerVisual(boolean local) {
        idles = new SpriteVisual[4];
        runs = new AnimationVisual[4];
        idles[0] = new SpriteVisual("assets\\img\\player\\walk_back_01.png");
        idles[1] = new SpriteVisual("assets\\img\\player\\walk_right_01.png");
        idles[2] = new SpriteVisual("assets\\img\\player\\walk_front_01.png");
        idles[3] = new SpriteVisual("assets\\img\\player\\walk_left_01.png");
        runs[0] = new AnimationVisual(fps, "assets\\img\\player\\walk_back", Animation.PlayMode.LOOP_PINGPONG);
        runs[1] = new AnimationVisual(fps, "assets\\img\\player\\walk_right", Animation.PlayMode.LOOP_PINGPONG);
        runs[2] = new AnimationVisual(fps, "assets\\img\\player\\walk_front", Animation.PlayMode.LOOP_PINGPONG);
        runs[3] = new AnimationVisual(fps, "assets\\img\\player\\walk_left", Animation.PlayMode.LOOP_PINGPONG);

        for (SpriteVisual idle : idles) {
            idle.setZIndex(80);
        }
        for (AnimationVisual run : runs) {
            run.setZIndex(80);
        }

        currentVisual = idles[0];
        setSprite(true);
    }

    public void updateMovment(int direction, boolean moving) {
        this.moving = moving;
        if (moving) {
            this.direction = direction;
        }
        if(!animationInProgress)
            setSprite(moving);
    }

    private void setSprite(boolean moving) {
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(currentVisual);
        if (moving) {
            runs[direction].setPosition(currentVisual.getX(), currentVisual.getY());
            runs[direction].setFps(fps);
            currentVisual = runs[direction];
        } else {
            idles[direction].setPosition(currentVisual.getX(), currentVisual.getY());
            currentVisual = idles[direction];
        }
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(currentVisual);
    }

    public void playSlideAnimation(float diffX, float diffY) {
        setSprite(false);
        animationInProgress = true;
        Tween.to(currentVisual, VisualAccessor.POSITION, 1).targetRelative(diffX, diffY).setCallback((int type, BaseTween<?> source) -> {
            animationInProgress = false;
            if(moving)
                setSprite(true);
        }).start(GameScreen.GAMESCREEN_SINGLETON.tweenManager);
    }

    private float alpha = 0.2f;

    public void lerpPosition(float x, float y) {
        if(animationInProgress)
            return;
        final float invAlpha = 1.0f - alpha;
        x += offsetX;
        y += offsetY;
        currentVisual.setX((x * invAlpha) + (x * alpha));
        currentVisual.setY((y * invAlpha) + (y * alpha));
    }
}
