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
package fr.mnf.nbapals.webparser;

import fr.mnf.nbapals.webparser.dates.DatesUtils;
import fr.mnf.nbapals.webparser.exceptions.WebDownloaderException;
import fr.mnf.nbapals.webparser.utils.WebParserUtils;
import fr.mnf.nbapals.webparser.utils.NBAGameTemp;
import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.nodes.Document;

/**
 *
 * @author Francois
 */
public class WebParser {

    private Document doc;
    private JSONArray dataEvents;

    private NBAGamesDay gameDay;

    private String date;

    public WebParser(String date) {
        this.date = date;
    }

    public NBAGamesDay parse() {
        try {
            doc = WebDownloader.download(this.date);
            WebDataExtractor dataExtractor = new WebDataExtractor(doc);
            dataEvents = dataExtractor.extractDataEvents();
            this.gameDay = extractEvents();
            return gameDay;
        } catch (WebDownloaderException | JSONException | IOException ex) {
            Logger.getLogger(WebParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Go through a list of nodes containing all the data
     *
     * @param events
     * @return
     */
    private NBAGamesDay extractEvents() throws JSONException {
        NBAGamesDay journey = new NBAGamesDay();
        for (int i = 0; i < dataEvents.length(); i++) {
            JSONObject obj = dataEvents.getJSONObject(i);
            String startDate = obj.getString("date");

            obj = obj.getJSONArray("competitions").getJSONObject(0);

            System.out.println("=================================");
            GameStatus status = GameStatus.fromString(obj.getJSONObject("status")
                    .getJSONObject("type").getString("detail"));
            String dateUS = DatesUtils.convertUTCHour(startDate,
                    DatesUtils.ZONE_AMERICA).split(" ")[0];
            System.out.println("Date US : " + dateUS);
            String fullDateEU = DatesUtils.convertUTCHour(startDate,
                    DatesUtils.ZONE_EUROPA);
            String dateEU = fullDateEU.split(" ")[0];
            String timeEU = fullDateEU.split(" ")[1];
            System.out.println("Date EU : " + dateEU);
            System.out.println("Time EU : " + timeEU);
            System.out.println("Status : " + status);
            JSONArray competitors = obj.getJSONArray("competitors");
            NBAGameTemp gameTemp = extractGameFromCompetitorJSONObject(competitors);
            String id = dateUS + "_" + gameTemp.getHome().name() + "_" 
                    + gameTemp.getAway().name();
            
            NBAGame game = new NBAGame(id, gameTemp.getHome(), 
                    gameTemp.getAway(), gameTemp.getHomeScore(), 
                    gameTemp.getAwayScore(), dateUS, dateEU, timeEU, status, 
                    WebParserUtils.ESPN_BASE_URI + this.date);
            journey.addGame(game);
            System.out.println("=================================");
        }
        return journey;
    }

    private NBAGameTemp extractGameFromCompetitorJSONObject(JSONArray competitors) throws JSONException {
        NBAGameTemp tempGame = null;
        tempGame = new NBAGameTemp();
        for (int j = 0; j < competitors.length(); j++) {
            JSONObject competitor = competitors.getJSONObject(j);
            int score = competitor.getInt("score");
            String abbreviation = competitor.getJSONObject("team")
                    .getString("abbreviation");
            String homeAway = competitor.getString("homeAway");
            switch (homeAway) {
                case "home":
                    tempGame.setHomeScore(score);
                    tempGame.setHome(Teams.fromString(abbreviation));
                    break;
                case "away":
                    tempGame.setAwayScore(score);
                    tempGame.setAway(Teams.fromString(abbreviation));
                    break;
            }
            System.out.println("HomeAway : " + homeAway);
            System.out.println("Score : " + score);
            System.out.println("Abbreviation : " + Teams.fromString(abbreviation));
            System.out.println("--------------------------------");
        }
        return tempGame;
    }

    public NBAGamesDay getGameDay() {
        return gameDay;
    }

    public void setGameDay(NBAGamesDay gameDay) {
        this.gameDay = gameDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }   

}
