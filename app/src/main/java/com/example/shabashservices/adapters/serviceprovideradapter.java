package com.example.shabashservices.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabashservices.Models.serviceprovidermodel;
import com.example.shabashservices.R;
import com.example.shabashservices.requestservice;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class serviceprovideradapter extends RecyclerView.Adapter<serviceprovideradapter.ViewHolder> {
    ArrayList<serviceprovidermodel> model;
    Context context;

    public serviceprovideradapter(ArrayList<serviceprovidermodel> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.serviceprovider_recycler_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(model.get(position).getImg());
        holder.name.setText(model.get(position).getServiceprovidername());
        holder.rating.setRating(model.get(position).getRating());
        holder.serviceprovidercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(context, requestservice.class);
                context.startActivity(next);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name;
        RatingBar rating;
        CardView serviceprovidercard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            serviceprovidercard=itemView.findViewById(R.id.serviceprovider_card);
        }
    }
}
