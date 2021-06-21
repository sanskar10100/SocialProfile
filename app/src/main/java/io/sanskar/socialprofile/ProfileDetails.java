package io.sanskar.socialprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.sanskar.socialprofile.databinding.ActivityProfileDetailsBinding;

public class ProfileDetails extends AppCompatActivity {

    ActivityProfileDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Glide.with(this).load(currentUser.getPhotoUrl()).into(binding.imageUserPhoto);

        String displayName = currentUser.getDisplayName();
        if (displayName == null) {
            binding.textViewUsername.setText("Not Available");
        } else {
            binding.textViewUsername.setText(displayName);
        }

        String email = currentUser.getEmail();
        if (email == null) {
            binding.textViewUserMail.setText("Not Available");
        } else {
            binding.textViewUserMail.setText(email);
        }

        String phoneNumber = currentUser.getPhoneNumber();
        if (phoneNumber == null) {
            binding.textViewUserPhone.setText("Not Available");
        } else {
            binding.textViewUserPhone.setText(phoneNumber);
        }
    }

    public void onSignOutButtonClicked(View view) {
        AuthUI.getInstance().signOut(this);
        finish();
    }
}