package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TravelAlbum extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_album);

        String userAnswer = getIntent().getStringExtra("user_answer");

        TextView displayAnswer = findViewById(R.id.a1);
        displayAnswer.setText(userAnswer);

        Button btnnext = findViewById(R.id.next);



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TravelAlbum.this, RecommendNextTravel.class);
                startActivity(intent);
            }
        });

    }

}
