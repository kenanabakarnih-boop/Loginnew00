package com.example.loginnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.RideViewHolder> {

    ArrayList<RideModel> list;

    public RideAdapter(ArrayList<RideModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ride, parent, false);
        return new RideViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        RideModel ride = list.get(position);
        holder.tvStart.setText("من: " + ride.startLocation);
        holder.tvDest.setText("إلى: " + ride.destination);
        holder.tvPrice.setText("السعر: " + ride.price + " شيكل");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RideViewHolder extends RecyclerView.ViewHolder {

        TextView tvStart, tvDest, tvPrice;

        public RideViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvDest = itemView.findViewById(R.id.tvDest);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}

