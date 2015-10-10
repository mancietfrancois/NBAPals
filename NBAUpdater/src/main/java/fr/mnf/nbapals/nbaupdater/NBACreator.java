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
package fr.mnf.nbapals.nbaupdater;

import fr.mnf.nbapals.webparser.WebParser;
import fr.mnf.nbapals.webparser.utils.WebDateUtils;
import fr.mnf.nbapals.nbamodel.NBA;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.ranking.NBARanker;
import fr.mnf.nbapals.nbapalsdao.NBAPalsDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francois
 */
public class NBACreator {
    
    private NBA myNBA;
    
    public NBACreator() {
        myNBA = new NBA();
        myNBA.populate();
    }
    
    public void populate() {
        List<String> nbaDates = WebDateUtils.getEspnRegularSeasonDates();
        List<NBAGamesDay> gamesDay = new ArrayList<>();
        for (String date : nbaDates) {
            WebParser espnParser = new WebParser(date);
            espnParser.parse();
            gamesDay.add(espnParser.getGameDay());
        }
        myNBA.setNbaGames(gamesDay);
        NBARanker ranker = new NBARanker();
        ranker.rankNBA(myNBA);
    }
    
    public void insertIntoDBNBAPals() {
        NBAPalsDAO.insertNBA(myNBA);
    }
    
    public static void main(String [] args) {
        NBACreator creator = new NBACreator();
        creator.populate();
        creator.insertIntoDBNBAPals();
    }
    
    
}
