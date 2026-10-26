package com.example.order.repository;

import com.example.order.model.Order;
import com.example.order.model.dto.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);

    List<Order> findByCustomerNameContaining(String customerName);

    @Query("SELECT o FROM Order o WHERE o.totalPrice > (SELECT AVG(ord.totalPrice) FROM Order ord)")
    List<Order> findHighValueOrders();

    @Query("SELECT new com.example.order.model.dto.OrderSummary(o.orderCode, o.customerName, o.totalPrice) FROM Order o")
    Page<OrderSummary> findAllAndPagination(Pageable pageable);

    @Query("SELECT new com.example.order.model.dto.OrderSummary(o.orderCode, o.customerName, o.totalPrice) " +
            "FROM Order o WHERE (:status IS NULL OR o.status = :status) " +
            "AND (:minPrice IS NULL OR o.totalPrice >= :minPrice)")
    Page<OrderSummary> filterOrders(@Param("status") String status,
                                    @Param("minPrice") BigDecimal minPrice,
                                    Pageable pageable);
}