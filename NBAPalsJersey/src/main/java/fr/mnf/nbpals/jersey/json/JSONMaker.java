/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbpals.jersey.json;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Francois
 */
public class JSONMaker {
    
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", status);
        } catch (JSONException e) {
        }
        return obj.toString();
    }
    
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @param message
     * @return
     */
    public static String constructJSON(String tag, boolean status, String message) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", status);
            obj.put("message", message);
        } catch (JSONException e) {
        }
        return obj.toString();
    }
    
}
