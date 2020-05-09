package com.example.noamsway.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.noamsway.R;

public class SignInFragment extends Fragment {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView moveToSignUp;
    private SignInViewModel signInViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signInViewModel =
                ViewModelProviders.of(this).get(SignInViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        this.email = root.findViewById(R.id.login_email);
        this.password = root.findViewById(R.id.login_password);
        this.loginButton = root.findViewById(R.id.loginButton);
        this.moveToSignUp = root.findViewById(R.id.moveToSingUp);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(emailText.isEmpty() && passwordText.isEmpty()){
                    Toast.makeText(getActivity(),"Please provide email and password!",Toast.LENGTH_SHORT).show();
                }
                else if(emailText.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();
                }
                else if(passwordText.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }

            }
        });
        moveToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignUpFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.replace(R.id.loginFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        signInViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
