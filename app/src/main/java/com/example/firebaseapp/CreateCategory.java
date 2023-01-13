package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityCreateCategoryBinding;

import java.util.ArrayList;

public class CreateCategory extends BaseActivity {
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


        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        fragments.add(BlankFragment.newInstance(""));
        BookAdapter adapter=new BookAdapter(this,fragments);
        binding.VP.setAdapter(adapter);






    }
}