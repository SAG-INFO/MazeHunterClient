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
   
   public World() {
       
   }
   

   //position -1 means not found
   public int getPositionX(Block block) {
       for (int i = 0; i < breite; i++) {
           for (int j = 0; j < breite; j++) {
               if (blocklist[j][i] == block) {
               return j;
               }
               
               
           }
       }
       return -1;
   }
    
    //position -1 means not found
   public int getPositionY(Block block) {
       for (int j = 0; j < breite; j++) {
           for (int i = 0; i < breite; i++) {
               if (blocklist[j][i] == block) {
               return i;
               }
               
               
           }
       }
       return -1;
   }


}
