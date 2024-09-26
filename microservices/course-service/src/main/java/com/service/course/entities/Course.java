package com.service.course.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String id;

    private String title;

    private String shortDesc;

    @Column(length = 2000)
    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    // add your fields

    //is image field
    private String banner;

    private  String bannerContentType;

    private String categoryId;


}
