package com.example.disciplinarycommitteeassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.disciplinarycommitteeassistant.databinding.ActivityDirectorDashboardBinding;
import com.google.android.material.navigation.NavigationView;

public class DirectorDashboardActivity extends AppCompatActivity {
ActivityDirectorDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectorDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    }