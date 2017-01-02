package by.lpe.shipping.repository;

import by.lpe.shipping.domain.Order;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.mapping.annotations.QueryParameters;

/**
 * Created by pavel on 29.12.16.
 */
@Accessor
public interface OrderAccessor {

    @Query("SELECT * FROM ks.orders")
    Result<Order> findAll();

    @Query("SELECT * FROM ks.orders WHERE courier_id=:courierId LIMIT 1000 ALLOW FILTERING")
    @QueryParameters(consistency = "ONE")
    Result<Order> findAllByCourier(@Param("courierId") int courierId);
}
