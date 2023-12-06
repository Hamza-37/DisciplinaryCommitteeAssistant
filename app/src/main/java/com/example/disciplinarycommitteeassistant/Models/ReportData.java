package com.example.disciplinarycommitteeassistant.Models;
public class ReportData {
   String rb_id;
    String st_id;
    String description;
    String viol_date;
    String Category;

    public ReportData() {

    }

    public String getRb_id() {
        return rb_id;
    }

    public void setRb_id(String rb_id) {
        this.rb_id = rb_id;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViol_date() {
        return viol_date;
    }

    public void setViol_date(String viol_date) {
        this.viol_date = viol_date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }


    public ReportData(String rb_id, String st_id, String description, String viol_date, String category) {
        this.rb_id = rb_id;
        this.st_id = st_id;
        this.description = description;
        this.viol_date = viol_date;
        this.Category = category;
    }
}
