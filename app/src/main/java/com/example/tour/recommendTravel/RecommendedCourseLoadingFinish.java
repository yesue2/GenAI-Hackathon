package com.example.tour.recommendTravel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.R;
import com.example.tour.data.RecommendResponse;

import java.io.Serializable;
import java.util.List;

public class RecommendedCourseLoadingFinish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_end);

        List<RecommendResponse.TravelSuggestions> travelSuggestionsList = (List<RecommendResponse.TravelSuggestions>) getIntent().getSerializableExtra("travelSuggestionsList");

        for (RecommendResponse.TravelSuggestions item : travelSuggestionsList) {
                Log.d("item-day", String.valueOf(item.getTravelDay()));
                Log.d("item-content", item.getContent());
        }

        Button nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendedCourseLoadingFinish.this, RecommendCourse.class);
                intent.putExtra("travelList",(Serializable) travelSuggestionsList);
                startActivity(intent);
            }
        });


    }
}