package by.lpe.shipping.controller;

import by.lpe.shipping.CassandraInitializer;
import by.lpe.shipping.domain.Order;
import by.lpe.shipping.repository.OrderAccessor;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("couriers")
public class CourierCtrl {


    Mapper<Order> orderMapper;
    OrderAccessor orderAccessor;
    Gson gson;

    public CourierCtrl() {
        MappingManager mappingManager = CassandraInitializer.getMappingManager();
        orderMapper = mappingManager.mapper(Order.class);
        orderAccessor = mappingManager.createAccessor(OrderAccessor.class);
        gson = new Gson();
    }

    @GET
    @Path("{courierId}/orders")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCourierOrders(@PathParam("courierId") int courierId) {
        return gson.toJson(orderAccessor.findAllByCourier(courierId).all());
    }

}
