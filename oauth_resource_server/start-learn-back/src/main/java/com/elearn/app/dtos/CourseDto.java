package com.elearn.app.dtos;

import com.elearn.app.entities.Category;
import com.elearn.app.entities.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    private  String categoryId;

    //    videos

    private List<VideoDto> videos = new ArrayList<>();

    //

    private List<CategoryDto> categoryList = new ArrayList<>();

    public String getBannerUrl() {

        return "http://localhost:8081/api/v1/courses/" + id + "/banners";

    }

}
