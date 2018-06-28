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
 * @author goilster.typ.euw
 */
public class Map {

    Block[][] blocklist;

    public static int BlockWorldwidth;
    public static int TileWorldwidth;
    public static int CoordinateWorldwidth;

    public static int ecke;
    public static int center;
    public static int blockbreite;

    public Tile localPlayerTile;

    private static final Texture centerTex = new Texture(Gdx.files.local("assets\\img\\map\\centerOpen.png"));
    private static final Texture cornerTex = new Texture(Gdx.files.local("assets\\img\\map\\corner.png"));

    public Map() {
        this(cornerTex.getWidth(), centerTex.getWidth());
    }

    public Map(int e, int c) {
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
            CoordinateWorldwidth = BlockWorldwidth * (2 * ecke + center);
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
    }

    public void makeTestMap(int k) {
        BlockWorldwidth = k;
        TileWorldwidth = k * 3;
        CoordinateWorldwidth = BlockWorldwidth * (2 * ecke + center);
        blocklist = new Block[BlockWorldwidth][BlockWorldwidth];
        for (int j = 0; j < BlockWorldwidth; j++) {
            for (int i = 0; i < BlockWorldwidth; i++) {
                blocklist[i][j] = new Block(true, true, true, true, i, j);
                blocklist[i][j].draw();
            }
        }
    }

    public void update() {
        if (playerOnMap()) {
            Tile currentTile = talktoTile(GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x, GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y);
            if (currentTile != localPlayerTile) {
                localPlayerTile = currentTile;
            }
        }

        for (int i = 0; i < BlockWorldwidth; i++) {
            for (int j = 0; j < BlockWorldwidth; j++) {
                blocklist[i][j].update();
            }
        }
    }

    private boolean playerOnMap() {
        return GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x <= CoordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x >= 0 && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y <= CoordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y >= 0;
    }

    private int getIndex(float pixelCoordiante) {
        while (pixelCoordiante >= blockbreite) {
            pixelCoordiante = pixelCoordiante - blockbreite;
        }
        if (pixelCoordiante < ecke) {
            return 0;
        } else if (pixelCoordiante < ecke + center) {
            return 1;
        } else if (pixelCoordiante < blockbreite) {
            return 2;
        } else {
            throw new RuntimeException("translateCoordinateToTile funktioniert mit diesem Wert nicht!");
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

    //moves row to the right; k is moved row
    private void moveRowRight(int k) {
        Block b = blocklist[BlockWorldwidth - 1][k];
        
        Block b2 = b.clone();
        
        b.indexX = BlockWorldwidth; 
        b.fadeOut();
        
        b2.indexX = -1;
        b2.updateGrafXPosition();
        
        for (int i = BlockWorldwidth - 2; i >= 0; i--) {
            blocklist[i][k].indexX = blocklist[i][k].indexX + 1;
            blocklist[i][k].animateIn(i, i);
            blocklist[i + 1][k] = blocklist[i][k];
        }
        blocklist[0][k] = b2;
        blocklist[0][k].indexX = 0;
        for (int m = 0; m < BlockWorldwidth; m++) {
            blocklist[m][k].animateGrafXPosition();

        }
    }

    //moves row to the left; k is moved row
    private void moveRowLeft(int k) {
        Block b = blocklist[0][k];
        Block b2 = b.clone();
        
        b.indexX = -1; 
        b.fadeOut();
        
        b2.indexX = BlockWorldwidth;
        b2.updateGrafXPosition();
        
        for (int i = 1; i < BlockWorldwidth; i++) {
            blocklist[i][k].indexX = blocklist[i][k].indexX - 1;
            blocklist[i - 1][k] = blocklist[i][k];
        }
        blocklist[BlockWorldwidth - 1][k] = b2;
        blocklist[BlockWorldwidth - 1][k].indexX = BlockWorldwidth - 1;
        for (int m = 0; m < BlockWorldwidth; m++) {
            blocklist[m][k].animateGrafXPosition();
        }
    }

    //moves row up; k is moved row
    private void moveRowUp(int k) {
        Block b = blocklist[k][BlockWorldwidth - 1];
        
        Block b2 = b.clone();
        
        b.indexY = BlockWorldwidth; 
        b.fadeOut();
        
        b2.indexY = -1;
        b2.updateGrafXPosition();
        
        for (int i = BlockWorldwidth - 2; i >= 0; i--) {
            blocklist[k][i].indexY = blocklist[k][i].indexY + 1;
            blocklist[k][i + 1] = blocklist[k][i];
        }
        blocklist[k][0] = b2;
        blocklist[k][0].indexY = 0;
        for (int m = 0; m < BlockWorldwidth; m++) {
            blocklist[k][m].animateGrafXPosition();
        }
    }

    //moves row down; k is moved row
    private void moveRowDown(int k) {
        Block b = blocklist[k][0];
        
        Block b2 = b.clone();
        
        b.indexY = -1; 
        b.fadeOut();
        
        b2.indexY = BlockWorldwidth;
        b2.updateGrafXPosition();
        
        for (int i = 1; i < BlockWorldwidth; i++) {
            blocklist[k][i].indexY = blocklist[k][i].indexY - 1;
            blocklist[k][i - 1] = blocklist[k][i];
        }
        blocklist[k][BlockWorldwidth - 1] = b2;
        blocklist[k][BlockWorldwidth - 1].indexY = BlockWorldwidth - 1;
        for (int m = 0; m < BlockWorldwidth; m++) {
            blocklist[k][m].animateGrafXPosition();
        }
    }

    //direction: 1 moves row up, 2 moves row right, 3 moves row down, 4 moves row
    //row: what row to move; X coordinate when up or down, Y coordinate when left or right
    public void moveRow(int row, int direction) {
        switch (direction) {
            case 1:
                moveRowUp(row);
                break;
            case 2:
                moveRowRight(row);
                break;
            case 3:
                moveRowDown(row);
                break;
            case 4:
                moveRowLeft(row);
                break;
        }
    }
}
