package com.app.bookapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Logout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        Button buttonLogout = view.findViewById(R.id.logout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });

        return view;
    }

    private void logoutUser() {
        new LogoutTask().execute();
    }

    private class LogoutTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://117.247.182.134:8081/ic/mad/Bookapp/logout.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                return connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer responseCode) {
            if (responseCode != null && responseCode == HttpURLConnection.HTTP_OK) {
                // Logout successful
                // Handle any necessary UI updates or navigation to the login screen
                Toast.makeText(getContext(), "Logout Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } else {
                // Logout failed
                Toast.makeText(getContext(), "Logout Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
