package com.example.disciplinarycommitteeassistant.Models;

import android.widget.Button;

public class PreviousReportsModel {
    String name;
    String arid_no;

    public PreviousReportsModel(String name, String arid_No) {
        this.name = name;
        this.arid_no = arid_No;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArid_no() {
        return arid_no;
    }

    public void setArid_no(String arid_no) {
        this.arid_no = arid_no;
    }
}
