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
public enum NBATeams {
    ATL("ATL"), 
    BOS("BOS"),
    BKN("BKN"),
    CHA("CHA"),
    CHI("CHI"), 
    CLE("CLE"),
    DAL("DAL"),
    DEN("DEN"),
    DET("DET"), 
    GS("GS"),
    HOU("HOU"),
    IND("IND"),
    LAC("LAC"), 
    LAL("LAL"),
    MEM("MEM"),
    MIA("MIA"),
    MIL("MIL"), 
    MIN("MIN"),
    NO("NO"),
    NK("NY"),
    OKC("OKC"), 
    ORL("ORL"),
    PHI("PHI"),
    PHX("PHX"), 
    POR("POR"),
    SAC("SAC"),
    SA("SA"),
    TOR("TOR"), 
    UTAH("UTAH"),
    WSH("WSH");  
   
    private final String value;

    NBATeams(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
    
    public static NBATeams fromString(String text) {
    if (text != null) {
      for (NBATeams team : NBATeams.values()) {
        if (text.equalsIgnoreCase(team.value)) {
          return team;
        }
      }
    }
    return null;
  }
    
    
}
