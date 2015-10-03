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
public class ConferenceTest {
    

    /**
     * Test of values method, of class Conference.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Conference[] expResult = {Conference.EAST, Conference.WEST};
        Conference[] result = Conference.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Conference.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String [] name = {"EAST", "WEST" };
        Conference [] expResult = {Conference.EAST, Conference.WEST};
        for (int i = 0; i < name.length; i++) {
            Conference result = Conference.valueOf(name[i]);
            assertEquals(expResult[i], result);
        }
    }

    /**
     * Test of getValue method, of class Conference.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Conference [] instance = {Conference.EAST, Conference.WEST};
        String [] expResult = {"EAST", "WEST"};
        for (int i = 0; i < instance.length; i++) {
            String result = instance[i].getValue();
            assertEquals(expResult[i], result);
        }
    }

    /**
     * Test of fromString method, of class Conference.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        String [] s = {"WEST", "EAST", "..."};
        Conference [] expResult = {Conference.WEST, Conference.EAST, null};
        
        for (int i = 0; i < s.length; i++) {
            Conference result = Conference.fromString(s[i]);
            assertEquals(expResult[i], result);
        }
    }
}
