package com.elearn.app.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoDto
{
    private String id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  CourseDto course;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String courseId;

}
