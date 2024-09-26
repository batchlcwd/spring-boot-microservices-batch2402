package com.service.video;

import com.service.video.documents.Video;
import com.service.video.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void run(String... args) throws Exception {
//        Video video = new Video();
//        video.setTitle("Learn Spring Introduction");
//        video.setDesc("This video is very informational video for spring boot learners");
//        video.setContentType("video/mp4");
//        video.setFilePath("./videos/first.mp4");
//
//        Video save = videoRepository.save(video);
//        System.out.println("Video saved with id: " + save.getId());

        videoRepository.findAll().forEach(System.out::println);

    }
}
