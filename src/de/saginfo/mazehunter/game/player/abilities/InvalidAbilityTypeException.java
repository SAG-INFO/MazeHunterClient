/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities;

/**
 *
 * @author Karl Huber
 */
public class InvalidAbilityTypeException extends Exception {
    public InvalidAbilityTypeException(String message) {
        super(message);
    }
}
