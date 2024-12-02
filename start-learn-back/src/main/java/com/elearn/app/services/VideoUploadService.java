package com.elearn.app.services;


import com.elearn.app.dtos.VideoUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface VideoUploadService {

    VideoUploadResponse uploadVideo(String courseId, String videoId, MultipartFile file);

}
