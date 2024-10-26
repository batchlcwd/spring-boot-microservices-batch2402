package com.notification.service.functions;

import com.notification.service.dto.OrderInformation;
import org.apache.http.impl.conn.LoggingSessionOutputBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class NotificationService {


    @Bean
    //test :
    public Supplier<String> testing() {
        return () -> "This is testing";
    }

    @Bean
    public Function<String, String> sayHello() {
        return (message) -> "Hello how are  you  ? " + message;
    }

    @Bean
    public Function<OrderInformation, String> orderNotification() {

        //logic to send notification
        return orderInformation -> {
            //logic  to send notfication
            System.out.println("notification send...");
            sendNotification(orderInformation);
            System.out.println(orderInformation.getEmailId());
            System.out.println(orderInformation.getPrice());
            System.out.println(orderInformation.getUserPhone());
            System.out.println(orderInformation.getCreatedDate());
            return orderInformation.getOrderId();
        };
    }

    private void sendNotification(OrderInformation orderInformation) {

    }


}
