package com.example.tour;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TravelRecord extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_record);

        Button savebtn = findViewById(R.id.record_btn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.editText);
                Log.d("saved text", text.getText().toString());
                Toast.makeText(TravelRecord.this, text.getText().toString(),Toast.LENGTH_SHORT);
                text.setText("");

            }
        });


    }
}

