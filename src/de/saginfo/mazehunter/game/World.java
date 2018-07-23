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

    public World() {
        map = new Map(46, 46);
        map.makeMap(true, false, false, true, true, true, false, true, true, false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, false, false, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, false, true, false, true, true, false, true, false, true, false, false, true, false, true, false, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, false, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, true, true, false, false, true, true);
        visionSystem = new VisionSystem(map);
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
    }

    public void update(float delta) {
        entityManager.update(delta);
        visionSystem.update(delta);
    }
}
