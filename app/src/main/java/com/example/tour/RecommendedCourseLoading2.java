package com.example.tour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tour.data.RecommendResponse;

import org.json.JSONException;
import org.json.JSONObject;

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
        setContentView(R.layout.activity_recommended_course_loading);

        ConstraintLayout loadingLayout = findViewById(R.id.loading);

        List<RecommendResponse.TravelSuggestions> travelSuggestionsList = (List<RecommendResponse.TravelSuggestions>) getIntent().getSerializableExtra("travelSuggestionsList");

        for (RecommendResponse.TravelSuggestions item : travelSuggestionsList) {
                Log.d("item-day", String.valueOf(item.getTravelDay()));
                Log.d("item-content", item.getContent());
        }





        Button button = new Button(this);

        button.setText("다음");

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;

        // 버튼에 LayoutParams 설정
        button.setLayoutParams(params);

        // 레이아웃에 버튼 추가
        loadingLayout.addView(button);



    }
}