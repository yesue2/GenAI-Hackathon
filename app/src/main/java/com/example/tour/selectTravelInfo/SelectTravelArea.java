package com.example.tour.selectTravelInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class SelectTravelArea extends AppCompatActivity {
    protected String TravelArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_travel_area);

        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        // string 배열만큼 버튼 생성
        List<CurrentTrip> tripGroups = loadCurrentTripFromGson();
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        AtomicReference<RadioButton> checkedRadioButton = new AtomicReference<>();
        for (int i = 0; i < tripGroups.size(); i++) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);

            RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.travel_item_button,flexboxLayout, false );

            radioButton.setText(tripGroups.get(i).getCity());
            radioButton.setId(View.generateViewId());

            // FlexboxLayout에 RadioButton 추가
            flexboxLayout.addView(radioButton);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        RadioButton previousCheckedRadioButton = checkedRadioButton.get();
                        if (previousCheckedRadioButton != null) {
                            previousCheckedRadioButton.setChecked(false);
                        }
                        checkedRadioButton.set(radioButton);
                        TravelArea = checkedRadioButton.get().getText().toString();
                    }
                }
            });
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tv = findViewById(R.id.tv_selectMember);
        String text = tv.getText().toString();
        Spannable spannable = new SpannableString(text);
        int startIndex = text.indexOf("여행 지역");
        int endIndex = startIndex + "여행 지역".length();
        spannable.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1.2f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(Objects.equals(TravelArea, "세종특별자치시")){
                        editor.putString("area", "세종특별자치시");
                        editor.commit();
                        Intent intent = new Intent(SelectTravelArea.this, SelectTravelPeriod.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(SelectTravelArea.this, SelectTravelArea2.class);
                        intent.putExtra("TravelArea", TravelArea);
                        startActivity(intent);
                    }
            }
        });

    }

    private List<CurrentTrip> loadCurrentTripFromGson() {
        List<CurrentTrip> trips = new ArrayList<>();
        try {
            InputStream is = getAssets().open("currentTrip.json");
            Reader reader = new InputStreamReader(is, "UTF-8");
            Gson gson = new Gson();
            trips = Arrays.asList(gson.fromJson(reader, CurrentTrip[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trips;
    }
}
