package com.example.disciplinarycommitteeassistant;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.disciplinarycommitteeassistant.Interfaces.Api;
import com.example.disciplinarycommitteeassistant.Interfaces.RetrofitClient;
import com.example.disciplinarycommitteeassistant.Models.AddNewCategoryModel;
import com.example.disciplinarycommitteeassistant.Models.FacultyMembersModel;
import com.example.disciplinarycommitteeassistant.Models.User;
import com.example.disciplinarycommitteeassistant.databinding.ActivityCreateCommitteeBinding;
import com.example.tobibur.printarray.PrintArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateCommitteeActivity extends AppCompatActivity {
    ActivityCreateCommitteeBinding binding;

    Spinner SpinSelectCategory;
    Spinner mSpinSelectCommitteeMember1;
    ListView mListViewCommittee;
    ArrayList<String> mCommitteeMembers;
    ArrayAdapter<String> mCommitteeMembersAdapter;
    Api api;
    public ArrayList<FacultyMembersModel> facultyMembersModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidNetworking.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        binding = ActivityCreateCommitteeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AndroidNetworking.initialize(getApplicationContext());
        String addcat = binding.addnewcategoryEditText.getText().toString();
        binding.BtnCreateCommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AndroidNetworking.post("http://92.168.43.97/DCA-FYP-API/api/Server/AssignCommittee/")
                       .addBodyParameter("u_id", String.valueOf(selectedFacultyMembers))
                       .addBodyParameter("com_id", String.valueOf(SpinSelectCategory))
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if(response!=null)
                                {
                                    Toast.makeText(getApplicationContext(),"Created Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                Toast.makeText(getApplicationContext(),"Not Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        binding.btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addcat = binding.addnewcategoryEditText.getText().toString();
                Api api = RetrofitClient.getInstance().getMyApi();
                Call<String> call = api.addcategory(addcat);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            if (response.isSuccessful()) {
                                String res = response.body();
                                Toast.makeText(getApplicationContext(), "" + res, Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(),"" +response.code(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        // Handle failure
                    }
                });
                SpinSelectCategory = binding.SpinSelectCategory;
                Api api1 = RetrofitClient.getInstance().getMyApi();
                Call<List<String>> call1 = api.getcategory();
                call1.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<String> titles = response.body();
                            titles.add(addcat);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateCommitteeActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, titles);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SpinSelectCategory.setAdapter(adapter);
                            //int newPosition = titles.indexOf(addcat);
                            //SpinSelectCategory.setSelection(newPosition);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Failed to retrieve categories", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Toast.makeText(CreateCommitteeActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        SpinSelectCategory = binding.SpinSelectCategory;
        Api api = RetrofitClient.getInstance().getMyApi();
        Call<List<String>> call = api.getcategory();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> titles = response.body();
                    if (addcat != null) {
                        titles.add(addcat);
                    }
                    if (titles != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateCommitteeActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, titles);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpinSelectCategory.setAdapter(adapter);
                    }
                   // int newPosition = titles.indexOf(addcat);
                   // SpinSelectCategory.setSelection(newPosition);
                   // adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Failed to retrieve categories", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(CreateCommitteeActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnselectmembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_LONG).show();
                RetrofitClient client = RetrofitClient.getInstance();

                api.getmembers().enqueue(new Callback<ArrayList<FacultyMembersModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FacultyMembersModel>> call, Response<ArrayList<FacultyMembersModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            //     Toast.makeText(getApplicationContext(), "test: " + response.body(), Toast.LENGTH_LONG).show();

                            facultyMembersModels.addAll(response.body());
                            showFacultyMembersDialog(facultyMembersModels);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FacultyMembersModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        binding.BtnCreateCommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = null;
                AddNewCategoryModel category = null;
               /* api.assigncommittee(user.getU_id(),category.getCom_id());
                call.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error"+response.code(),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {

                    }
                });
         */
            }
        });
    }

    private CharSequence[] getFacultyMemberNames
            (ArrayList<FacultyMembersModel> facultyMembers) {
        CharSequence[] facultyMemberNames = new CharSequence[facultyMembers.size()];
        for (int i = 0; i < facultyMembers.size(); i++) {
            facultyMemberNames[i] = facultyMembers.get(i).getName();
        }
        return facultyMemberNames;
    }

    private ArrayList<FacultyMembersModel> selectedFacultyMembers; // Declare as a class variable

    private void showFacultyMembersDialog(ArrayList<FacultyMembersModel> facultyMembers) {
        try {

            boolean[] checkedItems = new boolean[facultyMembers.size()];

            String[] facultyMemberNames = new String[facultyMembers.size()];

            for (int i = 0; i < facultyMembers.size(); i++) {
                facultyMemberNames[i] = facultyMembers.get(i).getName();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Faculty Members");

            builder.setMultiChoiceItems(facultyMemberNames, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    checkedItems[which] = isChecked;
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedFacultyMembers = getSelectedFacultyMembers(facultyMembers, checkedItems);
                    // Call the function to display the selected faculty members
                    displaySelectedMembers(selectedFacultyMembers);
                }
            });

            builder.setNegativeButton("Cancel", null);
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    facultyMembers.clear();

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<FacultyMembersModel> getSelectedFacultyMembers(ArrayList<FacultyMembersModel> facultyMembers, boolean[] checkedItems) {
        ArrayList<FacultyMembersModel> selectedFacultyMembers = new ArrayList<>();

        for (int i = 0; i < facultyMembers.size(); i++) {
            if (checkedItems[i]) {
                selectedFacultyMembers.add(facultyMembers.get(i));
            }
        }

        return selectedFacultyMembers;
    }


    private void displaySelectedMembers(ArrayList<FacultyMembersModel> selectedFacultyMembers) {
        // Example: Update a list view with the names of the selected faculty members
        ListView listView = findViewById(R.id.ListViewCommittee); // Replace `R.id.listView` with the ID of your list view

        ArrayList<String> selectedNames = new ArrayList<>();
        for (FacultyMembersModel facultyMember : selectedFacultyMembers) {
            selectedNames.add(facultyMember.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedNames);
        listView.setAdapter(adapter);
    }
}

