package com.example.disciplinarycommitteeassistant.Models;

public class CreateCommitteeModel {
    int cm_id;
    String committee_member_name;
    String category;
    String date;

    public CreateCommitteeModel(int id, String committee_member_name, String category, String date) {
        this.cm_id = id;
        this.committee_member_name = committee_member_name;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return cm_id;
    }

    public void setId(int id) {
        this.cm_id = cm_id;
    }

    public String getCommittee_member_name() {
        return committee_member_name;
    }

    public void setCommittee_member_name(String committee_member_name) {
        this.committee_member_name = committee_member_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
