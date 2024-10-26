package com.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    private String orderId;
    private  String email;
    private  String userId;
    private  String userPhone;
    private  boolean orderPaymentStatus=false;
    private  boolean orderStatus=false;
    private  String courseId;
    ///.. amount..

}
