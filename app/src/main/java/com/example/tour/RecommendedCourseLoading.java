package com.example.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class RecommendedCourseLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES) // 연결 타임아웃
                .readTimeout(10, TimeUnit.MINUTES) // 읽기 타임아웃
                .writeTimeout(10, TimeUnit.MINUTES) // 쓰기 타임아웃
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t-api-play.actionfriends.net/api/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TravelService service = retrofit.create(TravelService.class);

        JSONObject jsonObject = new JSONObject();
        String area = sharedPreferences.getString("area", "default Area");
        String member = sharedPreferences.getString("member", "default member");
        int day = Integer.parseInt(sharedPreferences.getString("day", "default startDate"));
        String travelPreferences = sharedPreferences.getString("travelPreferences", "default endDate");
        try{
            jsonObject.put("desiredLocation", area);
            jsonObject.put("travelType", member);
            jsonObject.put("travelDuration",day);
            jsonObject.put("travelPreferences", travelPreferences);

            Call<RecommendResponse> call = service.sandTravelData(jsonObject);
            Log.d("callRetrofit", "call start");
            call.enqueue(new Callback<RecommendResponse>() {
                @Override
                public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                    Log.d("onResponse", "get response");
                    if (response.isSuccessful()) {
                        RecommendResponse result = response.body();

                        List<RecommendResponse.TravelSuggestions> travelList = result.get_embedded().getTravelSuggestionsList();
                        Log.d("Retrofit", "Data sent susccessfully");
                        Log.d("Retrofit", result.get_embedded().getTravelSuggestionsList().toString());
                        Intent intent = new Intent(RecommendedCourseLoading.this, RecommendedCourseLoading2.class);
                        intent.putExtra("travelSuggestionsList", (Serializable) travelList);
                        startActivity(intent);
                    } else {
                        Log.d("Retrofit",  response.code() + " : Data not sent");
                    }
                }

                @Override
                public void onFailure(Call<RecommendResponse> call, Throwable t) {
                    Log.d("Retrofit", "Error: " + t.getMessage());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}