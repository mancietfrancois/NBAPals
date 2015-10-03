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

import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.NBATeams;

/**
 *
 * @author François_2
 */
public class NBATeam {

    private final NBATeams teamId;
    private final Conference conference;
    private final Division division;

    private int wins;
    private int loss;

    private int confWins;
    private int confLoss;
    private int confRanking;

    private int divWins;
    private int divLoss;
    private int divRanking;
    
    private int playoffWins;
    private int playoffLoss;

    public NBATeam(NBATeams teamId, Conference conference, Division division,
            int wins, int loss, int confWins, int confLoss, int confRanking,
            int divWins, int divLoss, int divRanking, int playoffWins,
            int playoffLoss) {

        this.teamId = teamId;
        this.conference = conference;
        this.division = division;
        this.wins = wins;
        this.loss = loss;
        this.confWins = confWins;
        this.confLoss = confLoss;
        this.confRanking = confRanking;
        this.divWins = divWins;
        this.divLoss = divLoss;
        this.divRanking = divRanking;
        this.playoffLoss = playoffLoss;
        this.playoffWins = playoffWins;
    }

    public Conference getConference() {
        return conference;
    }

    public Division getDivision() {
        return division;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getConfWins() {
        return confWins;
    }

    public void setConfWins(int confWins) {
        this.confWins = confWins;
    }

    public int getConfLoss() {
        return confLoss;
    }

    public void setConfLoss(int confLoss) {
        this.confLoss = confLoss;
    }

    public int getDivWins() {
        return divWins;
    }

    public void setDivWins(int divWins) {
        this.divWins = divWins;
    }

    public int getDivLoss() {
        return divLoss;
    }

    public void setDivLoss(int divLoss) {
        this.divLoss = divLoss;
    }

    public NBATeams getTeamId() {
        return teamId;
    }

    public void addWin() {
        this.wins++;
    }

    public void addLoss() {
        this.loss++;
    }

    public void addConfWin() {
        this.confWins++;
    }

    public void addConfLoss() {
        this.confLoss++;
    }

    public void addDivWin() {
        this.divWins++;
    }

    public void addDivLoss() {
        this.divLoss++;
    }

    public int getConfRanking() {
        return confRanking;
    }

    public void setConfRanking(int confRanking) {
        this.confRanking = confRanking;
    }

    public int getDivRanking() {
        return divRanking;
    }

    public void setDivRanking(int divRanking) {
        this.divRanking = divRanking;
    }

    public int getPlayoffWins() {
        return playoffWins;
    }

    public void setPlayoffWins(int playoffWins) {
        this.playoffWins = playoffWins;
    }

    public int getPlayoffLoss() {
        return playoffLoss;
    }

    public void setPlayoffLoss(int playoffLoss) {
        this.playoffLoss = playoffLoss;
    }
}
