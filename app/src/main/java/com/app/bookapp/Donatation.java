package com.app.bookapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.w3c.dom.Text;


public class Donatation extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donatation, container, false);

        TextInputEditText textInputEditTextUsername = view.findViewById(R.id.usr);
        TextInputEditText textInputEditTextBookname = view.findViewById(R.id.bookname);
        TextInputEditText textInputEditTextsubject = view.findViewById(R.id.subject);
        TextInputEditText textInputEditTextsemester = view.findViewById(R.id.semester);
        TextInputEditText textInputEditTextbooktype = view.findViewById(R.id.type);
        TextInputEditText textInputEditTextbookMobile = view.findViewById(R.id.mobile);
        Button buttonSubmit = view.findViewById(R.id.submit);

        //progressB=findViewById(R.id.progress1);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookname, subject, semester, booktype,mobile,username;
                username=String.valueOf(textInputEditTextUsername.getText());
                bookname = String.valueOf(textInputEditTextBookname.getText());
                subject = String.valueOf(textInputEditTextsubject.getText());
                semester = String.valueOf(textInputEditTextsemester.getText());
                booktype = String.valueOf(textInputEditTextbooktype.getText());
                mobile = String.valueOf(textInputEditTextbookMobile.getText());
                if (!username.equals("") && !bookname.equals("") && !subject.equals("") && !semester.equals("") && !booktype.equals("")&& !mobile.equals("")) {
                    //progressB.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[6];
                            field[0] = "username";
                            field[1] = "bookname";
                            field[2] = "subject";
                            field[3] = "semester";
                            field[4] = "booktype";
                            field[5] = "mobile";
                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = username;
                            data[1] = bookname;
                            data[2] = subject;
                            data[3] = semester;
                            data[4] = booktype;
                            data[5] = mobile;
                            PutData putData = new PutData("http://117.247.182.134:8081/ic/mad/Bookapp/donate.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();
                                    if (result.equals("Submit Success")){
                                        Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getActivity().getApplicationContext(),Donatation.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                    }
                                    else {
                                        Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}