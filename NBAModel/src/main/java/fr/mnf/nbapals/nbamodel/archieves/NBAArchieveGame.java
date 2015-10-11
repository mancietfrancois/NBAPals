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
package fr.mnf.nbapals.nbamodel.archieves;

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.RegularSeason;
import fr.mnf.nbapals.nbamodel.utils.Teams;

/**
 *
 * @author Francois
 */
public class NBAArchieveGame extends NBAGame {
    
    private RegularSeason season;

    public NBAArchieveGame(String id, Teams teamHome, Teams teamAway, 
            int homeScore, int awayScore, String dateUs, GameStatus status, 
            RegularSeason season) {
        super(id, teamHome, teamAway, homeScore, awayScore, dateUs, "", "", 
                status, "");
        this.season = season;
    }
    
    public NBAArchieveGame(NBAGame game, RegularSeason season) {
        this(game.getId(), game.getTeamHome(), game.getTeamAway(), 
                game.getHomeScore(), game.getAwayScore(), game.getDateUs(), 
                game.getStatus(), season);
    }

    public RegularSeason getSeason() {
        return season;
    }

}
