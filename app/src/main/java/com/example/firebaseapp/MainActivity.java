package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=binding.etLoginUsername.getText().toString();
                String Passwerd=binding.etPassword.getText().toString();
                if (!TextUtils.isEmpty(Email)&& !TextUtils.isEmpty(Passwerd) )
                    Login(Email, Passwerd);
            }
        });

    }

    private void Login(String email, String passwerd) {
        mAuth.signInWithEmailAndPassword(email, passwerd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(MainActivity.this, "Welcome"+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(getBaseContext(), "Authentication failed." +task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("erro",task.getException().toString());
                        }

                    }
                });
    }
}