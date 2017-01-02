package by.lpe.shipping.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloCtrl {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Shipping item -> Hello world!";
    }

}
