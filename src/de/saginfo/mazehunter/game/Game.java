package de.saginfo.mazehunter.game;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.map.World;
import de.saginfo.mazehunter.game.map.pickups.PickupManager;
import de.saginfo.mazehunter.game.player.HealthUpdateListener;
import de.saginfo.mazehunter.game.player.movement.MovementInput;
import de.saginfo.mazehunter.game.player.movement.MovementListener;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.AttackInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.MobilityInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.UtilityInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.DashListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.FireballListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.StandardHealListener;
import de.saginfo.mazehunter.game.player.abilities.EquipAbilityListener;
import de.saginfo.mazehunter.game.player.abilities.projectiles.ProjectileManager;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.ui.LobbyScreen.LobbyListener;
import java.util.ArrayList;

/**
 *
 */
public class Game {

    public final ArrayList<Player> players;
    private SpriteVisual visual;
    public World world;
    
    //TODO: Migrate Pickupmanager into Map, or somewhere else. 
    public PickupManager pickupManager;
    
    public ProjectileManager projectileManager;
    
    public Game() {
        players = new ArrayList<>();
    }

    public void startGame() {
        MovementInput movementInput = new MovementInput();
        MovementListener ml = new MovementListener();
        HealthUpdateListener hul = new HealthUpdateListener();
        EquipAbilityListener eal = new EquipAbilityListener();
        
        ConfigListener cL = new ConfigListener();
        
        createAbilityIO();
        
        pickupManager = new PickupManager();
        
        projectileManager = new ProjectileManager();
        
        //Testing
        // CCTestInput test = new CCTestInput();
        
        world = new World();
        world.makeMap(true, false, false, true, true, true, false, true, true, false, false, true, true, true, true, true, false, true, true, true, true, false, true, false, false, true, true, false, true, true, false, true, false, true, true, true);
    }
    
    public void createAbilityIO() {
        AttackInput aI = new AttackInput();
        UtilityInput uI = new UtilityInput();
        MobilityInput mI = new MobilityInput();
        
        DashListener dL = new DashListener();
        FireballListener fL = new FireballListener();
        StandardHealListener shL = new StandardHealListener();
    }

    /**
     * this method is right now beeing called from
     * {@link LobbyListener#createPlayers()} dunno if this is the best place to
     * do it, but works for now
     */
    public void createPlayer(int id, String name, Vector2 position) {
        Player player = new Player();
        player.id = id;
        player.name = name;
        player.position.set(position);
        players.add(player);
        player.maxHealth = 100; //TODO: get the Config from the server
        player.health = player.maxHealth;
    }

    public void update(float delta) {
        for (Player player : players) {
            player.update(delta);
        }
    }

    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.id == id) {
                return player;
            }
        }
        throw new RuntimeException("Player not found: " + id);
    }

    public Player getLocalPlayer() {
        return getPlayer(GameScreen.GAMESCREEN_SINGLETON.client.getID());
    }

    /**
     * called when the user closes the window
     */
    public void close() {

    }
}
