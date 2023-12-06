package com.example.disciplinarycommitteeassistant.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.disciplinarycommitteeassistant.R;
import com.example.disciplinarycommitteeassistant.databinding.ActivityMeetingScheduleBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;


public class CheckScheduleFragment extends Fragment {
    ActivityMeetingScheduleBinding binding;
    private MaterialDatePicker<Long> datePicker;
    private MaterialTimePicker timePicker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_schedule, container, false);
    }
}
