package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TravelRecord extends AppCompatActivity {

    protected Feedback[] feedbacks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_record);

        Button savebtn = findViewById(R.id.record_btn);

        try{
        InputStream is = getAssets().open("feedback.json");
        Reader reader = new InputStreamReader(is, "UTF-8");
        Gson gson = new Gson();
        feedbacks = gson.fromJson(reader, Feedback[].class);
        Log.d("feedbacks", feedbacks.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.editText);
                String userAnswer = text.getText().toString();
                Log.d("saved text", userAnswer);

                Intent intent = new Intent(TravelRecord.this, TravelAlbum.class);
                intent.putExtra("user_answer", userAnswer);
                startActivity(intent);

                text.setText("");
            }
        });

    }
}

