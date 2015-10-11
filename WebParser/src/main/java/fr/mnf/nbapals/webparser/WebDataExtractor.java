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
package fr.mnf.nbapals.webparser;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Francois
 */
public class WebDataExtractor {

    private Document doc;

    public WebDataExtractor(Document doc) {
        this.doc = doc;
    }

    public JSONArray extractDataEvents() throws JSONException {
        /*
         Data received : JSON embedded in HTML content.
         The array events contain the different games which are played
         .....
         <div ... class="scoreboards" ... data-data='{
         "events" = [
         {
         ...
         }, {
         ...
         }
         ]
         }
         */
        JSONObject jsonObj = null;
        Element headElement = doc.getElementsByTag("head").first();
        Element scriptElement = headElement.getElementsByTag("script").last();
        String jsonText = scriptElement.dataNodes().get(0).getWholeData();
//        for (Element element : scriptElements) {
//            for (DataNode node : element.dataNodes()) {
//                System.out.println(node.getWholeData());
//            }
//            System.out.println("-------------------");
//        }
//        Element e = scriptElements.get(scriptElements.size() - 1);
//        JSONObject jsonObj = null;
//         = e.text();
        jsonText = jsonText.split("window.espn.scoreboardData 	=")[1];
        jsonText = jsonText.replace(";if(!window.espn_ui.device.isMobile){window.espn.loadType = \"ready\"};", "");
        //System.out.println(jsonText);
        jsonObj = new JSONObject(jsonText);
        JSONArray events = jsonObj.getJSONArray("events");
        if (events.length() == 0) {
            throw new JSONException(
                    "The object does not contain an array \"events\".");
        }
        System.out.println("Number of events : " + events.length());
        return events;

    }

}
