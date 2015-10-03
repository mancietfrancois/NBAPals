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

/**
 *
 * @author François_2
 */
public class Record implements Comparable<Record>{
    
    private float perct;
    private NBATeam team;

    public Record(float perct, NBATeam team) {
        this.perct = perct;
        this.team = team;
    }
    
    public float getPerct() {
        return perct;
    }

    public void setPerct(float perct) {
        this.perct = perct;
    }

    public NBATeam getTeam() {
        return team;
    }

    public void setTeam(NBATeam team) {
        this.team = team;
    }   
    
    @Override
    public int compareTo(Record o) {
        if (this.perct < o.perct) {
            
            return 1;
        } else if (this.perct > o.perct) {
            return -1;
        } else {
            return 0;
        }
    }
    
    
    
}
