package com.order.service.order_service.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NotifyService {

    @Bean
    public Consumer<String> consumeAck() {
        return (orderId) -> {
            updateOrder();
            System.out.println("Ack Notification Success :" + orderId);
        };
    }

    private void updateOrder() {
//        order database update logic
    }
}
