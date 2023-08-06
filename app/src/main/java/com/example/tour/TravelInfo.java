package com.example.tour;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TravelInfo extends AppCompatActivity {

    private Button selectedArea, startDateButton, endDateButton;
    private RadioGroup memberRadioGroup;
    private RadioButton selectedMemberRadioButton;

    private Spinner destinationSpinner;

    private Calendar startDateCalendar, endDateCalendar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);
        memberRadioGroup = findViewById(R.id.memberRadioGroup);
        destinationSpinner = findViewById(R.id.destinationSpinner);

        startDateCalendar = Calendar.getInstance();
        endDateCalendar = Calendar.getInstance();

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateCalendar, startDateButton);
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endDateCalendar, endDateButton);
            }
        });

        // 여행지 선택 드롭다운 메뉴 초기화
        ArrayAdapter<CharSequence> destinationAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.destination_array,
                android.R.layout.simple_spinner_item
        );
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(destinationAdapter);

        // 여행지 선택 리스너 등록
        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedArea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무것도 선택하지 않았을 때의 처리
            }
        });

        Button createPlanButton = findViewById(R.id.createPlanButton);
        createPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedMemberRadioButtonId = memberRadioGroup.getCheckedRadioButtonId();
                selectedMemberRadioButton = findViewById(selectedMemberRadioButtonId);

                String selectedArea = destinationSpinner.getSelectedItem().toString();
                String selectedStartDate = formatDate(startDateCalendar);
                String selectedEndDate = formatDate(endDateCalendar);
                String selectedMember = selectedMemberRadioButton.getText().toString();

                // 결과를 로그로 출력
                String result = "선택된 지역: " + selectedArea +
                        "\n출발일: " + selectedStartDate +
                        "\n도착일: " + selectedEndDate +
                        "\n구성원: " + selectedMember;

                // 결과 출력
                Log.d("TravelPlanActivity", result);
            }
        });
    }

    // Date를 yyyy-MM-dd 형식으로 변환하는 메서드
    private String formatDate(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    private void showDatePickerDialog(final Calendar calendar, final Button button) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateButtonLabel(button, calendar);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // 현재 날짜 이후의 날짜만 선택 가능하도록 설정
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void updateButtonLabel(Button button, Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        button.setText(sdf.format(calendar.getTime()));
    }
}
