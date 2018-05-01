/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author sreis
 */
public class PlayerVisual extends SpriteVisual{

    private static final Texture TEX = new Texture(Gdx.files.local("assets\\img\\player\\dot.png"));
    
    public PlayerVisual() {
        super(new Sprite(TEX));
        super.setScale(0.25f);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(this);
    }
    
    private float alpha = 0.2f;
    public void lerpPosition(float x, float y){
        final float invAlpha = 1.0f - alpha;
        super.setX((x * invAlpha) + (x * alpha));
        super.setY((y * invAlpha) + (y * alpha));
    }
}
