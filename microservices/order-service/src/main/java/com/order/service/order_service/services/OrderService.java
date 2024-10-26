package com.order.service.order_service.services;

import com.order.service.order_service.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    public OrderDetail createOrder(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(UUID.randomUUID().toString());
        orderDetail.setEmail("manish@gmail");
        orderDetail.setUserPhone("9839466732");
        orderDetail.setUserId("23525216");
        orderDetail.setCourseId(UUID.randomUUID().toString());
        // save this to database:
        //mysql-jpa,mongo-db
        //Assignment: for guys
        return orderDetail;

    }

}
