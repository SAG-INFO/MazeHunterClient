/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.requests;

/**
 *
 * @author karl.huber
 */
public class SlideRequest {
    public char direction;

    public SlideRequest(char direction) {
        this.direction = direction;
    }

    public SlideRequest() {
    }
}
