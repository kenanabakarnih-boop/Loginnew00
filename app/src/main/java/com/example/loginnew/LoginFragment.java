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
import android.widget.ImageView;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    private EditText etEmailLogin, etPasswordLogin;
    private Button btnLogin, btnResetPassword;
    private TextView tvGoToSignup;
    private ImageView btnBackToSignup, btnGoToAdd;

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

        // أمثلة على الضغط
        btnLogin.setOnClickListener(v -> {
            String email = etEmailLogin.getText().toString().trim();
            String password = etPasswordLogin.getText().toString().trim();
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
}

