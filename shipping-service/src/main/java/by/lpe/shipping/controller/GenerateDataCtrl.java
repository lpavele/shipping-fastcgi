package by.lpe.shipping.controller;

import by.lpe.shipping.CassandraInitializer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by pavel on 2.1.17.
 */

@Path("generate")
public class GenerateDataCtrl {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        CassandraInitializer.generateData();
        return "Data generated!";
    }

}