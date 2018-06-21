/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Player;
import java.util.ArrayList;

/**
 *
 * @author julian.mittermeier
 */
public class World {

    private Block[][] blocklist;
    public int worldwidth;
    public static int ecke;
    public static int center;
    public static int blockbreite;

    private static final Texture TEX = new Texture(Gdx.files.local("assets\\img\\map\\white.png"));
    
    public World(){
        this(TEX.getWidth(), TEX.getWidth());
    }
    
    public World(int e, int c) {
        ecke = e;
        center = c;
        blockbreite = c + 2 * e;
    }

    /**
     * World generiert die Map. Die Booleanwerte werden im Uhrzeigersinn,
     * beginnend oben eingetragen. Anschließend werden zuerst die Zeilen und
     * dann die Spalten generiert, beginnend beim Ursprung (0|0).
     *
     * @param b = größe der welt
     */
    public void makeMap(boolean... b) {
        if (b.length / 4 == 1 || b.length / 4 == 4 || b.length / 4 == 9 || b.length / 4 == 16 || b.length / 4 == 25 || b.length / 4 == 36 || b.length / 4 == 49 || b.length / 4 == 64 || b.length / 4 == 81 || b.length / 4 == 100) {
            worldwidth = (int) Math.sqrt(b.length / 4);
            blocklist = new Block[worldwidth][worldwidth];
            int h = 0;
            for (int j = 0; j < worldwidth; j++) {
                for (int i = 0; i < worldwidth; i++) {
                    blocklist[i][j] = new Block(b[h], b[h + 1], b[h + 2], b[h + 3], this.translateBlockToCoordinate(i), this.translateBlockToCoordinate(j));
                    h = h + 4;
                }
            }
        }
    }

//    
//    /**
//     * 
//     * generates a random map
//     *
//     * @param worldwith
//     */
//    public void makeMap(int worldwith) {
//        
//        blocklist = new Block[worldwidth][worldwidth];
//        boolean N;
//        boolean O;
//        boolean S;
//        boolean W;
//          
//        for (int x = 0; x < worldwith; x++) {
//            if (x == 0) {
//                blocklist[x][0]= new Block(false, getRandom(), getRandom(), false, this.translateBlockToCoordinate(x), this.translateBlockToCoordinate(0));
//            }
//            blocklist[x][0]= new Block(false, getRandom(), getRandom(), blocklist[x-1][0].tilelist[2][1].open, this.translateBlockToCoordinate(x), this.translateBlockToCoordinate(0));
//        }
//        
//        for (int y = 1; y < worldwidth; y++) {
//            for (int x = 0; x < worldwidth; x++) {
//                if (x == 0) {
//                    O = false;
//                    if (y == 0) {
//                        S = false;
//                        N = blocklist[x][y+1].tilelist[1][2].open;
//                        W = blocklist[x-1][y].tilelist[2][1].open;
//                    } else if (y == worldwith-1) {
//                        N = false;
//                        S = getRandom();
//                        W = blocklist[x-1][y].tilelist[2][1].open;
//                    }
//                } else if (x == worldwith-1) {
//                    W = false;
//                    if (y == 0) {
//                        S = false;
//                    } else if (y == worldwith-1) {
//                        N = false;
//                    }
//                }
//                
//                if (true) {
//                    N = blocklist[x][y-1].tilelist[1][2].open;
//                    O = getRandom();
//                    S = getRandom();
//                    W = blocklist[x-1][y].tilelist[2][1].open;
//                } 
//                blocklist[x][y] = new Block(N, O, S, W, this.translateBlockToCoordinate(x), this.translateBlockToCoordinate(y));
//            }
//        }
//    }
    
    private boolean getRandom() {
        return Math.random()<0.6f;
    }
    
    public void makeTestMap(int größe) {
        worldwidth = größe;
        blocklist = new Block[worldwidth][worldwidth];
        for (int j = 0; j < worldwidth; j++) {
            for (int i = 0; i < worldwidth; i++) {
                blocklist[j][i] = new Block(true, true, true, true, this.translateBlockToCoordinate(j), this.translateBlockToCoordinate(i));
            }

        }
    }
    
    public void movePlayers(Vector2 position, Vector2 velocity, char xory) {
        
        
        GameScreen.GAMESCREEN_SINGLETON.game.players.stream().forEach((player) -> {
            if (xory == 'x' && player.position.y - position.y < blockbreite) {
                player.position.add(velocity);
            } else if (xory == 'y' && player.position.x - position.x < blockbreite) {
                player.position.add(velocity);
            }
        });
    }

    //position -1 means not found
    public int getPositionBlockXinBlock(Block block) {
        for (int j = 0; j < worldwidth; j++) {
            for (int i = 0; i < worldwidth; i++) {
                if (blocklist[j][i] == block) {
                    return j;
                }

            }
        }
        return -1;
    }

    //position -1 means not found
    public int getPositionBlockXinCoordinate(Block block) {
        int k;
        k = this.getPositionBlockXinBlock(block);
        if (k >= 0) {
            return k * blockbreite;
        } else {
            System.out.println("ERROR");
            return -1;
        }
    }

    //position -1 means not found
    public int getPositionBlockYinBlock(Block block) {
        for (int j = 0; j < worldwidth; j++) {
            for (int i = 0; i < worldwidth; i++) {
                if (blocklist[j][i] == block) {
                    return i;
                }

            }
        }
        return -1;
    }

    //position -1 means not found
    public int getPositionBlockYinCoordinate(Block block) {
        int k;
        k = this.getPositionBlockYinBlock(block);
        if (k >= 0) {
            return k * blockbreite;
        } else {
            System.out.println("ERROR");
            return -1;
        }
    }

    public boolean isTileOpen(int blockX, int blockY, int tileX, int tileY) {
        return blocklist[blockX][blockY].tilelist[tileX][tileY].getOpen();
    }

    public void checkTileOpenStatus() {
        for (int x = 0; x < this.worldwidth; x++) {
            for (int y = 0; y < this.worldwidth; y++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                    }
                }
            }
        }
    }

    public int translateTileToCoordinate(int k) {
        switch (k) {
            case 0:
                return 0;
            case 1:
                return World.ecke;
            case 2:
                return World.ecke + World.center;
            default:
                return -1;
        }
    }

    public int translateBlockToCoordinate(int k) {
        return (k * blockbreite);
    }
    
    

}
