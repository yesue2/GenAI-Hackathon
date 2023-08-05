package com.example.tour;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RecommendNextTravel extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_next_travel);

        List<NextTrip> trips = loadNexttripFromGson();

        Random random = new Random();
        int nextRandom = random.nextInt(trips.size());
        NextTrip trip = trips.get(nextRandom);
        TextView text = findViewById(R.id.area);
        TextView detail = findViewById(R.id.detail);

        text.setText(trip.getTravel());
        detail.setText(trip.getDescription());

    }

    private List<NextTrip> loadNexttripFromGson() {
        List<NextTrip> trips = new ArrayList<>();

        try{
            InputStream is = getAssets().open("nextTrip.json");
            Reader reader = new InputStreamReader(is, "UTF-8");

            Gson gson = new Gson();
            trips = Arrays.asList(gson.fromJson(reader, NextTrip[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trips;
    }
}
