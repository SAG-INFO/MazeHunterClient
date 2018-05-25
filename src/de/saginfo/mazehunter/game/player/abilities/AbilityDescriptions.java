/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

/**
 *
 * @author Karl Huber
 * 
 * Strings with the Ability type and a short description of what it does.
 * 
 * available types
 * NAME_TYPE = "utility" eg. heal/trap keybind is F
 * NAME_TYPE = "attack" eg. blizzard keybind is SPACE
 * NAME_TYPE = "mobility" eg. dash keybind is SHIFT_LEFT
 * NAME_TYPE = "skillshot" eg. damage projectile keybind is leftmouse
 * 
 * if YOU have an idea for an ability, add it here with a //not implemented yet. note above
 */
public class AbilityDescriptions {
    
    public static String DASH_TYPE = "mobility";
    public static String DASH_DESCRIPTION = "Teleports the player a short distance in the direction he is currently walking.";
    
    //not implemented yet.
    public static String HEAL_TYPE = "utility";
    public static String HEAL_DESCRIPTION = "Roots and heals (yourself) over a period of time.";
    
    //not implemented yet.
    public static String BLIZZARD_TYPE = "attack";
    public static String BLIZZARD_DESCRIPTION = "Slows and deals damage to players around you.";
    
    //not implemeted yet.
    public static String FIREBALL_TYPE = "skillshot";
    public static String FIREBALL_DESCRIPTION = "Projectile that flies towards in the direction of the cursor and deals damage on impact.";
}