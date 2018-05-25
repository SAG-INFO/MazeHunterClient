/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

import de.saginfo.mazehunter.client.networkData.abilities.blizzard.BlizzardConfigRequest;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashConfigRequest;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealConfigRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.DashInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityInputs.StandardHealInput;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.DashListener;
import de.saginfo.mazehunter.game.player.abilities.AbilityListener.StandardHealListener;

/**
 *
 * @author Karl Huber
 * 
 * Methods used to choose an Ability from the menu.
 */
public class ChooseAbilities {
    
    public static String ability1; //mobility
    public static String ability2; //utility
    public static String ability3; //attack
    public static String ability4; //skillshot
    
    public static void chooseDash() {
        ability1 = "Dash";
        DashInput dashInput = new DashInput();
        DashListener dashListener = new DashListener();
        
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new DashConfigRequest());
    }
    
    public static void chooseStandardHeal() {
        ability2 = "Heal";
        StandardHealInput sHealInput = new StandardHealInput();
        StandardHealListener sHealListener = new StandardHealListener();
                
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new StandardHealConfigRequest());
    }
    
    public static void chooseBlizzard() {
        ability3 = "Blizzard";
        //TODO: Input/Listener
        
        GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new BlizzardConfigRequest());
    }
}
