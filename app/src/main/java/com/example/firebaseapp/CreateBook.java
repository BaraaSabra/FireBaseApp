package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.YuvImage;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityCreateBookBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CreateBook extends BaseActivity {
    ActivityCreateBookBinding binding;
    private static final int GALIERY_INTENT = 1000;
    private Uri mainImageURL;
    //يعنى انا وين بدي احط بيناتي
    StorageReference storageReference;
    ProgressDialog progressDialog;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    CreateBookClass createBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("CreateBookClass");

        createBook = new CreateBookClass();

        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALIERY_INTENT);
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainImageURL.getPath() != null) {
                    UpLodImage();
                }
                String bookName = binding.etName.getText().toString();
                String AutheName = binding.edAuthor.getText().toString();
                String year = binding.edYear.getText().toString();
                String pageNumber = binding.edPage.getText().toString();
                String Category = binding.catogre.getText().toString();
                if (TextUtils.isEmpty(bookName) && TextUtils.isEmpty(AutheName) && TextUtils.isEmpty(year) && TextUtils.isEmpty(pageNumber)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(CreateBook.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(bookName, AutheName, year, Integer.parseInt(pageNumber), Category);
                }
            }

//                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
//                startActivity(intent);

        });

    }

    private void addDatatoFirebase(String bookeName, String AutherName, String Releaseyear, int pagesNumber, String Category) {
        // below 3 lines of code is used to set
        // data in our object class.
        createBook.setBookName(bookeName);
        createBook.setAutherName(AutherName);
        createBook.setReleaseYear(Releaseyear);
        createBook.setPagesNumer(pagesNumber);
        createBook.setCategory(Category);
        // we are use add value event listener method
        // which is called with database reference.

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child((createBook.getBookName())).setValue(createBook);
                Toast.makeText(CreateBook.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CreateBook.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                Log.d("errorfile",error.getMessage());

            }
        });

    }

    private void UpLodImage() {
        ShowProgress();
        //حعمل ملف اسمه images وبدخله ححط الصوره حسب uid
        storageReference = firebaseStorage.getReference("images/" + currentuser.getUid() + "/" + mainImageURL.getLastPathSegment());
        StorageTask<UploadTask.TaskSnapshot> uploadTask = storageReference.putFile(mainImageURL);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateBook.this,
                                "Image Uploded", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        Toast.makeText(CreateBook.this,
                                "Image Uploded failure" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Image Uploded failure", e.getMessage());
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        //بطعطي من مئه كم ضايل ليحمل
                        progressDialog.setMessage("Uploaded" + (int) progress + "%");
                        progressDialog.setProgress((int) progress);
                        Log.d("UploadActivity", "Upload is" + progress + "% done");

                    }
                });
    }

    public void ShowProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploding");
        progressDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                mainImageURL = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(mainImageURL);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                binding.imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}