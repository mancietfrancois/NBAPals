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

import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francois
 */
public class NBATest {
    
    private final NBA nba;
    private final List<NBATeam> nbaTeams;
    
    public NBATest() {
        nba = new NBA();
        nba.populate();
        nbaTeams = new ArrayList<>();
        nbaTeams.add(new NBATeam(Teams.ATL, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.BKN, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.BOS, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.CHA, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.CHI, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.CLE, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.DAL, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.DEN, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.DET, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.GS, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.HOU, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.IND, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.LAC, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.LAL, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.MEM, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.MIA, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.MIL, Conference.EAST, Division.CENTRAL, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.MIN, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.NO, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.NY, Conference.WEST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.OKC, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.ORL, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.PHI, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.PHX, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.POR, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.SA, Conference.WEST, Division.SOUTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.SAC, Conference.WEST, Division.PACIFIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.TOR, Conference.EAST, Division.ATLANTIC, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.UTAH, Conference.WEST, Division.NORTHWEST, 0, 0, 0, 0, 0, 0, 0, 0));
        nbaTeams.add(new NBATeam(Teams.WSH, Conference.EAST, Division.SOUTHEAST, 0, 0, 0, 0, 0, 0, 0, 0));
    }
    

    /**
     * Test of populate method, of class NBA.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> div = instance.getAtlantic();
        assertTrue(div.size() == 5);
        div = instance.getCentral();
        assertTrue(div.size() == 5);
        div = instance.getSoutheast();
        assertTrue(div.size() == 5);
        div = instance.getNorthwest();
        assertTrue(div.size() == 5);
        div = instance.getPacific();
        assertTrue(div.size() == 5);
        div = instance.getSouthwest();
        assertTrue(div.size() == 5);
    }

    /**
     * Test of populateFromExistingList method, of class NBA.
     */
    @Test
    public void testPopulateFromExistingList() {
        System.out.println("populateFromExistingList");
        List<NBATeam> teams = nbaTeams;
        NBA instance = new NBA();
        instance.populateFromExistingList(teams);
        List<NBATeam> div = instance.getAtlantic();
        assertTrue(div.size() == 5);
        div = instance.getCentral();
        assertTrue(div.size() == 5);
        div = instance.getSoutheast();
        assertTrue(div.size() == 5);
        div = instance.getNorthwest();
        assertTrue(div.size() == 5);
        div = instance.getPacific();
        assertTrue(div.size() == 5);
        div = instance.getSouthwest();
        assertTrue(div.size() == 5);
    }

    /**
     * Test of getAtlantic method, of class NBA.
     */
    @Test
    public void testGetAtlantic() {
        System.out.println("getAtlantic");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getAtlantic();
        Teams [] teams = {Teams.BKN, Teams.BOS, Teams.NY, Teams.PHI, Teams.TOR};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                }
                if (nbaTeam.getDivision() != Division.ATLANTIC) {
                    fail("The team " + nbaTeam.getTeamId().getShortname()+ " does "
                            + "not belong to the atlantic division!");
                }
                if (nbaTeam.getConference()!= Conference.EAST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the eastern conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
            
        }
    }

    /**
     * Test of getSoutheast method, of class NBA.
     */
    @Test
    public void testGetSoutheast() {
        System.out.println("getSoutheast");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getSoutheast();
        Teams [] teams = {Teams.ATL, Teams.CHA, Teams.MIA, Teams.ORL, Teams.WSH};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getDivision() != Division.SOUTHEAST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the southeast division!");
                }
                if (nbaTeam.getConference()!= Conference.EAST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the eastern conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
        }
    }

    /**
     * Test of getCentral method, of class NBA.
     */
    @Test
    public void testGetCentral() {
        System.out.println("getCentral");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getCentral();
        Teams [] teams = {Teams.CHI, Teams.CLE, Teams.DET, Teams.IND, Teams.MIL};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getDivision() != Division.CENTRAL) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the central division!");
                }
                if (nbaTeam.getConference()!= Conference.EAST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the eastern conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
        }
    }

    /**
     * Test of getSouthwest method, of class NBA.
     */
    @Test
    public void testGetSouthwest() {
        System.out.println("getSouthwest");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getSouthwest();
        Teams [] teams = {Teams.DAL, Teams.HOU, Teams.MEM, Teams.NO, Teams.SA};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getDivision() != Division.SOUTHWEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the southwest division!");
                }
                if (nbaTeam.getConference()!= Conference.WEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the western conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
        }
    }

    /**
     * Test of getPacific method, of class NBA.
     */
    @Test
    public void testGetPacific() {
        System.out.println("getPacific");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getPacific();
        Teams [] teams = {Teams.GS, Teams.LAC, Teams.LAL, Teams.PHX, Teams.SAC};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getDivision() != Division.PACIFIC) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the pacific division!");
                }
                if (nbaTeam.getConference()!= Conference.WEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the western conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
        }
    }

    /**
     * Test of getNorthwest method, of class NBA.
     */
    @Test
    public void testGetNorthwest() {
        System.out.println("getNorthwest");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getNorthwest();
        Teams [] teams = {Teams.DEN, Teams.MIN, Teams.OKC, Teams.POR, Teams.UTAH};
        if (result.size() != 5) {
            fail("The size of the division should be 5 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getDivision() != Division.NORTHWEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the northwest division!");
                }
                if (nbaTeam.getConference()!= Conference.WEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the western conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the division");
            }
        }
    }

    /**
     * Test of getEast method, of class NBA.
     */
    @Test
    public void testGetEast() {
        System.out.println("getEast");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getEast();
        Teams [] teams = {Teams.BKN, Teams.BOS, Teams.NY, Teams.PHI, Teams.TOR, 
            Teams.ATL, Teams.CHA, Teams.MIA, Teams.ORL, Teams.WSH, 
            Teams.CHI, Teams.CLE, Teams.DET, Teams.IND, Teams.MIL};
        if (result.size() != 15) {
            fail("The size of the conference should be 15 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getConference()!= Conference.EAST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the eastern conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the conference");
            }
        }
    }

    /**
     * Test of getWest method, of class NBA.
     */
    @Test
    public void testGetWest() {
        System.out.println("getWest");
        NBA instance = new NBA();
        instance.populate();
        List<NBATeam> result = instance.getWest();
        Teams [] teams = {Teams.DAL, Teams.HOU, Teams.MEM, Teams.NO, Teams.SA, 
            Teams.GS, Teams.LAC, Teams.LAL, Teams.PHX, Teams.SAC, Teams.DEN, 
            Teams.MIN, Teams.OKC, Teams.POR, Teams.UTAH};
        if (result.size() != 15) {
            fail("The size of the conference should be 15 instead of " + 
                    result.size() + "!");
        }
        for (Teams team : teams) {
            boolean found = false;
            for (NBATeam nbaTeam : result) {
                if (nbaTeam.getTeamId() == team) {
                    found = true;
                    break;
                }
                if (nbaTeam.getConference()!= Conference.WEST) {
                    fail("The team " + nbaTeam.getTeamId().getShortname() + " does "
                            + "not belong to the western conference!");
                }
            }
            if (!found) {
                fail("The team : " + team.getShortname() + " is not in the conference");
            }
        }
    }

    /**
     * Test of getNbaGames method, of class NBA.
     */
    @Test
    public void testGetNbaGames() {
        System.out.println("getNbaGames");
        NBA instance = new NBA();
        List<NBAGamesDay> expResult = new ArrayList<>();
        List<NBAGamesDay> result = instance.getNbaGames();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNbaGames method, of class NBA.
     */
    @Test
    public void testSetNbaGames() {
        System.out.println("setNbaGames");
        List<NBAGamesDay> nbaGames = new ArrayList<>();
        NBA instance = new NBA();
        instance.setNbaGames(nbaGames);
        assertEquals(nbaGames, instance.getNbaGames());
    }
    
}
