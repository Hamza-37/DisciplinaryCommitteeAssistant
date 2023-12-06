package com.example.disciplinarycommitteeassistant.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.disciplinarycommitteeassistant.AssignPunishmentActivity;
import com.example.disciplinarycommitteeassistant.R;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;


public class CheckProgressFragment extends Fragment {
    CustomCalendar customCalendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AssignPunishmentActivity activity= new AssignPunishmentActivity();
        return inflater.inflate(R.layout.fragment_check_progress, container, false);
    }
}