package com.example.shabashservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabashservices.Models.servicenextmodel;
import com.example.shabashservices.R;

import java.util.ArrayList;

public class servicenextadapter extends RecyclerView.Adapter<servicenextadapter.ViewHolder>{
    ArrayList<servicenextmodel> model;
    Context context;

    public servicenextadapter(ArrayList<servicenextmodel> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.servicenext_recycler_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.servicenext.setText(model.get(position).getServicenext());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox servicenext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            servicenext=itemView.findViewById(R.id.checkboxnextservice);
        }
    }
}
