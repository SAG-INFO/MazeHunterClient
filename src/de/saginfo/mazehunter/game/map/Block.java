/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.utils.Timer;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author heftigster.guy.na
 */
public class Block {

    public Tile[][] tilelist;
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    private int indexX;
    private int indexY;

    public Block(boolean u, boolean r, boolean d, boolean l, int x, int y) {
        up = u;
        right = r;
        down = d;
        left = l;

        indexX = x;
        indexY = y;

        tilelist = new Tile[3][3];
        tilelist[0][0] = new Corner(this, 0, 0);
        tilelist[1][0] = new PathUp(this, 1, 0, d);
        tilelist[2][0] = new Corner(this, 2, 0);
        tilelist[0][1] = new PathSide(this, 0, 1, l);
        tilelist[2][1] = new PathSide(this, 2, 1, r);
        tilelist[0][2] = new Corner(this, 0, 2);
        tilelist[1][2] = new PathUp(this, 1, 2, u);
        tilelist[2][2] = new Corner(this, 2, 2);

        if (u == false && r == false && d == false && l == false) {
            tilelist[1][1] = new Centerclosed(this, 1, 1);
        } else {
            tilelist[1][1] = new Centeropen(this, 1, 1);
        }

        Corner.width = Map.ecke;
        Corner.height = Map.ecke;
        Centeropen.width = Map.center;
        Centeropen.height = Map.center;
        PathUp.height = Map.ecke;
        PathUp.width = Map.center;
        PathSide.height = Map.center;
        PathSide.width = Map.ecke;

    }

    public int getPixelX() {
        return indexX * Map.blockbreite;
    }
    public int getPixelY() {
        return indexY * Map.blockbreite;
    }

    public int getX() {
        return indexX;
    }
    public int getY() {
        return indexY;
    }
    
    public void animatePosition(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tilelist[i][j].animatePosition();
            }
        }
    }
    public void setPosition(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tilelist[i][j].setPosition();
            }
        }
    }

    public Block clone() {
        Block b = new Block(this.up, this.right, this.down, this.left, this.indexX, this.indexY);
        return b;
    }

    public void dispose() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tilelist[i][j].dispose();
            }
        }
    }
    public void disposeAfterDelay(){
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
               dispose();
            }
        }, 1f);
    }
}
