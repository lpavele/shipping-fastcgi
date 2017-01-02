package by.lpe.shipping.controller;

import by.lpe.shipping.CassandraInitializer;
import by.lpe.shipping.domain.Order;
import by.lpe.shipping.repository.OrderAccessor;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.gson.Gson;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.PathTemplateMatch;

/**
 * Created by pavel on 29.12.16.
 */
public class GetOrdersByCourierHandler implements HttpHandler {

    Mapper<Order> orderMapper;
    OrderAccessor orderAccessor;
    Gson gson;

    public GetOrdersByCourierHandler() {
        MappingManager manager = CassandraInitializer.getMappingManager();
        orderMapper = manager.mapper(Order.class);
        orderAccessor = manager.createAccessor(OrderAccessor.class);
        gson = new Gson();
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        PathTemplateMatch pathMatch = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);
        int courierId =Integer.valueOf(pathMatch.getParameters().get("courierId"));
        String result = gson.toJson(orderAccessor.findAllByCourier(courierId).all());

        exchange.getResponseSender().send(result);
    }
}
