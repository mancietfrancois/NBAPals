/* 
 * Copyright (C) 2015 Francois
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package fr.mnf.nbapals.nbamodel;


import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.NBATeams;

/**
 *
 * @author François_2
 */
public class NBAGame {
    
    private final String id;
    private final NBATeams teamHome;
    private final NBATeams teamAway;
    private int homeScore;
    private int awayScore;
    private final String dateUs;
    private final String dateEu;
    private final String time;
    private GameStatus status;
    private final String espnURL;

    public NBAGame(String id, NBATeams teamHome, NBATeams teamAway, int homeScore, 
            int awayScore, String dateUs, String dateEu, String time, 
            GameStatus status, String espnURL) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.dateUs = dateUs;
        this.dateEu = dateEu;
        this.time = time;
        this.status = status;
        this.espnURL = espnURL;
        this.id = id;
    }
    
    public NBATeams getTeamHome() {
        return teamHome;
    }
    
    public NBATeams getTeamAway() {
        return teamAway;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getDateUs() {
        return dateUs;
    }

    public String getDateEu() {
        return dateEu;
    }

    public String getTime() {
        return time;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getEspnURL() {
        return espnURL;
    }

    public String getId() {
        return id;
    }    
}
