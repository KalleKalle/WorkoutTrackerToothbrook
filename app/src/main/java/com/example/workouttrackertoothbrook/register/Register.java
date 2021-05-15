package com.example.workouttrackertoothbrook.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workouttrackertoothbrook.MainActivity;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText emailField;
    EditText passwordField, confirmPasswordField;
    Button signupButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.register_email);
        passwordField = findViewById(R.id.registerPassword);
        confirmPasswordField = findViewById(R.id.registerConfirmPassword);
        signupButton =findViewById(R.id.signupButton);
        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        registerViewModel viewModel= new registerViewModel();

        signupButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password= passwordField.getText().toString();
            String confirmPassword= confirmPasswordField.getText().toString();
            if(email.isEmpty()){
                emailField.setError("Email is required");
                return;
            }
            else if(password.isEmpty()){
                passwordField.setError("password is required");
                return;
            }
            else if(confirmPassword.isEmpty()){
                confirmPasswordField.setError("confirm password is required");
                return;
            }
            else if(!password.equals(confirmPassword)){
                confirmPasswordField.setError("passwords do not match");

                return;
            }
            else if(passwordField.length()<8){
                passwordField.setError("Password must be longer than 7 characters");
            }

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this,"User created",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(Register.this,"Error!!! "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

        });

    }
}