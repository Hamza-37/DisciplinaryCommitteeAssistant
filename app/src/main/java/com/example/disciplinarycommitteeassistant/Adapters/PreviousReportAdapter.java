package com.example.disciplinarycommitteeassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disciplinarycommitteeassistant.Models.PreviousReportsModel;
import com.example.disciplinarycommitteeassistant.R;

import java.util.ArrayList;

public class PreviousReportAdapter extends RecyclerView.Adapter<PreviousReportAdapter.ViewHolder>{
    Context context;
    ArrayList<PreviousReportsModel>arrReports;
    public PreviousReportAdapter(Context context, ArrayList<PreviousReportsModel> arrReports) {
        this.context=context;
        this.arrReports=arrReports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.report_view_design,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.name.setText(arrReports.get(position).getName());
    holder.arid_no.setText(arrReports.get(position).getArid_no());

    }

    @Override
    public int getItemCount() {
        return arrReports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,arid_no;
        Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.t2);
            arid_no=itemView.findViewById(R.id.t1);
            btn=itemView.findViewById(R.id.t3);
        }
    }
}
