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

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Francois
 */
public class GameTest {
    
    private static final String ID = "CHA_ATL_2015-06-05";
    private static final int HOME_SCORE = 105;
    private static final int AWAY_SCORE = 85;
    private static final Teams HOME_TEAM = Teams.ATL;
    private static final Teams AWAY_TEAM = Teams.BKN;
    private static final String DATE_US = "2015-06-04";
    private static final String DATE_EU = "2015-06-05";
    private static final String TIME = "1:00";
    private static final GameStatus STATUS = GameStatus.FINAL;
    private static final String URL = "url";
       
    private final NBAGame game;
    
    public GameTest() {
        game = new NBAGame(ID, HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE, 
                DATE_US, DATE_EU, TIME, STATUS, URL);
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
     * Test of getTeamHome method, of class Game.
     */
    @org.junit.Test
    public void testGetTeamHome() {
        System.out.println("getTeamHome");
        NBAGame instance = game;
        Teams expResult = HOME_TEAM;
        Teams result = instance.getTeamHome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeamAway method, of class Game.
     */
    @org.junit.Test
    public void testGetTeamAway() {
        System.out.println("getTeamAway");
        NBAGame instance = game;
        Teams expResult = AWAY_TEAM;
        Teams result = instance.getTeamAway();
        assertEquals(expResult, result);
    }


    /**
     * Test of getHomeScore method, of class Game.
     */
    @org.junit.Test
    public void testGetHomeScore() {
        System.out.println("getHomeScore");
        NBAGame instance = game;
        int expResult = HOME_SCORE;
        int result = instance.getHomeScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHomeScore method, of class Game.
     */
    @org.junit.Test
    public void testSetHomeScore() {
        System.out.println("setHomeScore");
        int homeScore = 0;
        NBAGame instance = game;
        instance.setHomeScore(homeScore);
        int result = instance.getHomeScore();
        assertEquals(result, homeScore);
    }

    /**
     * Test of getAwayScore method, of class Game.
     */
    @org.junit.Test
    public void testGetAwayScore() {
        System.out.println("getAwayScore");
        NBAGame instance = game;
        int expResult = AWAY_SCORE;
        int result = instance.getAwayScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAwayScore method, of class Game.
     */
    @org.junit.Test
    public void testSetAwayScore() {
        System.out.println("setAwayScore");
        int awayScore = 0;
        NBAGame instance = game;
        instance.setAwayScore(awayScore);
        int result = instance.getAwayScore();
        assertEquals(awayScore, result);
    }

    /**
     * Test of getDateUs method, of class Game.
     */
    @org.junit.Test
    public void testGetDateUs() {
        System.out.println("getDateUs");
        NBAGame instance = game;
        String expResult = DATE_US;
        String result = instance.getDateUs();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateEu method, of class Game.
     */
    @org.junit.Test
    public void testGetDateEu() {
        System.out.println("getDateEu");
        NBAGame instance = game;
        String expResult = DATE_EU;
        String result = instance.getDateEu();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Game.
     */
    @org.junit.Test
    public void testGetTime() {
        System.out.println("getTime");
        NBAGame instance = game;
        String expResult = TIME;
        String result = instance.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class Game.
     */
    @org.junit.Test
    public void testGetStatus() {
        System.out.println("getStatus");
        NBAGame instance = game;
        GameStatus expResult = STATUS;
        GameStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Game.
     */
    @org.junit.Test
    public void testSetStatus() {
        System.out.println("setStatus");
        GameStatus status = STATUS;
        NBAGame instance = game;
        instance.setStatus(status);
        GameStatus res = instance.getStatus();
        assertEquals(res, status);
    }

    /**
     * Test of getEspnURL method, of class Game.
     */
    @org.junit.Test
    public void testGetEspnURL() {
        System.out.println("getEspnURL");
        NBAGame instance = game;
        String expResult = URL;
        String result = instance.getEspnURL();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Game.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        NBAGame instance = game;
        String expResult = ID;
        String result = instance.getId();
        assertEquals(expResult, result);
    }    
}
