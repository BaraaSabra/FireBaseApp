package com.example.firebaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class BaseActivity extends AppCompatActivity {
    public  FirebaseAuth mAuta;
   public FirebaseUser currentuser ;
   public FirebaseStorage firebaseStorage;
   public DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        mAuta=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();



    }
}
