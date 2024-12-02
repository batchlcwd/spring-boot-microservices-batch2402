package com.elearn.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Video {

    @Id
    private String id;

    private  String title;

    @Column(name = "description",length = 1000)
    private  String desc;

    private  String filePath;

    private  String contentType;

    // add your choice of field

    @ManyToOne
    private  Course course;

}
