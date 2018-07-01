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
import de.saginfo.mazehunter.grafik.VectorAcessor;
import de.saginfo.mazehunter.grafik.VisualAccessor;
import java.util.stream.Stream;

/**
 *
 * @author julian.mittermeier
 */
public abstract class Tile {

    public boolean open;
    private boolean visible;
    public Block parent;

    public SpriteVisual visualVisible;
    public SpriteVisual visualNotVisible;

    private int tileIndexX;
    private int blockIndexY;
    private int worldIndexX;
    private int worldIndexY;

    public Tile(Block block, int x, int y, boolean open) {
        this.parent = block;
        this.open = open;
        this.tileIndexX = x;
        this.worldIndexX = parent.getX() * 3 + x;
        this.blockIndexY = y;
        this.worldIndexY = parent.getY() * 3 + y;
    }

    public void setPosition() {
        this.worldIndexX = parent.getX() * 3 + tileIndexX;
        this.worldIndexY = parent.getY() * 3 + blockIndexY;
        visualVisible.setPosition(getPixelX(), getPixelY());
    }

    public void animatePosition() {
        this.worldIndexX = parent.getX() * 3 + tileIndexX;
        this.worldIndexY = parent.getY() * 3 + blockIndexY;
        Tween.to(this.visualVisible, VisualAccessor.POSITION, 1).target(getPixelX(), getPixelY()).start(GAMESCREEN_SINGLETON.tweenManager);
    }

    public int getBlockIndexX() {
        return tileIndexX;
    }

    public int getBlockIndexY() {
        return blockIndexY;
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
        switch (blockIndexY) {
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
            visualNotVisible.setPosition(getPixelX(), getPixelY());
            visualVisible.setPosition(getPixelX(), getPixelY());
        } else {
            visualNotVisible.setAlpha(1);
            visualVisible.setAlpha(0);
            visualNotVisible.setPosition(getPixelX(), getPixelY());
            visualVisible.setPosition(getPixelX(), getPixelY());
        }
    }

    public boolean isVisible() {
        return visible;
    }

    void dispose() {
        GAMESCREEN_SINGLETON.renderSystem.removeSprite(visualVisible);
    }
}
