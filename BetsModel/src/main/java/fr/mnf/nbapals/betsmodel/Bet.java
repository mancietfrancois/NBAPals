/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.betsmodel;

import fr.mnf.nbapals.nbamodel.utils.Teams;

/**
 *
 * @author Francois
 */
public class Bet {
    
    private String gameId;
    private String gamblerId;
    private Teams teamId;

    public Bet(String gameId, String gamblerId, Teams teamId) {
        this.gameId = gameId;
        this.gamblerId = gamblerId;
        this.teamId = teamId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGamblerId() {
        return gamblerId;
    }

    public void setGamblerId(String gamblerId) {
        this.gamblerId = gamblerId;
    }

    public Teams getTeamId() {
        return teamId;
    }

    public void setTeamId(Teams teamId) {
        this.teamId = teamId;
    }
    
    
    
}
