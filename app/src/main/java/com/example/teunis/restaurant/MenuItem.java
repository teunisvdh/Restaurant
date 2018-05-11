package com.example.teunis.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {

    // MenuItem consists of strings name, description, imageUrl and category and double price
    public String name, description, imageUrl, category;
    public double price;


    public MenuItem(String name, String description, String imageUrl, String category, double price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
