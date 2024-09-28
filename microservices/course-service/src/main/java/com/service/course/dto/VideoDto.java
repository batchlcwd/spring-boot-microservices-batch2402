package com.service.course.dto;

import lombok.Data;

@Data
public class VideoDto {
    private String id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;
    private  String courseId;
}
