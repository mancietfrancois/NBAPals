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
package fr.mnf.nbapals.webparser.dates;

import fr.mnf.nbapals.webparser.utils.WebParserUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author Francois
 */
public class DatesUtils {
    
    public static final String ZONE_AMERICA = "EST5EDT";
    public static final String ZONE_EUROPA = "Europe/Paris";
    
    public static String addDays(String date, int day) {
        SimpleDateFormat df = new SimpleDateFormat(WebParserUtils.ESPN_DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(date));
        } catch (ParseException ex) {
            Logger.getLogger(DatesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        cal.add(Calendar.DATE, day); //minus number would decrement the days
        return df.format(cal.getTime());
    }
    
    public static String convertUTCHour(String date, String timeZoneToId) {
        
        DateTime utc = new DateTime(date, DateTimeZone.UTC);
        DateTime edt = utc.withZone(DateTimeZone.forID(timeZoneToId));
        return edt.toString("yyyy-MM-dd HH:mm");
    }
}
