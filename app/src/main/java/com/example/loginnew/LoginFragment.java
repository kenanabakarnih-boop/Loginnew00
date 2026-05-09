package com.example.loginnew;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment {

    private EditText etEmailLogin, etPasswordLogin;
    private Button btnLogin, btnResetPassword;
    private TextView tvGoToSignup;
    private ImageView btnBackToSignup, btnGoToAdd;
    private FirebaseServices fbs;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ربط العناصر من fragment_login.xml
        etEmailLogin = view.findViewById(R.id.etEmailLogin);
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        tvGoToSignup = view.findViewById(R.id.tvGoToSignup);
        btnBackToSignup = view.findViewById(R.id.btnBackToSignup);
        btnGoToAdd = view.findViewById(R.id.btnGoToAdd);
        fbs = FirebaseServices.getInstance();

        // أمثلة على الضغط
        btnLogin.setOnClickListener(v -> {
                    String email = etEmailLogin.getText().toString().trim();
                    String password = etPasswordLogin.getText().toString().trim();

                    if (email.trim().isEmpty() || password.trim().isEmpty()) {
                        Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                        return;

                    }
//                String name=fbs.getFire().collection("users").getParent().getId().toString();

                    //Signup procedure

                    fbs.getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "you have successfully logged in  ", Toast.LENGTH_SHORT).show();
                                gotoRideListFragment();
                            } else {
                                Toast.makeText(getActivity(), "failed to loge in ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                });

        tvGoToSignup.setOnClickListener(v -> {
            // الانتقال للساين اب
        });

        btnResetPassword.setOnClickListener(v -> {
            // الانتقال لنسيت كلمة المرور
        });

        btnGoToAdd.setOnClickListener(v -> {
            // الانتقال إلى AddFragment
        });

        btnBackToSignup.setOnClickListener(v -> {
            // رجوع للساين اب
        });

    }

    private void gotoRideListFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main, new RideListFragment());
        ft.commit();
    }

}

