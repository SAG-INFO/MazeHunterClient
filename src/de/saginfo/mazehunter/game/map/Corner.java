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

    private static final Texture TEX = new Texture(Gdx.files.local("assets\\img\\map\\corner.png"));

    public Corner(Block block, int x, int y) {
        super(block, x, y, false);
        
        visual = new SpriteVisual(new Sprite(TEX));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        visual.setZIndex((int) (75 + Math.random() * 10));
        setVisible(false);
    }
}
