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

    @ManyToMany(mappedBy = "categoryList")
    private List<Course> courses=new ArrayList<>();


}


