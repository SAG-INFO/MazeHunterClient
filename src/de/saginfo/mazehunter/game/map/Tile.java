/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.Color;
import de.saginfo.mazehunter.game.Game;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.grafik.RenderSystem;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author julian.mittermeier
 */
public abstract class Tile {

    public final boolean open;
    private boolean visible;
    public Block parent;

    public SpriteVisual visualVisible;
    public SpriteVisual visualNotVisible;
    private int tileIndexX;
    private int tileIndexY;
    private int worldIndexX;
    private int worldIndexY;

    public Tile(Block block, int x, int y, boolean open) {
        this.parent = block;
        this.open = open;
        this.tileIndexX = x;
        this.worldIndexX = parent.getX() * 3 + x;
        this.tileIndexY = y;
        this.worldIndexY = parent.getY() * 3 + y;
    }

    public void setPosition() {
        this.worldIndexX = parent.getX() * 3 + tileIndexX;
        this.worldIndexY = parent.getY() * 3 + tileIndexY;
        visualVisible.setPosition(getPixelX(), getPixelY());
        visualNotVisible.setPosition(getPixelX(), getPixelY());
    }

    public void animatePosition() {
        this.worldIndexX = parent.getX() * 3 + tileIndexX;
        this.worldIndexY = parent.getY() * 3 + tileIndexY;
        Tween.to(this.visualVisible, VisualAccessor.POSITION, 1).target(getPixelX(), getPixelY()).start(GAMESCREEN_SINGLETON.tweenManager);
        Tween.to(this.visualNotVisible, VisualAccessor.POSITION, 1).target(getPixelX(), getPixelY()).start(GAMESCREEN_SINGLETON.tweenManager);
    }

    public int getBlockIndexX() {
        return tileIndexX;
    }

    public int getBlockIndexY() {
        return tileIndexY;
    }

    public int getWorldIndexX() {
        return worldIndexX;
    }

    public int getWorldIndexY() {
        return worldIndexY;
    }

    public int getPixelX() {
        switch (tileIndexX) {
            case 0:
                return parent.getPixelX() + (int) ((Map.ecke - visualVisible.getWidth()) / 2);
            case 1:
                return parent.getPixelX() + Map.ecke + (int) ((Map.center - visualVisible.getWidth()) / 2);
            case 2:
                return parent.getPixelX() + Map.ecke + Map.center + (int) ((Map.ecke - visualVisible.getWidth()) / 2);
            default:
                throw new RuntimeException("getXvonTile");
        }
    }

    public int getPixelY() {
        switch (tileIndexY) {
            case 0:
                return parent.getPixelY() + (int) ((Map.ecke - visualVisible.getHeight()) / 2);
            case 1:
                return parent.getPixelY() + Map.ecke + (int) ((Map.center - visualVisible.getHeight()) / 2);
            case 2:
                return parent.getPixelY() + Map.ecke + Map.center + (int) ((Map.center - visualVisible.getHeight()) / 2);
            default:
                throw new RuntimeException("getYvonTile");
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        if (visible) {
            visualVisible.setAlpha(1);
            visualNotVisible.setAlpha(0);
        } else {
            visualNotVisible.setAlpha(1);
            visualVisible.setAlpha(0);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    void dispose() {
        GAMESCREEN_SINGLETON.renderSystem.removeSprite(visualVisible);
        GAMESCREEN_SINGLETON.renderSystem.removeSprite(visualNotVisible);
    }
}
