package com.example.shabashservices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Service_adapter extends RecyclerView.Adapter<Service_adapter.ViewHolder> {
    Context context;
    ArrayList<serviceModel>service;
    Service_adapter(Context context, ArrayList<serviceModel> service){
        this.context=context;
        this.service=service;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.services_card,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.img_view.setImageResource(service.get(position).img);
            holder.sercvice_name.setText(service.get(position).service_name);
            holder.service_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent next=new Intent(context,Service_next.class);
                    //next.putExtra("Name",service.get(position).service_name);
                    context.startActivity(next);
                }
            });
    }

    @Override
    public int getItemCount() {
        return service.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sercvice_name;
        CardView service_cardview;
        ImageView img_view;
        public ViewHolder(View itemView) {
            super(itemView);
            sercvice_name=itemView.findViewById(R.id.txtservice);
            img_view=itemView.findViewById(R.id.imgservice);
            service_cardview=itemView.findViewById(R.id.service_cardview);
        }
    }
}
