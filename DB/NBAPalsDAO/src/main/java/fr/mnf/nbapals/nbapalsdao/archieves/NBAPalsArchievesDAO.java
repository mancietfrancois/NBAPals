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
package fr.mnf.nbapals.nbapalsdao.archieves;

import fr.mnf.nbapals.nbamodel.NBA;
import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.NBATeam;
import fr.mnf.nbapals.nbamodel.archieves.NBAArchieveGame;
import fr.mnf.nbapals.nbamodel.archieves.NBAArchieveTeam;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
import fr.mnf.nbapals.nbamodel.utils.RegularSeason;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author François_2
 */
public class NBAPalsArchievesDAO {

    private static final String DB_NAME = "nbapals_archieves";
    private static final String DB_CLASS = "com.mysql.jdbc.Driver";
    private static final String TABLE_GAMES_NAME = "games";
    private static final String TABLE_TEAMS_NAME = "nba_team";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("MYSQL_SERVER_PASSWORD");

    /**
     * Method to create DB Connection
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(DB_CLASS);
            con = DriverManager.getConnection(DB_URL,
                    DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            throw e;
        } finally {
            return con;
        }
    }

    public static List<NBAArchieveTeam> getNBAArchieveTeams() {
        Connection dbConn = null;
        List<NBAArchieveTeam> teams = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM " + TABLE_TEAMS_NAME + "";
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                teams = new ArrayList<>();
                while (results.next()) {
                    teams.add(new NBAArchieveTeam(
                            Teams.fromString(results.getString("shortname")),
                            Conference.fromString(results.getString("conf")),
                            Division.fromString(results.getString("division")),
                            Integer.parseInt(results.getString("wins")),
                            Integer.parseInt(results.getString("loss")),
                            Integer.parseInt(results.getString("wins_conf")),
                            Integer.parseInt(results.getString("loss_conf")),
                            Integer.parseInt(results.getString("conf_ranking")),
                            Integer.parseInt(results.getString("wins_div")),
                            Integer.parseInt(results.getString("loss_div")),
                            Integer.parseInt(results.getString("div_ranking")),
                            RegularSeason.fromString(results.getString("regular_season"))
                    ));

                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsArchievesDAO.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsArchievesDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return teams;
    }

    public static boolean insertNBACalendar(List<NBAGamesDay> calendar) {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            dbConn = createConnection();
            for (NBAGamesDay j : calendar) {
                List<NBAGame> games = j.getGames();
                for (NBAGame game : games) {
                    NBAArchieveGame anArchieveGame = (NBAArchieveGame) game;
                    if (!insertNBAArchieveGame(anArchieveGame, dbConn)) {
                        System.err.println("Error inserting values :" + anArchieveGame);
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                    insertStatus = true;
                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsArchievesDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertStatus;
    }
    
    public static boolean insertNBA(NBA nba, RegularSeason season) {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            dbConn = createConnection();
            for (NBATeam aTeam : nba.getNBATeams()) {
                NBAArchieveTeam anArchieveTeam = new NBAArchieveTeam(aTeam, season);
                insertNBAArchieveTeam(anArchieveTeam, dbConn);
            }
            insertNBACalendar(nba.getNbaGames());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                    insertStatus = true;
                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsArchievesDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertStatus;
    }

    public static boolean insertNBAArchieveGame(NBAArchieveGame game, Connection dbConn) {
        boolean insertStatus = false;
        try {
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO " + DB_NAME + "."
                    + TABLE_GAMES_NAME + " VALUES ('"
                    + game.getId() + "', '"
                    + game.getTeamHome().toString() + "', '"
                    + game.getTeamAway().toString() + "', "
                    + game.getHomeScore() + ", "
                    + game.getAwayScore() + ", '"
                    + game.getDateUs() + "', '"
                    + game.getStatus().toString() + "', '"
                    + game.getSeason().getValue() + "');";

            System.out.println(query);
            int records = stmt.executeUpdate(query);
            // System.out.println(records);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsArchievesDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }
    
    public static boolean insertNBAArchieveTeam(NBAArchieveTeam aTeam, Connection dbConn) {
        boolean insertStatus = false;
        try {
            System.out.println("Regular Season Value : " + aTeam.getSeason().getValue());
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO " + DB_NAME + "."
                    + TABLE_TEAMS_NAME + " VALUES ('"
                    + aTeam.getTeamId().getCity() + "', '"
                    + aTeam.getTeamId().getName()+ "', '"
                    + aTeam.getTeamId().getShortname() + "', '"
                    + aTeam.getConference() + "', '"
                    + aTeam.getDivision() + "', "
                    + aTeam.getWins() + ", "
                    + aTeam.getLoss() + ", "
                    + aTeam.getConfWins() + ", "
                    + aTeam.getConfLoss() + ", "
                    + aTeam.getDivWins() + ", "
                    + aTeam.getDivLoss() + ", "
                    + aTeam.getDivRanking() + ", "
                    + aTeam.getConfRanking() + ", '"
                    + aTeam.getSeason().getValue() + "');";

            // System.out.println(query);
            int records = stmt.executeUpdate(query);
            // System.out.println(records);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsArchievesDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }
    
    public static NBAGamesDay getNBAArchievesGamesDay(String date){
        Connection dbConn = null;
        NBAGamesDay gamesDay = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM " + TABLE_GAMES_NAME +" WHERE "
                        + "date_us LIKE " + date + "";
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                gamesDay = new NBAGamesDay();
                while (results.next()) {
                    gamesDay.addGame(new NBAArchieveGame(
                            results.getString("id"),
                            Teams.fromString(results.getString("team_home")), 
                            Teams.fromString(results.getString("team_away")), 
                            Integer.parseInt(results.getString("home_score")),
                            Integer.parseInt(results.getString("away_score")), 
                            results.getString("date_us"),
                            GameStatus.fromString(results.getString("status")), 
                            RegularSeason.fromString(results.getString("regular_season"))));
                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsArchievesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsArchievesDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return gamesDay;
    }
    
    public static List<NBAArchieveGame> getArchieveScores(NBATeam teamA, NBATeam teamB, 
            RegularSeason season) {
        Connection dbConn = null;
        List<NBAArchieveGame> games = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM "
                        + TABLE_GAMES_NAME + " WHERE ((team_home LIKE "
                        + teamA.getTeamId() + " AND team_away LIKE "
                        + teamB.getTeamId() + ") OR (team_home LIKE "
                        + teamB.getTeamId() + " AND team_away LIKE "
                        + teamA.getTeamId() +")) AND "
                        + "(status LIKE FINAL OR status LIKE "
                        + "FINAL/OT) AND regular_season LIKE "
                        + season.getValue();
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                games = new ArrayList<>();
                while (results.next()) {
                    games.add(new NBAArchieveGame(
                            results.getString("id"),
                            Teams.fromString(results.getString("team_home")), 
                            Teams.fromString(results.getString("team_away")), 
                            Integer.parseInt(results.getString("home_score")),
                            Integer.parseInt(results.getString("away_score")), 
                            results.getString("date_us"),
                            GameStatus.fromString(results.getString("status")), 
                            RegularSeason.fromString(results.getString("regular_season"))));
                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsArchievesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsArchievesDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return games;
    }
}