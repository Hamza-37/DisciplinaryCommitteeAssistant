package com.example.disciplinarycommitteeassistant.Models;


public class User {
    private int u_id;
    private String username;
    private String password;
    private String name;
    private String image;
    private String usertype;

    // Getters and setters...


    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public User(int u_id, String username, String password, String name, String image, String usertype) {
        this.u_id = u_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.image = image;
        this.usertype = usertype;
    }
}