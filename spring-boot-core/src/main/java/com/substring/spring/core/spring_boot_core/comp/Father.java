package com.substring.spring.core.spring_boot_core.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Father {

//    @Autowired
//    @Qualifier("student4")
    private Student student;
    private Test test;


    public Father(Student student1
            , Test test12) {
        this.student = student1;
        this.test = test12;
        System.out.println("creating father");
    }

    public void useStudent() {
        System.out.println("using  student");
        student.study();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
