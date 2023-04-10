package com.example.kisan_suvidha.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String name;
    String description;
    String rating;
    String img_url;
    String type;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String rating, String img_url, String type, int price) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.img_url = img_url;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
