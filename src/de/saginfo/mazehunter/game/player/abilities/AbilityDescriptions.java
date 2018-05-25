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
 * Strings with a short description of the Ability type and what it does.
 * 
 * available types
 * type = "utility" eg. heal/trap keybind is SPACE 
 * type = "skillshot" eg. damage projectile keybind is leftmouse
 * type = "mobility" eg. dash keybind is SHIFT_LEFT
 * 
 * IF YOU HAVE AN IDEA FOR AN ABILITY, ADD IT HERE 
 */
public class AbilityDescriptions {
    
    public static String DASH_TYPE = "mobility";
    public static String DASH_DESCRIPTION = "Teleports the player a short distance in the direction he is currently walking.";

}