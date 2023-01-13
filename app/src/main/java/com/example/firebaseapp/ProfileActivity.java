package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuta=FirebaseAuth.getInstance();
      currentuser=mAuta.getCurrentUser();

//        UpdateProfile();
//        UpdateEmail();
//        UpdatePassword();

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
}