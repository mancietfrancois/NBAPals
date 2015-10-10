/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.nbapalsdao;

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.NBATeam;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.NBADates;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NBAPalsDAOTest {
    
    public NBAPalsDAOTest() {
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
     * Test of createConnection method, of class DBPronostixDAO.
     */
    @Test
    public void testCreateConnection() throws Exception {
        System.out.println("createConnection");
        Connection result = NBAPalsDAO.createConnection();
        assertFalse(result.isClosed());
    }

    /**
     * Test of getNBATeams method, of class DBPronostixDAO.
     */
    @Test
    public void testGetNBATeams() {
        System.out.println("getNBATeams");
        List<NBATeam> result = NBAPalsDAO.getNBATeams();
        assertTrue(result.size() == 0);
    }

    /**
     * Test of insertNBACalendar method, of class DBPronostixDAO.
     */
    @Test
    public void testInsertNBACalendar() {
        System.out.println("insertNBACalendar");
        List<NBAGamesDay> calendar = null;
        boolean expResult = false;
    }

    /**
     * Test of updateTeamResults method, of class DBPronostixDAO.
     */
    @Test
    public void testUpdateTeamResults() {
        System.out.println("updateTeamResults");
        List<NBATeam> teams = null;
        boolean expResult = false;
    }

    /**
     * Test of insertGame method, of class DBPronostixDAO.
     */
    @Test
    public void testInsertGame() {
        System.out.println("insertGame");
        NBAGame game = new NBAGame("FAKEIDFORTEST", Teams.CHA, Teams.ATL, 120, 88, "2015-04-15", "2015-04-16", "test", GameStatus.FINAL, "http://scores.espn.go.com/nba/scoreboard?date=20150411");
        boolean expResult = true;
        try {
            NBAPalsDAO.insertGame(game, NBAPalsDAO.createConnection());
        } catch (Exception ex) {
            Logger.getLogger(NBAPalsDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNBAGamesDay method, of class DBPronostixDAO.
     */
    @Test
    public void testGetNBAGamesDay() {
        System.out.println("getNBAGamesDay");
        String date = "";
        NBAGamesDay expResult = null;
    }

    /**
     * Test of getScores method, of class DBPronostixDAO.
     */
    @Test
    public void testGetScores() {
        System.out.println("getScores");
        NBATeam teamA = null;
        NBATeam teamB = null;
        List<NBAGame> expResult = null;
    }
    
}
