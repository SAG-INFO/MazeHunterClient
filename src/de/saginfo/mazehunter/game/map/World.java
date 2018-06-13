/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;

/**
 *
 * @author julian.mittermeier
 */
public class World {

    private Block[][] blocklist;
    public int BlockWorldwidth;
    public int TileWorldwidth;
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
            BlockWorldwidth = (int) Math.sqrt(b.length / 4);
            TileWorldwidth = BlockWorldwidth * 3;
            blocklist = new Block[BlockWorldwidth][BlockWorldwidth];
            int h = 0;
            for (int j = 0; j < BlockWorldwidth; j++) {
                for (int i = 0; i < BlockWorldwidth; i++) {
                    blocklist[i][j] = new Block(b[h], b[h + 1], b[h + 2], b[h + 3], i, j);
                    blocklist[i][j].draw();
                    h = h + 4;
                }
            }
        }
        System.out.println(TileWorldwidth);
    }

    public void update() {
        for (int i = 0; i < BlockWorldwidth; i++) {
            for (int j = 0; j < BlockWorldwidth; j++) {
                blocklist[i][j].update();
            }
        }
    }

    private int getIndex(float k) {
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

    public int getWorldIndex(float k) {
        int t = 0;
        while (k >= blockbreite) {
            k = k - blockbreite;
            t = t + 3;
        }
        if (k < ecke) {
            return t;
        } else if (k < ecke + center) {
            return t + 1;
        } else if (k < blockbreite) {
            return t + 2;
        } else {
            throw new RuntimeException("translateCoordinatetorealTile funktioniert mit diesem Wert nicht!");
        }
    }

    public Tile talktoTile(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)].tilelist[getIndex(x)][getIndex(y)];
    }

    public Block talktoBlock(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)];
    }

    public int translateCoordinateToBlock(float k) {
        return (int) k / blockbreite;
    }

    public Tile talktoNumber(int x, int y) {
        int bx = (int) x / 3;
        int tx = x - bx * 3;
        int by = (int) y / 3;
        int ty = y - by * 3;
        if (bx <= BlockWorldwidth && by <= BlockWorldwidth) {
            return blocklist[bx][by].tilelist[tx][ty];
        } else {
            throw new RuntimeException("talktonumberdoesntwork:(");
        }
    }

    public void markVision(float x, float y) {
        cleanVision();
        if(getWorldIndex(x)<TileWorldwidth && getWorldIndex(x) >= 0 && getWorldIndex(y) < TileWorldwidth && getWorldIndex(y) >= 0) {
        if (talktoTile(x, y).open == true) {
            talktoTile(x, y).visible = true;
            int a = getWorldIndex(x);
            int b = getWorldIndex(y);
            if (talktoNumber(a, b + 1).open && b + 1 < TileWorldwidth) {
                markVisionRow(talktoNumber(a, b + 1), 1);
            }
            if (talktoNumber(a + 1, b).open && a + 1 < TileWorldwidth) {
                markVisionRow(talktoNumber(a + 1, b), 2);
            }
            if (talktoNumber(a, b - 1).open && b - 1 >= 0) {
                markVisionRow(talktoNumber(a, b - 1), 3);
            }
            if (talktoNumber(a - 1, b).open && a - 1 >= 0) {
                markVisionRow(talktoNumber(a - 1, b), 4);
            }
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
            t.visible = true;
            int x = t.IndexX + (t.parent.IndexX * 3);
            int y = t.IndexY + (t.parent.IndexY * 3);
            if (richtung == 1 || richtung == 3) {
                if (talktoNumber(x + 1, y).open && x + 1 < TileWorldwidth) {
                    markVisionRow2(talktoNumber(x + 1, y), 2);
                }
                if (talktoNumber(x - 1, y).open && x - 1 >= 0) {
                    markVisionRow2(talktoNumber(x - 1, y), 4);
                }
            } else if (richtung == 2 || richtung == 4) {
                if (talktoNumber(x, y + 1).open && y + 1 < TileWorldwidth) {
                    markVisionRow2(talktoNumber(x, y + 1), 1);
                }
                if (talktoNumber(x, y - 1).open && y - 1 >= 0) {
                    markVisionRow2(talktoNumber(x, y - 1), 3);
                }
            }

            if (richtung == 1 && y + 1 < TileWorldwidth) {
                markVisionRow(talktoNumber(x, y + 1), richtung);
            } else if (richtung == 2 && x + 1 < TileWorldwidth) {
                markVisionRow(talktoNumber(x + 1, y), richtung);
            } else if (richtung == 3 && y - 1 >= 0) {
                markVisionRow(talktoNumber(x, y - 1), richtung);
            } else if (richtung == 4 && x - 1 >= 0) {
                markVisionRow(talktoNumber(x - 1, y), richtung);
            }
        }
    }

    private void markVisionRow2(Tile t, int richtung) {
        if (t.open == true) {
            t.visible = true;
            int x = t.IndexX + (t.parent.IndexX * 3);
            int y = t.IndexY + (t.parent.IndexY * 3);
            if (richtung == 1 && y + 1 < TileWorldwidth) {
                markVisionRow2(talktoNumber(x, y + 1), richtung);
            } else if (richtung == 2 && x + 1 < TileWorldwidth) {
                markVisionRow2(talktoNumber(x + 1, y), richtung);
            } else if (richtung == 3 && y - 1 >= 0) {
                markVisionRow2(talktoNumber(x, y - 1), richtung);
            } else if (richtung == 4 && x - 1 >= 0) {
                markVisionRow2(talktoNumber(x - 1, y), richtung);
            }
        }
    }

    public void cleanVision() {
        for (int i = 0; i < BlockWorldwidth; i++) {
            for (int j = 0; j < BlockWorldwidth; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        blocklist[i][j].tilelist[k][l].visible = false;
                    }
                }
            }
        }
    }

}
