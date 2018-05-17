/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author karl.huber
 */
public class DashResponse {
    public Vector2 position;

    public DashResponse(Vector2 position) {
        this.position = position;
    }

    public DashResponse() {
    }
}
