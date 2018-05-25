/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.blizzard;

import de.saginfo.mazehunter.game.player.abilities.AbilityConfigs.BlizzardConfig;

/**
 *
 * @author Karl Huber
 */
public class BlizzardConfigResponse {
    public BlizzardConfig blizzardConfig;

    public BlizzardConfigResponse(BlizzardConfig blizzardConfig) {
        this.blizzardConfig = blizzardConfig;
    }

    public BlizzardConfigResponse() {
    }
}
