package com.example.loginnew;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;


public class LoginFragment extends Fragment {

    private FirebaseServices fbs;
    private EditText etUsername ;
    private EditText etPassword ;
    private Button btnlogin ;
    private TextView tvSignuplink;
    private TextView tvSignupLinkLogin ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etUsername = getView().findViewById(R.id.etUsernameLogin);
        etPassword = getView().findViewById(R.id.etPasswordLogin);
        btnlogin= getView().findViewById(R.id.btnlogin);
            tvSignuplink =getView().findViewById(R.id.tvSignupLinkLogin);
            tvSignupiink.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View view){
                  gotoSignupFragment();
               }

        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Some fields are empty!",Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().signInWithEmailAndPassword(username, password).addOnCompleteListener(getActivity(),
                @Override
                public void onComplate(@NonNull Task<AuthResult> task){
                    if (task.isSuccessful()){
                        Toast.makeText(getActivity(), "You have successfully loged in!", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        Toast.makeText(getActivity(), "Faild to login ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void gotoSignupFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain , new SignupFragment());
        ft.commit();

    }

}

