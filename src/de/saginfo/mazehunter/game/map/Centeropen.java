/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.map.pickups.AbilityPickup;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author paul.kuschfeldt
 */
public class Centeropen extends Tile {

    public static int width;
    public static int height;

    public AbilityPickup pickup;

    private static final Texture TEXvisible = new Texture(Gdx.files.local("assets\\img\\map\\path.png"));
    private static final Texture TEXnotvisible = new Texture(Gdx.files.local("assets\\img\\map\\fog_path.png"));

    public Centeropen(Block block, int x, int y) {
        super(block, x, y, true);

        visualVisible = new SpriteVisual(new Sprite(TEXvisible));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualVisible);
        visualVisible.setZIndex(10);
        visualVisible.setPosition(getPixelX(), getPixelY());

        visualNotVisible = new SpriteVisual(new Sprite(TEXnotvisible));
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visualNotVisible);
        visualNotVisible.setZIndex(10);
        visualNotVisible.setPosition(getPixelX(), getPixelY());

        setVisible(false);
    }

    @Override
    public void animatePosition() {
        super.animatePosition();
        if (pickup != null) {
            float offset = TEXvisible.getWidth() / 2 - pickup.visual.getWidth() / 2;
            Tween.to(pickup.visual, VisualAccessor.POSITION, 1).target(getPixelX() + offset, getPixelY() + offset).start(GAMESCREEN_SINGLETON.tweenManager);
        }
    }

    @Override
    public void setPosition() {
        super.setPosition();
        if (pickup != null) {
            float offset = TEXvisible.getWidth() / 2 - pickup.visual.getWidth() / 2;
            pickup.visual.setPosition(getPixelX() + offset, getPixelY() + offset);
        }
    }
}
