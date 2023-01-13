package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.firebaseapp.databinding.ActivityLibraryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LibraryActivity extends BaseActivity {
    ActivityLibraryBinding binding;
    CreateBookClass createBookClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<CreateBookClass> arrayList=new ArrayList<>();
        LiabraryAdapter liabraryAdapter=new LiabraryAdapter(arrayList, LibraryActivity.this, new Details() {
            @Override
            public void OnClike(int postion) {
                CreateBookClass createBookClass=arrayList.get(postion);
                Intent intent=new Intent(getBaseContext(),UpdateActivity.class);

                intent.putExtra("Namebook",createBookClass.getBookName());
                intent.putExtra("AutherName",createBookClass.getAutherName());
                intent.putExtra("Category",createBookClass.getCategory());
                intent.putExtra("pageNumber",String.valueOf(createBookClass.getPagesNumer()));
                intent.putExtra("ReleasYear",createBookClass.getReleaseYear());
                startActivity(intent);
            }
        });
        binding.RV.setHasFixedSize(true);
        binding.RV.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        binding.RV.setAdapter(liabraryAdapter);
//
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("CreateBookClass");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot:snapshot.getChildren() ) {
//                    CreateBookClass createBook=dataSnapshot.getValue(CreateBookClass.class);
//                    String name=createBook.getBookName();
//                    arrayList.add(createBook);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        ValueEventListener reference= FirebaseDatabase.getInstance().getReference("CreateBookClass")
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Log.d("bboks",snapshot.toString());
                            Log.d("bboks",snapshot.getValue().toString());
                        for (DataSnapshot dataSnapshot:snapshot.getChildren() ) {
                            createBookClass = dataSnapshot.getValue(CreateBookClass.class);
                            Log.d("cratebooksss", createBookClass.toString());
                            arrayList.add(createBookClass);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        error.getCode();
                        Log.d("errrrr",error.getMessage());

                    }
                });
    }
}