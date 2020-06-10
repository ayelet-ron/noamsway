package com.example.noamsway.ui.auth;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.Model;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.User;
import com.example.noamsway.ui.home.HomeFragment;
import com.example.noamsway.utils.Listener;

public class SignUpFragment extends Fragment {
    private EditText fullName;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private EditText confirmPassword;
    private EditText userName;
    private Button signUpButton;
    private TextView moveToSignIn;
    private SignUpViewModel mViewModel;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(SignUpViewModel.class);
        View root = inflater.inflate(R.layout.sign_up_fragment, container, false);
        this.fullName = root.findViewById(R.id.signup_full_name);
        this.email = root.findViewById(R.id.signup_email);
        this.password = root.findViewById(R.id.signup_password);
        this.confirmPassword = root.findViewById(R.id.signup_confirm_password);
        this.signUpButton = root.findViewById(R.id.signUpButton);
        this.moveToSignIn = root.findViewById(R.id.moveToSignIn);
        progressBar = root.findViewById(R.id.signin_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String fullNameText = fullName.getText().toString();
                String rePasswordText = confirmPassword.getText().toString();
                if(emailText.isEmpty() && passwordText.isEmpty() && fullNameText.isEmpty()){
                    Toast.makeText(getActivity(),"Please provide email, password and full name!",Toast.LENGTH_SHORT).show();
                }
                else if(fullNameText.isEmpty()){
                    fullName.setError("Please enter your Name");
                    fullName.requestFocus();
                }
                else if(emailText.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();
                }
                else if(passwordText.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(!rePasswordText.equals(passwordText)){
                    confirmPassword.setError("Passwords do not match");
                    confirmPassword.requestFocus();
                }
                if(!emailText.isEmpty() && !passwordText.isEmpty() && !fullNameText.isEmpty()){
                    progressBar.setVisibility(View.VISIBLE);
                    User user = new User(emailText,fullNameText,passwordText);
                    ModelAuth.instance.signUp(user, new Listener<Boolean>() {
                        @Override
                        public void onComplete(Boolean data) {
                            if(data){
                                Intent intToMain = new Intent(getActivity(), MainActivity.class);
                                startActivity(intToMain);
                            }
                            else{
                                //Toast.makeText(getActivity(),"Login failed due to incorrect password or email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        moveToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignInFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.replace(R.id.signUpFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
    }

}
