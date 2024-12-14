package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.OrderDto;
import com.elearn.app.dtos.OrderRequestDto;
import com.elearn.app.entities.Course;
import com.elearn.app.entities.Order;
import com.elearn.app.entities.User;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import com.elearn.app.repositories.OrderRepo;
import com.elearn.app.repositories.UserRepo;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private OrderRepo orderRepo;


    private CourseRepo courseRepo;

    private UserRepo userRepo;

    private ModelMapper modelMapper;
    private RazorpayClient client;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public OrderService(OrderRepo orderRepo, CourseRepo courseRepo, ModelMapper modelMapper, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;


    }

    public OrderDto createOrder(OrderRequestDto orderRequestDto) throws RazorpayException {


        Course course = courseRepo.findById(orderRequestDto.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
        User user = userRepo.findById(orderRequestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
                .course(course)
                .user(user)
                .address(orderRequestDto.getAddress())
                .userId(orderRequestDto.getUserId()).
                build();
        return modelMapper.map(orderRepo.save(order), OrderDto.class);

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

    public OrderDto verifyPayment(
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
            return modelMapper.map(this.updateOrder(razorpayOrderId, "PAID"), OrderDto.class);
        } else {
            //fail
            return null;
        }

    }


    //get all orders

    public List<OrderDto> getAllOrder() {
        return this.orderRepo.findAll().stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    // get user order

    public List<OrderDto> getByUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));
        return this.orderRepo.findByUser(user).stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    // find all order using status
    public List<OrderDto> getByUserAndStatus(String userId, String status) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));
        return this.orderRepo.findByUserAndPmtStatus(user, status).stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }



}

