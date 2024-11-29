package com.service.video.services;

import com.service.video.dto.VideoUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface VideoUploadService {

    VideoUploadResponse uploadVideo(String courseId, String videoId, MultipartFile file);

}
