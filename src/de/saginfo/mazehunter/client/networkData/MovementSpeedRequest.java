/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData;

/**
 *
 * @author Karl Huber
 */
public class MovementSpeedRequest {
    public float change;

    public MovementSpeedRequest(float change) {
        this.change = change;
    }

    public MovementSpeedRequest() {
    }
}
