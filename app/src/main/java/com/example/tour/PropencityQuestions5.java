package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropencityQuestions5 extends AppCompatActivity {
    private int[] answers;
    private List<Integer> selectedOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_propencity_travel);

        double avg1 = getIntent().getDoubleExtra("avg1", 0.0);
        double avg2 = getIntent().getDoubleExtra("avg2", 0.0);
        double avg3 = getIntent().getDoubleExtra("avg3", 0.0);
        double avg4 = getIntent().getDoubleExtra("avg4", 0.0);
        Log.d("PropencityQuestions4", "avg1: " + avg1);
        Log.d("PropencityQuestions4", "avg2: " + avg2);
        Log.d("PropencityQuestions4", "avg3: " + avg3);
        Log.d("PropencityQuestions4", "avg4: " + avg4);

        List<Question> questions = loadQuestionsFromGson();
        LinearLayout linearLayout = findViewById(R.id.question_linearlayout);

        String[] options = {"매우그렇다","그렇다","보통이다","그렇지않다","매우그렇지않다"};
        for (int i = 20; i < 25; i++) {
            Question question = questions.get(i);
            TextView textView = new TextView(this);
            textView.setText(question.getText());
            linearLayout.addView(textView);

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOrientation(RadioGroup.HORIZONTAL);
            for (int j = 0; j < options.length; j++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(options[j]);
                radioGroup.addView(radioButton);
            }
            linearLayout.addView(radioGroup);
        }

        Button nextBtn = findViewById(R.id.propencity_submit_button);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allQuestionsAnswered()) {
                    int sum = 0;
                    for (int optionIndex : selectedOptions) {
                        sum += (optionIndex + 1);
                    }
                    double average = (double) sum / selectedOptions.size();
                    Log.d("PropencityQuestions5", "Average: " + average);

                    String avg1Status = getAverageStatus(avg1);
                    String avg2Status = getAverageStatus(avg2);
                    String avg3Status = getAverageStatus(avg3);
                    String avg4Status = getAverageStatus(avg4);
                    String avg5Status = getAverageStatus(average);

                    Log.d("PropencityQuestions5", "avg1Status: " + avg1Status);
                    Log.d("PropencityQuestions5", "avg2Status: " + avg2Status);
                    Log.d("PropencityQuestions5", "avg3Status: " + avg3Status);
                    Log.d("PropencityQuestions5", "avg4Status: " + avg4Status);
                    Log.d("PropencityQuestions5", "avg5Status: " + avg5Status);


                    Intent intent = new Intent(PropencityQuestions5.this, PropencityResult.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PropencityQuestions5.this, "모든 질문에 답변해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean allQuestionsAnswered() {
        LinearLayout linearLayout = findViewById(R.id.question_linearlayout);
        selectedOptions.clear();

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof RadioGroup) {
                RadioGroup radioGroup = (RadioGroup) childView;
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    return false;
                } else {
                    int optionIndex = radioGroup.indexOfChild(findViewById(checkedRadioButtonId));
                    selectedOptions.add(optionIndex);
                }
            }
        }
        return true;
    }


    private List<Question> loadQuestionsFromGson() {
        List<Question> questions = new ArrayList<>();
        try {
            InputStream is = getAssets().open("question.json");
            Reader reader = new InputStreamReader(is, "UTF-8");

            Gson gson = new Gson();
            questions = Arrays.asList(gson.fromJson(reader, Question[].class));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private String getAverageStatus(double avg) {
        if (avg >= 3.0) {
            return "high";
        } else {
            return "low";
        }
    }
}
