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

    private static final Texture TEXleaves = new Texture(Gdx.files.local("assets\\img\\map\\path_side_leaves.png"));
    private static final Texture TEXvisible = new Texture(Gdx.files.local("assets\\img\\map\\path_side.png"));
    private static final Texture TEXnotvisible = new Texture(Gdx.files.local("assets\\img\\map\\path_side_fog.png"));

    public PathSide(Block block, int x, int y, boolean open) {
        super(block, x, y, open);

        if (open) {
            visualVisible = new SpriteVisual(new Sprite(TEXvisible));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);
            visualVisible.setZIndex(10);
            visualVisible.setPosition(getPixelX(), getPixelY());

            visualNotVisible = new SpriteVisual(new Sprite(TEXnotvisible));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
            visualNotVisible.setZIndex((70));
            visualNotVisible.setPosition(getPixelX(), getPixelY());
        } else {
            visualVisible = new SpriteVisual(new Sprite(TEXleaves));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);
            visualVisible.setZIndex((int) (75 + Math.random() * 10));
            visualVisible.setPosition(getPixelX(), getPixelY());
            
            visualNotVisible = new SpriteVisual(new Sprite(TEXleaves));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
            visualNotVisible.setZIndex((int) (75 + Math.random() * 10));
            visualNotVisible.setPosition(getPixelX(), getPixelY());
        }
        super.setVisible(false);
    }
}
