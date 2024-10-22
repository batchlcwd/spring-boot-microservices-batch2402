package com.service.video.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.standard.InstantFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/info")
@RestController
public class InfoController {


    private Logger logger = LoggerFactory.getLogger(InstantFormatter.class);

    int count = 0;

    @GetMapping("/video")
    public ResponseEntity<String> getInfo() {
        count++;
        logger.info("Retry {}",count);
        if (count <= 3) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video service is undermaintains");
        }

        return ResponseEntity.ok("This is video information");
    }

}
