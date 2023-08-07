package com.example.tour.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmbeddedData {
    @SerializedName("travelSuggestionsList")
    private List<TravelSuggestion> travelSuggestionsList;

    public List<TravelSuggestion> getTravelSuggestionsList() {
        return travelSuggestionsList;
    }

    public void setTravelSuggestionsList(List<TravelSuggestion> travelSuggestionsList) {
        this.travelSuggestionsList = travelSuggestionsList;
    }
}
