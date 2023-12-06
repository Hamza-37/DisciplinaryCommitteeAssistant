package com.example.disciplinarycommitteeassistant;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.androidnetworking.AndroidNetworking;
import com.example.disciplinarycommitteeassistant.databinding.ActivityCommitteeMemberDashboardBinding;
import com.google.android.material.navigation.NavigationView;

import okhttp3.OkHttpClient;

public class CommitteeMemberDashboardActivity extends AppCompatActivity {
    ActivityCommitteeMemberDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommitteeMemberDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.drawerToolbar;
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navView = binding.navDrawer;
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle menu item clicks here
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}