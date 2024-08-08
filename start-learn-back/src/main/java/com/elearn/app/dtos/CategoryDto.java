package com.elearn.app.dtos;

import com.elearn.app.entities.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.MemoryAddress;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Date addedDate;

//


}
