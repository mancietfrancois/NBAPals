/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.nbapalsdao.bets;

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
public class EncryptorTest {

    /**
     * Test of getKey1 method, of class Encryptor.
     */
    @Test
    public void testGetKey1() {
        System.out.println("getKey1");
        //case key shorter than 16 character
        String key1 = "username";
        String expResult = "usernameusername";
        String result = Encryptor.getKey1(key1);
        assertEquals(expResult, result);
        //case too long key (more than 16 characters)
        key1 = "usernameusername1";
        expResult = "usernameusername";
        result = Encryptor.getKey1(key1);
        assertEquals(expResult, result);
        //Case key empty
        key1 = "";
        expResult = Encryptor.DEFAULT_KEY;
        result = Encryptor.getKey1(key1);
        assertEquals(expResult, result);
    }
    
}
