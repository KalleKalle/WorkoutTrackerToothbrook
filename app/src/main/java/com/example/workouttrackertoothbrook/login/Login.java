package com.example.workouttrackertoothbrook.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workouttrackertoothbrook.MainActivity;
import com.example.workouttrackertoothbrook.R;
import com.example.workouttrackertoothbrook.register.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView register;
    EditText emailField;
    EditText passwordField;
    Button signin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.dontHaveUserRegister);
        emailField = findViewById(R.id.loginEmail);
        passwordField= findViewById(R.id.loginPassword);
        signin= findViewById(R.id.signinButton);
        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        register.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });

        signin.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password= passwordField.getText().toString();
            if(email.isEmpty()){
                emailField.setError("Email is required");
                return;
            }
            else if(password.isEmpty()){
                passwordField.setError("password is required");
                return;
            }

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Logged in", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Error!!! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        });


    }
}