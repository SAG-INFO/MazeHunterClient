package de.saginfo.mazehunter.game;

import com.badlogic.gdx.math.Vector3;
import de.saginfo.mazehunter.game.map.Map;
import de.saginfo.mazehunter.game.map.VisionSystem;
import de.saginfo.mazehunter.game.map.pickups.PickupManager;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityManager;

/**
 *
 * @author sreis
 */
public class World {

    public final Map map;
    public final VisionSystem visionSystem;
    public final PickupManager pickupManager;
    public EntityManager entityManager;

    private final float cameraSmooth = 0.3f;
    private final Vector3 tmpVec = new Vector3();

    public World() {
        map = new Map(46, 46);
        map.makeMap(true, false, false, true, true, true, false, true, true, false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, false, false, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, false, true, false, true, true, false, true, false, true, false, false, true, false, true, false, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, false, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, true, true, false, false, true, true);
        visionSystem = new VisionSystem(map);
        pickupManager = new PickupManager();
        entityManager = new EntityManager();

        GameScreen.GAMESCREEN_SINGLETON.camera.zoom = 0.7f;
    }

    public void update(float delta) {
        entityManager.update(delta);
        visionSystem.update(delta);
        updateCam();
    }

    private void updateCam() {
        GameScreen.GAMESCREEN_SINGLETON.camera.position.lerp(tmpVec.set(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position, 0), cameraSmooth);
    }
}
