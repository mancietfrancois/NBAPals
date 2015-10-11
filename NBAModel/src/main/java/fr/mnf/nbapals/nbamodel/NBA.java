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


import fr.mnf.nbapals.nbamodel.archieves.NBAArchieveTeam;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import static fr.mnf.nbapals.nbamodel.utils.Division.ATLANTIC;
import static fr.mnf.nbapals.nbamodel.utils.Division.CENTRAL;
import static fr.mnf.nbapals.nbamodel.utils.Division.NORTHWEST;
import static fr.mnf.nbapals.nbamodel.utils.Division.PACIFIC;
import static fr.mnf.nbapals.nbamodel.utils.Division.SOUTHEAST;
import static fr.mnf.nbapals.nbamodel.utils.Division.SOUTHWEST;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author François_2
 */
public class NBA {

    public static final int CONFERENCE_RANKING = 0;
    public static final int DIVISION_RANKING = 1;

    private final List<NBATeam> southeast;
    private final List<NBATeam> central;
    private final List<NBATeam> atlantic;

    private final List<NBATeam> southwest;
    private final List<NBATeam> pacific;
    private final List<NBATeam> northwest;

    private final List<NBATeam> east;
    private final List<NBATeam> west;
    
    private List<NBAGamesDay> nbaGames;

    public NBA() {
        southeast = new ArrayList<>();
        atlantic = new ArrayList<>();
        central = new ArrayList<>();
        southwest = new ArrayList<>();
        northwest = new ArrayList<>();
        pacific = new ArrayList<>();

        east = new ArrayList<>();
        west = new ArrayList<>();
        
        nbaGames = new ArrayList<>();
    }
    
    public void populate() {
        NBATeam team = new NBATeam(Teams.ATL, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0);
        southeast.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.BKN, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0);
        atlantic.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.BOS, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0);
        atlantic.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.CHA, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0);
        southeast.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.CHI, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0);
        central.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.CLE, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0);
        central.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.DAL, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        southwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.DEN, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        northwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.DET, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0);
        central.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.GS, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0);
        pacific.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.HOU, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        southwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.IND, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0);
        central.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.LAC, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0);
        pacific.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.LAL, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0);
        pacific.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.MEM, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        southwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.MIA, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0);
        southeast.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.MIL, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0);
        central.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.MIN, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        northwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.NO, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        southwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.NY, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0);
        atlantic.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.OKC, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        northwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.ORL, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0);
        southeast.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.PHI, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0);
        atlantic.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.PHX, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0);
        pacific.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.POR, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        northwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.SA, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        southwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.SAC, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0);
        pacific.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.TOR, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0);
        atlantic.add(team);
        east.add(team);
        
        team = new NBATeam(Teams.UTAH, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0);
        northwest.add(team);
        west.add(team);
        
        team = new NBATeam(Teams.WSH, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0);
        southeast.add(team);
        east.add(team);
    }
    
    private void clearAll() {
        east.clear();
        west.clear();
        atlantic.clear();
        central.clear();
        southeast.clear();
        southwest.clear();
        pacific.clear();
        northwest.clear();
    }

    /**
     * Initiate the nba with a list of all the nba teams
     * @param teams
     */
    public void populateFromExistingList(List<NBATeam> teams) {
        for (NBATeam team : teams) {
            switch (team.getDivision()) {
                case ATLANTIC:
                    atlantic.add(team);
                    east.add(team);
                    break;
                case CENTRAL:
                    central.add(team);
                    east.add(team);
                    break;
                case SOUTHEAST:
                    southeast.add(team);
                    east.add(team);
                    break;
                case NORTHWEST:
                    northwest.add(team);
                    west.add(team);
                    break;
                case PACIFIC:
                    pacific.add(team);
                    west.add(team);
                    break;
                case SOUTHWEST:
                    southwest.add(team);
                    west.add(team);
                    break;
            }
        }
    }
    
    public List<NBATeam> getAtlantic() {
        return atlantic;
    }

    public List<NBATeam> getSoutheast() {
        return southeast;
    }

    public List<NBATeam> getCentral() {
        return central;
    }

    public List<NBATeam> getSouthwest() {
        return southwest;
    }

    public List<NBATeam> getPacific() {
        return pacific;
    }

    public List<NBATeam> getNorthwest() {
        return northwest;
    }

    public List<NBATeam> getEast() {
        return east;
    }

    public List<NBATeam> getWest() {
        return west;
    }

    public List<NBAGamesDay> getNbaGames() {
        return nbaGames;
    }
    
    public void setNbaGames(List<NBAGamesDay> nbaGames) {
        this.nbaGames = nbaGames;
        clearAll();
        populate();
        for(NBAGamesDay aDay : nbaGames) {
            for (NBAGame aGame : aDay.getGames()) {
                if(aGame.getStatus() == GameStatus.FINAL || 
                        aGame.getStatus() == GameStatus.FINAL_OT) {
                    
                    NBATeam homeTeam = findTeam(aGame.getTeamHome());
                    NBATeam awayTeam = findTeam(aGame.getTeamAway());
                    
                    if (aGame.getHomeScore() > aGame.getAwayScore()) {
                        homeTeam.addWin();
                        awayTeam.addLoss();
                        if (homeTeam.getDivision() == awayTeam.getDivision()) {
                            homeTeam.addDivWin();
                            awayTeam.addDivLoss();
                        }
                        if (homeTeam.getConference() == awayTeam.getConference()) {
                            homeTeam.addConfWin();
                            awayTeam.addConfLoss();
                        }
                    } else {
                        awayTeam.addWin();
                        homeTeam.addLoss();
                        if (homeTeam.getDivision() == awayTeam.getDivision()) {
                            awayTeam.addDivWin();
                            homeTeam.addDivLoss();
                        }
                        if (homeTeam.getConference() == awayTeam.getConference()) {
                            awayTeam.addConfWin();
                            homeTeam.addConfLoss();
                        }
                    }
                }
            }
        }
    }
    
    public NBATeam findTeam(Teams team) {
        List<NBATeam> teams = new ArrayList<>();
        teams.addAll(west);
        teams.addAll(east);
        
        for (NBATeam aTeam : teams) {
            if (aTeam.getTeamId() == team) {
                return aTeam;
            }
        }
        return null;
    }
    
    public List<NBATeam> getNBATeams() {
        List<NBATeam> teams = new ArrayList<>();
        teams.addAll(west);
        teams.addAll(east);
        return teams;
    }
    
    public List<NBAArchieveTeam> getNBAArchieveTeams() {
        List<NBAArchieveTeam> teams = new ArrayList<>();
        for(NBATeam team : west) {
            teams.add((NBAArchieveTeam) team);
        }
        for(NBATeam team : east) {
            teams.add((NBAArchieveTeam) team);
        }
        return teams;
    }

    public List<NBATeam> getPlayoffableTeams(Conference conference) {
        if (conference == Conference.EAST) {
            return east.subList(0, 7);
        } else {
            return west.subList(0, 7);
        }
    }

}