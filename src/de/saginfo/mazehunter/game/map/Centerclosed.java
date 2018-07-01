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

    private static final Texture TEXleaves = new Texture(Gdx.files.local("assets\\img\\map\\path_leaves.png"));

    public static int width;
    public static int height;

    public Centerclosed(Block block, int x, int y) {
        super(block, x, y, false);

        visualVisible = new SpriteVisual(new Sprite(TEXleaves));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);
        visualVisible.setZIndex((int) (75 + Math.random() * 10));
        visualVisible.setPosition(getPixelX(), getPixelY());
        
        visualNotVisible = new SpriteVisual(new Sprite(TEXleaves));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
        visualNotVisible.setPosition(getPixelX(), getPixelY());
        visualNotVisible.setZIndex((int) (75 + Math.random() * 10));
        
        super.setVisible(false);
    }
}
