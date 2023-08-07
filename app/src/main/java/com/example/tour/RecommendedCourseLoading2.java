package com.example.tour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tour.data.RecommendResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendedCourseLoading2 extends AppCompatActivity {

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
                Intent intent = new Intent(RecommendedCourseLoading2.this, RecommendCourse2.class);
                intent.putExtra("travelList",(Serializable) travelSuggestionsList);
                startActivity(intent);
            }
        });


    }
}