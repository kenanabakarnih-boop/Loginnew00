
package com.example.loginnew;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class AddFragment extends Fragment {

    private EditText etStartLocation, etDestination, etPrice;
    private Button btnAddRide, btnShowRides;

    public AddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStartLocation = view.findViewById(R.id.etStartLocation);
        etDestination = view.findViewById(R.id.etDestination);
        etPrice = view.findViewById(R.id.etPrice);
        btnAddRide = view.findViewById(R.id.btnAddRide);
        btnShowRides = view.findViewById(R.id.btnShowRides);

        btnAddRide.setOnClickListener(v -> {
            String start = etStartLocation.getText().toString().trim();
            String dest = etDestination.getText().toString().trim();
            String price = etPrice.getText().toString().trim();

            if (start.isEmpty() || dest.isEmpty() || price.isEmpty()) {
                Toast.makeText(getContext(), "املأ جميع الحقول", Toast.LENGTH_SHORT).show();
                return;
            }

            String rideId = FirebaseDatabase.getInstance()
                    .getReference("rides")
                    .push()
                    .getKey();

            RideModel ride = new RideModel(rideId, start, dest, price);

            FirebaseDatabase.getInstance().getReference("rides")
                    .child(rideId)
                    .setValue(ride)
                    .addOnSuccessListener(unused ->
                            Toast.makeText(getContext(), "تم إضافة الرحلة بنجاح", Toast.LENGTH_SHORT).show()
                    )
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "حدث خطأ أثناء الإضافة", Toast.LENGTH_SHORT).show()
                    );
        });

        btnShowRides.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new RideListFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
