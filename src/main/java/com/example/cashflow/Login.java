package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashflow.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

   private FirebaseAuth mAuth;
  private EditText Email;
   private EditText Password;
   private Button loginButn;

   private TextView newUserText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        loginButn=findViewById(R.id.lgnButton);

        newUserText=findViewById(R.id.gotoRegister);

        newUserText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUP.class));
            }
        });


        loginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });



    }
    public void login() {
        String userEmail = Email.getText().toString().trim();
        String userPass = Password.getText().toString().trim();
        if (userEmail.isEmpty()) {
            Email.setError("email is required");
        }
        if (userPass.isEmpty()) {
            Password.setError("password is required");
        } else {
            mAuth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curentUser=mAuth.getCurrentUser();
        if(curentUser!=null)
        startActivity(new Intent(Login.this,MainActivity.class));
    }
}