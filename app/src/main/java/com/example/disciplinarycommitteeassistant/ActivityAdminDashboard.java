package com.example.disciplinarycommitteeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.disciplinarycommitteeassistant.databinding.ActivityAdminDashboardBinding;

public class ActivityAdminDashboard extends AppCompatActivity {

ActivityAdminDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
binding.CardAddReport.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        Intent intent= new Intent(getApplicationContext(),ActivityReportForm.class);
        startActivity(intent);
    }
});
binding.cardviewRegister10.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(getApplicationContext(),Register10.class);
        startActivity(intent);
    }
});
    }
}