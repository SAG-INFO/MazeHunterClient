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
 * @author paul.kuschfeldt
 */
public class Corner extends Tile {

    public static int width;
    public static int height;

    private static final Texture TEXleaves = new Texture(Gdx.files.local("assets\\img\\map\\leaves.png"));

    public Corner(Block block, int x, int y) {
        super(block, x, y, false);

        visualVisible = new SpriteVisual(new Sprite(TEXleaves));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);
        visualVisible.setZIndex((int) (75 + Math.random() * 10));
        visualVisible.setPosition(getPixelX(), getPixelY());
        
        visualNotVisible = new SpriteVisual(new Sprite(TEXleaves));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
        visualNotVisible.setZIndex((int) (75 + Math.random() * 10));
        visualNotVisible.setPosition(getPixelX(), getPixelY());
        
        setVisible(false);
    }
}
