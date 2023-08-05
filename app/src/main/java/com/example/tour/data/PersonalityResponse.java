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

    public Integer getId() {
        return id;
    }

    public String getOpenness() {
        return openness;
    }

    public String getConscientiousness() {
        return conscientiousness;
    }

    public String getExtraversion() {
        return extraversion;
    }

    public String getAgreeableness() {
        return agreeableness;
    }

    public String getNeuroticism() {
        return neuroticism;
    }

    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    public String getTravelPreferences() {
        return travelPreferences;
    }
    public void setTravelPreferences(String travelPreferences) {
        this.travelPreferences = travelPreferences;
    }
}
