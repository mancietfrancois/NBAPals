/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.espnparser;

import fr.mnf.nbapals.webparser.WebParser;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.utils.NBADates;
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
public class WebParserTest {

    /**
     * Test of parse method, of class ESPNParser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        WebParser instance = new WebParser(NBADates.REGULAR_SEASON_2014_2015_FIRST_DAY);
        NBAGamesDay result = instance.parse();
        assertTrue(result.getGames().size() == 3);
    }

    /**
     * Test of getGameDay method, of class ESPNParser.
     */
    @Test
    public void testGetGameDay() {
        System.out.println("getGameDay");
        WebParser instance = new WebParser(null);
        NBAGamesDay expResult = null;
        NBAGamesDay result = instance.getGameDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGameDay method, of class ESPNParser.
     */
    @Test
    public void testSetGameDay() {
        System.out.println("setGameDay");
        NBAGamesDay gameDay = null;
        WebParser instance = new WebParser(null);
        instance.setGameDay(gameDay);
        assertEquals(gameDay, instance.getGameDay());
    }

    /**
     * Test of getDate method, of class ESPNParser.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        WebParser instance = new WebParser("");
        String expResult = "";
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class ESPNParser.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        String date = "toto";
        WebParser instance = new WebParser("");
        instance.setDate(date);
        assertEquals(date, instance.getDate());
    }
    
}
