/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.responses;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Karl Huber
 */
public class StunArrowResponse {
    public int id;
    public Vector2 velocity;
    public float duration;

    public StunArrowResponse(int id, Vector2 velocity, float duration) {
        this.id = id;
        this.velocity = velocity;
        this.duration = duration;
    }
}
