package com.example.tour;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecommendCourse extends AppCompatActivity {

    private String selectedArea; // 선택된 여행지
    private String selectedStartDate; // 출발일
    private String selectedEndDate; // 도착일
    private String selectedMember; // 여행 구성원

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_course);

        // TravelInfo 액티비티로부터 여행 정보를 받아옴
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedArea = extras.getString("selectedArea");
            selectedStartDate = extras.getString("selectedStartDate");
            selectedEndDate = extras.getString("selectedEndDate");
            selectedMember = extras.getString("selectedMember");
        }

        // 여행 정보를 로그로 출력 (테스트용)
        Log.d("RecommendCourse", "선택된 지역: " + selectedArea);
        Log.d("RecommendCourse", "출발일: " + selectedStartDate);
        Log.d("RecommendCourse", "도착일: " + selectedEndDate);
        Log.d("RecommendCourse", "구성원: " + selectedMember);
    }
}

