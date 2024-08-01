package com.boot.jpa.ecom.entities;

import com.boot.jpa.ecom.listeners.CategoryEventListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jpa_category")
@EntityListeners(CategoryEventListener.class)
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;


//    @OneToOne(mappedBy = "category")
//    private  Product product;


    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
//            orphanRemoval = true
    )
    private List<Product> productList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }



}/**/

