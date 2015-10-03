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
package fr.mnf.nbapals.nbamodel;

import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.Teams;

/**
 *
 * @author François_2
 */
public class NBAPlayoffGame extends NBAGame {
    
    private int serieID;
    private int gameNumber;

    public NBAPlayoffGame(String id, Teams teamHome, Teams teamAway, int homeScore, 
            int awayScore, String dateUs, String dateEu, String time, 
            GameStatus status, String espnURL, int serieID, int gameNumber) {
        super(id, teamHome, teamAway, homeScore, awayScore, dateUs, dateEu, time, status, espnURL);
        this.serieID = serieID;
        this.gameNumber = gameNumber;
    }

    public int getSerieID() {
        return serieID;
    }

    public void setSerieID(int serieID) {
        this.serieID = serieID;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }    
}
