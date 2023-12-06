package com.example.disciplinarycommitteeassistant;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disciplinarycommitteeassistant.databinding.ActivityRelaxPunishmentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RelaxPunishmentActivity extends AppCompatActivity {
    ActivityRelaxPunishmentBinding binding;
    private MaterialDatePicker<Long> datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRelaxPunishmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create a MaterialDatePicker builder and set the title
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select date");
        datePicker = builder.build();

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
                binding.datePicker.getEditText().setTextColor(getResources().getColor(R.color.teal_200));
                binding.datePicker.getEditText().setText(sdf.format(date));
            }
        });

        // Set a listener to reset the text color when the date is cleared
        binding.datePicker.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    TextInputEditText editText = (TextInputEditText) view;
                    if (editText.getText().toString().isEmpty()) {
                        editText.setTextColor(getResources().getColor(R.color.black));
                    }
                }
            }
        });
    }
}
