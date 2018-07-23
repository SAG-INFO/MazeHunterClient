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
        if(!GAMESCREEN_SINGLETON.game.localPlayerExists())
            return;
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

////  trag für radius 0 ein
//    public void setVisionRadius(Tile tile, int radius, int maxRadius) {
//        if (radius <= maxRadius) {
//            if (tile.open) {
//                tile.setVisible(true);
//            }
//            setVisionRadius(GAMESCREEN_SINGLETON.game.world.map.talktoNumber(tile.getWorldIndexX() + 1, tile.getWorldIndexY()), radius++, maxRadius);
//            setVisionRadius(GAMESCREEN_SINGLETON.game.world.map.talktoNumber(tile.getWorldIndexX(), tile.getWorldIndexY() + 1), radius++, maxRadius);
//            setVisionRadius(GAMESCREEN_SINGLETON.game.world.map.talktoNumber(tile.getWorldIndexX() - 1, tile.getWorldIndexY()), radius++, maxRadius);
//            setVisionRadius(GAMESCREEN_SINGLETON.game.world.map.talktoNumber(tile.getWorldIndexX(), tile.getWorldIndexY() - 1), radius++, maxRadius);
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
