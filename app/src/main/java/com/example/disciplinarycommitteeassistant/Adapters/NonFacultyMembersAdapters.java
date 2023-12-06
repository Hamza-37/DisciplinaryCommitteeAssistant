package com.example.disciplinarycommitteeassistant.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disciplinarycommitteeassistant.Models.User;
import com.example.disciplinarycommitteeassistant.R;

import java.util.ArrayList;
import java.util.List;

public class NonFacultyMembersAdapters extends RecyclerView.Adapter<NonFacultyMembersAdapters.ViewHolder>implements Filterable {
    private List<User> nonfacultymemberlist;

    public NonFacultyMembersAdapters(List<User> NonFacultyMembersList) {
        this.nonfacultymemberlist=NonFacultyMembersList;

    }

    public void NonFacultyMembersAdapter(List<User> nonfacultyMembersList) {
        this.nonfacultymemberlist = nonfacultyMembersList;
        ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView facultyMemberNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            facultyMemberNameTextView = itemView.findViewById(R.id.facultyMemberNameTextView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_facultymemberslist, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NonFacultyMembersAdapters.ViewHolder holder, int position) {
        User nonfacultymembername = nonfacultymemberlist.get(position);
        holder.facultyMemberNameTextView.setText(nonfacultymembername.getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void updateList(List<User> newList) {
        nonfacultymemberlist = newList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString().toLowerCase();
                List<User> filteredList = new ArrayList<>();

                if (query.isEmpty()) {
                    filteredList.addAll(nonfacultymemberlist); // Add the entire list
                } else {
                    for (User user : nonfacultymemberlist) {
                        if (user.getName().toLowerCase().contains(query)) {
                            filteredList.add(user);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<User> filteredList = (List<User>) results.values;
                updateList(filteredList);
                notifyDataSetChanged();
            }
        };
    }
}
