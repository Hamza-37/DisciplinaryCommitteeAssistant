package com.example.disciplinarycommitteeassistant.Models;
public class FacultyMembersModel {
    public int u_id;
    public String username;

public String password;
public String usertype;
public String name;

    public FacultyMembersModel(int u_id, String username, String password, String usertype, String name) {
        this.u_id = u_id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.name = name;
    }
    public FacultyMembersModel() {
    }

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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
