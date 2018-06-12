/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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

    private static final Texture centerTex = new Texture(Gdx.files.local("assets\\img\\map\\centerOpen.png"));
    private static final Texture cornerTex = new Texture(Gdx.files.local("assets\\img\\map\\corner.png"));

    public World() {
        this(cornerTex.getWidth(), centerTex.getWidth());
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

    public void makeTestMap(int größe) {
        worldwidth = größe;
        blocklist = new Block[worldwidth][worldwidth];
        for (int j = 0; j < worldwidth; j++) {
            for (int i = 0; i < worldwidth; i++) {
                blocklist[j][i] = new Block(true, true, true, true, this.translateBlockToCoordinate(j), this.translateBlockToCoordinate(i));
            }

        }
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
                throw new RuntimeException("translateTileToCoordinate funktioniert mit diesem Wert nicht!");
        }
    }

    public int translateBlockToCoordinate(int k) {
        return (k * blockbreite);
    }

    public int translateCoordinateToBlock(float k) {
        return (int) k / blockbreite;
    }

    public int translateCoordinateToTile(float k) {
        while (k >= blockbreite) {
            k = k - blockbreite;
        }
        if (k < ecke) {
            return 0;
        } else if (k < ecke + center) {
            return 1;
        } else if (k < blockbreite) {
            return 2;
        } else {
            throw new RuntimeException("translateCoordinateToTile funktioniert mit diesem Wert nicht!");
        }
    }

    public int cutCoordinatetoTile(float k) {
        int i = 0;
        while (k >= blockbreite) {
            k = k - blockbreite;
            i = i + blockbreite;
        }
        if (k < ecke) {
            return i + 0;
        } else if (k < ecke + center) {
            return i + ecke;
        } else if (k < blockbreite) {
            return i + ecke + center;
        } else {
            throw new RuntimeException("cutCoordinatetoTile funktioniert mit diesem Wert nicht!");
        }
    }

    public Tile talktoTile(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)].tilelist[translateCoordinateToTile(x)][translateCoordinateToTile(y)];
    }

    public Block talktoBlock(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)];
    }

    public Tile talktoNumber(int x, int y) {
        int bx = (int) x / 3;
        int tx = x - bx * 3;
        int by = (int) y / 3;
        int ty = y - by * 3;
        return blocklist[translateCoordinateToBlock(bx)][translateCoordinateToBlock(by)].tilelist[translateCoordinateToTile(tx)][translateCoordinateToTile(ty)];
    }

    public void markVision(float x, float y) {
        if (talktoTile(x, y).open == true) {
            talktoTile(x, y).seen = true;
            if (talktoNumber((int) x, (int) y + 1).open == true) {
                markVisionRow(talktoNumber((int) x, (int) y + 1), 1);
            }
            if (talktoNumber((int) x + 1, (int) y).open == true) {
                markVisionRow(talktoNumber((int) x + 1, (int) y), 2);
            }
            if (talktoNumber((int) x, (int) y - 1).open == true) {
                markVisionRow(talktoNumber((int) x, (int) y - 1), 3);
            }
            if (talktoNumber((int) x - 1, (int) y).open == true) {
                markVisionRow(talktoNumber((int) x - 1, (int) y), 4);
            }
        }
    }


    /*@param richtung   Oben = 1
                        Rechts = 2
                        Unten = 3
                        Links = 4
     */
    private void markVisionRow(Tile t, int richtung) {
        if (t.open == true) {
            t.seen = true;
            if (richtung == 1 || richtung == 3) {
                if (talktoNumber(t.getIndexX() + 1, t.getIndexY()).open == true) {
                    markVisionRow2(talktoNumber(t.getIndexX() + 1, t.getIndexY()), 2);
                }
                if (talktoNumber(t.getIndexX() - 1, t.getIndexY()).open == true) {
                    markVisionRow2(talktoNumber(t.getIndexX() - 1, t.getIndexY()), 4);
                }
            } else if (richtung == 2 || richtung == 4) {
                if (talktoNumber(t.getIndexX(), t.getIndexY() + 1).open == true) {
                    markVisionRow2(talktoNumber(t.getIndexX(), t.getIndexY() + 1), 1);
                }
                if (talktoNumber(t.getIndexX(), t.getIndexY() - 1).open == true) {
                    markVisionRow2(talktoNumber(t.getIndexX(), t.getIndexY() - 1), 3);
                }
            }
            switch (richtung) {
                case 1:
                    markVisionRow(talktoNumber(t.getIndexX(), t.getIndexY() + 1), richtung);
                    break;
                case 2:
                    markVisionRow(talktoNumber(t.getIndexX() + 1, t.getIndexY()), richtung);
                    break;
                case 3:
                    markVisionRow(talktoNumber(t.getIndexX(), t.getIndexY() - 1), richtung);
                    break;
                case 4:
                    markVisionRow(talktoNumber(t.getIndexX() - 1, t.getIndexY()), richtung);
                    break;
                default:
                    break;
            }
        }
    }

    private void markVisionRow2(Tile t, int richtung) {
        if (t.open == true) {
            t.seen = true;
            switch (richtung) {
                case 1:
                    markVisionRow2(talktoNumber(t.getIndexX(), t.getIndexY() + 1), richtung);
                    break;
                case 2:
                    markVisionRow2(talktoNumber(t.getIndexX() + 1, t.getIndexY()), richtung);
                    break;
                case 3:
                    markVisionRow2(talktoNumber(t.getIndexX(), t.getIndexY() - 1), richtung);
                    break;
                case 4:
                    markVisionRow2(talktoNumber(t.getIndexX() - 1, t.getIndexY()), richtung);
                    break;
                default:
                    break;
            }
        }
    }

    public void cleanVision() {
        for (int i = 0; i < worldwidth; i++) {
            for (int j = 0; j < worldwidth; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        blocklist[i][j].tilelist[k][l].seen = false;
                    }
                }
            }
        }
    }

}
