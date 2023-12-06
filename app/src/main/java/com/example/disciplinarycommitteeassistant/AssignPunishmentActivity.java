package com.example.disciplinarycommitteeassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disciplinarycommitteeassistant.databinding.ActivityAssignPunishmentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssignPunishmentActivity extends AppCompatActivity {
    ActivityAssignPunishmentBinding binding;
    private MaterialDatePicker<Long> datePicker;
    private MaterialDatePicker<Long> datePicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignPunishmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rdFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rdFine.isChecked())
                {
                    binding.txtfine.setEnabled(true);
                    binding.spinassignpunishment.setEnabled(false);
                    binding.AddPunishment.setEnabled(false);
                }
            }
        });
        binding.rdcommunityservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rdcommunityservice.isChecked())
                {
                    binding.txtfine.setEnabled(false);
                    binding.spinassignpunishment.setEnabled(true);
                    binding.AddPunishment.setEnabled(true);
                }
            }
        });
        binding.rdboth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rdboth.isChecked())
                {
                    binding.txtfine.setEnabled(true);
                    binding.spinassignpunishment.setEnabled(true);
                    binding.AddPunishment.setEnabled(true);
                }

            }
        });
        // Create a MaterialDatePicker builder and set the title
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select date");
        datePicker = builder.build();

        MaterialDatePicker.Builder builder2 = MaterialDatePicker.Builder.datePicker();
        builder2.setTitleText("Select end date");
        datePicker2 = builder2.build();

        // Set a click listener on the date text input to show the date picker
        binding.datePicker.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // Set a listener to capture the selected date
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // Create a SimpleDateFormat instance to format the date
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                // Get the selected date as a Date object
                Date date = new Date(selection);
                // Format the date as a string and set it to the text input
                binding.datePicker.getEditText().setText(sdf.format(date));
            }
        });

        // Set a click listener on the end date text input to show the end date picker
        binding.datePicker1.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2.show(getSupportFragmentManager(), "datePicker2");
            }
        });

        // Set a listener to capture the selected end date
        datePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // Create a SimpleDateFormat instance to format the date
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                // Get the selected date as a Date object
                Date date = new Date(selection);
                // Format the date as a string and set it to the text input
                binding.datePicker1.getEditText().setText(sdf.format(date));
            }
        });

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Window Cleaning");
        spinnerArray.add("Dish Cleaning");
        spinnerArray.add("Watering Plants");
        spinnerArray.add("Dusting Labs");
        spinnerArray.add("Cleaning Lab Tables");
        spinnerArray.add("Door Cleaning");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = binding.spinassignpunishment;
        sItems.setAdapter(adapter);
    }
}
