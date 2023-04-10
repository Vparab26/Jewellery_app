package com.example.kisan_suvidha.models;

public class Usermodel {
    String name;
    String email;
    String Password;
    String profileImg;

    public Usermodel() {
    }

    public Usermodel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        Password = password;

    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

