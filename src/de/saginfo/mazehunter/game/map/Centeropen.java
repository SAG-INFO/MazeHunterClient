/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.map.pickups.AbilityPickup;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author paul.kuschfeldt
 */
public class Centeropen extends Tile {

    public static int width;
    public static int height;
    
    public AbilityPickup pickup;
    
    private static final Texture TEX = new Texture(Gdx.files.local("assets\\img\\map\\center_open.png"));

    public Centeropen(Block block, int x, int y) {
        super(block, x, y, true);
        
        visual = new SpriteVisual(new Sprite(TEX));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        visual.setZIndex(20);
        setVisible(false);
    }

    @Override
    public void animatePosition() {
        super.animatePosition();
        if(pickup != null){
            float offset = TEX.getWidth()/2-pickup.visual.getWidth()/2;
            Tween.to(pickup.visual, VisualAccessor.POSITION, 1).target(getPixelX()+offset, getPixelY()+offset).start(GAMESCREEN_SINGLETON.tweenManager);
        }
    }

    @Override
    public void setPosition() {
        super.setPosition();
        if(pickup != null){
            float offset = TEX.getWidth()/2-pickup.visual.getWidth()/2;
            pickup.visual.setPosition(getPixelX()+offset, getPixelY()+offset);
        }
    }
}
