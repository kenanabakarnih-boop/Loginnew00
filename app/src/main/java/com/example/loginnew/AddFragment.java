package com.example.loginnew;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFragment extends Fragment {

    private EditText etStart, etEnd, etDate, etTime, etPrice;
    private Button btnSave;

    private DatabaseReference ridesRef;

    public AddFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        etStart = view.findViewById(R.id.etStart);
        etEnd = view.findViewById(R.id.etEnd);
        etDate = view.findViewById(R.id.etDate);
        etTime = view.findViewById(R.id.etTime);
        etPrice = view.findViewById(R.id.etPrice);
        btnSave = view.findViewById(R.id.btnSave);

        ridesRef = FirebaseDatabase.getInstance().getReference("rides");

        btnSave.setOnClickListener(v -> saveRide());

        return view;
    }

    private void saveRide() {

        String start = etStart.getText().toString().trim();
        String end = etEnd.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        if (start.isEmpty() || end.isEmpty() || date.isEmpty() || time.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        String rideId = ridesRef.push().getKey();
        String userId = "USER_001"; // لاحقًا تربطيه بالـ Firebase Auth

        RideModel ride = new RideModel(
                rideId,
                userId,
                start,
                end,
                date,
                time,
                price,
                "Pending"
        );

        ridesRef.child(rideId).setValue(ride)
                .addOnSuccessListener(unused ->
                        Toast.makeText(getContext(), "Ride added successfully", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}

