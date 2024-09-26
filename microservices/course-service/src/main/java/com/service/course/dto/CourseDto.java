package com.service.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {


    private String id;

    private String title;

    private String shortDesc;

    //    @JsonIgnore
    @JsonProperty("long_description")
    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    // add your fields

    private String banner;


    public String getBannerUrl() {
        return "http://localhost:9092/api/v1/courses/" + id + "/banners";

    }


    private String categoryId;

    private  CategoryDto categoryDto;

}
