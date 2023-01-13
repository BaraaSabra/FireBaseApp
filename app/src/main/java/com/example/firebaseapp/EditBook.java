package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityEditBookBinding;

public class EditBook extends BaseActivity {
    ActivityEditBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = getIntent();
//        String bookName = intent.getStringExtra("Namebook");
//        String AutherName = intent.getStringExtra("AutherName");
//        String Category = intent.getStringExtra("Category");
//        String pagerNumber = intent.getStringExtra("pageNumber");
//        String ReleasYear = intent.getStringExtra("ReleasYear");
//
//        binding.tvtName.setText(bookName);
//        binding.tvAuthor.setText(AutherName);
//        binding.tvPage.setText(String.valueOf(pagerNumber));
//        binding.tvYear.setText(ReleasYear);
//        binding.catogre.setText(Category);
//
//        binding.btnedite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1=new Intent(getBaseContext(),UpdateActivity.class);
//                startActivity(intent1);
//            }
//        });
//    }

        
    }
}