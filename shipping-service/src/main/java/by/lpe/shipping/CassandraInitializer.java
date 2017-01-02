package by.lpe.shipping;

import by.lpe.shipping.domain.Order;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import java.util.Random;
import java.util.UUID;

/**
 * Created by pavel on 29.12.16.
 */
public class CassandraInitializer {

    static String[] CONTACT_POINTS = {"127.0.0.1"};
    static int PORT = 9042;

    private static Cluster cluster;

    private static Session session;

    private static MappingManager mappingManager;

    public static void init() {
        connect(CONTACT_POINTS, PORT);
        createSchema();
        mappingManager = new MappingManager(session);
//        generateData();
    }

    public static MappingManager getMappingManager(){
        return mappingManager;
    }

    public static void generateData(){
        Mapper<Order> orderMapper = mappingManager.mapper(Order.class);

        Random rand = new Random();
        for (int i = 1; i < 10; i++) {
            int v = rand.nextInt(10000);
            System.out.println(v);
            for (int j = 0; j < v; j++) {
                Order order = new Order();
                order.setRestarauntId(rand.nextInt(9)+1);
                order.setOrderId(UUID.randomUUID());
                order.setCourierId(i);
                order.setState(Order.State.values()[rand.nextInt(3)].name());
                orderMapper.save(order);
            }
        }
    }


    public static void connect(String[] contactPoints, int port) {

        cluster = Cluster.builder()
                .addContactPoints(contactPoints).withPort(port)
                .build();

        System.out.printf("Connected to cluster: %s%n", cluster.getMetadata().getClusterName());

        session = cluster.connect();
    }

    public static void createSchema() {

        session.execute("CREATE KEYSPACE IF NOT EXISTS ks WITH replication " +
                "= {'class':'SimpleStrategy', 'replication_factor':1};");

        session.execute(
                "CREATE TABLE IF NOT EXISTS ks.orders (" +
                        "order_id uuid PRIMARY KEY," +
                        "restaraunt_id int," +
                        "courier_id int," +
                        "start_date timestamp," +
                        "finish_date timestamp," +
                        "state text" +
                        ");");
    }

    public static void close() {
        session.close();
        cluster.close();
    }
}
