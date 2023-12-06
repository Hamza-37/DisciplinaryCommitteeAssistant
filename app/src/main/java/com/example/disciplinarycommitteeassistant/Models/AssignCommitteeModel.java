package com.example.disciplinarycommitteeassistant.Models;

public class AssignCommitteeModel
{
    public int com_id;
    public int t_id;

    public AssignCommitteeModel(int com_id, int t_id) {
        this.com_id = com_id;
        this.t_id = t_id;
    }
    public AssignCommitteeModel() {
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }
}
