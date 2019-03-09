package com.example.gerard.socialapp.view;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerard.socialapp.GlideApp;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserDataActivity extends AppCompatActivity {

    Uri imageProfile;
    ImageView imagenLayout;
    TextView tvName;
    TextView tvDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        imagenLayout = findViewById(R.id.imageView2);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);

        String uid = getIntent().getStringExtra("uid");

        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                GlideApp.with(UserDataActivity.this)
                        .load(user.photoUrl)
                        .circleCrop()
                        .into(imagenLayout);

                tvName.setText(user.displayName);
                tvDescription.setText(user.email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

