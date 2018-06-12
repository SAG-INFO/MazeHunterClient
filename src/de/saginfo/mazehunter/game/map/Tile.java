/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;


import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;

/**
 *
 * @author julian.mittermeier
 */
public class Tile {

    public boolean open;
    public boolean seen = false;
    public int blockPositionX;
    public int blockPositionY;
    public int tilePositionX;
    public int tilePositionY;

    //Graphic image;
    public Tile() {

    }

    public Tile(boolean o) {
        open = o;
    }

    public void setOpen() {
        open = true;
    }

    public void setClose() {
        open = false;
    }

    public boolean getOpen() {
        return open;
    }
    
    public int getIndexX() {
        return GAMESCREEN_SINGLETON.game.world.translateCoordinateToTile(tilePositionX);
    }
    
    public int getIndexY() {
        return GAMESCREEN_SINGLETON.game.world.translateCoordinateToTile(tilePositionY);
    }

}
