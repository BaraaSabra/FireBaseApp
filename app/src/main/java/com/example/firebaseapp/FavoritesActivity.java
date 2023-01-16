package com.example.firebaseapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.firebaseapp.databinding.ActivityFavoritesBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends BaseActivity  {
    ActivityFavoritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<CreateBookClass>BookNme=new ArrayList<>();
        CreateBookClass createBookClass=new CreateBookClass();

        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(BookNme, this, new Favorite() {
            @Override
            public String Favarite(String BookName) {

                    databaseReference.child(currentuser.getUid()).push().setValue(BookName).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("bookName",createBookClass.getBookName() );
                        }
                    });
                    {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                return BookName;
            }

            @Override
            public String unfavarite(String BookName) {
                return BookName;
            }

        });
        binding.RV.setAdapter(favoriteAdapter);
        binding.RV.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL,false));




    }
}