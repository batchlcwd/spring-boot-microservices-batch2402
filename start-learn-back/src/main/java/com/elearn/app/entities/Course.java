package com.elearn.app.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
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

    private String banner;


    //    videos
    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    //
    @ManyToMany
    private  List<Category> categoryList=new ArrayList<>();


}
