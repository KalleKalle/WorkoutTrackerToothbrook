package com.example.workouttrackertoothbrook.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workouttrackertoothbrook.Data.Group;
import com.example.workouttrackertoothbrook.Data.workoutModel;
import com.example.workouttrackertoothbrook.MainActivity;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText emailField;
    EditText passwordField, confirmPasswordField;
    EditText heightField, weightField;
    EditText nameField;
    Button signupButton;
    String userId;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.register_email);
        passwordField = findViewById(R.id.registerPassword);
        confirmPasswordField = findViewById(R.id.registerConfirmPassword);
        heightField = findViewById(R.id.heightRegister);
        weightField = findViewById(R.id.weightRegister);
        nameField= findViewById(R.id.nameRegister);
        signupButton =findViewById(R.id.signupButton);
        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        registerViewModel viewModel= new registerViewModel();

        signupButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password= passwordField.getText().toString();
            String confirmPassword= confirmPasswordField.getText().toString();
            int height= Integer.parseInt(heightField.getText().toString());
            double weight=  Double.parseDouble(weightField.getText().toString());
            String name= nameField.getText().toString();


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
                        userId= fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference= firestore.collection("users").document(userId);
                        Map<String,Object> user= new HashMap<>();
                        workoutModel model = workoutModel.getInstance();
                        model.getSelf().setEmail(email);
                        model.getSelf().setHeight(height);
                        model.getSelf().setWeight(weight);
                        model.getSelf().setName(name);
                        model.getSelf().setId(userId);
                        user.put("userData",model);
                        ArrayList<Group> groups= new ArrayList<>();
                        user.put("groups",groups);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("database","user is created successfully");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("database","error: "+ e.toString());
                            }
                        });
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