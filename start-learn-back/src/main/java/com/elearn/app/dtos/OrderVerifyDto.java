package com.elearn.app.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVerifyDto {

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

}
