package com.order.service.order_service.controllers;


import com.order.service.order_service.OrderDetail;
import com.order.service.order_service.OrderRepo;
import com.order.service.order_service.dto.OrderRequestDto;
import com.order.service.order_service.dto.PaymentVerifyDto;
import com.order.service.order_service.entities.Order;
import com.order.service.order_service.services.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private OrderRepo repo;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequestDto dto
    ) throws RazorpayException {
        Order order = orderService.createOrder(dto);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerifyDto paymentVerifyDto) throws RazorpayException {

        boolean b = orderService.verifyPayment(paymentVerifyDto.getRazorpayPaymentId(), paymentVerifyDto.getRazorpayOrderId(), paymentVerifyDto.getRazorpaySignature());
        if (b) {
            OrderDetail orderDetail = new OrderDetail();
            Order order = repo.findByRazorpayOrderId(paymentVerifyDto.getRazorpayOrderId());
            orderDetail.setCourseId(order.getCourseId());
            orderDetail.setOrderStatus(true);
            orderDetail.setUserId(order.getUserId());
            orderDetail.setOrderPaymentStatus(true);
            orderDetail.setEmail(order.getUserName());
            orderCreatedNotification(new OrderDetail());
            return ResponseEntity.ok("Order Verified");

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment Not Verified");
        }

    }


    @Autowired
    private StreamBridge streamBridge;

    private void orderCreatedNotification(OrderDetail orderDetail) {
        //logic to send notification to notification service

        boolean send = streamBridge.send("orderCreatedEvent-out-0", orderDetail);
        if (send) {
            System.out.println("Order Success Event is successfully send to notification service");
        } else {
            System.out.println("Event fail:");
        }
    }
}
