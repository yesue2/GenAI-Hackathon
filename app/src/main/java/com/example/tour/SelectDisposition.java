package com.example.tour;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectDisposition extends AppCompatActivity {

    private String selectedDisposition; // 선택된 성향



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_disposition);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RadioGroup dispositionRadioGroup = findViewById(R.id.dispositionRadioGroup);

        dispositionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.dispositionARadioButton:
                        selectedDisposition = "a";
                        break;
                    case R.id.dispositionBRadioButton:
                        selectedDisposition = "b";
                        break;
                    case R.id.dispositionCRadioButton:
                        selectedDisposition = "c";
                        break;
                    case R.id.dispositionDRadioButton:
                        selectedDisposition = "d";
                        break;
                }
            }
        });

        // 텍스트뷰 부분 볼드체로 변경
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvType = findViewById(R.id.tv_selectType);
        String textType = tvType.getText().toString();
        Spannable spannableType = new SpannableString(textType);
        int startTypeIndex = textType.indexOf("여행 타입");
        int endTypeIndex = startTypeIndex + "여행 타입".length();
        spannableType.setSpan(new StyleSpan(Typeface.BOLD), startTypeIndex, endTypeIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableType.setSpan(new RelativeSizeSpan(1.2f), startTypeIndex, endTypeIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvType.setText(spannableType);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvA = findViewById(R.id.dispositionARadioButton);
        String textA = tvA.getText().toString();
        Spannable spannableA = new SpannableString(textA);
        int startIndexA = textA.indexOf("자유로운 A타입");
        int endIndexA = startIndexA + "자유로운 A타입".length();
        spannableA.setSpan(new StyleSpan(Typeface.BOLD), startIndexA, endIndexA, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableA.setSpan(new RelativeSizeSpan(1.3f), startIndexA, endIndexA, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvA.setText(spannableA);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvB = findViewById(R.id.dispositionBRadioButton);
        String textB = tvB.getText().toString();
        Spannable spannableB = new SpannableString(textB);
        int startIndexB = textB.indexOf("계획적인 B타입");
        int endIndexB = startIndexB + "계획적인 B타입".length();
        spannableB.setSpan(new StyleSpan(Typeface.BOLD), startIndexB, endIndexB, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableB.setSpan(new RelativeSizeSpan(1.3f), startIndexB, endIndexB, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvB.setText(spannableB);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvC = findViewById(R.id.dispositionCRadioButton);
        String textC = tvC.getText().toString();
        Spannable spannableC = new SpannableString(textC);
        int startIndexC = textC.indexOf("혼자가는 C타입");
        int endIndexC = startIndexC + "혼자가는 C타입".length();
        spannableC.setSpan(new StyleSpan(Typeface.BOLD), startIndexC, endIndexC, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableC.setSpan(new RelativeSizeSpan(1.3f), startIndexC, endIndexC, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvC.setText(spannableC);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvD = findViewById(R.id.dispositionDRadioButton);
        String textD = tvD.getText().toString();
        Spannable spannableD = new SpannableString(textD);
        int startIndexD = textD.indexOf("함께하는 D타입");
        int endIndexD = startIndexD + "함께하는 D타입".length();
        spannableD.setSpan(new StyleSpan(Typeface.BOLD), startIndexD, endIndexD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableD.setSpan(new RelativeSizeSpan(1.3f), startIndexD, endIndexD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvD.setText(spannableD);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDisposition != null) {
                    // 선택된 성향을 로그로 출력
                    Log.d("SelectDisposition", "선택된 성향: " + selectedDisposition);

                    // TravelInfo 화면으로 넘어가기 위한 Intent 생성
                    Intent intent = new Intent(SelectDisposition.this, TravelInfo.class);
                    startActivity(intent);
                } else {
                    // 성향을 선택하지 않은 경우
                    Log.d("SelectDisposition", "성향을 선택해주세요.");
                }
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button myPageButton = findViewById(R.id.historyButton);
        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectDisposition.this, MyPage.class);
                startActivity(intent);
            }
        });
    }
}
