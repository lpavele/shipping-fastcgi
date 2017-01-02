package by.lpe.shipping.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.UUID;

/**
 * Created by pavel on 29.12.16.
 */
@Table(keyspace = "ks", name = "orders",
        readConsistency = "ONE",
        writeConsistency = "ONE",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
public class Order {
    public enum State{
        NEW,ON_PROCESSING,FINISHED
    }
    @PartitionKey
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "restaraunt_id")
    private int restarauntId;
    @Column(name = "courier_id")
    private int courierId;
    private String state;

    public Order() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getRestarauntId() {
        return restarauntId;
    }

    public void setRestarauntId(int restarauntId) {
        this.restarauntId = restarauntId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String   toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restarauntId=" + restarauntId +
                ", courierId=" + courierId +
                ", state='" + state + '\'' +
                '}';
    }
}
