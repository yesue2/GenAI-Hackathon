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
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropencityQuestions4 extends AppCompatActivity {
    private int[] agreeableness = new int[5];
    private List<Integer> selectedOptions = new ArrayList<>();
    private AnswerModel viewModel;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_propencity_travel);

        MyApplication myApplication = (MyApplication) getApplicationContext();
        viewModel = myApplication.getSharedViewModel();

        List<Question> questions = loadQuestionsFromGson();
        LinearLayout linearLayout = findViewById(R.id.question_linearlayout);

        String[] options = {"매우그렇다","그렇다","보통이다","그렇지않다","매우그렇지않다"};
        for (int i = 15; i < 20; i++) {
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
                    for (int i = 0; i < selectedOptions.size(); i++) {
                        agreeableness[i] = selectedOptions.get(i) + 1;
                    }

                    viewModel.setAgreeablenessData(agreeableness);
                    int[] agreeablenessData = viewModel.getAgreeablenessData().getValue();
                    if (agreeablenessData != null) {
                        Log.d("PropencityQuestions4", "agreeableness data: " + Arrays.toString(agreeablenessData));
                    }

                    Intent intent = new Intent(PropencityQuestions4.this, PropencityQuestions5.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PropencityQuestions4.this, "모든 질문에 답변해주세요.", Toast.LENGTH_SHORT).show();
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
}
