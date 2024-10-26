package com.notification.service.functions;

import com.notification.service.dto.OrderDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class OrderNotificationFunctions {

    @Bean
    public Function<OrderDetail, String> orderEventReceiver() {
        return (orderDetail -> {
            //process
            System.out.println("Sending notification to user");
            logicToSendEmailAndMessageToUser(orderDetail.getEmail(), orderDetail.getUserPhone());
            return orderDetail.getOrderId();
        });
    }

    private void logicToSendEmailAndMessageToUser(String email, String userPhone) {
        System.out.println("Sending Email to " + email);
        System.out.println("Sending Sms to " + userPhone);
        System.out.println("notification sent to user");
        System.out.println("------------------");
    }
}
