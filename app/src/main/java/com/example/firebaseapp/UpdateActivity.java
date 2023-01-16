package com.example.firebaseapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebaseapp.databinding.ActivityUpdateBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends BaseActivity {
    ActivityUpdateBinding binding;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//
//
        Intent intent = getIntent();
        String bookName = intent.getStringExtra("Namebook");
        String AutherName = intent.getStringExtra("AutherName");
        String Category = intent.getStringExtra("Category");
        String pagerNumber = intent.getStringExtra("pageNumber");
        String ReleasYear = intent.getStringExtra("ReleasYear");

        binding.NameUpdate.setText(bookName);
        binding.AuthorUpdate.setText(AutherName);
        binding.PageUpdate.setText(String.valueOf(pagerNumber));
        binding.YearUpdate.setText(ReleasYear);
        binding.catogre.setText(Category);


//ArrayList<CreateBookClass> arrayList=new ArrayList<>();
//        ValueEventListener reference= FirebaseDatabase.getInstance().getReference("CreateBookClass")
//                .addValueEventListener(new ValueEventListener() {
//                    @SuppressLint("SuspiciousIndentation")
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        Log.d("bboks",snapshot.toString());
//                        Log.d("bboks",snapshot.getValue().toString());
//                        for (DataSnapshot dataSnapshot:snapshot.getChildren() ) {
//                            createBookClass = dataSnapshot.getValue(CreateBookClass.class);
//                            Log.d("cratebooksss", createBookClass.toString());
//
//
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        error.getCode();
//                        Log.d("errrrr",error.getMessage());
//
//                    }
//                });
//        Intent intent = getIntent();
//        CreateBookClass createBookClass = (CreateBookClass) intent.getSerializableExtra("book_update");
//        binding.NameUpdate.setText(createBookClass.getBookName());
//        binding.AuthorUpdate.setText(createBookClass.getAutherName());
//        binding.YearUpdate.setText(createBookClass.getReleaseYear());
//        binding.catogre.setText(createBookClass.getCategory());
//        binding.PageUpdate.setText(String.valueOf(createBookClass.getPagesNumer()));

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.NameUpdate.getText().toString();
                String Catagory=binding.catogre.getText().toString();
                String AutherName=binding.AuthorUpdate.getText().toString();
                String year=binding.YearUpdate.getText().toString();
                String page=binding.PageUpdate.getText().toString();
//         writeNewPost(name,Catagory,AutherName,year, Integer.parseInt(page));
//         onStarClicked("");
                final FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference ref = database.getReference("CreateBookClass");

                DatabaseReference usersRef = ref.child(page);
                DatabaseReference hopperRef = usersRef.child("bookName");
                Map<String, Object> hopperUpdates = new HashMap<>();
                hopperUpdates.put("bookName", name);

                hopperRef.updateChildren(hopperUpdates);

            }
        });

    }

//    private void writeNewPost(String bookName, String Catagory, String AutherName, String year,int page) {
//        // Create new post at /user-posts/$userid/$postid and at
//        // /posts/$postid simultaneously
//        String key = databaseReference.child("CreateBookClass").push().getKey();
//        CreateBookClass createBookClass = new CreateBookClass(bookName, Catagory, AutherName, page,year);
//        Map<String, Object> postValues = createBookClass.toMap();
//        ArrayList<CreateBookClass> createBookClasses=new ArrayList<>();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/CreateBookClass/" + key, postValues);
//        childUpdates.put("/good/" + bookName + "/" + key, postValues);
//
//        databaseReference.updateChildren(childUpdates);
//    }
//    private void onStarClicked(String uid, String key) {
//        Map<String, Object> updates = new HashMap<>();
//        updates.put("CreateBookClass/"+key+"/stars/"+uid, true);
//        updates.put("CreateBookClass/"+key+"/starCount", ServerValue.increment(1));
//        updates.put("user-posts/"+uid+"/"+key+"/stars/"+uid, true);
//        updates.put("user-posts/"+uid+"/"+key+"/starCount", ServerValue.increment(1));
//        databaseReference.updateChildren(updates);
//    }



}
