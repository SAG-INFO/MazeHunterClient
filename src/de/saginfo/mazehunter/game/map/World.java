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
public class World {
    
   private Block[][] blocklist;
   private int breite;
   
   public World(int größe) {
       breite = größe;
       blocklist = new Block[breite][breite];
       for (int i = 0; i < breite - 1; i++) {
           for (int j = 0; j < breite - 1; j++) {
                blocklist[i][j] = new Block(true, true, true, true);
           }

       }
       
   }
   
//   public int getPosition(Block) {
//       
//   }
}
