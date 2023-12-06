package com.example.disciplinarycommitteeassistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.disciplinarycommitteeassistant.R;

import java.util.ArrayList;

public class Register10Adapter extends RecyclerView.Adapter<Register10Adapter.MyViewHolder> {
private final Context context;
private final ArrayList<Integer> integerArrayList;

    public Register10Adapter(Context context, ArrayList<Integer> integerArrayList) {
        this.context = context;
        this.integerArrayList = integerArrayList;
    }

    @NonNull
    @Override
    public Register10Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.register10layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Register10Adapter.MyViewHolder holder, int position) {
        Glide.with(context).load(integerArrayList.get(position)).into(holder.imageview);
        Glide.with(context).load(integerArrayList.get(position)).into(holder.imageview1);
    }

    @Override
    public int getItemCount() {
        return integerArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageview;
       public ImageView imageview1;
        ImageView imageView1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        /*   imageview=itemView.findViewById(R.id.image_view1);
           imageView1=itemView.findViewById(R.id.image_view2);*/
        }
    }
}
