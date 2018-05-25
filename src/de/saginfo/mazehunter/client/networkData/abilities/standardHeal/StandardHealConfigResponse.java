/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.standardHeal;

import de.saginfo.mazehunter.game.player.abilities.AbilityConfigs.StandardHealConfig;

/**
 *
 * @author Karl Huber
 */
public class StandardHealConfigResponse {
    public StandardHealConfig healConfig;

    public StandardHealConfigResponse(StandardHealConfig healConfig) {
        this.healConfig = healConfig;
    }

    public StandardHealConfigResponse() {
    }
}
