/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbapals.betsmodel;

import java.util.List;

/**
 *
 * @author Francois
 */
public class GamblerGroup {
    
    private int id;
    private List<Gambler> gamblers;

    public GamblerGroup(int id, List<Gambler> gamblers) {
        this.id = id;
        this.gamblers = gamblers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void setGamblers(List<Gambler> gamblers) {
        this.gamblers = gamblers;
    }
    
    
}
