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
public class DivisionTest {

    /**
     * Test of values method, of class Division.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Division[] expResult = {Division.ATLANTIC, Division.CENTRAL, 
            Division.NORTHWEST, Division.PACIFIC, Division.SOUTHEAST, 
            Division.SOUTHWEST};
        Division[] result = Division.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Division.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String [] names = {"ATLANTIC", "CENTRAL", "NORTHWEST", "PACIFIC", 
            "SOUTHEAST", "SOUTHWEST"};
        Division [] expResults = {Division.ATLANTIC, Division.CENTRAL, 
            Division.NORTHWEST, Division.PACIFIC, Division.SOUTHEAST, 
            Division.SOUTHWEST};
        for (int i = 0; i < names.length; i++) {
            Division result = Division.valueOf(names[i]);
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of getValue method, of class Division.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Division [] instances = {Division.ATLANTIC, Division.CENTRAL, 
            Division.NORTHWEST, Division.PACIFIC, Division.SOUTHEAST, 
            Division.SOUTHWEST};
        String [] expResults = {"ATLANTIC", "CENTRAL", "NORTHWEST", 
            "PACIFIC", "SOUTHEAST", "SOUTHWEST"};
        for (int i = 0; i < instances.length; i++) {
            String result = instances[i].getValue();
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of fromString method, of class Division.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        String[] s = {"ATLANTIC", "CENTRAL", "NORTHWEST", "PACIFIC", 
            "SOUTHEAST", "SOUTHWEST", "..."};
        Division [] expResults = {Division.ATLANTIC, Division.CENTRAL, 
            Division.NORTHWEST, Division.PACIFIC, Division.SOUTHEAST, 
            Division.SOUTHWEST, null};
        for (int i = 0; i < s.length; i++) {
            Division result = Division.fromString(s[i]);
            assertEquals(expResults[i], result);
        }
    }
    
}
