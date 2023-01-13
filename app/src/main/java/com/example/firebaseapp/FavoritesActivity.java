package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityFavoritesBinding;

import java.util.ArrayList;

public class FavoritesActivity extends BaseActivity implements Favorite {
    ActivityFavoritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFavoritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    @Override
    public void Favarite(String expressions) {

    }

    @Override
    public void unfavarite(String expressions) {

    }
}