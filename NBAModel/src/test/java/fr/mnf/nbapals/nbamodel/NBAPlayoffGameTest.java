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

import fr.mnf.nbapals.nbamodel.NBAPlayoffGame;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
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
public class NBAPlayoffGameTest {
    
    private static final int ID = 1;
    private static final int GAME_NUMBER = 1;
    
    private final NBAPlayoffGame game;
    
    public NBAPlayoffGameTest() {
        game = new NBAPlayoffGame(null, Teams.CHA, Teams.ATL, 0, 0, 
                null, null, null, GameStatus.FINAL, null, ID, GAME_NUMBER);
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
     * Test of getSerieID method, of class NBAPlayoffGame.
     */
    @Test
    public void testGetSerieID() {
        System.out.println("getSerieID");
        NBAPlayoffGame instance = game;
        int expResult = ID;
        int result = instance.getSerieID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSerieID method, of class NBAPlayoffGame.
     */
    @Test
    public void testSetSerieID() {
        System.out.println("setSerieID");
        int serieID = ID + 1;
        NBAPlayoffGame instance = game;
        instance.setSerieID(serieID);
        int result = instance.getSerieID();
        assertEquals(serieID, result);
    }

    /**
     * Test of getGameNumber method, of class NBAPlayoffGame.
     */
    @Test
    public void testGetGameNumber() {
        System.out.println("getGameNumber");
        NBAPlayoffGame instance = game;
        int expResult = GAME_NUMBER;
        int result = instance.getGameNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGameNumber method, of class NBAPlayoffGame.
     */
    @Test
    public void testSetGameNumber() {
        System.out.println("setGameNumber");
        int gameNumber = GAME_NUMBER + 1;
        NBAPlayoffGame instance = game;
        instance.setGameNumber(gameNumber);        
        int result = instance.getGameNumber();
        assertEquals(gameNumber, result);
    }
}
