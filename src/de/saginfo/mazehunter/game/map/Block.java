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
public class Block {
    
    private Tile [][] tilelist;
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    
    public Block(boolean u, boolean r, boolean d, boolean l) {
        up = u;
        right = r;
        down = d;
        left = l;
        tilelist = new Tile[3][3];
        
        if (up) {
            tilelist[2][1].setOpen();
        } else {
            tilelist[2][1].setClose();
        } if (right) {
            tilelist[1][2].setOpen();
        } else {
            tilelist[1][2].setClose();
        } if (down){
            tilelist[1][0].setOpen();
        } else {
            tilelist[1][0].setClose();
        } if (left) {
            tilelist[0][1].setOpen();
        } else {
            tilelist[0][1].setClose();
        }
        
    }
}
