package com.example.tour;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Loading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_loading);

        LottieAnimationView loadingAnimationView = findViewById(R.id.man_tourist_in_plane);

        loadingAnimationView.playAnimation();

        Intent intent = new Intent(Loading.this, RecommendCourse.class);
        startActivity(intent);

        finish();
    }

}