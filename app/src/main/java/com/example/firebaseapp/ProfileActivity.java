package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    private Uri mainImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuta=FirebaseAuth.getInstance();
      currentuser=mAuta.getCurrentUser();

binding.addimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (mainImageURL.getPath() != null) {
            UpLodImage();
        }
    }
});


        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            }
        });

        binding.btnUpdateEmali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UpdatePassword();
//                UpdateProfile();
                UpdateEmail();

            }
        });

        binding.btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePassword();
            }
        });
        binding.btnUpdateUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile();
            }
        });
    }

    private void UpdateEmail() {
        AuthCredential authCredential = EmailAuthProvider.getCredential(currentuser.getEmail(), binding.etPassword.getText().toString());
        currentuser.reauthenticate(authCredential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //اول اشي حيدخل كلمه السر اذا طلعت صح حيغير الايميل
                        if (task.isSuccessful()) {
                            currentuser.updateEmail(binding.etEmail.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //يعنلى حدث البيانات
                                                currentuser.reload();
                                                Toast.makeText(ProfileActivity.this, "Emali update " + currentuser.getEmail(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                task.getException().printStackTrace();
                                                Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        } else {
                            task.getException().printStackTrace();
                            Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void UpdateProfile() {
        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
                .setDisplayName(binding.etUsername.getText().toString())
                .build();
        currentuser.updateProfile(userProfileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //يعنلى حدث البيانات
                            currentuser.reload();
                            Toast.makeText(ProfileActivity.this, "profile update "+currentuser.getDisplayName(), Toast.LENGTH_SHORT).show();
                        }else {
                            task.getException().printStackTrace();
                            Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void UpdatePassword() {

        AuthCredential authCredential = EmailAuthProvider.getCredential(currentuser.getEmail(), binding.etOldPassword.getText().toString());
        currentuser.reauthenticate(authCredential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //اول اشي حيدخل كلمه السر اذا طلعت صح حيغير الايميل
                        if (task.isSuccessful()) {
                            currentuser.updatePassword(binding.etNewPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //يعنلى حدث البيانات
                                                currentuser.reload();
                                                Toast.makeText(ProfileActivity.this, "passwoord update " + currentuser.getEmail(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                task.getException().printStackTrace();
                                                Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        } else {
                            task.getException().printStackTrace();
                            Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
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
                        Toast.makeText(getBaseContext(),
                                "Image Uploded", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),
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