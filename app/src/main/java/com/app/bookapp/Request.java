package com.app.bookapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Request extends Fragment {
    private RecyclerView recyclerView;
    private bookadapter bookAdapter;
    private List<booksavailable> booksList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        recyclerView = view.findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booksList = new ArrayList<>();
        bookAdapter = new bookadapter(getContext(), booksList);
        recyclerView.setAdapter(bookAdapter);
        LoadAllDonations();
        Button buttonrefresh=view.findViewById(R.id.refresh);

        buttonrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadAllDonations();
            }
        });
        return view;
    }
    private void clearData() {
        booksList.clear();
        bookAdapter.notifyDataSetChanged();
    }

    private void LoadAllDonations() {
        clearData();
        JsonArrayRequest request = new JsonArrayRequest(booksavailable.SHOW_ALL_USER_DATA_URL, array -> {
            Log.d("BookListSize", "Size before: " + booksList.size());
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject object = array.getJSONObject(i);
                    String username = object.getString("username".trim());
                    String bookname = object.getString("bookname".trim());
                    String subject = object.getString("subject".trim());
                    String semester = object.getString("semester".trim());
                    String booktype = object.getString("booktype".trim());
                    String mobile = object.getString("mobile".trim());

                    // Create a new booksavailable object for each item
                    booksavailable bookavail = new booksavailable();
                    bookavail.setUsername(username);
                    bookavail.setBookname(bookname);
                    bookavail.setSubject(subject);
                    bookavail.setSemester(semester);
                    bookavail.setBooktype(booktype);
                    bookavail.setMobile(mobile);
                    booksList.add(bookavail);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("BookListSize", "Size after: " + booksList.size());
            // Notify the adapter that the data set has changed
            bookAdapter.notifyDataSetChanged();
        }, error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
