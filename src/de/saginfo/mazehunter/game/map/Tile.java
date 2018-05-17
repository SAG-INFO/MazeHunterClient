/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

/**
 *
 * @author julian.mittermeier
 */

public class Tile {
    
    boolean open;
    
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
    
//    
//    public int getPositionX() {
//        
//    }
//    
}
