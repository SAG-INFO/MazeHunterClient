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
public class PathUp extends Tile {

    public static int width;
    public static int height;

    private static final Texture TEXblack = new Texture(Gdx.files.local("assets\\img\\map\\pathup_closed.png"));
    private static final Texture TEXwhite = new Texture(Gdx.files.local("assets\\img\\map\\pathup_open.png"));

    public PathUp(Block block, int x, int y, boolean b) {
        super(block, x, y);
        open = b;
    }

    @Override
    public void draw() {
        if (open) {
            visual = new SpriteVisual(new Sprite(TEXwhite));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        } else {
            visual = new SpriteVisual(new Sprite(TEXblack));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        }
        visual.setPosition(getVisualX(), getVisualY());
    }

    @Override
    public float getVisualX() {
        return getX() + ((Map.center - visual.getWidth()) / 2);
    }

    @Override
    public float getVisualY() {
        return getY() + ((Map.ecke - visual.getHeight()) / 2);
    }
}
