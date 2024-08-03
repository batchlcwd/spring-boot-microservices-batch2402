package com.boot.jpa.ecom.controller;


import com.boot.jpa.ecom.entities.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RestController = @Controller + @ResponseBody
//@Controller
@RestController
@RequestMapping("/pages")
public class PageController {

    @RequestMapping("/home")
//    @ResponseBody
    public List<String> home(){

        if(true)
        {
            throw new RuntimeException("home page runtime exception");

        }
        List<String> names=List.of("ankit","ravi","sanjay");
        return names;
    }

    @GetMapping("/courses")
//    @ResponseBody
    public Map<String, String> getFees(){
        Map<String,String> courses=new HashMap<>();
        courses.put("Spring Boot","10K");
        courses.put("Django","8K");
        courses.put("Spring AI","12K");

        return courses;
    }


//    going to create and return category

    @RequestMapping("/category")
    public Category category(){
        //category
        Category category = new Category();
        category.setId(315235);
        category.setTitle("Trending for this month");
        return category;
    }
}
