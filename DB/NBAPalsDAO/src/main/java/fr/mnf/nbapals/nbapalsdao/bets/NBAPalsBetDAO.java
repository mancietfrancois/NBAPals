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
package fr.mnf.nbapals.nbapalsdao.bets;

import fr.mnf.nbapals.nbapalsdao.NBAPalsDAO;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author François_2
 */
public class NBAPalsBetDAO {

    private static final String DB_NAME = "nbapals";
    private static final String DB_CLASS = "com.mysql.jdbc.Driver";
    private static final String TABLE_BETS_NAME = "bets";
    private static final String TABLE_GAMBLER_NAME = "gambler";
    private static final String TABLE_GAMBLER_GROUP = "gambler_group";
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

    public static boolean registerUser(String name, String password) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = createConnection();
        Statement stmt = dbConn.createStatement();
        String hash;
        String salt;
        salt = PasswordEncryptionService.generateSalt();
        hash = PasswordEncryptionService.getEncryptedPassword(password, salt);
        String query = "INSERT INTO " + DB_NAME + "."
                + TABLE_GAMBLER_NAME + "(name, password, salt) VALUES ('"
                + name + "', '"
                + hash + "', '"
                + salt + "');";

        System.out.println(query);
        int records = stmt.executeUpdate(query);
        // System.out.println(records);
        // When record is successfully inserted
        if (records > 0) {
            insertStatus = true;
        }
        return insertStatus;
    }

    public static boolean checkGamblerExists(String name, String password)
            throws SQLException, Exception {
        Connection dbConn = createConnection();
        Statement stmt = dbConn.createStatement();
        String query = "SELECT * FROM " + DB_NAME + "."
                + TABLE_GAMBLER_NAME + " WHERE name LIKE '" + name + "';";
        System.out.println(query);
        ResultSet res = stmt.executeQuery(query);
        //Check if the result set is empty
        if (!res.isBeforeFirst()) {
            return false;
        }
        res.next();
        String hash = res.getString("password");
        String salt = res.getString("salt");
        System.out.println("Hash : " + hash);
        System.out.println("Salt : " + salt);
        if (hash == null || salt == null) {
            return false;
        }
        return PasswordEncryptionService.authenticate(password, hash, salt);
    }

    public static boolean registerGroup(String name, String password,
            Connection dbConn) {
        boolean insertStatus = false;
        try {
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO " + DB_NAME + "."
                    + TABLE_GAMBLER_GROUP + " ( name, password) VALUES ('"
                    + name + "', '"
                    + password + "');";

            System.out.println(query);
            int records = stmt.executeUpdate(query);
            // System.out.println(records);
            // When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NBAPalsDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return insertStatus;
    }
}
