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

import com.example.kisan_suvidha.MainActivity;
import com.example.kisan_suvidha.R;
import com.example.kisan_suvidha.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {



    Button signIn;
    EditText email,password;
    TextView signUp;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signIn = findViewById(R.id.log_in);
        email = findViewById(R.id.editt1);
        password = findViewById(R.id.editt2);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
                progressBar.setVisibility(View.VISIBLE);

                if (email.getText().toString().equals("Users")&& password.getText().toString().equals("Users")){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();

                }
                if (email.getText().toString().equals("Users")){
                    Toast.makeText(Login.this, "It is valid Email", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Login.this, "It is not valid Email", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().equals("Users")){
                    Toast.makeText(Login.this, "Password is correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Login.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void loginUser() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length()<6){
            Toast.makeText(this, "Password length must be greater than 6 letter", Toast.LENGTH_SHORT).show();
            return;
        }
        //login user
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Error"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}