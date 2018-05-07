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
        tilelist[0][0] = new Corner();
        tilelist[1][0] = new PathUp(d);
        tilelist[2][0] = new Corner();
        tilelist[0][1] = new PathSide(l);
        tilelist[1][1] = new Center();
        tilelist[2][1] = new PathSide(r);
        tilelist[0][2] = new Corner();
        tilelist[1][2] = new PathUp(u);
        tilelist[2][2] = new Corner();
        
    }
    
    //position -1 means not found
   public int getPositionX(Tile tile) {
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               if (tilelist[j][i] == tile) {
               return j;
               }
               
               
           }
       }
       return -1;
   }
   
   //position -1 means not found
   public int getPositionY(Tile tile) {
       for (int j = 0; j < 3; j++) {
           for (int i = 0; i < 3; i++) {
               if (tilelist[j][i] == tile) {
               return i;
               }
               
               
           }
       }
       return -1;
   }
   
}
