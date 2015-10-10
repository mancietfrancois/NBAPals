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

/**
 *
 * @author François_2
 */
public enum Teams {
    ATL("ATLANTA", "HAWKS", "ATL"),
    BKN("BROOKLYN", "NETS", "BKN"), 
    BOS("BOSTON", "CELTICS", "BOS"),
    CHA("CHARLOTTE", "HORNETS", "CHA"),
    CHI("CHICAGO", "BULLS", "CHI"), 
    CLE("CLEVELAND", "CAVALIERS", "CLE"),
    DAL("DALLAS", "MAVERICKS", "DAL"),
    DEN("DENVER", "NUGGETS", "DEN"),
    DET("DETROIT", "PISTONS", "DET"), 
    GS("GOLDEN STATE", "WARRIORS", "GS"),
    HOU("HOUSTON", "ROCKETS", "HOU"),
    IND("INDIANA", "PACERS", "IND"),
    LAC("LOS ANGELES", "CLIPPERS", "LAC"), 
    LAL("LOS ANGELES", "LAKERS", "LAL"),
    MEM("MEMPHIS", "GRIZZLIES", "MEM"),
    MIA("MIAMI", "HEAT", "MIA"),
    MIL("MILWAUKEE", "BUCKS", "MIL"), 
    MIN("MINNESOTA", "TIMBERWOLVES", "MIN"),
    NO("NEW ORLEANS", "PELICANS", "NO"),
    NY("NEW YORK", "KNICKS", "NY"),
    OKC("OKLAHOMA CITY", "THUNDER", "OKC"), 
    ORL("ORLANDO", "MAGIC", "ORL"),
    PHI("PHILADELPHIA", "76ERS", "PHI"),
    PHX("PHOENIX", "SUNS", "PHX"), 
    POR("PORTLAND", "TRAIL BLAZERS", "POR"),
    SA("SAN ANTONIO", "SPURS", "SA"),
    SAC("SACRAMENTO", "KINGS", "SAC"),
    TOR("TORONTO", "RAPTORS", "TOR"), 
    UTAH("UTAH", "JAZZ", "UTAH"),
    WSH("WASHINGTON", "WIZARDS", "WSH");  
   
    private final String city;
    private final String name;
    private final String shortname;

    Teams(String city, String name, String shortname) {
        this.city = city;
        this.name = name;
        this.shortname = shortname;
    }

    public String getCity() {
        return city;
    }
    
    public String getName() {
        return name;
    }
    
    public String getShortname() {
        return shortname;
    }

    @Override
    public String toString() {
        return this.getShortname();
    }
    
    public static Teams fromString(String text) {
    if (text != null) {
      for (Teams team : Teams.values()) {
        if (text.equalsIgnoreCase(team.shortname)) {
          return team;
        }
      }
    }
    return null;
  }
    
    
}
