package de.saginfo.mazehunter.game;

import com.badlogic.gdx.math.Vector3;
import de.saginfo.mazehunter.game.map.Map;
import de.saginfo.mazehunter.game.map.pickups.PickupManager;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityManager;

/**
 *
 * @author sreis
 */
public class World {
    
    public final Map map;
    public final PickupManager pickupManager;
    public EntityManager entityManager;
    
    private final float cameraSmooth = 0.8f;
    private final Vector3 tmpVec = new Vector3();

    public World() {
        map = new Map(25, 50);
        map.makeMap(true, false, false, true, true, true, false, true, true, false, false, true, true, true, true, true, false, true, true, true, true, false, true, false, false, true, true, false, true, true, false, true, false, true, true, true);
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
    }
    
    public void update(float delta){
        entityManager.update(delta);
        map.update();
    }
    
    private void updateCam(){
        GameScreen.GAMESCREEN_SINGLETON.camera.position.lerp(tmpVec.set(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position, cameraSmooth), 0);
    }
}
