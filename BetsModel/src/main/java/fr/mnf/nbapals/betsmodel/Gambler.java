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
public class Gambler {
    
    private String name;
    private String password;
    private int id;
    
    private List<Integer> groups;

    public Gambler(String name, String password, int id, List<Integer> groups) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }
    
    
    
}
