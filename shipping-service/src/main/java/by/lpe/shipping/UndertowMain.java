package by.lpe.shipping;

import by.lpe.shipping.controller.GetOrdersByCourierHandler;
import by.lpe.shipping.controller.HelloHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;

public class UndertowMain {

    public static void main(String[] args) {

        CassandraInitializer.init();

        Undertow server = Undertow.builder()
                .addHttpListener(80, "0.0.0.0")
                .setHandler(
                        Handlers.pathTemplate()
                                .add("/hello", new HelloHandler())
                                .add("/couriers/{courierId}/orders", new GetOrdersByCourierHandler())
                )
                .build();
        server.start();
        System.out.println("Undertow app started.");
    }
}
