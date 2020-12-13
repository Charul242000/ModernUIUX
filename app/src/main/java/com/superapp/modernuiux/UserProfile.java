package com.superapp.modernuiux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullname,email,phnno;
    TextView namebesideslogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phnno=findViewById(R.id.phnno);
        namebesideslogo=findViewById(R.id.namebesideslogo);

        //show all data
        showAllData();
    }

    private void showAllData() {
        Intent intent= getIntent();

        String user_name= intent.getStringExtra("name");
        String user_email= intent.getStringExtra("email");
        String user_phone= intent.getStringExtra("phone");

        namebesideslogo.setText(user_name);
        fullname.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phnno.getEditText().setText(user_phone);



    }
}
