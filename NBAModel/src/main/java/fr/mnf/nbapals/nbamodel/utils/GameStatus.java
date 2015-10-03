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
public enum GameStatus {

    FINAL("FINAL"),
    FINAL_OT("FINAL_OT"),
    POSTPONED("POSTPONED"),
    TO_BE_PLAYED("TO_BE_PLAYED");

    private final String value;

    GameStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static GameStatus fromString(String text) {
        if (text != null) {
            for (GameStatus status : GameStatus.values()) {
                if (text.equalsIgnoreCase(status.value)) {
                    return status;
                }
            }
            if (text.toUpperCase().matches("FINAL/([0-9]*)OT")) {
                return GameStatus.FINAL_OT;
            }
        }
        //In case there are several overtimes, text could be "Final/2OT" 
        //or "Final/3OT" ...

        return GameStatus.TO_BE_PLAYED;
    }
}
