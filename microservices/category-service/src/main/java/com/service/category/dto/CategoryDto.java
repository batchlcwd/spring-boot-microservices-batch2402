package com.service.category.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;


    @NotEmpty(message = "title is required !!")
    @Size(min = 3,max = 50,message = "title must be between 3 and 50 characters")
    private String title;

    @NotEmpty(message = "description required!!")
//    @Pattern(regexp = "")
    private String desc;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm:ss a",timezone = "IST")
    private Date addedDate;

    private  String bannerImageUrl;



}
