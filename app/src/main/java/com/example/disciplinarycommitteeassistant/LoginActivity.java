package com.example.disciplinarycommitteeassistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.HttpResponse;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.disciplinarycommitteeassistant.Interfaces.Api;
import com.example.disciplinarycommitteeassistant.Interfaces.RetrofitClient;
import com.example.disciplinarycommitteeassistant.Models.User;
import com.example.disciplinarycommitteeassistant.databinding.ActivityLoginBinding;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.txtusername.getText().toString();
                String password = binding.txtpassword.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this,
                            "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                } else
                {
                    Api api=RetrofitClient.getInstance().getMyApi();
                    api.Login(userName,password).enqueue(new Callback<ArrayList<User>>() {
                        @Override
                        public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                            if (response.isSuccessful()&& response.body()!=null) {
                                ArrayList<User> users = response.body();
                                for(User user:users) {
                                    int userid=user.getU_id();
                                    String username=user.getUsername();
                                    String usertype=user.getUsertype();
                                    if(usertype.equals("Admin"))
                                    {
                                        Intent intent= new Intent(LoginActivity.this,ActivityAdminDashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(usertype.equals("Std"))
                                    {
                                        Intent intent= new Intent(LoginActivity.this,StudentDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(usertype.equals("Dir"))
                                    {
                                        Intent intent= new Intent(LoginActivity.this,DirectorDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(usertype.equals("FM"))
                                    {
                                        Intent intent= new Intent(LoginActivity.this,CommitteeMemberDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(usertype.equals("Hoc"))
                                    {
                                        Intent intent= new Intent(LoginActivity.this,HOCDashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }


                            else
                            {
                                Toast.makeText(LoginActivity.this,
                                        "No User Found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}