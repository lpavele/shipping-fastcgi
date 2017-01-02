package by.lpe.shipping.controller;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.PathTemplateMatch;

import javax.ws.rs.core.MediaType;

/**
 * Created by pavel on 29.12.16.
 */
public class HelloHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, MediaType.TEXT_PLAIN);

        exchange.getResponseSender().send("Shipping item -> Hello world!");
    }
}