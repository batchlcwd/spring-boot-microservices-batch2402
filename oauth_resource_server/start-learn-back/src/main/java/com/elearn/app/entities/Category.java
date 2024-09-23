package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    private String id;

    @Column(nullable = true)
    private String title;

    @Column(name = "description")
    private String desc;

    private Date addedDate;

//

    @ManyToMany(
            mappedBy = "categoryList",
            cascade = CascadeType.ALL

    )
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {

        //list mein add kiya hai course
        courses.add(course);

        // course ki category list mein add kiya current category
        course.getCategoryList().add(this);


    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getCategoryList().remove(this);
    }


}


