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
package fr.mnf.nbapals.nbamodel.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francois
 */
public class TeamsTest {
    
    public TeamsTest() {
        //Teams.valueOf("NY");
    }
    /**
     * Test of values method, of class Teams.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Teams[] expResult = {Teams.ATL, Teams.BKN, Teams.BOS, Teams.CHA, Teams.CHI,
            Teams.CLE, Teams.DAL, Teams.DEN, Teams.DET, Teams.GS, Teams.HOU,
            Teams.IND, Teams.LAC, Teams.LAL, Teams.MEM, Teams.MIA, Teams.MIL,
            Teams.MIN, Teams.NK, Teams.NO, Teams.OKC, Teams.ORL, Teams.PHI,
            Teams.PHX, Teams.POR, Teams.SA, Teams.SAC, Teams.TOR, Teams.UTAH,
            Teams.WSH};
        Teams[] result = Teams.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Teams.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String [] names = {"ATL", "BKN", "BOS", "CHA", "CHI", "CLE", "DAL", "DEN",
            "DET", "GS", "HOU", "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
            "NO", "OKC", "ORL", "PHI", "PHX", "POR", "SA", "SAC", "TOR", 
            "UTAH", "WSH"};
        Teams [] expResults = {Teams.ATL, Teams.BKN, Teams.BOS, Teams.CHA, Teams.CHI,
            Teams.CLE, Teams.DAL, Teams.DEN, Teams.DET, Teams.GS, Teams.HOU,
            Teams.IND, Teams.LAC, Teams.LAL, Teams.MEM, Teams.MIA, Teams.MIL,
            Teams.MIN, Teams.NO, Teams.OKC, Teams.ORL, Teams.PHI,
            Teams.PHX, Teams.POR, Teams.SA, Teams.SAC, Teams.TOR, Teams.UTAH,
            Teams.WSH};
        for(int i = 0; i < names.length; i++) {
            Teams result = Teams.valueOf(names[i]);
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of getValue method, of class Teams.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Teams [] instances = {Teams.ATL, Teams.BKN, Teams.BOS, Teams.CHA, Teams.CHI,
            Teams.CLE, Teams.DAL, Teams.DEN, Teams.DET, Teams.GS, Teams.HOU,
            Teams.IND, Teams.LAC, Teams.LAL, Teams.MEM, Teams.MIA, Teams.MIL,
            Teams.MIN, Teams.NK, Teams.NO, Teams.OKC, Teams.ORL, Teams.PHI,
            Teams.PHX, Teams.POR, Teams.SA, Teams.SAC, Teams.TOR, Teams.UTAH,
            Teams.WSH};
        String [] expResults = {"ATL", "BKN", "BOS", "CHA", "CHI", "CLE", "DAL", "DEN",
            "DET", "GS", "HOU", "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
            "NY", "NO", "OKC", "ORL", "PHI", "PHX", "POR", "SA", "SAC", "TOR", 
            "UTAH", "WSH"};
        for(int i = 0; i < instances.length; i++) {
            String result = instances[i].getValue();
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of toString method, of class Teams.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Teams [] instances = {Teams.ATL, Teams.BKN, Teams.BOS, Teams.CHA, Teams.CHI,
            Teams.CLE, Teams.DAL, Teams.DEN, Teams.DET, Teams.GS, Teams.HOU,
            Teams.IND, Teams.LAC, Teams.LAL, Teams.MEM, Teams.MIA, Teams.MIL,
            Teams.MIN, Teams.NK, Teams.NO, Teams.OKC, Teams.ORL, Teams.PHI,
            Teams.PHX, Teams.POR, Teams.SA, Teams.SAC, Teams.TOR, Teams.UTAH,
            Teams.WSH};
        String [] expResults = {"ATL", "BKN", "BOS", "CHA", "CHI", "CLE", "DAL", "DEN",
            "DET", "GS", "HOU", "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
            "NY", "NO", "OKC", "ORL", "PHI", "PHX", "POR", "SA", "SAC", "TOR", 
            "UTAH", "WSH"};
        for(int i = 0; i < instances.length; i++) {
            String result = instances[i].toString();
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of fromString method, of class Teams.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        String [] texts = {"ATL", "BKN", "BOS", "CHA", "CHI", "CLE", "DAL", "DEN",
            "DET", "GS", "HOU", "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN",
            "NY", "NO", "OKC", "ORL", "PHI", "PHX", "POR", "SA", "SAC", "TOR", 
            "UTAH", "WSH", "", null};
        Teams [] expResults = {Teams.ATL, Teams.BKN, Teams.BOS, Teams.CHA, Teams.CHI,
            Teams.CLE, Teams.DAL, Teams.DEN, Teams.DET, Teams.GS, Teams.HOU,
            Teams.IND, Teams.LAC, Teams.LAL, Teams.MEM, Teams.MIA, Teams.MIL,
            Teams.MIN, Teams.NK, Teams.NO, Teams.OKC, Teams.ORL, Teams.PHI,
            Teams.PHX, Teams.POR, Teams.SA, Teams.SAC, Teams.TOR, Teams.UTAH,
            Teams.WSH, null, null};
        for(int i = 0; i < texts.length; i++) {
            Teams result = Teams.fromString(texts[i]);
            assertEquals(expResults[i], result);
        }
    }
    
}
