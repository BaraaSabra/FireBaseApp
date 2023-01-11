package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.firebaseapp.databinding.ActivityLibraryBinding;

import java.util.ArrayList;

public class LibraryActivity extends BaseActivity {
    ActivityLibraryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<CreateLibraryClass> arrayList=new ArrayList<>();
        LiabraryAdapter liabraryAdapter=new LiabraryAdapter(arrayList,LibraryActivity.this);
        binding.RV.setHasFixedSize(true);
        binding.RV.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        binding.RV.setAdapter(liabraryAdapter);


    }
}