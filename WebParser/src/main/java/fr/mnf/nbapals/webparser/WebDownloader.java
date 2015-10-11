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
package fr.mnf.nbapals.webparser;

import fr.mnf.nbapals.webparser.exceptions.WebDownloaderException;
import fr.mnf.nbapals.webparser.utils.WebDateUtils;
import fr.mnf.nbapals.webparser.utils.WebParserUtils;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Francois
 */
public class WebDownloader {

    private static final int LIMIT_OF_TRIES = 10;

    public static Document download(String dateCode, String [] noGamesDay)
            throws WebDownloaderException, IOException {
        if (!WebDateUtils.checkDateOk(dateCode, noGamesDay)) {
            throw new WebDownloaderException("Date not in a valid format! "
                    + "Must be " + WebParserUtils.ESPN_DATE_FORMAT);
        }
        Document doc = null;
        boolean downloadSucceeded = false;
        int tries = 0;
        //before we try to download the file, we see the cache
        if (checkFileExist(WebParserUtils.ESPN_CACHE_FOLDER + dateCode
                + ".html")) {
            System.out.println("The file exists, no need to download it.");
            doc = Jsoup.parse(new File(WebParserUtils.ESPN_CACHE_FOLDER
                    + dateCode + ".html"),
                    "UTF-8");
        } else {
            //the cache is empty, we download the file
            System.out.println("The file does not exist.");
            System.out.println("DOWNLOADING "
                    + WebParserUtils.ESPN_BASE_URI + dateCode + "....");
            while (!downloadSucceeded && tries <= LIMIT_OF_TRIES) {
                try {
                    doc = Jsoup.connect(WebParserUtils.ESPN_BASE_URI
                            + dateCode).get();
                    downloadSucceeded = true;
                } catch (IOException ex) {
                    tries++;
                    System.out.println("\t[DOWNLOADING FAILED... : "
                            + WebParserUtils.ESPN_BASE_URI + dateCode
                            + ". RESTARTING...[" + tries + "/" + LIMIT_OF_TRIES
                            + "]");

                }
            }
        }
        //check the doc is not null
        if (doc == null) {
            throw new WebDownloaderException("Impossible to download or read"
                    + WebParserUtils.ESPN_BASE_URI + dateCode
                    + ". Check the URI or the internet connection.");
        } else {
            if (downloadSucceeded) {
                //we save the file in the cache
                final File f = new File(WebParserUtils.ESPN_CACHE_FOLDER
                        + dateCode + ".html");
                FileUtils.writeStringToFile(f, doc.toString(), "UTF-8");
            }
        }
        return doc;
    }

    private static boolean checkFileExist(String filename) {
        File f = new File(filename);
        return f.exists();
    }

}
