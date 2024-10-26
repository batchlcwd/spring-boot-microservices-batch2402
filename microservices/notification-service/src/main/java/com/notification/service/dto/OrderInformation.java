package com.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformation {
    private String orderId;

    private Date createdDate;

    private String price;
    //    ....... any information
    private String emailId;

    private String userPhone;
}
