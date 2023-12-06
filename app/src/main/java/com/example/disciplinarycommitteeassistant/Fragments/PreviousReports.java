package com.example.disciplinarycommitteeassistant.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.disciplinarycommitteeassistant.Adapters.RecyclerReportAdapter;
import com.example.disciplinarycommitteeassistant.Adapters.PreviousReportAdapter;
import com.example.disciplinarycommitteeassistant.Models.PreviousReportsModel;
import com.example.disciplinarycommitteeassistant.PreviousReportsItems;
import com.example.disciplinarycommitteeassistant.R;
import com.example.disciplinarycommitteeassistant.databinding.FragmentPreviousReportsBinding;

import java.util.ArrayList;
import java.util.List;

public class PreviousReports extends Fragment {
    FragmentPreviousReportsBinding binding;
    ArrayList<PreviousReportsModel>arrReports=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_previous_reports, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RcycPreviousReport);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PreviousReportsModel model=new PreviousReportsModel("Ali Ashraf","2019-Arid-1234");
        arrReports.add(new PreviousReportsModel("Ali Ashraf","2019-Arid-1234"));
        arrReports.add(new PreviousReportsModel("Huzaifa Arshad","2019-Arid-3432"));
        arrReports.add(new PreviousReportsModel("Taha Mehmood","2019-Arid-4553"));
        arrReports.add(new PreviousReportsModel("Ahmed Abdullah","2019-Arid-1253"));
        arrReports.add(new PreviousReportsModel("Sikander Khan","2019-Arid-1765"));
        PreviousReportAdapter adapter= new PreviousReportAdapter(getContext(),arrReports);
        recyclerView.setAdapter(adapter);
        return view;
    }
}