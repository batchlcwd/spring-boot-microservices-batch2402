package com.boot.jpa.ecom.models;

public class CategoryCreateRequest {


    private  String title;

    private  String description;

    private  boolean live;

    private  double price;

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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CategoryCreateRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isLive=" + live +
                ", price=" + price +
                '}';
    }
}
