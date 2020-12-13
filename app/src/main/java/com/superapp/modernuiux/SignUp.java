package com.superapp.modernuiux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextInputLayout username,useremail,userpassword,userphn;
    Button signupgo,backtosignin;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.Signuptxtinptlyt3);
        useremail=findViewById(R.id.Signuptxtinptlyt1);
        userpassword=findViewById(R.id.Signuptxtinptlyt2);
        userphn=findViewById(R.id.Signuptxtinptlyt4);
        signupgo=findViewById(R.id.signupgo);
        backtosignin=findViewById(R.id.backtosignin);




        signupgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase=FirebaseDatabase.getInstance();
                reference=firebaseDatabase.getReference("users");

                if (!validateName()| !validateEmail()| !validatePassword()| !validatePhn()){
                    return;
                }

                String name =  username.getEditText().getText().toString();
                String email =  useremail.getEditText().getText().toString();
                String password =  userpassword.getEditText().getText().toString();
                String phoneNo =  userphn.getEditText().getText().toString();


                UserHelper userHelper=new UserHelper(name,email,password,phoneNo);


                reference.child(email).setValue(userHelper);
            }
        });
    }
    private Boolean validateName(){
        String val =  username.getEditText().getText().toString();
        if (val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val =  useremail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            useremail.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)){
            useremail.setError("Invalid email address");
            return false;
        }
        else {
            useremail.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val =  userpassword.getEditText().getText().toString();

        if (val.isEmpty()){
            userpassword.setError("Field cannot be empty");
            return false;
        }


        else {
            userpassword.setError(null);
            return true;
        }
    }
    private Boolean validatePhn(){
        String val =  userphn.getEditText().getText().toString();
        if (val.isEmpty()){
            userphn.setError("Field cannot be empty");
            return false;
        }
        else {
            userphn.setError(null);
            return true;
        }
    }
}
