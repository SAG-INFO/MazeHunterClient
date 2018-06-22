package de.saginfo.mazehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.map.Map;
import de.saginfo.mazehunter.game.map.pickups.PickupManager;
import de.saginfo.mazehunter.game.player.HealthUpdateListener;
import de.saginfo.mazehunter.game.player.movement.MovementInput;
import de.saginfo.mazehunter.game.player.movement.MovementListener;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.AttackInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.MobilityInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.SlideInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.UtilityInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.DashListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.FireballListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.SlideListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.StandardHealListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.StunArrowListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.TrapListener;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityManager;
import de.saginfo.mazehunter.game.player.abilities.Entity.EntityListener;
import de.saginfo.mazehunter.game.player.abilities.EquipAbilityListener;
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
    
    public Game() {
        players = new ArrayList<>();
    }

    public void startGame() {
        MovementInput movementInput = new MovementInput();
        MovementListener ml = new MovementListener();
        HealthUpdateListener hul = new HealthUpdateListener();
        EquipAbilityListener eal = new EquipAbilityListener();
        ConfigListener cL = new ConfigListener();
        EntityListener eL = new EntityListener();
        createAbilityIO();
        
        world = new World();
    }
    
    public void createAbilityIO() {
        AttackInput aI = new AttackInput();
        UtilityInput uI = new UtilityInput();
        MobilityInput mI = new MobilityInput();
        SlideInput sI = new SlideInput();
        
        DashListener dL = new DashListener();
        FireballListener fL = new FireballListener();
        StandardHealListener shL = new StandardHealListener();
        StunArrowListener sAL = new StunArrowListener();
        TrapListener tL = new TrapListener();
        SlideListener sL = new SlideListener();
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
        player.maxHealth = 100; //TODO: get the Config from the server
        player.health = player.maxHealth;
        players.add(player);
    }

    public void update(float delta) {
        players.forEach((p) -> {p.update(delta);});
        world.update(delta);
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
