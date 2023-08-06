package com.example.tour.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecommendResponse {
    @SerializedName("_embedded")
    private EmbeddedData embedded;

    public EmbeddedData getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedData embedded) {
        this.embedded = embedded;
    }
}
    class EmbeddedData {
        @SerializedName("travelSuggestionsList")
        private List<TravelSuggestion> travelSuggestionsList;

        public List<TravelSuggestion> getTravelSuggestionsList() {
            return travelSuggestionsList;
        }

        public void setTravelSuggestionsList(List<TravelSuggestion> travelSuggestionsList) {
            this.travelSuggestionsList = travelSuggestionsList;
        }
    }

    class TravelSuggestion {
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
