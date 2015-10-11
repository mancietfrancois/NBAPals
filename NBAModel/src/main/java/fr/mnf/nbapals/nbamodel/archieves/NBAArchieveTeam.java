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

import fr.mnf.nbapals.nbamodel.NBATeam;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.RegularSeason;
import fr.mnf.nbapals.nbamodel.utils.Teams;

/**
 *
 * @author Francois
 */
public class NBAArchieveTeam extends NBATeam {
    
    private RegularSeason season;

    public NBAArchieveTeam(Teams teamId, Conference conference, Division division, 
            int wins, int loss, int confWins, int confLoss, int confRanking, 
            int divWins, int divLoss, int divRanking, RegularSeason season) {
        super(teamId, conference, division, wins, loss, confWins, confLoss, 
                confRanking, divWins, divLoss, divRanking);
        this.season = season;
    }

    public NBAArchieveTeam(NBATeam aTeam, RegularSeason season) {
        this(aTeam.getTeamId(), aTeam.getConference(), aTeam.getDivision(), aTeam.getWins(), 
                aTeam.getLoss(), aTeam.getConfWins(), aTeam.getConfLoss(), aTeam.getConfRanking(),
                aTeam.getDivWins(), aTeam.getDivLoss(), aTeam.getDivRanking(), season);
    }

    public RegularSeason getSeason() {
        return season;
    }
    
}
