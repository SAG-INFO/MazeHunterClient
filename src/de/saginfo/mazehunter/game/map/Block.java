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
public class Block {

    public Tile[][] tilelist;
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    int BlockPositionX;
    int BlockPositionY;

    public Block(boolean u, boolean r, boolean d, boolean l, int blockx, int blocky) {
        System.out.println(GAMESCREEN_SINGLETON.game);
        System.out.println(GAMESCREEN_SINGLETON.game.world);
        
        BlockPositionX = blockx;
        BlockPositionY = blocky;

        
        System.out.println(GAMESCREEN_SINGLETON.game.world.translateTileToCoordinate(2) + "LOLLOL");
        up = u;
        right = r;
        down = d;
        left = l;
        tilelist = new Tile[3][3];
        tilelist[0][0] = new Corner(BlockPositionX, BlockPositionY, 0, 0);
        tilelist[1][0] = new PathUp(d, BlockPositionX, BlockPositionY, 1, 0);
        tilelist[2][0] = new Corner(BlockPositionX, BlockPositionY, 2, 0);
        tilelist[0][1] = new PathSide(l, BlockPositionX, BlockPositionY, 0, 1);
        tilelist[1][1] = new Center(BlockPositionX, BlockPositionY, 1, 1);
        tilelist[2][1] = new PathSide(r, BlockPositionX, BlockPositionY, 2, 1);
        tilelist[0][2] = new Corner(BlockPositionX, BlockPositionY, 0, 2);
        tilelist[1][2] = new PathUp(u, BlockPositionX, BlockPositionY, 1, 2);
        tilelist[2][2] = new Corner(BlockPositionX, BlockPositionY, 2, 2);

        Corner.width = World.ecke;
        Corner.height = World.ecke;
        Center.width = World.center;
        Center.height = World.center;
        PathUp.height = World.ecke;
        PathUp.width = World.center;
        PathSide.height = World.center;
        PathSide.width = World.ecke;

    }

    //position -1 means not found
    public int getPositionTileXinTile(Tile tile) {
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
    public int getPositionTileYintTile(Tile tile) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (tilelist[j][i] == tile) {
                    return i;
                }

            }
        }
        return -1;
    }

    //position -1 means not found
    public int getPostitionTileXinCoordinate(Tile tile) {
        int k = -1;
        k = this.getPositionTileXinTile(tile);
        if (k == 0) {
            return 0;
        } else if (k == 1) {
            return World.ecke;
        } else if (k == 2) {
            return World.ecke + World.center;
        } else {
            return -1;
        }
    }

    //position -1 means not found
    public int getPostitionTileYinCoordinate(Tile tile) {
        int k = -1;
        k = this.getPositionTileYintTile(tile);
        if (k == 0) {
            return 0;
        } else if (k == 1) {
            return World.ecke;
        } else if (k == 2) {
            return World.ecke + World.center;
        } else {
            return -1;
        }
    }

}
