package com.example.tour.data;

import com.google.gson.annotations.SerializedName;

public class TravelSuggestion {
    @SerializedName("travelDay")
    private int travelDay;

    @SerializedName("content")
    private String content;

    public int getTravelDay() {
        return travelDay;
    }

    public void setTravelDay(int travelDay) {
        this.travelDay = travelDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
