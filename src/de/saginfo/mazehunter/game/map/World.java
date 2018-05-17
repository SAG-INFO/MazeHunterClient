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
    public int breite;

    public World(int größe) {
        breite = größe;
        blocklist = new Block[breite][breite];
        for (int i = 0; i < breite - 1; i++) {
            for (int j = 0; j < breite - 1; j++) {
                blocklist[i][j] = new Block(true, true, true, true);
            }

        }

    }

    /**
     * World generiert die Map. Die Booleanwerte werden im Uhrzeigersinn,
     * beginnend oben eingetragen. Anschließend werden zuerst die Zeilen und
     * dann die Spalten generiert, beginnend neim Ursprung (0|0).
     *
     * @param b = größe der welt
     * @ param e = größe der ecke
     * @param c = größe des centers
     */
    public World(int e, int c, boolean... b) {
        if (b.length / 4 == 1 || b.length / 4 == 4 || b.length / 4 == 9 || b.length / 4 == 16 || b.length / 4 == 25 || b.length / 4 == 36 || b.length / 4 == 49 || b.length / 4 == 64 || b.length / 4 == 81 || b.length / 4 == 100) {
            breite = (int) Math.sqrt(b.length / 4);
            blocklist = new Block[breite][breite];
            int h = 0;
            for (int j = 0; j < breite; j++) {
                for (int i = 0; i < breite; i++) {
                    blocklist[i][j] = new Block(b[h], b[h + 1], b[h + 2], b[h + 3]);
                    h = h + 4;

                }
            }
            Corner.width = e;
            Corner.height = e;
            Center.width = c;
            Center.height = c;
            PathUp.height = e;
            PathUp.width = c;
            PathSide.height = c;
            PathSide.width = e;

        } else {
            return;
        }
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

    public boolean isTileOpen(int blockX, int blockY, int tileX, int tileY) {
        return blocklist[blockX][blockY].tilelist[tileX][tileY].getOpen();
    }

    public void checkTileOpenStatus() {
        for (int x = 0; x < this.breite; x++) {
            for (int y = 0; y < this.breite; y++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.println(this.isTileOpen(y, x, j, i));
                    }
                }
            }

        }
    }

}
