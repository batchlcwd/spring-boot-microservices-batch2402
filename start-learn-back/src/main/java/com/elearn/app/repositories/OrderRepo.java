package com.elearn.app.repositories;

import com.elearn.app.entities.Order;
import com.elearn.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByRazorpayOrderId(String razorpayId);

    List<Order> findByUser(User user);

    List<Order> findByUserAndPmtStatus(User user, String status);




}


