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
package fr.mnf.nbapals.nbapalsdao;

import fr.mnf.nbapals.nbamodel.NBA;
import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.NBATeam;
import fr.mnf.nbapals.nbamodel.utils.Conference;
import fr.mnf.nbapals.nbamodel.utils.Division;
import fr.mnf.nbapals.nbamodel.utils.GameStatus;
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
public class NBAPalsDAO {

    private static final String DB_NAME = "nbapals";
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

    public static List<NBATeam> getNBATeams() {
        Connection dbConn = null;
        List<NBATeam> teams = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM " + TABLE_TEAMS_NAME + "";
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                teams = new ArrayList<>();
                while (results.next()) {
                    teams.add(new NBATeam(
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
                            Integer.parseInt(results.getString("div_ranking"))));

                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsDAO.class.getName())
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
                    Logger.getLogger(NBAPalsDAO.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return teams;
    }

    public static List<NBAGamesDay> getMonthNBAGamesDay(String period) 
            throws SQLException, Exception {
        Connection dbConn = null;
        List<NBAGamesDay> gamesDayList = null;
        dbConn = createConnection();
        Statement stmt = dbConn.createStatement();
        String query = "SELECT * FROM " + TABLE_GAMES_NAME + " WHERE "
                + "date_us REGEXP '" + period + "';";
        System.out.println(query);
        ResultSet results = stmt.executeQuery(query);
        gamesDayList = new ArrayList<>();
        NBAGamesDay gamesDay = new NBAGamesDay();
        String date = "";
        while (results.next()) {
            NBAGame game = new NBAGame(
                    results.getString("id"),
                    Teams.fromString(results.getString("team_home")),
                    Teams.fromString(results.getString("team_away")),
                    Integer.parseInt(results.getString("home_score")),
                    Integer.parseInt(results.getString("away_score")),
                    results.getString("date_us"),
                    results.getString("date_eu"),
                    results.getString("time_eu"),
                    GameStatus.fromString(results.getString("status")),
                    results.getString("code_espn"));
            if (date.isEmpty()) {
                date = game.getDateUs();
                gamesDay.addGame(game);
                gamesDay.setDate(date);
            } else if (date.equals(game.getDateUs())) {
                gamesDay.addGame(game);
            } else {
                gamesDayList.add(gamesDay);
                gamesDay = new NBAGamesDay();
                gamesDay.addGame(game);                
                date = game.getDateUs();
                gamesDay.setDate(date);
            }
        }
        //don't forget to add the last day
        gamesDayList.add(gamesDay);
        if (dbConn != null) {
            dbConn.close();
        }
        return gamesDayList;
    }

    public static boolean insertNBACalendar(List<NBAGamesDay> calendar) {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            dbConn = createConnection();
            for (NBAGamesDay j : calendar) {
                for (NBAGame game : j.getGames()) {
                    if (!insertGame(game, dbConn)) {
                        System.err.println("Error inserting values :" + game);
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
                    Logger.getLogger(NBAPalsDAO.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertStatus;
    }

    public static boolean insertNBA(NBA nba) {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            dbConn = createConnection();
            for (NBATeam aTeam : nba.getNBATeams()) {
                insertNBATeam(aTeam, dbConn);
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
                    Logger.getLogger(NBAPalsDAO.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertStatus;
    }

    public static boolean updateTeamResults(List<NBATeam> teams) {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            dbConn = createConnection();
            for (NBATeam team : teams) {
                if (!updateTeamResult(team, dbConn)) {
                    System.err.println("Error updating values :" + team);
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
                    Logger.getLogger(NBAPalsDAO.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertStatus;
    }

    public static boolean insertGame(NBAGame game, Connection dbConn) {
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
                    + game.getEspnURL() + "', '"
                    + game.getDateEu() + "', '"
                    + game.getTime() + "');";

            System.out.println(query);
            int records = stmt.executeUpdate(query);
            // System.out.println(records);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsDAO.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }

    public static boolean insertNBATeam(NBATeam aTeam, Connection dbConn) {
        boolean insertStatus = false;
        try {
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO " + DB_NAME + "."
                    + TABLE_TEAMS_NAME + " VALUES ('"
                    + aTeam.getTeamId().getCity() + "', '"
                    + aTeam.getTeamId().getName() + "', '"
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
                    + aTeam.getConfRanking() + ");";

            // System.out.println(query);
            int records = stmt.executeUpdate(query);
            // System.out.println(records);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsDAO.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }

    private static boolean updateTeamResult(NBATeam team, Connection dbConn) {
        boolean insertStatus = false;
        try {
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE " + DB_NAME + "." + TABLE_TEAMS_NAME
                    + " SET wins = " + team.getWins()
                    + ", loss = " + team.getLoss()
                    + ", wins_conf = " + team.getConfWins()
                    + ", loss_conf = " + team.getConfLoss()
                    + ", wins_div = " + team.getDivWins()
                    + ", loss_div = " + team.getDivLoss()
                    + ", conf_ranking = " + team.getConfRanking()
                    + ", div_ranking = " + team.getDivRanking()
                    + " WHERE " + TABLE_TEAMS_NAME
                    + ".shortname = " + team.getTeamId().getShortname() + "";

            // System.out.println(query);
            int records = stmt.executeUpdate(query);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsDAO.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }

    public static NBAGamesDay getNBAGamesDay(String date) {
        Connection dbConn = null;
        NBAGamesDay gamesDay = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM " + TABLE_GAMES_NAME + " WHERE "
                        + "date_us LIKE " + date + "";
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                gamesDay = new NBAGamesDay();
                while (results.next()) {
                    gamesDay.addGame(new NBAGame(
                            results.getString("id"),
                            Teams.fromString(results.getString("team_home")),
                            Teams.fromString(results.getString("team_away")),
                            Integer.parseInt(results.getString("home_score")),
                            Integer.parseInt(results.getString("away_score")),
                            results.getString("date_us"),
                            results.getString("date_eu"),
                            results.getString("time_eu"),
                            GameStatus.fromString(results.getString("status")),
                            results.getString("code_espn")));

                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsDAO.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return gamesDay;
    }

    public static List<NBAGame> getScores(NBATeam teamA, NBATeam teamB) {
        Connection dbConn = null;
        List<NBAGame> games = null;
        try {
            dbConn = createConnection();
            try {
                Statement stmt = dbConn.createStatement();
                String query = "SELECT * FROM "
                        + TABLE_GAMES_NAME + " WHERE ((team_home LIKE "
                        + teamA.getTeamId() + " AND team_away LIKE "
                        + teamB.getTeamId() + ") OR (team_home LIKE "
                        + teamB.getTeamId() + " AND team_away LIKE "
                        + teamA.getTeamId() + ")) AND "
                        + "(status LIKE FINAL OR status LIKE "
                        + "FINAL/OT)";
                // System.out.println(query);
                ResultSet results = stmt.executeQuery(query);
                games = new ArrayList<>();
                while (results.next()) {
                    games.add(new NBAGame(
                            results.getString("id"),
                            Teams.fromString(results.getString("team_home")),
                            Teams.fromString(results.getString("team_away")),
                            Integer.parseInt(results.getString("home_score")),
                            Integer.parseInt(results.getString("away_score")),
                            results.getString("date_us"),
                            results.getString("date_eu"),
                            results.getString("time_eu"),
                            GameStatus.fromString(results.getString("status")),
                            results.getString("code_espn")));

                }
                // System.out.println(records);
                // When record is successfully inserted
            } catch (SQLException ex) {
                Logger.getLogger(NBAPalsDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(NBAPalsDAO.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return games;
    }
}
