package com.example.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecommendCourse2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course2);
        TextView day = findViewById(R.id.course2_day);
        TextView morning = findViewById(R.id.course2_morning);
        TextView afternoon = findViewById(R.id.course2_afternoon);
        Intent intent = getIntent();
        List<Course.Information> information = (List<Course.Information>) intent.getSerializableExtra("information");
        if (information != null) {
            Log.d("Information", information.toString());
            Course.Information info = information.get(0);
            day.setText(info.getDay());
            morning.setText(info.getMorning());
            afternoon.setText(info.getAfternoon());

        } else {
            Log.d("Information", "Information is null");
        }

        Button button = findViewById(R.id.course2_nextbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendCourse2.this, TravelRecord.class);
                startActivity(intent);
            }
        });

    }
}