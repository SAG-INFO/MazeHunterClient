package de.saginfo.mazehunter.game.map;

import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import static de.saginfo.mazehunter.game.map.Map.BlockWorldwidth;
import static de.saginfo.mazehunter.game.map.Map.CoordinateWorldwidth;
import static de.saginfo.mazehunter.game.map.Map.TileWorldwidth;

/**
 *
 * @author sreis
 */
public class VisionSystem {
    
    private final Map map;
    public Tile localPlayerTile;

    public VisionSystem(Map map) {
        this.map = map;
    }
    
    public void update(float delta){
        if (playerOnMap()) {
            Tile currentTile = map.talktoTile(GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x, GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y);
            if (currentTile != localPlayerTile) {
                localPlayerTile = currentTile;
                markVision(localPlayerTile);
            }
        }
    }
    
    private boolean playerOnMap(){
        return GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x < CoordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x >= 0 && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y < CoordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y >= 0;
    }
    
//    public void markVision(Tile t, int direction, int edges){
//        if(!t.open || edges>=3)
//            return;
//        
//        t.visible = true;
//        int x = t.WorldIndexX;
//        int y = t.WorldIndexY;
//        
//        if(direction != 0 && x != TileWorldwidth-1)
//            markVision(map.talktoNumber(x+1, y), 2, direction==2?edges:edges+1);
//        if(direction != 2 && x != 0)
//            markVision(map.talktoNumber(x-1, y), 0, direction==0?edges:edges+1);
//        if(direction != 3 && y!= TileWorldwidth-1)
//            markVision(map.talktoNumber(x, y+1), 1, direction==1?edges:edges+1);
//        if(direction != 1 && y!=0)
//            markVision(map.talktoNumber(x, y-1), 3, direction==3?edges:edges+1);
//    }
    
    public void markVision(Tile t) {
        cleanVision();
        if (t.open) {
            t.visible = true;
            int x = t.WorldIndexX;
            int y = t.WorldIndexY;
            if ((y + 1) < TileWorldwidth && map.talktoNumber(x, y + 1).open) {
                markVisionRow(map.talktoNumber(x, y + 1), 1);
            }
            if ((x + 1) < TileWorldwidth && map.talktoNumber(x + 1, y).open) {
                markVisionRow(map.talktoNumber(x + 1, y), 2);
            }
            if ((y - 1) >= 0 && map.talktoNumber(x, y - 1).open) {
                markVisionRow(map.talktoNumber(x, y - 1), 3);
            }
            if ((x - 1) >= 0 && map.talktoNumber(x - 1, y).open) {
                markVisionRow(map.talktoNumber(x - 1, y), 4);
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
                if ((x + 1) < TileWorldwidth && map.talktoNumber(x + 1, y).open) {
                    markVisionRow2(map.talktoNumber(x + 1, y), 2);
                }
                if ((x - 1) >= 0 && map.talktoNumber(x - 1, y).open) {
                    markVisionRow2(map.talktoNumber(x - 1, y), 4);
                }
            } else if (richtung == 2 || richtung == 4) {
                if ((y + 1) < TileWorldwidth && map.talktoNumber(x, y + 1).open) {
                    markVisionRow2(map.talktoNumber(x, y + 1), 1);
                }
                if ((y - 1) >= 0 && map.talktoNumber(x, y - 1).open) {
                    markVisionRow2(map.talktoNumber(x, y - 1), 3);
                }
            }

            if (richtung == 1 && (y + 1) < TileWorldwidth) {
                markVisionRow(map.talktoNumber(x, y + 1), richtung);
            } else if (richtung == 2 && (x + 1) < TileWorldwidth) {
                markVisionRow(map.talktoNumber(x + 1, y), richtung);
            } else if (richtung == 3 && (y - 1) >= 0) {
                markVisionRow(map.talktoNumber(x, y - 1), richtung);
            } else if (richtung == 4 && (x - 1) >= 0) {
                markVisionRow(map.talktoNumber(x - 1, y), richtung);
            }
        }
    }

    private void markVisionRow2(Tile t, int richtung) {
        if (t.open == true) {
            t.visible = true;
            int x = t.IndexX + (t.parent.IndexX * 3);
            int y = t.IndexY + (t.parent.IndexY * 3);
            if (richtung == 1 && (y + 1) < TileWorldwidth) {
                markVisionRow2(map.talktoNumber(x, y + 1), richtung);
            } else if (richtung == 2 && (x + 1) < TileWorldwidth) {
                markVisionRow2(map.talktoNumber(x + 1, y), richtung);
            } else if (richtung == 3 && (y - 1) >= 0) {
                markVisionRow2(map.talktoNumber(x, y - 1), richtung);
            } else if (richtung == 4 && (x - 1) >= 0) {
                markVisionRow2(map.talktoNumber(x - 1, y), richtung);
            }
        }
    }

    public void cleanVision() {
        for (int i = 0; i < BlockWorldwidth; i++) {
            for (int j = 0; j < BlockWorldwidth; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        map.blocklist[i][j].tilelist[k][l].visible = false;
                    }
                }
            }
        }
    }
}
