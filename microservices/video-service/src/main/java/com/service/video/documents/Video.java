package com.service.video.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Video
{
    @Id
    private String id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;

    private  String courseId;

}
