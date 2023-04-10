package com.example.kisan_suvidha.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kisan_suvidha.MainActivity;
import com.example.kisan_suvidha.R;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    Button login, Signin;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(Welcome.this, MainActivity.class));
            Toast.makeText(this, "please wait! you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

        login = findViewById(R.id.login);
        Signin = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, Login.class));
            }
        });

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, Register.class));
            }
        });


    }
}