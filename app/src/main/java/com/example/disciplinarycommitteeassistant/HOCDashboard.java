package com.example.disciplinarycommitteeassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.disciplinarycommitteeassistant.databinding.ActivityHocdashboardBinding;

public class HOCDashboard extends AppCompatActivity {
ActivityHocdashboardBinding binding;
CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHocdashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cardView=binding.CardCreateCommittee;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),CreateCommitteeActivity.class);
                startActivity(intent);
            }
        });
        binding.CardViewCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),CommitteeMemberDashboardActivity.class);
                startActivity(intent);
            }
        });
        binding.CardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.AddSubMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),AddSubMemberActivity.class);
                startActivity(intent);
            }
        });
        binding.CardViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ViewCommentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
