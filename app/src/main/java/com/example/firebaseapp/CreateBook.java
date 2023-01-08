package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityCreateBookBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateBook extends AppCompatActivity {
    ActivityCreateBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton floatingActionButton;
             Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
             intent.setType("image/*");
             startActivityForResult(intent,100);
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}