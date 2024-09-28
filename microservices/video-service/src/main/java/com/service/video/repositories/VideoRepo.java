package com.service.video.repositories;

import com.service.video.documents.Video;
import com.service.video.dto.VideoDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoRepo extends MongoRepository<Video, String> {
    List<Video> findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(String keyword, String desc);

    List<Video> findByCourseId(String courseId);
}
