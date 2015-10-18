/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbpals.jersey;

import fr.mnf.nbapals.nbapalsdao.bets.NBAPalsBetDAO;
import fr.mnf.nbpals.jersey.json.JSONMaker;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Francois
 */
@Path("/register")
public class Register {
    
    public static final int CODE_ERROR = -1;
    public static final int CODE_OK = 0;
    public static final int CODE_DUP_KEY = -2;
    public static final int CODE_CHAR_ERROR = -3;
    public static final int CODE_DB_UNREACHABLE = -4;
    
    @GET
    @Path("/doregister")
    @Produces(MediaType.APPLICATION_JSON)
    public String doLogin(@QueryParam("name") String name,
            @QueryParam("password") String password) {
        
        System.out.println("Name = " + name);
        System.out.println("Password = " + password);
        String response = "";
        int retCode = registerUser(name, password);
        System.out.println("Return Code : " + retCode);
        switch (retCode) {
            case CODE_CHAR_ERROR : {
                response = JSONMaker.constructJSON("register", false, 
                        "Your password or name contains forbidden characters.");
                break;
            }
            case CODE_DB_UNREACHABLE : {
                response = JSONMaker.constructJSON("register", false, 
                        "Database unreachable.");
                break;
            }
            case CODE_DUP_KEY: {
                response = JSONMaker.constructJSON("register", false, 
                        "This name already exists.");
                break;
            }
            case CODE_ERROR: {
                response = JSONMaker.constructJSON("register", false);
                break;
            }
            case CODE_OK: {
                response = JSONMaker.constructJSON("register", true);
                break;
            }
            default: {
                response = JSONMaker.constructJSON("register", false);
                break;
            }
        }
        return response;
    }
    
    public int registerUser(String name, String password) {
        if (name.isEmpty() || password.isEmpty()) {
            return CODE_ERROR;
        }
        try {
            if (NBAPalsBetDAO.registerUser(name, password)) {
                return CODE_OK;
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.out.println("User already registered. Primary key violation");
                return CODE_DUP_KEY;
            } else if (ex.getErrorCode() == 1064) {
                System.out.println("Bad character used.");
                return CODE_CHAR_ERROR;
            } else {
                System.err.println("Error Code : " + ex.getErrorCode() + " " 
                        + ex.getMessage());
                return CODE_ERROR;
            }
        } catch (Exception ex) {
            return CODE_DB_UNREACHABLE;
        }
        //Unreachable statement
        return CODE_ERROR;
    }
    
}
