package com.example.tour;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelectTravelPeriod extends AppCompatActivity {

    protected String startDate;
    protected String endDate;
    protected int curYear, curMonth, curDay;

    protected Date startDay = null;
    protected Date endDay = null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_travel_period);
        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Button startBtn = findViewById(R.id.startDateButton);
        Button endBtn = findViewById(R.id.endDateButton);
        endBtn.setEnabled(false);

        // 출발일 선택 버튼 클릭 이벤트
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DatePickerDialog 생성
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectTravelPeriod.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 사용자가 선택한 날짜를 버튼의 텍스트로 설정
                        startBtn.setText(year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        curYear = year;
                        curMonth = month;
                        curDay = dayOfMonth;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        startDate = dateFormat.format(calendar.getTime());
                        endBtn.setEnabled(true);
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                // DatePickerDialog 표시
                datePickerDialog.show();
            }
        });
        // 도착일 선택 버튼 클릭 이벤트
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //DatePickerDialog 생성
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectTravelPeriod.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                         //사용자가 선택한 날짜를 버튼의 텍스트로 설정
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        endBtn.setText(year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                        endDate = dateFormat.format(calendar.getTime());
                    }
                }, curYear,curMonth,curDay);

                Calendar date = Calendar.getInstance();
                date.set(curYear, curMonth, curDay);
                datePickerDialog.getDatePicker().setMinDate(date.getTimeInMillis());
                date.add(Calendar.DAY_OF_MONTH,2);
                datePickerDialog.getDatePicker().setMaxDate(date.getTimeInMillis());

                // DatePickerDialog 표시
                datePickerDialog.show();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tv = findViewById(R.id.tv_selectPeriod);
        String text = tv.getText().toString();
        Spannable spannable = new SpannableString(text);
        int startIndex = text.indexOf("여행 기간");
        int endIndex = startIndex + "여행 기간".length();
        spannable.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1.2f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if( startDate != null & endDate != null){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        startDay = dateFormat.parse(startDate);
                        endDay = dateFormat.parse(endDate);

                        long diff = endDay.getTime() - startDay.getTime();
                        long diffDays = (diff / (24 * 60 * 60 * 1000)) + 1;
                        Log.d("SelectTravelPeriod", "SelectedDays: " + diffDays);

                        editor.putString("day", String.valueOf(diffDays));
                        editor.commit();

                        Intent intent = new Intent(SelectTravelPeriod.this, SelectTravelMember.class);
                        startActivity(intent);
                    }

                } catch(ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTravelPeriod.this, SelectTravelArea.class);
                startActivity(intent);
            }
        });
    }
}
