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

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmailLogin = view.findViewById(R.id.etEmailLogin);
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        tvGoToSignup = view.findViewById(R.id.tvGoToSignup);
        btnBackToSignup = view.findViewById(R.id.btnBackToSignup);
        btnGoToAdd = view.findViewById(R.id.btnGoToAdd);

        fbs = FirebaseServices.getInstance();

        // زر تسجيل الدخول
        btnLogin.setOnClickListener(v -> {
            String email = etEmailLogin.getText().toString().trim();
            String password = etPasswordLogin.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();

                                // ⭐ شرط الأدمن
                                if (email.equals("kenanabakarnih@gmail.com")) {
                                    // إذا الإيميل هو إيميل الأدمن → افتح صفحة الأدمن
                                    goToFragment(new AdminDetailsFragment());
                                } else {
                                    // المستخدم العادي → افتح صفحة الرحلات
                                    gotoRideListFragment();
                                }

                            } else {
                                Toast.makeText(getActivity(), "Failed to log in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // الذهاب إلى صفحة التسجيل
        tvGoToSignup.setOnClickListener(v -> {
            goToFragment(new SignupFragment());
        });

        // زر الرجوع للتسجيل
        btnBackToSignup.setOnClickListener(v -> {
            goToFragment(new SignupFragment());
        });

        // الذهاب إلى AddFragment
        btnGoToAdd.setOnClickListener(v -> {
            goToFragment(new AddFragment());
        });

        // زر نسيان كلمة المرور
        btnResetPassword.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Reset password coming soon", Toast.LENGTH_SHORT).show();
        });
    }

    private void gotoRideListFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new RideListFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void goToFragment(Fragment fragment) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}



