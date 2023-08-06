package com.example.tour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.data.Api;
import com.example.tour.data.PersonalityResponse;
import com.example.tour.data.RecommendRequest;
import com.example.tour.data.RecommendResponse;
import com.example.tour.data.RetrofitClient;
import com.example.tour.data.TravelSuggestion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectTravelMember extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_travel_member);
        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        RadioGroup dispositionRadioGroup = findViewById(R.id.dispositionRadioGroup);
        dispositionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                Log.d("SelectTravelMember","SelectedMember: "+ radioButton.getText().toString());
                editor.putString("member", radioButton.getText().toString());
                editor.commit();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tv = findViewById(R.id.tv_selectMember);
        String text = tv.getText().toString();
        Spannable spannable = new SpannableString(text);
        int startIndex = text.indexOf("여행원 구성");
        int endIndex = startIndex + "여행원 구성".length();
        spannable.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1.2f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLoadingActivity();

                RecommendRequest request = new RecommendRequest();
                String area = sharedPreferences.getString("area", "default Area");
                String member = sharedPreferences.getString("member", "default member");
                int day = Integer.parseInt(sharedPreferences.getString("day", "default startDate"));
                String travelPreferences = sharedPreferences.getString("travelPreferences", "default endDate");

                    request.setDesiredLocation(area);
                    request.setTravelType(member);
                    request.setTravelDuration(String.valueOf(day));
                    request.setTravelPreferences(travelPreferences);

                Log.d("Retrofit", "setArea: " + area);
                Log.d("Retrofit", "setMember: " + member);
                Log.d("Retrofit", "setDay: " + day);
                Log.d("Retrofit", "setTravel Preferences: " + travelPreferences);


                Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
                    Call<RecommendResponse> call = api.getTravelRecommend(request);

                    call.enqueue(new Callback<RecommendResponse>() {
                        @Override
                        public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("Retrofit", "Data sent susccessfully");

                                RecommendResponse recommendResponse = response.body();
                                if (recommendResponse != null) {
                                    List<TravelSuggestion> travelSuggestions = recommendResponse.getEmbedded().getTravelSuggestionsList();

                                    ArrayList<Integer> travelDaysList = new ArrayList<>();
                                    ArrayList<String> contentsList = new ArrayList<>();

                                    for (TravelSuggestion suggestion : travelSuggestions) {
                                        int travelDay = suggestion.getTravelDay();
                                        String content = suggestion.getContent();
                                        Log.d("Retrofit", "Travel Day: " + travelDay);
                                        Log.d("Retrofit", "Content: " + content);

                                        travelDaysList.add(travelDay);
                                        contentsList.add(content);
                                    }

                                    Intent intent = new Intent(SelectTravelMember.this, RecommendCourse.class);
                                    intent.putIntegerArrayListExtra("travelDays", travelDaysList);
                                    intent.putStringArrayListExtra("contents", contentsList);
                                    startActivity(intent);
                                } else {
                                    Log.d("Retrofit", "Data not sent");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RecommendResponse> call, Throwable t) {
                            Log.d("Retrofit", "Error: " + t.getMessage());
                        }
                    });

            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTravelMember.this, SelectTravelPeriod.class);
                startActivity(intent);
            }
        });
    }
    private void startLoadingActivity() {
        Intent loadingIntent = new Intent(SelectTravelMember.this, Loading.class);
        startActivity(loadingIntent);
    }
}