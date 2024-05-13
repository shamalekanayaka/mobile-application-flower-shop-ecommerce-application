package com.example.thebloomroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText emailEditext, passwordEditext;
    Button  signUpButton;

    TextView textView;

    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fAuth = FirebaseAuth.getInstance();

        emailEditext = findViewById(R.id.editTextTextEmailAddress);
        passwordEditext = findViewById(R.id.editTextTextPassword);
        signUpButton = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditext.getText().toString().trim();
                String password = passwordEditext.getText().toString();

                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignUp.this, "SignUp sucessful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, LoginActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this, "Signup Failed " + e , Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}