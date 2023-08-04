package com.example.tour;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.data.PersonalityResponse;

public class PropencityResult extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_propencity_result);

        Button nextBtn = findViewById(R.id.pp_result_next_button);

        Intent intent = getIntent();
        PersonalityResponse character = (PersonalityResponse) intent.getSerializableExtra("character");
        PersonalityResponse travelPreferences = (PersonalityResponse) intent.getSerializableExtra("travelPreferences");


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PropencityResult.this, SelectDisposition.class);
                startActivity(intent);
            }
        });
    }
}
