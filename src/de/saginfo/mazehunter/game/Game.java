package de.saginfo.mazehunter.game;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.player.Abilities.DashInput;
import de.saginfo.mazehunter.game.player.Abilities.DashListener;
import de.saginfo.mazehunter.game.player.movement.MovementInput;
import de.saginfo.mazehunter.game.player.movement.MovementListener;
import de.saginfo.mazehunter.game.player.Player;
import de.saginfo.mazehunter.ui.LobbyScreen.LobbyListener;
import java.util.ArrayList;

/**
 *
 */
public class Game {

    public final ArrayList<Player> players;
    //public final AbilityConfig abilityConfig;
    
    public Game() {
        players = new ArrayList<>();    
        //abilityConfig = requestAbilityConfig();
    }

    /*
    public AbilityConfig requestAbilityConfig() {
        return null; //TODO Config vom Server holen
    }
    */
    
    public void startGame(){
        // Movement
        MovementInput movementInput = new MovementInput();
        MovementListener movementListener = new MovementListener();
        
        //Abilities
        DashInput dashInput = new DashInput();
        DashListener dashListener = new DashListener();
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
