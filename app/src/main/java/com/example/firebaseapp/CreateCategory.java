package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityCreateCategoryBinding;

public class CreateCategory extends AppCompatActivity {
    ActivityCreateCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}