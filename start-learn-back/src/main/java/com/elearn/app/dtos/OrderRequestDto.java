package com.elearn.app.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequestDto {
    private double amount;
    private String courseId;
    private String userId;
    private String userName;

    private  String address;
}
