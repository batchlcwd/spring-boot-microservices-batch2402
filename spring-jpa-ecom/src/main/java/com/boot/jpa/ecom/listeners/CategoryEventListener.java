package com.boot.jpa.ecom.listeners;

import com.boot.jpa.ecom.entities.Category;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class CategoryEventListener {
    @PrePersist
    public void beforeSave(Category category) {

        System.out.println("going to save " + category.getTitle());


        System.out.println("before persist");
    }

    @PostPersist
    public void postSave(Category category) {

        System.out.println(category);

        System.out.println("After persist");
    }
}
