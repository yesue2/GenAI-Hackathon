package com.example.tour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.data.PersonalityResponse;

public class PropencityResult extends AppCompatActivity {

    private String travelPreferences = "변화를 즐길 수 있는 여행지";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propencity_result);

        SharedPreferences sharedPreferences = getSharedPreferences("selectTravel",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("travelPreferences", travelPreferences);
        editor.commit();

        Button nextBtn = findViewById(R.id.pp_result_next_button);

        String character = getIntent().getStringExtra("character");
        String travelPreferences = getIntent().getStringExtra("travelPreferences");

        TextView tv_character = findViewById(R.id.tv_character);
        tv_character.setText('"' + character + '"');

        TextView tv_travelPreferences = findViewById(R.id.tv_travelPreferences);
        tv_travelPreferences.setText('"' + travelPreferences + '"');

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PropencityResult.this, SelectTravelArea.class);
                startActivity(intent);
            }
        });
    }
}
