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

import java.util.List;

/**
 *
 * @author François_2
 */
public class Score {

    public static float getWinningPercentage(NBATeam team) {
        return (float) team.getWins() / (team.getLoss() + team.getWins())
                * 100;
    }
    
    public static float getDivWinningPercentage(NBATeam team) {
        return (float) team.getDivWins() / (team.getDivLoss() 
                + team.getDivWins()) * 100;
    }

    public static int getMaxRanking(NBATeam teamA, NBATeam teamB,
            int rankingType) {
        switch (rankingType) {
            case NBA.CONFERENCE_RANKING:
                return Math.min(teamA.getConfRanking(),
                        teamB.getConfRanking());
            case NBA.DIVISION_RANKING:
                return Math.min(teamA.getDivRanking(),
                        teamB.getDivRanking());
            default:
                return Math.min(teamA.getConfRanking(),
                        teamB.getConfRanking());
        }
    }
    
    public static int getMinRanking(NBATeam teamA, NBATeam teamB,
            int rankingType) {
        switch (rankingType) {
            case NBA.CONFERENCE_RANKING:
                return Math.max(teamA.getConfRanking(),
                teamB.getConfRanking());
            case NBA.DIVISION_RANKING:
                return Math.max(teamA.getDivRanking(),
                teamB.getDivRanking());
            default:
                return Math.max(teamA.getConfRanking(),
                teamB.getConfRanking());
        }
    }
    
    public static float extractWinningPercentage(List<NBAGame> games, NBATeam team) {
        int teamWins = 0;
        int teamLoss = 0;
        for (NBAGame game : games) {
            System.out.println(game.getHomeScore() + " - " + game.getAwayScore());
            if (game.getTeamHome() == team.getTeamId()
                    && game.getHomeScore() > game.getAwayScore()) {
                //TeamA win the game
                teamWins++;
            } else if (game.getTeamHome() == team.getTeamId()
                    && game.getHomeScore() < game.getAwayScore()) {
                //other team wins
                teamLoss++;
            } else if (game.getTeamHome() != team.getTeamId()
                    && game.getHomeScore() > game.getAwayScore()) {
                //other team wins
                teamLoss++;
            } else if (game.getTeamHome() != team.getTeamId()
                    && game.getHomeScore() < game.getAwayScore()) {
                //teamA wins
                teamWins++;
            }
        }
        return (float) teamWins / (teamWins + teamLoss) * 100;
    }

    public static float getConfWinningPercentage(NBATeam team) {
        return (float) team.getConfWins() / (team.getConfLoss() 
                + team.getConfWins()) * 100;
    }

}
