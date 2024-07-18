package com.substring.jdbc.ecom.model;

public class ProductWithCategory {

    private int id;

    private String title;

    private String description;

    private String catTitle;

    public ProductWithCategory(int id, String title, String description, String catTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.catTitle = catTitle;
    }

    public ProductWithCategory() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    @Override
    public String toString() {
        return "ProductWithCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", catTitle='" + catTitle + '\'' +
                '}';
    }
}
