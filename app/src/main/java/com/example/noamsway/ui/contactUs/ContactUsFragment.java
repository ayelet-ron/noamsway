package com.example.noamsway.ui.contactUs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.noamsway.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactUsFragment extends Fragment {
    private ScrollView scroll;
    private ContactUsViewModel mViewModel;

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contact_us_fragment, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        this.scroll = root.findViewById(R.id.scroll);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollDown();
            }
        });
        return root;
    }

    public void scrollDown() {
        this.scroll.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactUsViewModel.class);
        // TODO: Use the ViewModel
    }

}
