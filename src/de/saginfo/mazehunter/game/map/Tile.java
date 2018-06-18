/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author julian.mittermeier
 */
public abstract class Tile {

    public boolean open;
    public boolean visible = false;
    public int IndexX;
    public int IndexY;
    int WorldIndexX;
    int WorldIndexY;
    public Block parent;
    public SpriteVisual visual;

    public Tile() {

    }

    public Tile(Block block, int x, int y) {
        parent = block;
        IndexX = x;
        WorldIndexX = parent.IndexX * 3 + x;
        IndexY = y;
        WorldIndexY = parent.IndexY * 3 + y;
    }

    public Tile(boolean o) {
        open = o;
    }

    public void update() {
        if (!visible) {
            visual.setColor(Color.RED);
        } else {
            visual.setColor(Color.BLUE);
        }
    }

    public int getX() {
        switch (IndexX) {
            case 0:
                return parent.getX();
            case 1:
                return parent.getX() + World.ecke;
            case 2:
                return parent.getX() + World.ecke + World.center;
            default:
                throw new RuntimeException("getXvonTile");
        }
    }

    public int getY() {
        switch (IndexY) {
            case 0:
                return parent.getY();
            case 1:
                return parent.getY() + World.ecke;
            case 2:
                return parent.getY() + World.ecke + World.center;
            default:
                throw new RuntimeException("getYvonTile");
        }
    }
    
    public abstract float getVisualX();
    
    public abstract float getVisualY();

    public abstract void draw();
}
