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
 * @author Francois
 */
public enum RegularSeason {
    
    RS2011("RS2011"), 
    RS2012("RS2012"), 
    RS2013("RS2013"), 
    RS2014("RS2014"), 
    RS2015("RS2015");
    
    private final String value;

    RegularSeason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RegularSeason fromString(String s) {
        if (s != null) {
            for (RegularSeason season : RegularSeason.values()) {
                if (s.equalsIgnoreCase(season.value)) {
                    return season;
                }
            }
        }
        return null;
    }
}
