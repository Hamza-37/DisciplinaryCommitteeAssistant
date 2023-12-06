package com.example.disciplinarycommitteeassistant.Adapters;

import static android.media.CamcorderProfile.get;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disciplinarycommitteeassistant.Models.User;
import com.example.disciplinarycommitteeassistant.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyMembersAdapter extends RecyclerView.Adapter<FacultyMembersAdapter.ViewHolder> implements Filterable
{
    private List<User> facultyMembersList;
    public static SearchView sv;

    public FacultyMembersAdapter(List<User> facultyMembersList) {
        this.facultyMembersList = facultyMembersList;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_facultymemberslist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User facultyMemberName = facultyMembersList.get(position);
        holder.facultyMemberNameTextView.setText(facultyMemberName.getName());
        holder.facultyMemberNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.setQuery(facultyMemberName.getName(),true);
                sv.clearFocus();;
                updateList(new ArrayList<>());
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyMembersList.size();
    }

    public void updateList(List<User> newList) {
        facultyMembersList = newList;
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
                    filteredList.addAll(facultyMembersList); // Add the entire list
                } else {
                    for (User user : facultyMembersList) {
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

                // Update the adapter's data with the filtered list
                updateList(filteredList);

                notifyDataSetChanged();
            }
        };
    }

}
