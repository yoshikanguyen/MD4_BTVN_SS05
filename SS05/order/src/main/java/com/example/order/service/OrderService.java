package com.example.order.service;

import com.example.order.model.Order;
import com.example.order.model.dto.OrderSummary;
import com.example.order.model.dto.PaginationResponse;
import com.example.order.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameContaining(customerName);
    }

    public List<Order> getAllOrdersSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return orderRepository.findAll(sort);
    }

    public Page<Order> getOrdersPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepository.findAll(pageable);
    }

    public List<Order> getHighValueOrders() {
        return orderRepository.findHighValueOrders();
    }

    public PaginationResponse<OrderSummary> findAllAndPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderSummary> orderPage = orderRepository.findAllAndPagination(pageable);

        return new PaginationResponse<>(
                orderPage.getContent(),
                orderPage.getTotalPages(),
                orderPage.getTotalElements(),
                orderPage.getNumber()
        );
    }

    public PaginationResponse<OrderSummary> filterOrders(String status, BigDecimal minPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderSummary> orderPage = orderRepository.filterOrders(status, minPrice, pageable);

        return new PaginationResponse<>(
                orderPage.getContent(),
                orderPage.getTotalPages(),
                orderPage.getTotalElements(),
                orderPage.getNumber()
        );
    }
}
