package com.example.order.config;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository) {
        return args -> {

            // Kiểm tra nếu orders chưa có dữ liệu thì tiến hành thêm
            if (orderRepository.count() == 0) {
                String[] statuses = {"PENDING", "SHIPPING", "DELIVERED", "CANCELLED"};

                for (int i = 1; i <= 20; i++) {
                    Order order = new Order();
                    order.setOrderCode("ORD-" + i);
                    order.setCustomerName("Customer " + i);
                    order.setTotalPrice(i * 100.0);
                    order.setStatus(statuses[(i - 1) % statuses.length]);
                    order.setCreatedAt(LocalDateTime.now().minusDays(20 - i));

                    orderRepository.save(order);
                }
                System.out.println("Đã tự động chèn thành công 20 sample!");
            }
        };
    }
}
