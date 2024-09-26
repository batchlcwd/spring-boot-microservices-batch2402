package com.service.video.repositories;

import com.service.video.documents.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video,String> {
}
