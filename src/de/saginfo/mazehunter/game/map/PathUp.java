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

    private static final Texture TEXleaves = new Texture(Gdx.files.local("assets\\img\\map\\leaves.png"));
    private static final Texture TEXvisible = new Texture(Gdx.files.local("assets\\img\\map\\path.png"));
    private static final Texture TEXnotvisible = new Texture(Gdx.files.local("assets\\img\\map\\fog_path.png"));

    public PathUp(Block block, int x, int y, boolean open) {
        super(block, x, y, open);

        if (open) {
            visualVisible = new SpriteVisual(new Sprite(TEXvisible));
            visualNotVisible = new SpriteVisual(new Sprite(TEXnotvisible));
            visualNotVisible.setPosition(getPixelX(), getPixelY());
            visualNotVisible.setZIndex(60);
            visualVisible.setZIndex(10);
            visualVisible.setPosition(getPixelX(), getPixelY());
        } else {
            visualVisible = new SpriteVisual(new Sprite(TEXleaves));
            visualNotVisible = new SpriteVisual(new Sprite(TEXleaves));
            visualVisible.setZIndex(10);
            visualVisible.setPosition(getPixelX(), getPixelY());
            visualNotVisible.setPosition(getPixelX(), getPixelY());
            visualNotVisible.setZIndex((int) (75 + Math.random() * 10));
        }

        visualVisible.setZIndex(10);
        visualVisible.setPosition(getPixelX(), getPixelY());
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);

        visualNotVisible.setPosition(getPixelX(), getPixelY());
        visualNotVisible.setZIndex(60);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
        super.setVisible(false);
    }
}
