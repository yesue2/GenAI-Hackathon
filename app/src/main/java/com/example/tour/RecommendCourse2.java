package com.example.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tour.data.RecommendResponse;

import java.util.List;

public class RecommendCourse2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course2);
        List<RecommendResponse.TravelSuggestions> list = (List<RecommendResponse.TravelSuggestions>) getIntent().getSerializableExtra("travelList");
        LinearLayout ll = findViewById(R.id.recommended_LinearLayout);
        ScrollView sv = findViewById(R.id.recommended_scroll_view);

        for (RecommendResponse.TravelSuggestions item : list) {
            Button btn = new Button(this);
            TextView tv = findViewById(R.id.recommended_inner_text);
            String s = item.getContent();
            tv.setText(item.getContent());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView x = findViewById(R.id.recommended_inner_text);
                    x.setText(s);
                }
            });
            btn.setText(String.valueOf(item.getTravelDay()));;
            ll.addView(btn);

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