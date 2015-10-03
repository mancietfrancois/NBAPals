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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francois
 */
public class NBATeamTest {
    
    private static final Teams TEAM = Teams.ATL;
    private static final Conference CONF = Conference.EAST;
    private static final Division DIVISION = Division.SOUTHEAST;
   private static final int WINS = 10;
    private static final int LOSS = 20;
    private static final int CONF_WINS = 4;
    private static final int CONF_LOSS = 7;
    private static final int CONF_RANKING = 1;
    private static final int DIV_WINS = 12;
    private static final int DIV_LOSS = 2;
    private static final int DIV_RANKING = 4;
    private static final int PLAYOFF_WINS = 15;
    private static final int PLAYOFF_LOSS = 22;
    
    private final NBATeam nbaTeam;
    
    public NBATeamTest() {
        nbaTeam = new NBATeam(TEAM, CONF, DIVISION, WINS, LOSS, CONF_WINS, 
                CONF_LOSS, CONF_RANKING, DIV_WINS, DIV_LOSS, DIV_RANKING, 
                PLAYOFF_WINS, PLAYOFF_LOSS);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConference method, of class NBATeam.
     */
    @Test
    public void testGetConference() {
        System.out.println("getConference");
        NBATeam instance = nbaTeam;
        Conference expResult = CONF;
        Conference result = instance.getConference();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDivision method, of class NBATeam.
     */
    @Test
    public void testGetDivision() {
        System.out.println("getDivision");
        NBATeam instance = nbaTeam;
        Division expResult = DIVISION;
        Division result = instance.getDivision();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWins method, of class NBATeam.
     */
    @Test
    public void testGetWins() {
        System.out.println("getWins");
        NBATeam instance = nbaTeam;
        int expResult = WINS;
        int result = instance.getWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWins method, of class NBATeam.
     */
    @Test
    public void testSetWins() {
        System.out.println("setWins");
        int wins = WINS + 1;
        NBATeam instance = nbaTeam;
        instance.setWins(wins);
        int result = instance.getWins();
        assertEquals(result, wins);
    }

    /**
     * Test of getLoss method, of class NBATeam.
     */
    @Test
    public void testGetLoss() {
        System.out.println("getLoss");
        NBATeam instance = nbaTeam;
        int expResult = LOSS;
        int result = instance.getLoss();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoss method, of class NBATeam.
     */
    @Test
    public void testSetLoss() {
        System.out.println("setLoss");
        int loss = LOSS + 1;
        NBATeam instance = nbaTeam;
        instance.setLoss(loss);
        int result = instance.getLoss();
        assertEquals(result, loss);
        
    }

    /**
     * Test of getConfWins method, of class NBATeam.
     */
    @Test
    public void testGetConfWins() {
        System.out.println("getConfWins");
        NBATeam instance = nbaTeam;
        int expResult = CONF_WINS;
        int result = instance.getConfWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConfWins method, of class NBATeam.
     */
    @Test
    public void testSetConfWins() {
        System.out.println("setConfWins");
        int confWins = CONF_WINS + 1;
        NBATeam instance = nbaTeam;
        instance.setConfWins(confWins);
        int result = instance.getConfWins();
        assertEquals(result, confWins);
    }

    /**
     * Test of getConfLoss method, of class NBATeam.
     */
    @Test
    public void testGetConfLoss() {
        System.out.println("getConfLoss");
        NBATeam instance = nbaTeam;
        int expResult = CONF_LOSS;
        int result = instance.getConfLoss();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConfLoss method, of class NBATeam.
     */
    @Test
    public void testSetConfLoss() {
        System.out.println("setConfLoss");
        int confLoss = CONF_LOSS + 1;
        NBATeam instance = nbaTeam;
        instance.setConfLoss(confLoss);
        int result = instance.getConfLoss();
        assertEquals(result, confLoss);
    }

    /**
     * Test of getDivWins method, of class NBATeam.
     */
    @Test
    public void testGetDivWins() {
        System.out.println("getDivWins");
        NBATeam instance = nbaTeam;
        int expResult = DIV_WINS;
        int result = instance.getDivWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDivWins method, of class NBATeam.
     */
    @Test
    public void testSetDivWins() {
        System.out.println("setDivWins");
        int divWins = DIV_WINS + 1;
        NBATeam instance = nbaTeam;
        instance.setDivWins(divWins);
        int result = instance.getDivWins();
        assertEquals(result, divWins);
    }

    /**
     * Test of getDivLoss method, of class NBATeam.
     */
    @Test
    public void testGetDivLoss() {
        System.out.println("getDivLoss");
        NBATeam instance = nbaTeam;
        int expResult = DIV_LOSS;
        int result = instance.getDivLoss();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDivLoss method, of class NBATeam.
     */
    @Test
    public void testSetDivLoss() {
        System.out.println("setDivLoss");
        int divLoss = DIV_LOSS;
        NBATeam instance = nbaTeam;
        instance.setDivLoss(divLoss);
        int result = instance.getDivLoss();
        assertEquals(result, divLoss);
    }

    /**
     * Test of getTeamId method, of class NBATeam.
     */
    @Test
    public void testGetTeamId() {
        System.out.println("getTeamId");
        NBATeam instance = nbaTeam;
        Teams expResult = TEAM;
        Teams result = instance.getTeamId();
        assertEquals(expResult, result);
    }

    /**
     * Test of addWin method, of class NBATeam.
     */
    @Test
    public void testAddWin() {
        System.out.println("addWin");
        NBATeam instance = nbaTeam;
        instance.addWin();
        assertTrue(WINS + 1 == nbaTeam.getWins());
    }

    /**
     * Test of addLoss method, of class NBATeam.
     */
    @Test
    public void testAddLoss() {
        System.out.println("addLoss");
        NBATeam instance = nbaTeam;
        instance.addLoss();
        assertTrue(LOSS + 1 == nbaTeam.getLoss());
    }

    /**
     * Test of addConfWin method, of class NBATeam.
     */
    @Test
    public void testAddConfWin() {
        System.out.println("addConfWin");
        NBATeam instance = nbaTeam;
        instance.addConfWin();
        assertTrue(CONF_WINS + 1 == nbaTeam.getConfWins());
    }

    /**
     * Test of addConfLoss method, of class NBATeam.
     */
    @Test
    public void testAddConfLoss() {
        System.out.println("addConfLoss");
        NBATeam instance = nbaTeam;
        instance.addConfLoss();
        assertTrue(CONF_LOSS + 1 == nbaTeam.getConfLoss());
    }

    /**
     * Test of addDivWin method, of class NBATeam.
     */
    @Test
    public void testAddDivWin() {
        System.out.println("addDivWin");
        NBATeam instance = nbaTeam;
        instance.addDivWin();
        assertTrue(DIV_WINS + 1 == nbaTeam.getDivWins());
    }

    /**
     * Test of addDivLoss method, of class NBATeam.
     */
    @Test
    public void testAddDivLoss() {
        System.out.println("addDivLoss");
        NBATeam instance = nbaTeam;
        instance.addDivLoss();
        assertTrue(DIV_LOSS + 1 == nbaTeam.getDivLoss());
    }

    /**
     * Test of getConfRanking method, of class NBATeam.
     */
    @Test
    public void testGetConfRanking() {
        System.out.println("getConfRanking");
        NBATeam instance = nbaTeam;
        int expResult = CONF_RANKING;
        int result = instance.getConfRanking();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConfRanking method, of class NBATeam.
     */
    @Test
    public void testSetConfRanking() {
        System.out.println("setConfRanking");
        int confRanking = CONF_RANKING + 1;
        NBATeam instance = nbaTeam;
        instance.setConfRanking(confRanking);
        int result = instance.getConfRanking();
        assertEquals(result, confRanking);
    }

    /**
     * Test of getDivRanking method, of class NBATeam.
     */
    @Test
    public void testGetDivRanking() {
        System.out.println("getDivRanking");
        NBATeam instance = nbaTeam;
        int expResult = DIV_RANKING;
        int result = instance.getDivRanking();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDivRanking method, of class NBATeam.
     */
    @Test
    public void testSetDivRanking() {
        System.out.println("setDivRanking");
        int divRanking = DIV_RANKING + 1;
        NBATeam instance = nbaTeam;
        instance.setDivRanking(divRanking);
        int result = instance.getDivRanking();
        assertEquals(result, divRanking);
    }

    /**
     * Test of getPlayoffWins method, of class NBATeam.
     */
    @Test
    public void testGetPlayoffWins() {
        System.out.println("getPlayoffWins");
        NBATeam instance = nbaTeam;
        int expResult = PLAYOFF_WINS;
        int result = instance.getPlayoffWins();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayoffWins method, of class NBATeam.
     */
    @Test
    public void testSetPlayoffWins() {
        System.out.println("setPlayoffWins");
        int playoffWins = PLAYOFF_WINS + 1;
        NBATeam instance = nbaTeam;
        instance.setPlayoffWins(playoffWins);
        int result = instance.getPlayoffWins();
        assertEquals(result, playoffWins);
    }

    /**
     * Test of getPlayoffLoss method, of class NBATeam.
     */
    @Test
    public void testGetPlayoffLoss() {
        System.out.println("getPlayoffLoss");
        NBATeam instance = nbaTeam;
        int expResult = PLAYOFF_LOSS;
        int result = instance.getPlayoffLoss();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayoffLoss method, of class NBATeam.
     */
    @Test
    public void testSetPlayoffLoss() {
        System.out.println("setPlayoffLoss");
        int playoffLoss = PLAYOFF_LOSS;
        NBATeam instance = nbaTeam;
        instance.setPlayoffLoss(playoffLoss);
        int result = instance.getPlayoffLoss();
        assertEquals(result, playoffLoss);
    }
}
