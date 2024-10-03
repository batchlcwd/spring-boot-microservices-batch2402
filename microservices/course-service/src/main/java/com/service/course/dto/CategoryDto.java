package com.service.course.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {
    private String id;



    private String title;


    private String desc;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm:ss a",timezone = "IST")
     private Date addedDate;

    private  String bannerImageUrl;
}
