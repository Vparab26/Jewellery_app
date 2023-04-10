package com.example.kisan_suvidha.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kisan_suvidha.R;
import com.example.kisan_suvidha.models.Usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[!@#$%^&+=])"+
                    "(?=\\s+$])"+
                    ".{6,}"+
                    "$");

    Button signUp;
    EditText name,email,password;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signUp = findViewById(R.id.lgbtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.edit_text2);
        password = findViewById(R.id.edit_text3);
        signIn = findViewById(R.id.signin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }

        });
    }
    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!PASSWORD_PATTERN.matcher(userPassword).matches()){
            Toast.makeText(this, "Password is to weak", Toast.LENGTH_SHORT).show();

        }
        if (userPassword.length()<6){
            Toast.makeText(this, "Password length must be greater than 6 letter", Toast.LENGTH_SHORT).show();
            return;
        }
        //create user
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Usermodel usermodel = new Usermodel(userName,userEmail,userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(usermodel);
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}