package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBindings;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUP extends AppCompatActivity {

   private EditText inputEmail;
    private EditText inputPassword;
    private EditText cinputPassword;
    private Button signUpButn;
    private TextView alreadyUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        cinputPassword=findViewById(R.id.cinputPassword);
        signUpButn=findViewById(R.id.signButton);
        mAuth=FirebaseAuth.getInstance();
        String userEmail=inputEmail.getText().toString();
        String pass=inputPassword.getText().toString();
        String cinPass=cinputPassword.getText().toString();
        alreadyUser=findViewById(R.id.login1);

        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUP.this,Login.class));
            }
        });


        signUpButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (userEmail.isEmpty()) {
                    inputEmail.setError("email is required");
                }

                if (pass.isEmpty()) {
                    inputPassword.setError("password is required");
                }
                if (cinPass.isEmpty()) {
                    cinputPassword.setError("confirm password is required");
                }

                if (inputPassword.getText().toString() != cinputPassword.getText().toString())
                    Toast.makeText(SignUP.this, "Both Password do not match", Toast.LENGTH_SHORT);
                else {

                    mAuth.createUserWithEmailAndPassword(inputEmail.getText().toString(), inputPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUP.this, "User registered successfully", Toast.LENGTH_SHORT);
                                startActivity(new Intent(SignUP.this,MainActivity.class));
                            } else {
                                Toast.makeText(SignUP.this, "Registration unsuccessful" + task.getException(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });












    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null)
        {

            startActivity(new Intent(SignUP.this,MainActivity.class));
        }

    }
}