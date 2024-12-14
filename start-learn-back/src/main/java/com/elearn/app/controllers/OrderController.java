package com.elearn.app.controllers;

import com.elearn.app.dtos.OrderDto;
import com.elearn.app.dtos.OrderRequestDto;
import com.elearn.app.dtos.OrderVerifyDto;
import com.elearn.app.entities.Order;
import com.elearn.app.services.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create order
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequestDto dto
    ) throws RazorpayException {
        OrderDto order = orderService.createOrder(dto);
        return ResponseEntity.ok(order);
    }



    //verify payment
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody OrderVerifyDto paymentVerifyDto) throws RazorpayException {

        OrderDto order = orderService.verifyPayment(paymentVerifyDto.getRazorpayPaymentId(), paymentVerifyDto.getRazorpayOrderId(), paymentVerifyDto.getRazorpaySignature());
        if (order!=null) {
//            OrderDetail orderDetail = new OrderDetail();
//            Order order = repo.findByRazorpayOrderId(paymentVerifyDto.getRazorpayOrderId());
//            orderDetail.setCourseId(order.getCourseId());
//            orderDetail.setOrderStatus(true);
//            orderDetail.setUserId(order.getUserId());
//            orderDetail.setOrderPaymentStatus(true);
//            orderDetail.setEmail(order.getUserName());
//            orderCreatedNotification(new OrderDetail());
            return ResponseEntity.ok(order);

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment Not Verified");
        }

    }


    // Endpoint to get all orders
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }


    // Endpoint to get orders for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable String userId) {
        List<OrderDto> orders = orderService.getByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get orders for a user with a specific status
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserAndStatus(
            @PathVariable String userId,
            @PathVariable String status) {
        List<OrderDto> orders = orderService.getByUserAndStatus(userId, status);
        return ResponseEntity.ok(orders);
    }
}
