package com.order.service.order_service.services;

import com.order.service.order_service.OrderDetail;
import com.order.service.order_service.OrderRepo;
import com.order.service.order_service.dto.OrderRequestDto;
import com.order.service.order_service.entities.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private OrderRepo orderRepo;

    private RazorpayClient client;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;


    }

    public Order createOrder(OrderRequestDto orderRequestDto) throws RazorpayException {

        client = new RazorpayClient(razorpayKey, razorpaySecret);

        //razorpay order id bhi create karenge
        JSONObject options = new JSONObject();
        options.put("amount", orderRequestDto.getAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());


        com.razorpay.Order razorpayOrder = client.orders.create(options);


        Order order = Order.builder()
                .razorpayOrderId(razorpayOrder.get("id"))
                .amount(orderRequestDto.getAmount() * 100)
                .pmtStatus("PENDING")
                .createdDate(LocalDate.now())
                .courseId(orderRequestDto.getCourseId())
                .userId(orderRequestDto.getUserId())
                .userName(orderRequestDto.getUserName()).
                build();
        return orderRepo.save(order);

    }

    //order update
    public Order updateOrder(String razorpayId, String status) {

        Order order = orderRepo.findByRazorpayOrderId(razorpayId);
        if (order != null) {
            order.setPmtStatus(status);
            Order save = orderRepo.save(order);
            return save;
        } else {
            return null;
        }

    }

    // verify payment from razorpay

    public boolean verifyPayment(
            String razorpayPaymentId,
            String razorpayOrderId,
            String razorpaySignature

    ) throws RazorpayException {
        client = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", razorpayOrderId);
        options.put("razorpay_payment_id", razorpayPaymentId);
        options.put("razorpay_signature", razorpaySignature);
        boolean isVerifed = Utils.verifyPaymentSignature(options, razorpaySecret);
        if (isVerifed) {
            //success
            this.updateOrder(razorpayOrderId, "PAID");
            return true;
        } else {
            //fail
            return false;
        }

    }


}
