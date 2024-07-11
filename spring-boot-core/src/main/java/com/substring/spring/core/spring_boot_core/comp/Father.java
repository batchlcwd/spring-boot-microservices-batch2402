package com.substring.spring.core.spring_boot_core.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Father {

    @Autowired
    @Qualifier("student4")
    private Student student;

//    public Father(@Qualifier("student2") Student student) {
//        this.student = student;
//        System.out.println("creating father");
//    }

    public void useStudent() {
        System.out.println("using  student");
        student.study();
    }

}
