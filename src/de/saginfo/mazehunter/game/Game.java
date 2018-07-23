package de.saginfo.mazehunter.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.player.Hunter;
import de.saginfo.mazehunter.game.player.movement.MovementInput;
import de.saginfo.mazehunter.game.player.movement.MovementListener;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.game.player.Runner;
import de.saginfo.mazehunter.game.player.SpawnListener;
import de.saginfo.mazehunter.game.player.StatusListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.AttackInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.MobilityInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.SlideInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.DashListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.FireballListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.SatanListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.SlideListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.SpeedBoostListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.StunArrowListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.TrapListener;
import de.saginfo.mazehunter.game.player.abilities.EquipAbilityListener;
import de.saginfo.mazehunter.game.player.movement.VisualListener;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.ui.gameoverscreen.GameoverListener;
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
        world = new World();

        createListeners();
    }
    
    public void createListeners() {
        new GameoverListener();
        SpawnListener sl = new SpawnListener();
        MovementListener ml = new MovementListener();
        EquipAbilityListener eal = new EquipAbilityListener();

        StatusListener sL = new StatusListener();
        ConfigListener cL = new ConfigListener();
        
        
        FireballListener fl = new FireballListener();
        GAMESCREEN_SINGLETON.client.addListener(new SpeedBoostListener());
        GAMESCREEN_SINGLETON.client.addListener(new SatanListener());
        DashListener dL = new DashListener();
        StunArrowListener sAL = new StunArrowListener();
        TrapListener tL = new TrapListener();
        SlideListener s = new SlideListener();
        
        VisualListener vl = new VisualListener();
        
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
                MovementInput movementInput = new MovementInput();
                AttackInput aI = new AttackInput();
                MobilityInput mI = new MobilityInput();
                SlideInput sI = new SlideInput();
//            }
//        }, 0.5f);
    }

    public void createPlayer(int id, String name, Vector2 position, boolean hunter) {
        Player player = hunter? new Hunter(id, name, position):new Runner(id, name, position);
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
    
    public boolean localPlayerExists(){
        return players.stream().anyMatch((p) -> {return GAMESCREEN_SINGLETON.client.getID()==p.id;});
    }

    /**
     * called when the user closes the window
     */
    public void close() {
        
    }
}
