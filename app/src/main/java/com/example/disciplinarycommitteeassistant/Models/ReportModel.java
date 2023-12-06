package com.example.disciplinarycommitteeassistant.Models;

import android.net.Uri;

public class ReportModel {
    public static int c_id;
    public static String rb_id;
    public static String st_id;
    public static String description;
    public static String viol_date;
    public static String Category;
    public static Uri image;

    public static int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public static String getRb_id() {
        return rb_id;
    }

    public void setRb_id(String rb_id) {
        this.rb_id = rb_id;
    }

    public static String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getViol_date() {
        return viol_date;
    }

    public void setViol_date(String viol_date) {
        this.viol_date = viol_date;
    }

    public static String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public static Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public ReportModel(int c_id, String rb_id, String st_id, String description, String viol_date, String category, Uri image) {
        this.c_id = c_id;
        this.rb_id = rb_id;
        this.st_id = st_id;
        this.description = description;
        this.viol_date = viol_date;
        this.Category = category;
        this.image = image;
    }
}


