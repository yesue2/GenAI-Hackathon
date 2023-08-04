package com.example.tour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecommendCourse extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course);

        LinearLayout linearLayout = findViewById(R.id.recommended_Wrapper);

        for (int i = 0; i < 3; i++){
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.recommend_course_item, null);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RecommendCourse.this, RecommendCourse2.class);
                    startActivity(intent);
                }
            });
            linearLayout.addView((item));
        }
    }
}

