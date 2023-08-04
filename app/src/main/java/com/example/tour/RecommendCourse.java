package com.example.tour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendCourse extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course);

        List<Course> courses = loadCourseFromGson();
        Log.d("course", courses.get(0).toString());

        LinearLayout linearLayout = findViewById(R.id.recommended_Wrapper);

        for (Course course : courses){
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.recommend_course_item, null);
            TextView titleNumber =  item.findViewById(R.id.title_number);
            TextView title = item.findViewById(R.id.recommended_title);
            TextView hashtag = item.findViewById(R.id.recommended_subtitle);
            titleNumber.setText(String.valueOf(course.getId()));
            title.setText(course.getName());
            hashtag.setText(course.getHashtag());


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RecommendCourse.this, RecommendCourse2.class);
                    intent.putExtra("information", (Serializable) course.getInfomation());
                    startActivity(intent);
                }
            });
            linearLayout.addView((item));
        }
        Button nextBtn = findViewById(R.id.nextbtn1);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendCourse.this, TravelRecord.class);
                startActivity(intent);
            }
        });

}
    private List<Course> loadCourseFromGson() {
        List<Course> courses = new ArrayList<>();
        try {
            InputStream is = getAssets().open("course.json");
            Reader reader  = new InputStreamReader(is, "UTF-8");

            Gson gson = new Gson();
            courses = Arrays.asList(gson.fromJson(reader, Course[].class));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
}

