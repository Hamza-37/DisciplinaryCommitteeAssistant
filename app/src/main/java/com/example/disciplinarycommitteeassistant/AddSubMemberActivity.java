package com.example.disciplinarycommitteeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSubMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_member);

         Spinner memberSpinner;
         ListView selectedMembersListView;
         Button appointButton;

        ArrayList<String> membersList;
        ArrayList<String> selectedMembersList;
        ArrayAdapter<String> spinnerAdapter;
        ArrayAdapter<String> listViewAdapter;

            // Initialize the Spinner, ListView, and Button
            memberSpinner = findViewById(R.id.memberSpinner);
            selectedMembersListView = findViewById(R.id.selectedMembersListView);
            appointButton = findViewById(R.id.appointButton);

            // Create a sample list of members
            membersList = new ArrayList<>();
            membersList.add("Dr Naseer");
            membersList.add("Dr Munir");
            membersList.add("Dr Saeed");
            membersList.add("Shahid Jamil");
        membersList.add("Zahid Ahmed");

            // Create an ArrayAdapter for the Spinner
            spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, membersList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            memberSpinner.setAdapter(spinnerAdapter);

            // Create an ArrayList to store the selected members
            selectedMembersList = new ArrayList<>();

            // Create an ArrayAdapter for the ListView
            listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedMembersList);
            selectedMembersListView.setAdapter(listViewAdapter);

            // Set a click listener for the Spinner
            memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedMember = membersList.get(position);
                    if (!selectedMembersList.contains(selectedMember)) {
                        selectedMembersList.add(selectedMember);
                        listViewAdapter.notifyDataSetChanged();
                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });

            // Set a click listener for the Appoint button
            appointButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform the appointing logic here
                    Toast.makeText(AddSubMemberActivity.this, "Appoint button clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }