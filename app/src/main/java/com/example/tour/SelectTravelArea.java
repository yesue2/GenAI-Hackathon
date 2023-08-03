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

import java.util.concurrent.atomic.AtomicReference;

public class SelectTravelArea extends AppCompatActivity {
    //protected String TravelArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_travel_area);
        //여행기본정보 페이지 1번
        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // string 배열만큼 버튼 생성
        String[] radioBtnGroup = {"서울","부산","대구","인천","광주","대전","울산","세종"};
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);
        AtomicReference<RadioButton> checkedRadioButton = new AtomicReference<>();
        for (int i = 0; i < radioBtnGroup.length; i++) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);

            RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.travel_item_button,flexboxLayout, false );

            radioButton.setText(radioBtnGroup[i]);
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
                        //todo: id 혹은 고유값을 받아서 저장되어야됨
                        editor.putString("area", checkedRadioButton.get().getText().toString());
                        editor.commit();
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
                    Intent intent = new Intent(SelectTravelArea.this, SelectTravelPeriod.class);
                    //intent.putExtra("TravelArea", TravelArea);
                    startActivity(intent);
            }
        });

    }
}
