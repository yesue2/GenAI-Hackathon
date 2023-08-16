package com.example.tour.recommendTravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tour.R;
import com.example.tour.TravelRecord;
import com.example.tour.data.RecommendResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RecommendCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course);

        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        String C = sharedPreferences.getString("character", "defualtName");
        TextView tvTitle = findViewById(R.id.recommended_result_title);
        tvTitle.setText('"' + C + '"' + "를 위한\n여행 코스 추천입니다.");

        List<RecommendResponse.TravelSuggestions> list = (List<RecommendResponse.TravelSuggestions>) getIntent().getSerializableExtra("travelList");
        LinearLayout ll = findViewById(R.id.recommended_result_linear);
        AtomicReference<RadioButton> checkedRadioButton = new AtomicReference<>();

        for (RecommendResponse.TravelSuggestions item : list) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);

            RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.travel_select_day_button, ll, false);
            switch(item.getTravelDay()) {
                case 1 : radioButton.setText("첫째날");
                break;
                case 2 : radioButton.setText("둘째날");
                break;
                case 3 : radioButton.setText("셋째날");
                break;
                default: radioButton.setText(String.valueOf(item.getTravelDay()));
                break;
            }
            TextView tv = findViewById(R.id.recommended_inner_text);
            String s = item.getContent();
            tv.setText(item.getContent());
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        RadioButton previousCheckedRadioButton = checkedRadioButton.get();
                        if(previousCheckedRadioButton != null) {
                            previousCheckedRadioButton.setChecked(false);
                        }
                        checkedRadioButton.set(radioButton);
                    }
                }
            });
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView x = findViewById(R.id.recommended_inner_text);
                    x.setText(s);
                }
            });
            ll.addView(radioButton);

        }



        Button button = findViewById(R.id.course2_nextbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendCourse.this, TravelRecord.class);
                startActivity(intent);
            }
        });

    }
}