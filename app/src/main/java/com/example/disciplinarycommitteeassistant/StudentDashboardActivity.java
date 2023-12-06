package com.example.disciplinarycommitteeassistant;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.disciplinarycommitteeassistant.Fragments.AppealFragement;
import com.example.disciplinarycommitteeassistant.Fragments.CheckProgressFragment;
import com.example.disciplinarycommitteeassistant.Fragments.CheckPunishmentFragment;
import com.example.disciplinarycommitteeassistant.Fragments.CheckScheduleFragment;
import com.example.disciplinarycommitteeassistant.databinding.ActivityStudentDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class StudentDashboardActivity extends AppCompatActivity {
    ActivityStudentDashboardBinding binding;
    private NavController navController;
    BottomNavigationView bottomNavigationView;
    CheckPunishmentFragment checkPunishmentFragment = new CheckPunishmentFragment();
    CheckScheduleFragment checkScheduleFragment = new CheckScheduleFragment();
    AppealFragement appealFragement = new AppealFragement();
    CheckProgressFragment progressFragment= new CheckProgressFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.drawerToolbar1;
        DrawerLayout drawerLayout = binding.DrawerStudent;
        NavigationView navView = binding.navDrawerStudent;
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers, checkPunishmentFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_action_appeal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, appealFragement).commit();
                        return true;
                    case R.id.menu_check_schedule:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, checkScheduleFragment).commit();
                        return true;
                    case R.id.checkPunishment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, checkPunishmentFragment).commit();
                        return true;
                    case R.id.menu_action_checkprogress:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers,progressFragment).commit();
                }
                return false;
            }
        });
    }
}

