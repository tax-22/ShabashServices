package com.example.shabashservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabashservices.Models.bookedservicesmodel;
import com.example.shabashservices.R;

import java.util.ArrayList;

public class Bookedservice extends RecyclerView.Adapter<Bookedservice.ViewHolder> {

    ArrayList<bookedservicesmodel> model;
    Context context;
    public Bookedservice(Context context, ArrayList<bookedservicesmodel> model){
        this.context=context;
        this.model=model;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bookedservice_recycler_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookservices.setText(model.get(position).getBooked_service());
        holder.dateofbooking.setText(model.get(position).getDate());
        holder.bookedimg.setImageResource(model.get(position).getBookedimage());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateofbooking,bookservices;
        CardView bookedcardview;
        ImageView bookedimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateofbooking=itemView.findViewById(R.id.dateofbooking);
            bookservices=itemView.findViewById(R.id.bookservice);
            bookedcardview=itemView.findViewById(R.id.bookedcardview);
            bookedimg=itemView.findViewById(R.id.bookedimage);
        }
    }

}
