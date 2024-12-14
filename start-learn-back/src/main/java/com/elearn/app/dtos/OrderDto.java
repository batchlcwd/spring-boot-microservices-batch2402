package com.elearn.app.dtos;

import com.elearn.app.entities.Course;
import com.elearn.app.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String razorpayOrderId;
    private double amount;
    private String pmtStatus;
    private LocalDate createdDate;
    private CourseDto course;
    private String userId;
    private UserDto user;
    private  String address;

}
