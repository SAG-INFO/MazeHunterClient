/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Admin
 */
public class Centerclosed extends Tile {

    private static final Texture TEX = new Texture(Gdx.files.local("assets\\img\\map\\center_closed.png"));

    public static int width;
    public static int height;

    public Centerclosed(Block block, int x, int y) {
        super(block, x, y);
        open = false;
    }

    @Override
    public void draw() {
        visual = new SpriteVisual(new Sprite(TEX));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        visual.setPosition(getVisualX(), getVisualY());
        visual.setZIndex((int) (75+Math.random()*10));
    }
    
    @Override
    public float getVisualX() {
        return getX()+((Map.center-visual.getWidth())/2);
    }

    @Override
    public float getVisualY() {
        return getY()+((Map.center-visual.getHeight())/2);
    }
}