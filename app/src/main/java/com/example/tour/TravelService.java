package com.example.tour;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TravelService {
    @POST("hackathon2023/travel_suggestions")
    Call<Void> sandTravelData(@Body JSONObject travelData);
}
