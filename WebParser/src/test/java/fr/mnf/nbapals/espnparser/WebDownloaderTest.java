/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.espnparser;

import fr.mnf.nbapals.webparser.WebDownloader;
import fr.mnf.nbapals.webparser.utils.WebParserUtils;
import fr.mnf.nbapals.nbamodel.utils.NBADates;
import org.jsoup.nodes.Document;
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
public class WebDownloaderTest {
    
    public WebDownloaderTest() {
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
     * Test of download method, of class ESPNDownloader.
     */
    @Test
    public void testDownload() throws Exception {
        System.out.println("download");
        String dateCode = NBADates.REGULAR_SEASON_2014_2015_FIRST_DAY;
        Document expResult = null;
        Document result = WebDownloader.download(dateCode);
    }    
}
