/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.Color;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.player.Player;
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

    public SpriteVisual visual;

    private int blockIndexX;
    private int blockIndexY;
    private int worldIndexX;
    private int worldIndexY;

    public Tile(Block block, int x, int y, boolean open) {
        this.parent = block;
        this.open = open;
        this.blockIndexX = x;
        this.worldIndexX = parent.getX() * 3 + x;
        this.blockIndexY = y;
        this.worldIndexY = parent.getY() * 3 + y;
    }

    public void setPosition() {
        this.worldIndexX = parent.getX() * 3 + blockIndexX;
        this.worldIndexY = parent.getY() * 3 + blockIndexY;
        visual.setPosition(getPixelX(), getPixelY());
    }

    public void animatePosition() {
        this.worldIndexX = parent.getX() * 3 + blockIndexX;
        this.worldIndexY = parent.getY() * 3 + blockIndexY;
        Tween.to(this.visual, VisualAccessor.POSITION, 1).target(getPixelX(), getPixelY()).start(GAMESCREEN_SINGLETON.tweenManager);
    }

    public int getBlockIndexX() {
        return blockIndexX;
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
        switch (blockIndexX) {
            case 0:
                return parent.getPixelX();
            case 1:
                return parent.getPixelX() + Map.ecke;
            case 2:
                return parent.getPixelX() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getXvonTile");
        }
    }

    public int getPixelY() {
        switch (blockIndexY) {
            case 0:
                return parent.getPixelY();
            case 1:
                return parent.getPixelY() + Map.ecke;
            case 2:
                return parent.getPixelY() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getYvonTile");
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        if (!visible) {
            visual.setColor(new Color(1, 0.5f, 0.5f, 1f));
        } else {
            visual.setColor(new Color(0.5f, 0.5f, 1, 1f));
        }
    }

    public boolean isVisible() {
        return visible;
    }

    void dispose() {
        GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
    }
}
