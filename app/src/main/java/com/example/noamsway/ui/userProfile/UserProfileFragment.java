package com.example.noamsway.ui.userProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;

public class UserProfileFragment extends Fragment {
    private TextView email;
    private TextView name;
    private Button logout_button;
    private UserProfileViewModel mViewModel;
    private ProgressBar progressBar;

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_profile_fragment, container, false);
        logout_button = root.findViewById(R.id.logoutButton);
        progressBar = root.findViewById(R.id.progressBar);
        email = root.findViewById(R.id.user_email);
        name = root.findViewById(R.id.user_name);
        name.setText(ModelAuth.instance.getUserFullName());
        email.setText(ModelAuth.instance.getUserEmail());
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ModelAuth.instance.longout();
                Intent intToMain = new Intent(getActivity(), MainActivity.class);
                startActivity(intToMain);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
