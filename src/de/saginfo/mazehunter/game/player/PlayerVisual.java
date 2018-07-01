/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.AnimationVisual;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.Visual;

/**
 *
 * @author sreis
 */
public class PlayerVisual{

    //TODO the other 3 directions ... 
    private final SpriteVisual idle_down;
    private final AnimationVisual run_down;
    
    private Visual currentVisual;
    
    public PlayerVisual() {
        idle_down = new SpriteVisual("assets\\img\\player\\dt.png");
        run_down = new AnimationVisual(24, "assets\\img\\player\\walk_front", Animation.PlayMode.LOOP_PINGPONG);
        setSprite(idle_down);
    }
    
    private void setSprite(Visual visual) {
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(currentVisual);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        currentVisual = visual;
        currentVisual.setZIndex(50);
    }
    
    private float alpha = 0.2f;
    public void lerpPosition(float x, float y){
        final float invAlpha = 1.0f - alpha;
        currentVisual.setX((x * invAlpha) + (x * alpha));
        currentVisual.setY((y * invAlpha) + (y * alpha));
    }
}
