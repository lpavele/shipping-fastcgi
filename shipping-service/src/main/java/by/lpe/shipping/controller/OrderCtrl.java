package by.lpe.shipping.controller;

import by.lpe.shipping.CassandraInitializer;
import by.lpe.shipping.domain.Order;
import by.lpe.shipping.repository.OrderAccessor;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("orders")
public class OrderCtrl {

    Mapper<Order> orderMapper;
    OrderAccessor orderAccessor;

    public OrderCtrl() {
        MappingManager mappingManager = CassandraInitializer.getMappingManager();
        orderMapper = mappingManager.mapper(Order.class);
        orderAccessor = mappingManager.createAccessor(OrderAccessor.class);
    }

    @PUT
    @Path("{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order saveOrder(@PathParam("orderId") UUID orderId, Order newOrder) {
        Order order = orderMapper.get(orderId);
        if(order==null)
            throw new NotFoundException("order not found with id=" + orderId);
        newOrder.setOrderId(orderId);
        orderMapper.save(newOrder);
        return newOrder;
    }
}
