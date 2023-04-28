package com.example.shabashservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabashservices.Models.currentservicesmodel;
import com.example.shabashservices.R;

import java.util.ArrayList;

public class Currentservice extends RecyclerView.Adapter<Currentservice.ViewHolder> {

    ArrayList<currentservicesmodel> model;
    Context context;

    public Currentservice(Context context, ArrayList<currentservicesmodel> model) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currentbooking_recycler_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.currentserviceimage.setImageResource(model.get(position).getCurrentimage());
        holder.currentservicetext.setText(model.get(position).getCurrentservice());


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView currentserviceimage;
        TextView currentservicetext;
        //CardView currentcardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currentserviceimage = itemView.findViewById(R.id.currentimage);
            currentservicetext = itemView.findViewById(R.id.curentservice);
            //currentcardview = itemView.findViewById(R.id.recyclercurrentservice);

        }
    }
}
