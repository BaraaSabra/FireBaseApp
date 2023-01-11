package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityCreateBookBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton floatingActionButton;
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

//                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void UpLodImage() {
        ShowProgress();
        //حعمل ملف اسمه images وبدخله ححط الصوره حسب uid
        storageReference = firebaseStorage.getReference("images/" + currentuser.getUid() + "/" + mainImageURL.getLastPathSegment());
        StorageTask<UploadTask.TaskSnapshot> uploadTask = storageReference.putFile(mainImageURL);
        uploadTask   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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
//        if (requestCode ==GALIERY_INTENT && resultCode == RESULT_OK &&  null != data && data.getData()!=null ){

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                binding.imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }

        mainImageURL = data.getData();


////            try {
//////                InputStream inputStream = getContentResolver().openInputStream(mainImageURL);
//////                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
////                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),mainImageURL);
////                binding.imageView.setImageBitmap(bitmap);
////            }catch (IOException e){
////                e.printStackTrace();
////            }

    }
}