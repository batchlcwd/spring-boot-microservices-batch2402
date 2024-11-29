package com.order.service.order_service;

import com.order.service.order_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {


    Order findByRazorpayOrderId(String razorPayId);


}
