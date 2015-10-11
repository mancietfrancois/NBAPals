/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.nbaupdater.archieves;

import fr.mnf.nbapals.nbamodel.NBA;
import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.archieves.NBAArchieveGame;
import fr.mnf.nbapals.nbamodel.archieves.NBAArchieveTeam;
import fr.mnf.nbapals.nbamodel.ranking.NBARanker;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.NBADates;
import fr.mnf.nbapals.nbamodel.utils.RegularSeason;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import fr.mnf.nbapals.nbapalsdao.NBAPalsDAO;
import fr.mnf.nbapals.nbapalsdao.archieves.NBAPalsArchievesDAO;
import fr.mnf.nbapals.nbaupdater.NBACreator;
import fr.mnf.nbapals.webparser.WebParser;
import fr.mnf.nbapals.webparser.utils.WebDateUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francois
 */
public class NBAArchieveCreator {
    
    private NBA myNBA;
    private String firstDay;
    private String lastDay;
    private String [] noGamesDay;
    private RegularSeason season;
    
    public NBAArchieveCreator(String firstDay, String lastDay, 
            String [] noGamesDay, RegularSeason season) {
        myNBA = new NBA();
        myNBA.populate();
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.noGamesDay = noGamesDay;
        this.season = season;
    }
    
    public void populate() {
        List<String> nbaDates = WebDateUtils.getEspnRegularSeasonDates(firstDay, 
                lastDay, noGamesDay);
        List<NBAGamesDay> gamesDay = new ArrayList<>();
        for (String date : nbaDates) {
            WebParser espnParser = new WebParser(date, noGamesDay);
            espnParser.parse();
            NBAGamesDay aGamesDay = espnParser.getGameDay();
            NBAGamesDay anArchieveGameDay = new NBAGamesDay();
            for (NBAGame aGame : aGamesDay.getGames()) {
                NBAArchieveGame game = new NBAArchieveGame(aGame, season);
                anArchieveGameDay.addGame(game);
            }
            gamesDay.add(anArchieveGameDay);
        }
        myNBA.setNbaGames(gamesDay);
        NBARanker ranker = new NBARanker();
        ranker.rankNBA(myNBA);
    }
    
    public void insertIntoDBNBAPals() {
        NBAPalsArchievesDAO.insertNBA(myNBA, season);
    }
    
    public static void main(String [] args) {
        //2010-2011
        NBAArchieveCreator creator = new NBAArchieveCreator(
                NBADates.REGULAR_SEASON_2010_2011_FIRST_DAY, 
                NBADates.REGULAR_SEASON_2010_2011_LAST_DAY, 
                NBADates.NO_GAMES_DAY_2010_2011, RegularSeason.RS2011);
        creator.populate();
        creator.insertIntoDBNBAPals();
        //2011-2012
        creator = new NBAArchieveCreator(
                NBADates.REGULAR_SEASON_2011_2012_FIRST_DAY, 
                NBADates.REGULAR_SEASON_2011_2012_LAST_DAY, 
                NBADates.NO_GAMES_DAY_2011_2012, RegularSeason.RS2012);
        creator.populate();
        creator.insertIntoDBNBAPals();
        //2012-2013
        creator = new NBAArchieveCreator(
                NBADates.REGULAR_SEASON_2012_2013_FIRST_DAY, 
                NBADates.REGULAR_SEASON_2012_2013_LAST_DAY, 
                NBADates.NO_GAMES_DAY_2012_2013, RegularSeason.RS2013);
        creator.populate();
        creator.insertIntoDBNBAPals();
        //2013-2014
        creator = new NBAArchieveCreator(
                NBADates.REGULAR_SEASON_2013_2014_FIRST_DAY, 
                NBADates.REGULAR_SEASON_2013_2014_LAST_DAY, 
                NBADates.NO_GAMES_DAY_2013_2014, RegularSeason.RS2014);
        creator.populate();
        creator.insertIntoDBNBAPals();
        //2014-2015
        creator = new NBAArchieveCreator(
                NBADates.REGULAR_SEASON_2014_2015_FIRST_DAY, 
                NBADates.REGULAR_SEASON_2014_2015_LAST_DAY, 
                NBADates.NO_GAMES_DAY_2014_2015, RegularSeason.RS2015);
        creator.populate();
        creator.insertIntoDBNBAPals();
    }
    
}
