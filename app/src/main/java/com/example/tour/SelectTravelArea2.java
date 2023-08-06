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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class SelectTravelArea2 extends AppCompatActivity {
    protected List<String> TravelCountry;
    protected int countryIndex;
    protected String coutry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_travel_area);
        //여행기본정보 페이지 1번
        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent it = getIntent();
        String answer = it.getStringExtra("TravelArea");


        List<CurrentTrip> tripGroups = loadCurrentTripFromGson();
        for (int i = 0; i < tripGroups.size(); i++) {
            if(Objects.equals(tripGroups.get(i).getCity(), answer)) {
                TravelCountry = tripGroups.get(i).getCountry();
                countryIndex = i;
                break;
            }
        }
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        AtomicReference<RadioButton> checkedRadioButton = new AtomicReference<>();
        for (int i = 0; i < TravelCountry.size(); i++) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);

            RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.travel_item_button,flexboxLayout, false );

            radioButton.setText(TravelCountry.get(i));
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
                        coutry = checkedRadioButton.get().getText().toString();
                        Log.d("SelectTravelArea2", "SelectedCoutry: " + coutry);
                        //todo: id 혹은 고유값을 받아서 저장되어야됨
                        editor.putString("area", coutry);
                        editor.commit();
                    }
                }
            });
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tv = findViewById(R.id.tv_selectMember);
        tv.setText("아름다운 "+ answer+"!"+"\n 그 중 어디를 가볼까요?");

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SelectTravelArea2.this, SelectTravelPeriod.class);
                    startActivity(intent);
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
