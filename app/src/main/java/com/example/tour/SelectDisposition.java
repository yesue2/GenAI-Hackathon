package com.example.tour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

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
        Button myPageButton = findViewById(R.id.myPageButton);
        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectDisposition.this, MyPage.class);
                startActivity(intent);
            }
        });
    }
}
