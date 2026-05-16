package com.example.loginnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminRideAdapter extends RecyclerView.Adapter<AdminRideAdapter.RideViewHolder> {

    private ArrayList<RideModel> rideList;
    private Context context;

    public AdminRideAdapter(ArrayList<RideModel> rideList, Context context) {
        this.rideList = rideList;
        this.context = context;
    }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ride_item, parent, false);
        return new RideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        RideModel ride = rideList.get(position);

        holder.tvStart.setText("From: " + ride.getStartLocation());
        holder.tvEnd.setText("To: " + ride.getEndLocation());
        holder.tvDate.setText("Date: " + ride.getDate());
        holder.tvTime.setText("Time: " + ride.getTime());
        holder.tvPrice.setText("Price: " + ride.getPrice() + "₪");
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class RideViewHolder extends RecyclerView.ViewHolder {

        TextView tvStart, tvEnd, tvDate, tvTime, tvPrice;

        public RideViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}


