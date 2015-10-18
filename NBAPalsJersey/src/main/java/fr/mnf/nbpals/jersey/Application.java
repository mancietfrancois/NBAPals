/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mnf.nbpals.jersey;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Francois
 */
@ApplicationPath("/ws")
public class Application extends ResourceConfig {

    public Application() {
        packages(this.getClass().getPackage().getName());
    }

}
