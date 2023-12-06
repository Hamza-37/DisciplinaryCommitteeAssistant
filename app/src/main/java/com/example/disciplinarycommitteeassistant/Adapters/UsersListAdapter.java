package com.example.disciplinarycommitteeassistant.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disciplinarycommitteeassistant.R;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder> {

    @NonNull
    @Override
    public UsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class UsersListViewHolder extends RecyclerView.ViewHolder {
        public UsersListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
