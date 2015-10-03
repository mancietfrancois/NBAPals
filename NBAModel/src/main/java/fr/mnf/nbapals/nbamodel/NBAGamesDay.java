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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author François_2
 */
public class NBAGamesDay {
    
    private List<NBAGame> games;

    public NBAGamesDay() {
        this.games = new ArrayList<>();
    }

    public List<NBAGame> getGames() {
        return games;
    }

    public void setGames(List<NBAGame> games) {
        this.games = games;
    }
    
    public void addGame(NBAGame game) {
        this.games.add(game);
    }
    
    public void removeGame(NBAGame game) {
        this.games.remove(game);
    }
    
    
    
}
