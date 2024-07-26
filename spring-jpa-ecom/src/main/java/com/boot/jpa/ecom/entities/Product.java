package com.boot.jpa.ecom.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_products")
public class Product {

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_title", unique = true, nullable = false, length = 500)
    private String title;

    private String description;

    private double price;

    private boolean isLive = false;

//    @OneToOne
//    private   Category category;

    // columns
//    private  Date date;


    @ManyToOne
    private Category category;


    public Product(int productId, String title, String description, double price, boolean isLive) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isLive = isLive;
    }

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isLive=" + isLive +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
