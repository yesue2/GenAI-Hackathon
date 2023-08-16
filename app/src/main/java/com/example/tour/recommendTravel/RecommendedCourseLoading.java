package com.example.tour.recommendTravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.tour.R;
import com.example.tour.data.Api;
import com.example.tour.data.RecommendRequest;
import com.example.tour.data.RecommendResponse;
import com.example.tour.data.RetrofitClient;
import com.example.tour.question.PropencityQuestions;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedCourseLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);

        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        RecommendRequest request = new RecommendRequest();

        JSONObject jsonObject = new JSONObject();
        String area = sharedPreferences.getString("area", "default Area");
        String member = sharedPreferences.getString("member", "default member");
        int day = Integer.parseInt(sharedPreferences.getString("day", "default startDate"));
        String travelPreferences = sharedPreferences.getString("travelPreferences", "default endDate");
        try{
            request.setDesiredLocation(area);
            request.setTravelType(member);
            request.setTravelDuration(String.valueOf(day));
            request.setTravelPreferences(travelPreferences);

            Call<RecommendResponse> call = api.getTravelRecommend(request);

            Log.d("callRetrofit", "call start");
            call.enqueue(new Callback<RecommendResponse>() {
                @Override
                public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                    Log.d("onResponse", "get response");
                    if (response.isSuccessful()) {
                        Log.d("responseSuccessful", "is succesfull");
                        RecommendResponse result = response.body();

                        if(result.get_embedded() == null){
                            Log.d("null", "true");
                            Log.d("null", response.body().toString());
                            Log.d("null", result.toString());
                        }
                        else {
                            Log.d("Retrofit", "Data sent susccessfully");
                            Log.d("Retrofit", result.get_embedded().getTravelSuggestionsList().toString());
                            List<RecommendResponse.TravelSuggestions> travelList = result.get_embedded().getTravelSuggestionsList();
                            Intent intent = new Intent(RecommendedCourseLoading.this, RecommendedCourseLoadingFinish.class);
                            intent.putExtra("travelSuggestionsList", (Serializable) travelList);
                            startActivity(intent);

                        }

                    } else {
                        Log.d("Retrofit", response.code() + " : Data not sent");

                        Intent intent = new Intent(RecommendedCourseLoading.this, PropencityQuestions.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<RecommendResponse> call, Throwable t) {
                        Log.d("Retrofit", "Error: " + t.getMessage());

                        Intent intent = new Intent(RecommendedCourseLoading.this, PropencityQuestions.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();                 }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}