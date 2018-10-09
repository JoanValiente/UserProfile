package com.example.joanv.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.module.LibraryGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    private UserProfile profile;
    private ImageView user_image;
    private ImageView background_image;
    private TextView name_text;
    private TextView nick_text;
    private TextView about_text;
    private TextView following_text;
    private TextView followers_text;
    private Gson gson;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        name_text = findViewById(R.id.nameView);
        nick_text = findViewById(R.id.userView);
        about_text = findViewById(R.id.aboutView);
        following_text = findViewById(R.id.followingView);
        followers_text = findViewById(R.id.followersView);
        user_image = findViewById(R.id.userImage);
        background_image = findViewById(R.id.backgroundImage);

        try {
            InputStream stream = getAssets().open("profile.json");
            InputStreamReader reader = new InputStreamReader(stream);
            profile = gson.fromJson(reader, UserProfile.class);
        } catch (IOException exception) {
            Toast.makeText(this,"NOT WORKING",Toast.LENGTH_SHORT).show();
        }

        Glide.with(this)
                .load("file:///android_asset/background.jpg")
                .into(background_image);
        Glide.with(this)
                .load("file:///android_asset/user.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(user_image);



        UpdateProfile();
    }

    private void UpdateProfile(){
        name_text.setText(profile.getName());
        nick_text.setText(profile.getUser());
        followers_text.setText(profile.getFollowers());
        following_text.setText(profile.getFollowing());
        about_text.setText(profile.getAbout());
    }
}

