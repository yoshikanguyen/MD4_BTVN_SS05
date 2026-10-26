package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.model.dto.OrderSummary;
import com.example.order.model.dto.PaginationResponse;
import com.example.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/searchStatus")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam("status") String status) {
        return new ResponseEntity<>(orderService.getOrdersByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@RequestParam("customerName") String customerName) {
        return new ResponseEntity<>(orderService.getOrdersByCustomerName(customerName), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Order>> getAllOrdersSorted(
            @RequestParam(defaultValue = "totalPrice") String sortBy,
            @RequestParam(defaultValue = "asc") String dir) {
        return new ResponseEntity<>(orderService.getAllOrdersSorted(sortBy, dir), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<Order>> getOrdersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(orderService.getOrdersPaged(page, size), HttpStatus.OK);
    }

    @GetMapping("/high-value")
    public ResponseEntity<List<Order>> getHighValueOrders() {
        return new ResponseEntity<>(orderService.getHighValueOrders(), HttpStatus.OK);
    }

    @GetMapping("/findAllAndSearch")
    public ResponseEntity<PaginationResponse<OrderSummary>> findAllAndPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(orderService.findAllAndPagination(page, size), HttpStatus.OK);
    }

    // Lọc theo status, minPrice, phân trang
    @GetMapping("/filter")
    public ResponseEntity<PaginationResponse<OrderSummary>> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(orderService.filterOrders(status, minPrice, page, size), HttpStatus.OK);
    }
}
