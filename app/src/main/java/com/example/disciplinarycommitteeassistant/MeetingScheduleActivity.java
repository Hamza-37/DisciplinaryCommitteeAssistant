package com.example.disciplinarycommitteeassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disciplinarycommitteeassistant.databinding.ActivityMeetingScheduleBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.MaterialTimePicker.Builder;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeetingScheduleActivity extends AppCompatActivity {
    ActivityMeetingScheduleBinding binding;
    private MaterialDatePicker<Long> datePicker;
    private MaterialTimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create a MaterialDatePicker builder and set the title
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select date");
        datePicker = builder.build();

        // Create a MaterialTimePicker builder and set the title and time format
        Builder timePickerBuilder = new Builder();
        timePickerBuilder.setTitleText("Select time");
        timePickerBuilder.setTimeFormat(TimeFormat.CLOCK_12H);
        timePicker = timePickerBuilder.build();

        // Set a click listener on the date text input to show the date picker
        binding.datePicker.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // Set a click listener on the time text input to show the time picker
        binding.timePicker.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });

        // Set a listener to capture the selected date and time
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

        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected time in hours and minutes
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                // Create a Calendar instance and set the selected time
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                // Create a SimpleDateFormat instance to format the time
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa", Locale.US);
                // Get the formatted time as a string and set it to the text input
                binding.timePicker.getEditText().setText(sdf.format(calendar.getTime()));
            }
        });
        Builder timePickerBuilder2 = new Builder();
        timePickerBuilder2.setTitleText("Select end time");
        timePickerBuilder2.setTimeFormat(TimeFormat.CLOCK_12H);
        MaterialTimePicker timePicker2 = timePickerBuilder2.build();

// Set a click listener on the second time text input to show the second time picker
        binding.timePicker1.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker2.show(getSupportFragmentManager(), "timePicker2");
            }
        });

// Set a listener to capture the selected end time
        timePicker2.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected time in hours and minutes
                int hour = timePicker2.getHour();
                int minute = timePicker2.getMinute();
                // Create a Calendar instance and set the selected time
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                // Create a SimpleDateFormat instance to format the time
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa", Locale.US);
                // Get the formatted time as a string and set it to the text input
                binding.timePicker1.getEditText().setText(sdf.format(calendar.getTime()));
            }
        });
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Faculty 1");
        spinnerArray.add("Faculty 2");
        spinnerArray.add("Faculty 3");
        spinnerArray.add("Lab 1");
        spinnerArray.add("Lab 2");
        spinnerArray.add("Lab 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = binding.spinvenue;
        sItems.setAdapter(adapter);
    }
}
