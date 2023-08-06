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

import org.json.JSONException;
import org.json.JSONObject;

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

                    Call<Void> call = service.sandTravelData(jsonObject);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Log.d("Retrofit", "Data sent susccessfully");
                                Log.d("Retrofit", response.toString());
                                Intent intent = new Intent(SelectTravelMember.this, RecommendCourse.class);

                                startActivity(intent);
                            } else {
                                Log.d("Retrofit", "Data not sent");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("Retrofit", "Error: " + t.getMessage());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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