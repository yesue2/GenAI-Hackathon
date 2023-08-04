package com.example.tour.data;

import com.google.gson.annotations.SerializedName;

public class PersonalityResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("openness")
    private String openness;

    @SerializedName("conscientiousness")
    private String conscientiousness;

    @SerializedName("extraversion")
    private String extraversion;

    @SerializedName("agreeableness")
    private String agreeableness;

    @SerializedName("neuroticism")
    private String neuroticism;

    @SerializedName("character")
    private String character;

    @SerializedName("travelPreferences")
    private String travelPreferences;
}
