package com.example.disciplinarycommitteeassistant.Models;

public class AddNewCategoryModel {
    public static int com_id;
    public String title;

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public AddNewCategoryModel(int id, String cat) {

        this.title = cat;
        this.com_id=id;
    }

    public AddNewCategoryModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String cat) {
        this.title = cat;
    }


}
