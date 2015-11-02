/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbpals.jersey;

import com.google.gson.Gson;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbapalsdao.NBAPalsDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Francois
 */
@Path("/games")
public class Games {
    
    @GET
    @Path("/getmonth")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMonth(@QueryParam("period") String period) {
        System.out.println("Period = " + period);
        List<NBAGamesDay> result = getMonthFromDB(period);
        Gson gson = new Gson();
        System.out.println(gson.toJson(result));
        return gson.toJson(result);
    }


    private List<NBAGamesDay> getMonthFromDB(String period) {
        List<NBAGamesDay> results = null;
        try {
            results = NBAPalsDAO.getMonthNBAGamesDay(period);
        } catch (SQLException ex) {
            //Do something?
        }catch (Exception ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    
}
