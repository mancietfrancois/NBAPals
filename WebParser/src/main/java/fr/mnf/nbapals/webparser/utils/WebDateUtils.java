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
package fr.mnf.nbapals.webparser.utils;

import fr.mnf.nbapals.webparser.dates.DatesUtils;
import fr.mnf.nbapals.nbamodel.utils.NBADates;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francois
 */
public class WebDateUtils {
        
    public static boolean checkDateOk(String date) {
        //we check if the date does not belong to the forbidden dates
        for (String s : NBADates.NO_GAMES_DAY) {
            if (s.equals(date)) {
                return false;
            }
        }
        //we check the date respects the format yyyyMMdd
        SimpleDateFormat df = new SimpleDateFormat(WebParserUtils.ESPN_DATE_FORMAT);
        try {
            df.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(WebDateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //The date is in a valid format, and is not equals to a forbidden date
        return true;
    }
    
    public static List<String> getEspnRegularSeasonDates() {
        List<String> nbaGamesDate = new ArrayList<>();
        String startingDate = NBADates.REGULAR_SEASON_FIRST_DAY;
        do {
            
            if (checkDateOk(startingDate)) {
                nbaGamesDate.add(startingDate);
            }
            startingDate = DatesUtils.addDays(startingDate, 1);
        } while (!(startingDate).equals(NBADates.REGULAR_SEASON_LAST_DAY));
        return nbaGamesDate;
    }
}
