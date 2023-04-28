package com.example.shabashservices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabashservices.Models.locationmodel;
import com.example.shabashservices.R;

import java.util.ArrayList;

public class locationadapter extends RecyclerView.Adapter<locationadapter.ViewHolder>{
    ArrayList<locationmodel> model;
//    ArrayList<locationmodel> searchlist;
    Context context;

    public locationadapter(ArrayList<locationmodel> model, Context context) {
//        this.model = searchlist;
//        this.searchlist = new ArrayList<>(model);
        this.model=model;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.location.setText(model.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }


//    @Override
//    public Filter getFilter() {
//        return searchfilter;
//    }

//    private Filter searchfilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            ArrayList<locationmodel> filterlocation = new ArrayList<>();
//            if (charSequence == null || charSequence.length() == 0) {
//                filterlocation.addAll(model);
//            } else {
//                String search = charSequence.toString().toLowerCase().trim();
//                for (locationmodel loaction : model) {
//                    if (loaction.heading.toLowercase().contains(search)) {
//                        filterlocation.add(loaction);
//                    }
//                }
//            }
//            FilterResults results=new FilterResults();
//            results.values=filterlocation;
//            results.count=filterlocation.size();
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults results) {
//            searchlist.clear();
//            searchlist.addAll((ArrayList)results.values);
//            notifyDataSetChanged();
//
//        }
//    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.location_name);
        }
    }
    public void filterList(ArrayList<locationmodel> filteredList){
        model=filteredList;
        notifyDataSetChanged();
    }
}
