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

    private SpriteVisual visual;

    private static final Texture TEXblack = new Texture(Gdx.files.local("assets\\img\\map\\black.png"));
    private static final Texture TEXwhite = new Texture(Gdx.files.local("assets\\img\\map\\white.png"));

    public PathSide(boolean b) {
        open = b;

        if (open) {
            visual = new SpriteVisual(new Sprite(TEXwhite));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        } else {
            visual = new SpriteVisual(new Sprite(TEXblack));
            GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        }
    }
}
