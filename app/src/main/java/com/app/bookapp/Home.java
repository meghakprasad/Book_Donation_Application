package com.app.bookapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Home extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textViewWelcome = view.findViewById(R.id.textViewWelcome);
        TextView textViewUsername = view.findViewById(R.id.textViewUsername);

        String loggedInUsername = SharedPrefManager.getInstance(context).getUsername();

        textViewWelcome.setText("Welcome to Book Donation Application");
        textViewUsername.setText("Logged in as: " + loggedInUsername);

        return view;
    }
}
