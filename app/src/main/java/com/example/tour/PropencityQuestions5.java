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

import com.example.tour.data.Api;
import com.example.tour.data.PersonalityRequest;
import com.example.tour.data.PersonalityResponse;
import com.example.tour.data.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropencityQuestions5 extends AppCompatActivity {
    private int[] neuroticism = new int[5];
    private List<Integer> selectedOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_propencity_travel);

        String opennessJson = getIntent().getStringExtra("opennessJson");
        String conscientiousnessJson = getIntent().getStringExtra("conscientiousnessJson");
        String extraversionJson = getIntent().getStringExtra("extraversionJson");
        String agreeablenessJson = getIntent().getStringExtra("agreeablenessJson");
        Log.d("PropencityQuestions5", "opennessJson" + opennessJson);
        Log.d("PropencityQuestions5", "opennessJson" + conscientiousnessJson);
        Log.d("PropencityQuestions5", "extraversionJson" + extraversionJson);
        Log.d("PropencityQuestions5", "agreeablenessJson" + agreeablenessJson);

        Gson gson = new Gson();
        PersonalityRequest opennessRequest = gson.fromJson(opennessJson, PersonalityRequest.class);
        PersonalityRequest conscientiousnessRequest = gson.fromJson(conscientiousnessJson, PersonalityRequest.class);
        PersonalityRequest extraversionRequest = gson.fromJson(extraversionJson, PersonalityRequest.class);
        PersonalityRequest agreeablenessRequest = gson.fromJson(agreeablenessJson, PersonalityRequest.class);


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
                    for (int i = 0; i < selectedOptions.size(); i++) {
                        neuroticism[i] = selectedOptions.get(i) + 1;
                    }

                    PersonalityRequest request = new PersonalityRequest();

                    request.setOpenness1(opennessRequest.getOpenness1());
                    request.setOpenness2(opennessRequest.getOpenness2());
                    request.setOpenness3(opennessRequest.getOpenness3());
                    request.setOpenness4(opennessRequest.getOpenness4());
                    request.setOpenness5(opennessRequest.getOpenness5());

                    request.setConscientiousness1(conscientiousnessRequest.getConscientiousness1());
                    request.setConscientiousness2(conscientiousnessRequest.getConscientiousness2());
                    request.setConscientiousness3(conscientiousnessRequest.getConscientiousness3());
                    request.setConscientiousness4(conscientiousnessRequest.getConscientiousness4());
                    request.setConscientiousness5(conscientiousnessRequest.getConscientiousness5());

                    request.setExtraversion1(extraversionRequest.getExtraversion1());
                    request.setExtraversion2(extraversionRequest.getExtraversion2());
                    request.setExtraversion3(extraversionRequest.getExtraversion3());
                    request.setExtraversion4(extraversionRequest.getExtraversion4());
                    request.setExtraversion5(extraversionRequest.getExtraversion5());

                    request.setAgreeableness1(agreeablenessRequest.getAgreeableness1());
                    request.setAgreeableness2(agreeablenessRequest.getAgreeableness2());
                    request.setAgreeableness3(agreeablenessRequest.getAgreeableness3());
                    request.setAgreeableness4(agreeablenessRequest.getAgreeableness4());
                    request.setAgreeableness5(agreeablenessRequest.getAgreeableness5());

                    request.setNeuroticism1(neuroticism[0]);
                    request.setNeuroticism2(neuroticism[1]);
                    request.setNeuroticism3(neuroticism[2]);
                    request.setNeuroticism4(neuroticism[3]);
                    request.setNeuroticism5(neuroticism[4]);

                    sendDataToServer(request);
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

    String character = "";
    String travelPreferences ="";
    private void sendDataToServer(PersonalityRequest request) {
        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<PersonalityResponse> call = api.getTravelPersonality(request);
        call.enqueue(new Callback<PersonalityResponse>() {
            @Override
            public void onResponse(
                    Call<PersonalityResponse> call,
                    Response<PersonalityResponse> response) {
                if (response.isSuccessful()) {
                    character = response.body().toString();
                    travelPreferences = response.body().toString();
                    Intent intent = new Intent(PropencityQuestions5.this, PropencityResult.class);
                    intent.putExtra("character", character);
                    intent.putExtra("travelPreferences", travelPreferences);

                    startActivity(intent);
                } else {
                }
            }

            @Override
            public void onFailure(Call<PersonalityResponse> call, Throwable t) {
                Log.d("PropencityQuestions5", "getTravelPersonality" + t);
            }
        });
    }
}
