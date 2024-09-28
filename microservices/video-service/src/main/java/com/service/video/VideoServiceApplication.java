package com.service.video;

import com.service.video.repositories.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideoServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Autowired
    private VideoRepo videoRepository;

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
//
//        videoRepository.findAll().forEach(System.out::println);

    }
}
