package com.app.bookapp;

import static com.app.bookapp.booksavailable.DELETE_USER_URL;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import com.android.volley.Request;

public class bookadapter extends RecyclerView.Adapter<bookadapter.donationholder> {
    private Context context;
    private List<booksavailable> booksList;
    private static final String KEY_USERNAME = "username";

    public bookadapter(Context context, List<booksavailable> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public donationholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bookavailable, parent, false);
        return new donationholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final donationholder holder, int position) {
        final booksavailable books = booksList.get(position);
        holder.bookname.setText(books.getBookname());
        holder.username.setText(books.getUsername());
        holder.subject.setText(books.getSubject());
        holder.semester.setText(books.getSemester());
        holder.booktype.setText(books.getBooktype());
        holder.mobile.setText(books.getMobile());

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("PLACE REQUEST");
                builder.setMessage("Confirm Request " + books.getBookname() + " Book\nContact : " + books.getMobile()+" To get the Book");
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final int position = holder.getAdapterPosition();
                        final String selectedUsername = booksList.get(position).getUsername(); // Get the selected username
                        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, DELETE_USER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("Response", response); // Print the response for debugging purposes

                                        if (response.equals("Record updated successfully")) {
                                            // Handle the success message here
                                            // For example, show a toast message or perform any necessary actions
                                            Toast.makeText(context, "Request successfully", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            try {
                                                JSONArray jsonArray = new JSONArray(response);

                                                // Clear the existing booksList
                                                booksList.clear();

                                                // Process each JSON object in the array
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String username = object.getString("username");
                                                    String bookname = object.getString("bookname");
                                                    String subject = object.getString("subject");
                                                    int semester = object.getInt("semester");
                                                    String booktype = object.getString("booktype");
                                                    long mobile = object.getLong("mobile");

                                                    // Create a new booksavailable object with the parsed values
                                                    booksavailable book = new booksavailable();
                                                    book.setUsername(username);
                                                    book.setBookname(bookname);
                                                    book.setSubject(subject);
                                                    book.setSemester(String.valueOf(semester));
                                                    book.setBooktype(booktype);
                                                    book.setMobile(String.valueOf(mobile));

                                                    // Add the book to the booksList
                                                    booksList.add(book);
                                                }

                                                // Notify the adapter that the data has changed
                                                notifyDataSetChanged();

                                                // Log the size of the list and the book details
                                                Log.d("BookListSize", "Size: " + booksList.size());
                                                for (booksavailable book : booksList) {
                                                    Log.d("BookData", book.getUsername() + " - " + book.getBookname());
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(context, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> deleteParams = new HashMap<>();
                                deleteParams.put("username", selectedUsername); // Pass the selected username to PHP file
                                return deleteParams;
                            }
                        };


                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(stringRequest);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class donationholder extends RecyclerView.ViewHolder {
        TextView username, bookname, subject, semester, booktype, mobile;
        ImageButton Delete;

        public donationholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.rcyusername);
            bookname = itemView.findViewById(R.id.rcybookname);
            subject = itemView.findViewById(R.id.rcysub);
            semester = itemView.findViewById(R.id.rcysem);
            booktype = itemView.findViewById(R.id.rcytype);
            mobile = itemView.findViewById(R.id.rcymobile);
            Delete = itemView.findViewById(R.id.rcydelete);
        }
    }
}
