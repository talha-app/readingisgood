package com.readingisgood.repository;

import com.readingisgood.entity.Customer;
import com.readingisgood.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomerOrderByOrderDate(Customer customer, Pageable pageable);

    List<Order> findByCustomerAndOrderDateBetweenOrderByOrderDate(Customer customer, LocalDateTime orderDateStart, LocalDateTime orderDateEnd);

    @Query(value = "SELECT month, count(*) AS totalOrderCount, sum(totalBookCount) AS totalBookCount, sum(orderAmount) AS totalOrderAmount\n" +
            "FROM (\n" +
            "         SELECT\n" +
            "             L.order_id,\n" +
            "             DATE_TRUNC('month',O.order_date) AS  month,\n" +
            "             Count(O.order_id) AS totalBookCount,\n" +
            "             SUM(L.price) AS orderAmount\n" +
            "         FROM order_line L FULL OUTER JOIN orders O On L.order_id = O.order_id\n" +
            "         WHERE O.customer_id = :customerId\n" +
            "         GROUP BY L.order_id, DATE_TRUNC('month',order_date)\n" +
            "     ) mx\n" +
            "GROUP BY month;\n", nativeQuery = true)
    Object[][] getMonthlyStatistics(long customerId);
}
