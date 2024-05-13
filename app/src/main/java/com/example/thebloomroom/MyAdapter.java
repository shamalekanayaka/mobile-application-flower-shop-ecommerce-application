package com.example.thebloomroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    Context context;
    ArrayList<Order> orderList;


    public MyAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {

        Order order = orderList.get(position);

        holder.address.setText("Address: "+order.address);
        holder.name.setText("Name: "+order.name);
        holder.category.setText(("Category: "+order.category));
        holder.phone.setText("Phone: "+order.phone);


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView address,name,category,phone;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.textView12);
            name = itemView.findViewById(R.id.textView13);
            category = itemView.findViewById(R.id.textView14);
            phone = itemView.findViewById(R.id.textView15);


        }
    }

}
