/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author julian.mittermeier
 */
public abstract class Tile {

    public boolean open;
    public boolean visible = false;
    public int IndexX;
    public int IndexY;
    int WorldIndexX;
    int WorldIndexY;
    public Block parent;
    
    public SpriteVisual visual;

    public Tile() {

    }

    public Tile(Block block, int x, int y) {
        parent = block;
        IndexX = x;
        WorldIndexX = parent.indexX * 3 + x;
        IndexY = y;
        WorldIndexY = parent.indexY * 3 + y;
    }

    public Tile(boolean o) {
        open = o;
    }

    public void update() {
        if (!visible) {
            visual.setColor(new Color(1, 0.5f, 0.5f, 1f));
        } else {
            visual.setColor(new Color(0.5f, 0.5f, 1, 1f));
        }
        //TODO: Effizienz steigern
        WorldIndexX = parent.indexX * 3 + IndexX;
        WorldIndexY = parent.indexY * 3 + IndexY;
    }
    
    public int getX() {
        switch (IndexX) {
            case 0:
                return parent.getX();
            case 1:
                return parent.getX() + Map.ecke;
            case 2:
                return parent.getX() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getXvonTile");
        }
    }

    public int getY() {
        switch (IndexY) {
            case 0:
                return parent.getY();
            case 1:
                return parent.getY() + Map.ecke;
            case 2:
                return parent.getY() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getYvonTile");
        }
    }
    
    public void animateIn(float x, float y){
    }
    
    public abstract float getVisualX();
    
    public abstract float getVisualY();

    public abstract void draw();
    
    public void animateGrafXPosition() {
        Tween t = Tween.to(this.visual, VisualAccessor.POSITION, 1).target(getVisualX(), getVisualY()).start(GAMESCREEN_SINGLETON.tweenManager);
    }
    
    public void updateGrafXPosition(){
        visual.setPosition(getVisualX(), getVisualY());
    }
    
    public void clean(){
        GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
    }

    void fadeOut() {
        Tween t = Tween.to(this.visual, VisualAccessor.POSITION, 1).target(getVisualX(), getVisualY()).start(GAMESCREEN_SINGLETON.tweenManager).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
            }
        });
    }
}
