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
public class PathSide extends Tile {

    public static int width;
    public static int height;

    private static final Texture TEXblack = new Texture(Gdx.files.local("assets\\img\\map\\pathside_closed.png"));
    private static final Texture TEXwhite = new Texture(Gdx.files.local("assets\\img\\map\\pathside_open.png"));

    public PathSide(Block block, int x, int y, boolean b) {
        super(block, x, y);
        open = b;
    }

    @Override
    public void draw() {
        if (open) {
            visual = new SpriteVisual(new Sprite(TEXwhite));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
            visual.setZIndex(20);
        } else {
            visual = new SpriteVisual(new Sprite(TEXblack));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
            visual.setZIndex((int) (75 + Math.random() * 10));
        }
        visual.setPosition(getVisualX(), getVisualY());
    }

    @Override
    public float getVisualX() {
        return getX() + ((Map.ecke - visual.getWidth()) / 2);
    }

    @Override
    public float getVisualY() {
        return getY() + ((Map.center - visual.getHeight()) / 2);
    }
}
