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

public class PropencityQuestions3 extends AppCompatActivity {
    private int[] extraversion = new int[5];
    private List<Integer> selectedOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_propencity_travel);

        String opennessJson = getIntent().getStringExtra("opennessJson");
        String conscientiousnessJson = getIntent().getStringExtra("conscientiousnessJson");
        Log.d("PropencityQuestions3", "opennessJson" + opennessJson);
        Log.d("PropencityQuestions3", "opennessJson" + conscientiousnessJson);

        List<Question> questions = loadQuestionsFromGson();
        LinearLayout linearLayout = findViewById(R.id.question_linearlayout);

        String[] options = {"매우그렇다","그렇다","보통이다","그렇지않다","매우그렇지않다"};
        for (int i = 10; i < 15; i++) {
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
                        extraversion[i] = selectedOptions.get(i) + 1;
                    }

                    Gson gson = new Gson();
                    String extraversionJson = gson.toJson(extraversion);

                    Intent intent = new Intent(PropencityQuestions3.this, PropencityQuestions4.class);
                    intent.putExtra("opennessJson", opennessJson);
                    intent.putExtra("conscientiousnessJson", conscientiousnessJson);
                    intent.putExtra("extraversionJson", extraversionJson);
                    startActivity(intent);
                } else {
                    Toast.makeText(PropencityQuestions3.this, "모든 질문에 답변해주세요.", Toast.LENGTH_SHORT).show();
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
