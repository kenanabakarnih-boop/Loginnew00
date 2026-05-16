package com.example.loginnew;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDetailsFragment extends Fragment {

    private RecyclerView rvAdminRides;
    private ArrayList<RideModel> rideList;
    private RideAdapter adapter;

    private DatabaseReference ridesRef;

    public AdminDetailsFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admindetails, container, false);

        rvAdminRides = view.findViewById(R.id.rvAdminRides);
        rvAdminRides.setLayoutManager(new LinearLayoutManager(getContext()));

        rideList = new ArrayList<>();
        adapter = new RideAdapter(rideList, getContext());
        rvAdminRides.setAdapter(adapter);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        loadRides();

        return view;
    }

    private void loadRides() {
        ridesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rideList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    RideModel ride = data.getValue(RideModel.class);
                    if (ride != null) {
                        rideList.add(ride);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

