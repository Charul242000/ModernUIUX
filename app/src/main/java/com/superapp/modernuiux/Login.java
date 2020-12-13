package com.superapp.modernuiux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Login extends AppCompatActivity {
    Button newuser, logingo;
    TextInputLayout logintxtinptlyt1,logintxtinptlyt2;
    TextView logintv1,logintv2;
    CircleImageView logoimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        newuser=findViewById(R.id.newuser);
        logingo=findViewById(R.id.logingo);
        logintxtinptlyt1=findViewById(R.id.logintxtinptlyt1);
        logintxtinptlyt2=findViewById(R.id.logintxtinptlyt2);
        logintv1=findViewById(R.id.logintv1);
        logintv2=findViewById(R.id.logintv2);
        logoimage=findViewById(R.id.logoimage);

        logingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Validate Login info
                if (!validateEmail()| !validatePassword()){
                    return;
                }
                else{
                    isUser();
                }

            }
        });


        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                Pair[] pairs=new Pair[7];
                pairs[0] =new Pair<View,String>(logoimage,"logoimage");
                pairs[1] =new Pair<View,String>(logintv1,"logintv1");
                pairs[2] =new Pair<View,String>(logintv2,"logintv2");
                pairs[3] =new Pair<View,String>(logintxtinptlyt1,"logintxtinptlyt1");
                pairs[4] =new Pair<View,String>(logintxtinptlyt2,"logintxtinptlyt2");
                pairs[5] =new Pair<View,String>(logingo,"logingo");
                pairs[6] =new Pair<View,String>(newuser,"newuser");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    private void isUser() {
        final String userEnteredUserEmail= logintxtinptlyt1.getEditText().getText().toString().trim();
        final String userEnteredPassword= logintxtinptlyt2.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser= reference.orderByChild("email").equalTo(userEnteredUserEmail);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String passwordFromDB = dataSnapshot.child(userEnteredUserEmail).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){
                        String nameFromDB = dataSnapshot.child(userEnteredUserEmail).child("name").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUserEmail).child("email").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUserEmail).child("phoneNo").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phone",phoneNoFromDB);

                        startActivity(intent);


                    }
                    else
                        logintxtinptlyt2.setError("Wrong password");
                        logintxtinptlyt2.requestFocus();
                }
                else {
                    logintxtinptlyt1.setError("No such user email id found");
                    logintxtinptlyt1.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private Boolean validateEmail(){
        String val =  logintxtinptlyt1.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            logintxtinptlyt1.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)){
            logintxtinptlyt1.setError("Invalid email address");
            return false;
        }
        else {
            logintxtinptlyt1.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val =  logintxtinptlyt2.getEditText().getText().toString();

        if (val.isEmpty()){
            logintxtinptlyt1.setError("Field cannot be empty");
            return false;
        }

        else {
            logintxtinptlyt1.setError(null);
            return true;
        }
    }
}
