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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment {

    private FirebaseServices fbs;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fbs = FirebaseServices.getInstance();

        etUsername = view.findViewById(R.id.etUsernameLogin);
        etPassword = view.findViewById(R.id.etPasswordLogin);
        Button btnLogin = view.findViewById(R.id.btnLoginLogin);
        TextView tvSignupLink = view.findViewById(R.id.tvSignupLinkLogin);

        tvSignupLink.setOnClickListener(v -> gotoSignupFragment());

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().signInWithEmailAndPassword(username, password)
                    .addOnSuccessListener(authResult ->
                            Toast.makeText(getActivity(), "You have successfully logged in!", Toast.LENGTH_SHORT).show()
                    )
                    .addOnFailureListener(e ->
                            Toast.makeText(getActivity(), "Failed to login", Toast.LENGTH_SHORT).show()
                    );
        });
    }

    private void gotoSignupFragment() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new SignupFragment());
        ft.commit();
    }
}

