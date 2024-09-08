package com.photos.oauth.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhotosController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/photos")
    public String photos(){
        return "photos";
    }

    @RequestMapping("/albums")
    public String albums(){
        return "albums";

    }

  

}
