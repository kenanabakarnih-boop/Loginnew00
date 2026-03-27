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

import com.google.firebase.auth.AuthResult;

public class SignupFragment extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnSignUp;
    private FirebaseServices fbs;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // الحصول على instance من FirebaseServices
        fbs = FirebaseServices.getInstance();

        // ربط عناصر الواجهة
        etUsername = view.findViewById(R.id.etUsernameSignUp);
        etPassword = view.findViewById(R.id.etPasswordSignup);
        btnSignUp = view.findViewById(R.id.btnSingUp);

        // حدث الضغط على زر Sign Up
        btnSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // التحقق من الحقول الفارغة
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            // إنشاء مستخدم جديد في Firebase
            fbs.getAuth().createUserWithEmailAndPassword(username, password)
                    .addOnSuccessListener((AuthResult authResult) -> {
                        Toast.makeText(getActivity(), "You have successfully signed up!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // عرض رسالة الخطأ الحقيقية من Firebase
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}



