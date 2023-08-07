package com.example.tour;

import com.example.tour.data.RecommendResponse;
import com.example.tour.data.TravelData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface TravelService {
    @POST("hackathon2023/travel_suggestions")
    Call<RecommendResponse> sandTravelData(@Body TravelData travelData);


}

