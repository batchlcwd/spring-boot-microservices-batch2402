package com.mvc.example.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/about")
    public String about(Model model) {
        System.out.println("this is about page");
        model.addAttribute("name","Spring MVC Learning");
        return "about";
    }
}
