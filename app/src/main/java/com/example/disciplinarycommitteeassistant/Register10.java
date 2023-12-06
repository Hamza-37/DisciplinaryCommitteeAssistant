package com.example.disciplinarycommitteeassistant;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.disciplinarycommitteeassistant.Adapters.Register10Adapter;
import com.example.disciplinarycommitteeassistant.Interfaces.Api;
import com.example.disciplinarycommitteeassistant.Interfaces.RetrofitClient;
import com.example.disciplinarycommitteeassistant.databinding.ActivityPreviousRecordBinding;
import com.example.disciplinarycommitteeassistant.databinding.ActivityRegister10Binding;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register10 extends AppCompatActivity {
ActivityRegister10Binding binding;
private RecyclerView recyclerView;
private ArrayList<Integer> integerArrayList;
Api api= RetrofitClient.getInstance().getMyApi();
public Spinner spinner;
private Register10Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegister10Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AndroidNetworking.initialize(getApplicationContext());

        spinner=findViewById(R.id.RegisterCategory);
        fetchCategories();

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= String.valueOf(findViewById(R.id.Hamza));
                TextView name1=findViewById(R.id.Hamza1);
                Api api=RetrofitClient.getInstance().getMyApi();
                Call<String>call=api.getregister10(name, String.valueOf(name1));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            String res=response.body();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Not Success",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        binding.btnSetthreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String val= binding.txtvalue.getText().toString();
                Api api=RetrofitClient.getInstance().getMyApi();
                Call<String>call=api.addthreshold(val);
                 call.enqueue(new Callback<String>() {
                     @Override
                     public void onResponse(Call<String> call, Response<String> response) {
                         if(response.isSuccessful())
                         {
                             Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_LONG).show();
                             String res = response.body();
                         }
                         else
                         {
                             Toast.makeText(getApplicationContext(),"" +response.code(), Toast.LENGTH_LONG).show();
                         }
                     }
                     @Override
                     public void onFailure(Call<String> call, Throwable t) {

                     }
                 });
            }
        });


//            binding.image1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getApplicationContext(), PreviousRecordActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private void fetchCategories() {
        Call<List<String>> call = api.getcategory();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> categories = response.body();

                    // Populate spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Register10.this,
                            android.R.layout.simple_spinner_item, categories);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                } else {
                    // Handle error response
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                // Handle network failure
            }
        });
    }
}
