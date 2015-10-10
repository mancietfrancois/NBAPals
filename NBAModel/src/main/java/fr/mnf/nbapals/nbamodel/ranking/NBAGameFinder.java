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
package fr.mnf.nbapals.nbamodel.ranking;

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francois
 */
public class NBAGameFinder {
    
    public static List<NBAGame> findGames(Teams teamA, Teams teamB, List<NBAGamesDay> games) {
        List<NBAGame> results = new ArrayList<>();
        for(NBAGamesDay gameDay : games) {
            for (NBAGame aGame : gameDay.getGames()) {
                if (aGame.getStatus() == GameStatus.TO_BE_PLAYED) {
                    return results;
                }
                if ((aGame.getTeamHome() == teamA || aGame.getTeamAway() == teamA)
                        && (aGame.getTeamHome() == teamB || aGame.getTeamAway() == teamB)) {
                    results.add(aGame);
                }
            }
        }
        return results;
    }

    static List<NBAGame> findGames(Teams teamId, List<NBAGamesDay> nbaGames) {
        List<NBAGame> results = new ArrayList<>();
        for(NBAGamesDay gameDay : nbaGames) {
            for (NBAGame aGame : gameDay.getGames()) {
                if (aGame.getStatus() == GameStatus.TO_BE_PLAYED) {
                    return results;
                }
                if (aGame.getTeamHome() == teamId || aGame.getTeamAway() == teamId) {
                    results.add(aGame);
                }
            }
        }
        return results;
    }
    
}
