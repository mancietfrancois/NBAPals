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
public class GameStatusTest {
    
    /**
     * Test of values method, of class GameStatus.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        GameStatus[] expResult = {GameStatus.FINAL, GameStatus.FINAL_OT, 
            GameStatus.POSTPONED, GameStatus.TO_BE_PLAYED};
        GameStatus[] result = GameStatus.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class GameStatus.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String [] names = {"FINAL", "FINAL_OT", "POSTPONED", "TO_BE_PLAYED"};
        GameStatus [] expResults = {GameStatus.FINAL, GameStatus.FINAL_OT, 
            GameStatus.POSTPONED, GameStatus.TO_BE_PLAYED};
        for (int i = 0; i < names.length; i++) {
            GameStatus result = GameStatus.valueOf(names[i]);
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of getValue method, of class GameStatus.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        GameStatus [] instances = {GameStatus.FINAL, GameStatus.FINAL_OT, 
            GameStatus.POSTPONED, GameStatus.TO_BE_PLAYED};
        String [] expResults = {"FINAL", "FINAL_OT", "POSTPONED", "TO_BE_PLAYED"};
        for (int i = 0; i < instances.length; i++) {
            String result = instances[i].getValue();
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of toString method, of class GameStatus.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GameStatus [] instances = {GameStatus.FINAL, GameStatus.FINAL_OT, 
            GameStatus.POSTPONED, GameStatus.TO_BE_PLAYED};
        String [] expResults = {"FINAL", "FINAL_OT", "POSTPONED", "TO_BE_PLAYED"};
        for (int i = 0; i < instances.length; i++) {
            String result = instances[i].toString();
            assertEquals(expResults[i], result);
        }
    }

    /**
     * Test of fromString method, of class GameStatus.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        String [] names = {"FINAL", "FINAL_OT", "POSTPONED", "TO_BE_PLAYED", 
            "FINAL/2OT", "...", null};
        GameStatus [] expResults = {GameStatus.FINAL, GameStatus.FINAL_OT, 
            GameStatus.POSTPONED, GameStatus.TO_BE_PLAYED, GameStatus.FINAL_OT, 
            GameStatus.TO_BE_PLAYED, GameStatus.TO_BE_PLAYED};
        for (int i = 0; i < names.length; i++) {
            GameStatus result = GameStatus.fromString(names[i]);
            assertEquals(expResults[i], result);
        }
    }
    
}
