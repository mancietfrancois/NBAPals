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

import fr.mnf.nbapals.nbamodel.NBA;
import static fr.mnf.nbapals.nbamodel.NBA.CONFERENCE_RANKING;
import static fr.mnf.nbapals.nbamodel.NBA.DIVISION_RANKING;
import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBATeam;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Francois
 */
public class NBARanker {

    private NBA nba;
    private boolean isRuleAlphabeticalUsed;

    public void rankNBA(NBA nba) {
        this.nba = nba;
        isRuleAlphabeticalUsed = false;
        updateAllConferenceRanking();
    }

    private void printDivisionRanking(List<NBATeam> teams) {
        teams = sortTeamsBasedOnRankings(teams, DIVISION_RANKING);
        System.out.println("---------- " + teams.get(0).getDivision()
                + " ----------");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("[" + i + "] " + teams.get(i).getTeamId()
                    + " Div Ranking : "
                    + teams.get(i).getDivRanking()
                    + " " + teams.get(i).getWins() + " - "
                    + teams.get(i).getLoss());
        }
        System.out.println("----------------------------");
    }

    private void printConferenceRanking(List<NBATeam> teams) {
        teams = sortTeamsBasedOnRankings(teams, CONFERENCE_RANKING);
        System.out.println("----------------- " + teams.get(0).getConference()
                + " -----------------");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(teams.get(i).getTeamId() + " Conf ranking : "
                    + teams.get(i).getConfRanking()
                    + " " + teams.get(i).getWins() + " - "
                    + teams.get(i).getLoss()
                    + " || Div ranking : " + teams.get(i).getDivRanking());
        }
        System.out.println("------------------------------------------");
    }

    private void printAllDivisionRankings() {
        printDivisionRanking(nba.getSoutheast());
        printDivisionRanking(nba.getAtlantic());
        printDivisionRanking(nba.getCentral());

        printDivisionRanking(nba.getSouthwest());
        printDivisionRanking(nba.getNorthwest());
        printDivisionRanking(nba.getPacific());
    }

    private void printAllConferenceRankings() {
        printConferenceRanking(nba.getEast());
        printConferenceRanking(nba.getWest());
    }

    /**
     * Sort a list of NBATeams (division) based on their winning pourcentage Do
     * not solve issues when winning percentage are the same
     *
     *
     */
    private List<NBATeam> sortTeamsBasedOnWinningPercentage(List<NBATeam> teams) {
        Collections.sort(teams, new Comparator<NBATeam>() {

            @Override
            public int compare(NBATeam team1, NBATeam team2) {

                float res1 = Score.getWinningPercentage(team1);
                float res2 = Score.getWinningPercentage(team2);
                if (res1 < res2) {
                    return 1;
                } else if (res1 > res2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return teams;
    }

    /**
     * Sort a list of NBATeams (division) based on their rankings Is normally
     * called when the issues (tie-breakers) are solved
     *
     *
     */
    private List<NBATeam> sortTeamsBasedOnRankings(List<NBATeam> teams, final int rankingType) {
        Collections.sort(teams, new Comparator<NBATeam>() {

            @Override
            public int compare(NBATeam team1, NBATeam team2) {
                int rankingA = 0;
                int rankingB = 0;
                switch (rankingType) {
                    case CONFERENCE_RANKING:
                        rankingA = team1.getConfRanking();
                        rankingB = team2.getConfRanking();
                        break;
                    case DIVISION_RANKING:
                        rankingA = team1.getDivRanking();
                        rankingB = team2.getDivRanking();
                        break;
                    default:
                        rankingA = team1.getConfRanking();
                        rankingB = team2.getConfRanking();
                        break;
                }

                if (rankingA > rankingB) {
                    return 1;
                } else if (rankingA < rankingB) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return teams;
    }

    /**
     * Update division ranking for a specific division
     *
     * @param division
     */
    private List<NBATeam> updateDivisionRanking(List<NBATeam> division) {
        division = sortTeamsBasedOnWinningPercentage(division);
        int rank = 1;
        int delta = 0;
        for (int i = 1; i < division.size(); i++) {
            float perct1 = Score.getWinningPercentage(division.get(i - 1));
            float perct2 = Score.getWinningPercentage(division.get(i));
            if (perct1 == perct2) {
                division.get(i - 1).setDivRanking(rank);
                division.get(i).setDivRanking(rank);
                delta++;
            } else if (perct1 > perct2) {
                division.get(i - 1).setDivRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                division.get(i).setDivRanking(rank);
            }
        }
        return division;
    }

    /**
     * Update rankings of all divisions
     */
    private void updateAllDivisionRankings() {
        updateDivisionRanking(nba.getSoutheast());
        updateDivisionRanking(nba.getCentral());
        updateDivisionRanking(nba.getAtlantic());
        updateDivisionRanking(nba.getSouthwest());
        updateDivisionRanking(nba.getNorthwest());
        updateDivisionRanking(nba.getPacific());
    }

    /**
     * Update conference ranking for a specific conference with its three
     * divisions
     *
     * @param conference
     * @param div1
     * @param div2
     * @param div3
     */
    private List<NBATeam> updateConferenceRanking(List<NBATeam> div1,
            List<NBATeam> div2, List<NBATeam> div3) {

        List<NBATeam> confSorted = new ArrayList<>();
        //We add the three division champs
        confSorted.add(div1.get(0));
        confSorted.add(div2.get(0));
        confSorted.add(div3.get(0));

        //We compute the best second record in the 12 remaining teams
        List<NBATeam> others = new ArrayList<>();
        for (int i = 1; i < div1.size(); i++) {
            others.add(div1.get(i));
            others.add(div2.get(i));
            others.add(div3.get(i));
        }
        others = sortTeamsBasedOnWinningPercentage(others);

        //We add this record to the confSorted list
        confSorted.add(others.get(0));
        //We sort the list again (in case the last record is better than a 
        // division champ record
        confSorted = sortTeamsBasedOnWinningPercentage(confSorted);

        //We add the remaining teams to the list
        for (int i = 1; i < others.size(); i++) {
            confSorted.add(others.get(i));
        }

        //We go through the list to set the conf ranking value
        int rank = 1;
        int delta = 0;
        for (int i = 1; i < confSorted.size(); i++) {
            float perct1 = Score.getWinningPercentage(confSorted.get(i - 1));
            float perct2 = Score.getWinningPercentage(confSorted.get(i));
            if (perct1 == perct2) {
                confSorted.get(i - 1).setConfRanking(rank);
                confSorted.get(i).setConfRanking(rank);
                delta++;
            } else {
                confSorted.get(i - 1).setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                confSorted.get(i).setConfRanking(rank);
            }
        }
        return confSorted;
    }

    /**
     * Update all the rankings (conference and division rankings) for the two
     * conferences and the six divisions. Solve the tie-breaker issues
     *
     */
    private void updateAllConferenceRanking() {

        updateAllDivisionRankings();
        updateConferenceRanking(nba.getSoutheast(), nba.getCentral(), nba.getAtlantic());
        updateConferenceRanking(nba.getSouthwest(), nba.getNorthwest(), nba.getPacific());

        printAllDivisionRankings();
        printAllConferenceRankings();

        System.out.println("Looking for tie-breakers in the western conf.");
        while (!checkNoTieBreakerRemains(nba.getWest())) {
            findTieBreaker(nba.getWest());
        }
        System.out.println("Looking for tie-breakers in the eastern conf now.");
        while (!checkNoTieBreakerRemains(nba.getEast())) {
            findTieBreaker(nba.getEast());
        }
        //We update the final div ranking based on the tie breakers solved
        if (!isRuleAlphabeticalUsed) {
            List<List<NBATeam>> nbaTeams = new ArrayList<>();
            nbaTeams.add(nba.getAtlantic());
            nbaTeams.add(nba.getCentral());
            nbaTeams.add(nba.getSoutheast());
            nbaTeams.add(nba.getSouthwest());
            nbaTeams.add(nba.getPacific());
            nbaTeams.add(nba.getNorthwest());
            for (List<NBATeam> aNBATeamDiv : nbaTeams) {
                for (int i = 1; i < aNBATeamDiv.size(); i++) {
                    NBATeam teamA = aNBATeamDiv.get(i - 1);
                    NBATeam teamB = aNBATeamDiv.get(i);
                    if (teamA.getDivRanking() == teamB.getDivRanking()) {
                        if (teamA.getConfRanking() < teamB.getConfRanking()) {
                            teamB.setDivRanking(teamB.getDivRanking() + 1);
                        } else {
                            teamA.setDivRanking(teamA.getDivRanking() + 1);
                        }
                    }
                }
            }
        }
        printAllConferenceRankings();
    }

    private void findTieBreaker(List<NBATeam> conference) {
        List<List<NBATeam>> tieBreakersToSolve = new ArrayList<>();
        List<NBATeam> tieBreakerGroup = new ArrayList<>();
        for (int i = 1; i < conference.size(); i++) {
            int rankA = conference.get(i - 1).getConfRanking();
            int rankB = conference.get(i).getConfRanking();

            //A tie breaker has been found
            if (rankA == rankB) {
                System.out.println("Two teams share the same winning percent : "
                        + conference.get(i - 1).getTeamId().getShortname()+ " ||| "
                        + conference.get(i).getTeamId().getShortname());
                //The list is empty, we add the two teams
                if (tieBreakerGroup.isEmpty()) {
                    System.out.println("No other teams share this record yet.\n");
                    tieBreakerGroup.add(conference.get(i - 1));
                    tieBreakerGroup.add(conference.get(i));
                } else {

                    //We compare with a previous record if the winning percent
                    //is the same
                    int sample = tieBreakerGroup.get(0).getConfRanking();
                    //Here there is a group of team with the same record
                    if (sample == rankA) {
                        System.out.println("A group already has this "
                                + "winning percent. We add it to the "
                                + "group.");
                        tieBreakerGroup.add(conference.get(i));
                    } else {
                        //A new tieBreaker group has to be created
                        System.out.println("No other teams share this record. "
                                + "The group has " + tieBreakerGroup.size()
                                + " teams.\n");
                        tieBreakersToSolve.add(tieBreakerGroup);
                        tieBreakerGroup = new ArrayList<>();
                        tieBreakerGroup.add(conference.get(i - 1));
                        tieBreakerGroup.add(conference.get(i));
                    }
                }
            }
        }
        //We add the last tieBreaker group found
        tieBreakersToSolve.add(tieBreakerGroup);
        System.out.println(tieBreakersToSolve.size() + " tie-breakers have "
                + "been found.");

        //We go through the list to solve the issues, group by group
        for (List<NBATeam> oneTieBreakerGroup : tieBreakersToSolve) {
            System.out.println("----------------");
            solveTieBreaker(oneTieBreakerGroup);
        }
    }

    private void solveTieBreaker(List<NBATeam> tieBreakerGroup) {
        /*
         (0) L'équipe championne de division est automatiquement devant
         (1) Meilleur pourcentage de victoires en confrontation directe 
         (2) Meilleur pourcentage de victoires contre les equipes de la meme 
         division (seulement si ex-aequo avec des equipes de la meme division).
         (3) Meilleur pourcentage de victoires contre les equipes de la meme
         conference.
         (4) Meilleur pourcentage de victoires contre les equipes eligibles 
         en playoffs dans la meme conference.
         (5) Meilleur pourcentage de victoires contre les equipes eligibles en 
         playoffs dans l’autre conference.
         (6) Meilleure difference de points marques et encaisses contre l’ensemble des autres equipes. 
         (7) Classement alphabétique (seulement pour les bilans nuls).
         */

        if (applyDivisionChampionRule(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Division Champion rule");
        } else if (applyDirectOppositionRule(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Direct Opposition rule");
        } else if (applyDivisionRule(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Division rule");
        } else if (applyConfRule(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Division Conf rule");
        } else if (applySameConfPlayoff(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Conference playoffable teams rule");
        } else if (applyOtherConfPlayoff(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Other Conference playoffable teams rule");
        } else if (applyGoalAverageRule(tieBreakerGroup)) {
            System.out.println("Tie Breaker solved applying Goal average rule");
        } else {
            applyAlphabeticalOrder(tieBreakerGroup);
            System.out.println("Tie Breaker solved applying Alphabetical order average rule");
        }
    }

    private boolean applyDivisionChampionRule(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Division Champ Rule...");
        //The group is bigger than 2, division title could not be applied
        if (tieBreakerGroup.size() != 2) {
            System.out.println("Too many teams to apply the divsion champion rule.");
            return false;
        }
        NBATeam teamA = tieBreakerGroup.get(0);
        NBATeam teamB = tieBreakerGroup.get(1);
        //Team A is champion of its division
        if (teamA.getDivRanking() == 1 && teamB.getDivRanking() > 1) {
            System.out.println("Team " + teamA.getTeamId()
                    + " are champions of their division (" + teamA.getDivision()
                    + "), whereas team " + teamB.getTeamId()
                    + " is not (division " + teamB.getDivision() + "). Team "
                    + teamA.getTeamId() + " has the tie-breaker against "
                    + teamB.getTeamId());
            teamB.setConfRanking(teamB.getConfRanking() + 1);
            return true;
            //TeamB is champion of its division
        } else if (teamB.getDivRanking() == 1 && teamA.getDivRanking() > 1) {
            System.out.println("Team " + teamB.getTeamId()
                    + " are champions of their division (" + teamB.getDivision()
                    + "), whereas team " + teamA.getTeamId()
                    + " is not (division " + teamA.getDivision() + "). Team "
                    + teamB.getTeamId() + " has the tie-breaker against "
                    + teamA.getTeamId());
            teamA.setConfRanking(teamA.getConfRanking() + 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean applyDirectOppositionRule(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the direct opposition Rule...");
        NBATeam teamA, teamB;
        List<NBAGame> games = new ArrayList<>();
        ArrayList<Record> percents = new ArrayList<>();
        for (int i = 0; i < tieBreakerGroup.size(); i++) {
            teamA = tieBreakerGroup.get(i);
            games.clear();
            for (int j = 0; j < tieBreakerGroup.size(); j++) {
                if (j != i) {
                    teamB = tieBreakerGroup.get(j);
                    System.out.println("Looking for results for games with "
                            + teamA.getTeamId() + " vs " + teamB.getTeamId());
                    games.addAll(NBAGameFinder.findGames(teamA.getTeamId(),
                            teamB.getTeamId(), nba.getNbaGames()));
                }
            }
            percents.add(new Record(Score.extractWinningPercentage(games,
                    teamA), teamA));
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private boolean applyDivisionRule(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Division Rule...");
        for (int i = 1; i < tieBreakerGroup.size(); i++) {
            if (tieBreakerGroup.get(i - 1).getDivision()
                    != tieBreakerGroup.get(i).getDivision()) {
                System.out.println("Teams don't belong to the same division.");
                return false;
            }
        }
        System.out.println("Teams belong to the same division.");
        ArrayList<Record> percents = new ArrayList<>();
        for (NBATeam team : tieBreakerGroup) {
            percents.add(new Record(Score.getDivWinningPercentage(team), team));
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private boolean applyConfRule(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Conference Rule...");
        ArrayList<Record> percents = new ArrayList<>();
        for (NBATeam team : tieBreakerGroup) {
            percents.add(new Record(Score.getConfWinningPercentage(team), team));
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private boolean applySameConfPlayoff(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Conference Playoffable Rule...");
        ArrayList<Record> percents = new ArrayList<>();
        Conference conference = tieBreakerGroup.get(0).getConference();
        List<NBATeam> playoffableTeams = nba.getPlayoffableTeams(conference);
        for (NBATeam team : tieBreakerGroup) {
            for (NBATeam otherTeam : playoffableTeams) {
                List<NBAGame> games = NBAGameFinder.findGames(team.getTeamId(), otherTeam.getTeamId(), nba.getNbaGames());
                percents.add(new Record(Score.extractWinningPercentage(games, team), team));
            }
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private boolean applyOtherConfPlayoff(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Other Conference Playoffable Rule...");
        if (tieBreakerGroup.size() > 2) {
            System.out.println("This tiebreaker group has too many team to apply this rules.");
            return false;
        }
        ArrayList<Record> percents = new ArrayList<>();
        Conference conference = tieBreakerGroup.get(0).getConference();
        if (conference == Conference.EAST) {
            conference = Conference.WEST;
        } else {
            conference = Conference.EAST;
        }
        List<NBATeam> playoffableTeams = nba.getPlayoffableTeams(conference);
        for (NBATeam team : tieBreakerGroup) {
            for (NBATeam otherTeam : playoffableTeams) {
                List<NBAGame> games = NBAGameFinder.findGames(team.getTeamId(), otherTeam.getTeamId(), nba.getNbaGames());
                percents.add(new Record(Score.extractWinningPercentage(games, team), team));
            }
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private boolean applyGoalAverageRule(List<NBATeam> tieBreakerGroup) {
        System.out.println("Trying to apply the Goal Average Rule...");
        ArrayList<Record> percents = new ArrayList<>();
        for (NBATeam team : tieBreakerGroup) {
            List<NBAGame> games = NBAGameFinder.findGames(team.getTeamId(), nba.getNbaGames());
            int pointsScores = 0;
            int pointsConceeded = 0;
            for (NBAGame game : games) {
                if (game.getTeamAway() == team.getTeamId()) {
                    pointsScores += game.getAwayScore();
                    pointsConceeded += game.getHomeScore();
                } else {
                    pointsScores += game.getHomeScore();
                    pointsConceeded += game.getAwayScore();
                }
            }
            percents.add(new Record(pointsScores - pointsConceeded, team));
        }
        //We sort the percents to create new groups
        Collections.sort(percents);
        //We update the group
        int rank = percents.get(0).getTeam().getConfRanking();
        int delta = 0;
        for (int i = 1; i < percents.size(); i++) {
            float perct1 = percents.get(i - 1).getPerct();
            float perct2 = percents.get(i).getPerct();
            if (perct1 == perct2) {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                percents.get(i).getTeam().setConfRanking(rank);
                delta++;
            } else {
                percents.get(i - 1).getTeam().setConfRanking(rank);
                rank = rank + delta + 1;
                delta = 0;
                percents.get(i).getTeam().setConfRanking(rank);
            }
        }
        return checkTieBreakerGroupSolved(tieBreakerGroup);
    }

    private void applyAlphabeticalOrder(List<NBATeam> tieBreakerGroup) {
        isRuleAlphabeticalUsed = true;
        System.out.println("Applying alphabetical order...");
        Collections.sort(tieBreakerGroup, new Comparator<NBATeam>() {

            @Override
            public int compare(NBATeam team1, NBATeam team2) {
                String nameA = team1.getTeamId().getShortname();
                String nameB = team2.getTeamId().getShortname();
                return nameA.compareTo(nameB);
            }
        });
        for (int i = 0; i < tieBreakerGroup.size(); i++) {
            tieBreakerGroup.get(i).setConfRanking(i);
        }
    }

    private boolean checkTieBreakerGroupSolved(List<NBATeam> tieBreakerGroup) {
        for (int i = 1; i < tieBreakerGroup.size(); i++) {
            if (tieBreakerGroup.get(i - 1).getConfRanking()
                    != tieBreakerGroup.get(i).getConfRanking()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNoTieBreakerRemains(List<NBATeam> teams) {
        for (int i = 1; i < teams.size(); i++) {
            System.out.println(teams.get(i - 1).getConfRanking()
                    + " " + teams.get(i).getConfRanking());
            if (teams.get(i - 1).getConfRanking()
                    == teams.get(i).getConfRanking()) {
                System.out.println("A tie breaker has been found!");
                return false;
            }
        }
        return true;
    }

}
