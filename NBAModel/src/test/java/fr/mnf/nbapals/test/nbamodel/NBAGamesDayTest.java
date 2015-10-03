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
package fr.mnf.nbapals.test.nbamodel;

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.NBATeams;
import java.util.ArrayList;
import java.util.List;
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
public class NBAGamesDayTest {
    
    private final NBAGamesDay gamesDay;
    private final NBAGame game;
    
    public NBAGamesDayTest() {
        gamesDay = new NBAGamesDay();
        game = new NBAGame(null, NBATeams.CHA, NBATeams.ATL, 0, 0, null, null, null, GameStatus.FINAL, null);
        gamesDay.addGame(game);
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
     * Test of getGames method, of class NBAGamesDay.
     */
    @Test
    public void testGetGames() {
        System.out.println("getGames");
        NBAGamesDay instance = gamesDay;
        List<NBAGame> result = instance.getGames();
        assertTrue(result.size() == 1);
        assertTrue(result.contains(game));
    }

    /**
     * Test of setGames method, of class NBAGamesDay.
     */
    @Test
    public void testSetGames() {
        System.out.println("setGames");
        List<NBAGame> games = new ArrayList<>();
        games.add(game);
        NBAGamesDay instance = gamesDay;
        instance.setGames(games);
        List<NBAGame> result = instance.getGames();
        assertTrue(result.size() == 1);
        assertTrue(result.contains(game));
    }

    /**
     * Test of addGame method, of class NBAGamesDay.
     */
    @Test
    public void testAddGame() {
        System.out.println("addGame");
        NBAGame gameTest = new NBAGame(null, NBATeams.CHA, NBATeams.ATL, 10, 20, 
                null, null, null, GameStatus.FINAL, null);
        NBAGamesDay instance = gamesDay;
        instance.addGame(gameTest);
        List<NBAGame> result = instance.getGames();
        assertTrue(result.size() == 2);
        assertTrue(result.contains(game));
        assertTrue(result.contains(gameTest));
    }

    /**
     * Test of removeGame method, of class NBAGamesDay.
     */
    @Test
    public void testRemoveGame() {
        System.out.println("removeGame");
        NBAGame gameTest = new NBAGame(null, NBATeams.CHA, NBATeams.ATL, 15, 35, null,
                null, null, GameStatus.FINAL, null);
        NBAGamesDay instance = gamesDay;
        instance.addGame(gameTest);
        instance.removeGame(gameTest);
        List<NBAGame> result = instance.getGames();
        assertTrue(result.size() == 1);
        assertFalse(result.contains(gameTest));
    }
    
}
