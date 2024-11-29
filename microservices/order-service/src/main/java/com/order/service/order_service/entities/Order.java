package com.order.service.order_service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="orders_mindup")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razorpayOrderId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String pmtStatus;

    private LocalDate createdDate;

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String userId;

    private String userName;

}
