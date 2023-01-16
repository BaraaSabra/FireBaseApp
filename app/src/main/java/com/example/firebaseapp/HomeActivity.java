package com.example.firebaseapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeActivity extends BaseActivity {
    ActivityHomeBinding binding;
    private static final String TAG = "DeleteProfileActivity";
    private Uri mainImageURL;
    public FirebaseUser firebaseUser;
    private FirebaseAuth authprofile;
    public static final String POST_NOTIFICATIONS = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authprofile = FirebaseAuth.getInstance();
        firebaseUser = authprofile.getCurrentUser();


        FirebaseMessaging.getInstance().subscribeToTopic("NotificationApp")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });



        binding.cardaddnewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateBook.class);
                startActivity(intent);
            }
        });
        binding.cardfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FavoritesActivity.class);
                startActivity(intent);
            }
        });
        binding.cardlibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LibraryActivity.class);
                startActivity(intent);
            }
        });
        binding.imProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
            }
        });

        binding.Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuta.signOut();
            }
        });


       binding.btnCrach.setText("Test Crash");
        binding.btnCrach.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });


    }
//
//    private void deletUser(FirebaseUser firebaseUser) {
//        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    DeleteUserData();
//                    authprofile.signOut();
//                    Toast.makeText(HomeActivity.this, "User has been deleted", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    try {
//                        throw task.getException();
//                    } catch (Exception e) {
//                        Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//        });
//
//
//    }
//
//
//    private void DeleteUserData() {
//        firebaseStorage = FirebaseStorage.getInstance();
//        StorageReference storageReference = firebaseStorage.getReference("images/" + currentuser.getUid() + "/" + mainImageURL.getLastPathSegment());
//        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Log.d(TAG, "OnSuccess: photo Deleted");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, e.getMessage());
//                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }

//    private  void Delet(){
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("CreateBookClass");
//        databaseReference.removeValue();
//        Toast.makeText(this, "the data is deleted", Toast.LENGTH_SHORT).show();
//    }
// Declare the launcher at the top of your Activity/Fragment:
private final ActivityResultLauncher<String> requestPermissionLauncher =
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // TODO: Inform user that that your app will not show notifications.
            }
        });

}