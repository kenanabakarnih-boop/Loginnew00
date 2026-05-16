package com.example.loginnew;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button btnAddRide, btnShowRides;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddRide = view.findViewById(R.id.btnAddRide);
        btnShowRides = view.findViewById(R.id.btnShowRides);

        btnAddRide.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new AddFragment())
                        .addToBackStack(null)
                        .commit()
        );

        btnShowRides.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new RideListFragment())
                        .addToBackStack(null)
                        .commit()
        );
    }
}

