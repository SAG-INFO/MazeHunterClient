package de.saginfo.mazehunter.game.map;

import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import static de.saginfo.mazehunter.game.map.Map.TileWorldwidth;
import static de.saginfo.mazehunter.game.map.Map.blockWorldwidth;
import static de.saginfo.mazehunter.game.map.Map.coordinateWorldwidth;

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

    public void update(float delta) {
        if (playerOnMap()) {
            Tile currentTile = map.talktoTile(GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x, GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y);
            if (currentTile != localPlayerTile) {
                localPlayerTile = currentTile;
                startVision();
            }
        }
    }

    private boolean playerOnMap() {
        return GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x <= coordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.x >= 0 && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y <= coordinateWorldwidth && GAMESCREEN_SINGLETON.game.getLocalPlayer().position.y >= 0;
    }

    public void startVision() {
        cleanVision();
        markVision(localPlayerTile, -1, 0, 1);
    }

    /**
     * @param direction 0 = alle Richtungen 1 = Oben 2 = Rechts 3 = Unten 4 =
     * Links
     * @param maxflags = Anzahl an Ecken um die man schauen kann
     */
    public void markVision(Tile t, int direction, int flags, int maxflags) {
        if (t.open && flags < maxflags + 2) {

            t.setVisible(true);
            int x = t.getWorldIndexX();
            int y = t.getWorldIndexY();

            if (direction != 2 && x != TileWorldwidth - 1) {
                markVision(map.talktoNumber(x + 1, y), 4, direction == 4 ? flags : flags + 1, maxflags);
            }
            if (direction != 4 && x != 0) {
                markVision(map.talktoNumber(x - 1, y), 2, direction == 2 ? flags : flags + 1, maxflags);
            }
            if (direction != 3 && y != TileWorldwidth - 1) {
                markVision(map.talktoNumber(x, y + 1), 1, direction == 1 ? flags : flags + 1, maxflags);
            }
            if (direction != 1 && y != 0) {
                markVision(map.talktoNumber(x, y - 1), 3, direction == 3 ? flags : flags + 1, maxflags);
            }
        }
    }

//    public void markVision(Tile t) {
//        cleanVision();
//        if (t.open) {
//            t.visible = true;
//            int x = t.WorldIndexX;
//            int y = t.WorldIndexY;
//            if ((y + 1) < TileWorldwidth && map.talktoNumber(x, y + 1).open) {
//                markVisionRow(map.talktoNumber(x, y + 1), 1);
//            }
//            if ((x + 1) < TileWorldwidth && map.talktoNumber(x + 1, y).open) {
//                markVisionRow(map.talktoNumber(x + 1, y), 2);
//            }
//            if ((y - 1) >= 0 && map.talktoNumber(x, y - 1).open) {
//                markVisionRow(map.talktoNumber(x, y - 1), 3);
//            }
//            if ((x - 1) >= 0 && map.talktoNumber(x - 1, y).open) {
//                markVisionRow(map.talktoNumber(x - 1, y), 4);
//            }
//        }
//
//    }
//  
//    /*@param richtung   Oben = 1
//                        Rechts = 2
//                        Unten = 3
//                        Links = 4
//     */
//    private void markVisionRow(Tile t, int richtung) {
//        if (t.open == true) {
//            t.visible = true;
//            int x = t.IndexX + (t.parent.IndexX * 3);
//            int y = t.IndexY + (t.parent.IndexY * 3);
//            if (richtung == 1 || richtung == 3) {
//                if ((x + 1) < TileWorldwidth && map.talktoNumber(x + 1, y).open) {
//                    markVisionRow2(map.talktoNumber(x + 1, y), 2);
//                }
//                if ((x - 1) >= 0 && map.talktoNumber(x - 1, y).open) {
//                    markVisionRow2(map.talktoNumber(x - 1, y), 4);
//                }
//            } else if (richtung == 2 || richtung == 4) {
//                if ((y + 1) < TileWorldwidth && map.talktoNumber(x, y + 1).open) {
//                    markVisionRow2(map.talktoNumber(x, y + 1), 1);
//                }
//                if ((y - 1) >= 0 && map.talktoNumber(x, y - 1).open) {
//                    markVisionRow2(map.talktoNumber(x, y - 1), 3);
//                }
//            }
//
//            if (richtung == 1 && (y + 1) < TileWorldwidth) {
//                markVisionRow(map.talktoNumber(x, y + 1), richtung);
//            } else if (richtung == 2 && (x + 1) < TileWorldwidth) {
//                markVisionRow(map.talktoNumber(x + 1, y), richtung);
//            } else if (richtung == 3 && (y - 1) >= 0) {
//                markVisionRow(map.talktoNumber(x, y - 1), richtung);
//            } else if (richtung == 4 && (x - 1) >= 0) {
//                markVisionRow(map.talktoNumber(x - 1, y), richtung);
//            }
//        }
//    }
//
//    private void markVisionRow2(Tile t, int richtung) {
//        if (t.open == true) {
//            t.visible = true;
//            int x = t.IndexX + (t.parent.IndexX * 3);
//            int y = t.IndexY + (t.parent.IndexY * 3);
//            if (richtung == 1 && (y + 1) < TileWorldwidth) {
//                markVisionRow2(map.talktoNumber(x, y + 1), richtung);
//            } else if (richtung == 2 && (x + 1) < TileWorldwidth) {
//                markVisionRow2(map.talktoNumber(x + 1, y), richtung);
//            } else if (richtung == 3 && (y - 1) >= 0) {
//                markVisionRow2(map.talktoNumber(x, y - 1), richtung);
//            } else if (richtung == 4 && (x - 1) >= 0) {
//                markVisionRow2(map.talktoNumber(x - 1, y), richtung);
//            }
//        }
//    }
    public void cleanVision() {
        for (int i = 0; i < blockWorldwidth; i++) {
            for (int j = 0; j < blockWorldwidth; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        map.blocklist[i][j].tilelist[k][l].setVisible(false);
                    }
                }
            }
        }
    }
}
