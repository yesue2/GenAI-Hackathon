package com.example.tour.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RecommendResponse {

    private final Embedded _embedded;

    public RecommendResponse(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    // Embedded 클래스와 TravelSuggestions 클래스도 마찬가지로 생성자와 getter만 정의

    public class Embedded {
        private final List<TravelSuggestions> travelSuggestionsList;

        public Embedded(List<TravelSuggestions> travelSuggestionsList) {
            this.travelSuggestionsList = travelSuggestionsList;
        }


        public List<TravelSuggestions> getTravelSuggestionsList() {
            return travelSuggestionsList;
        }
    }

    public class TravelSuggestions implements Serializable {
        private final int travelDay;
        private final String content;
        public TravelSuggestions(int travelDay, String content)  {
            this.travelDay = travelDay;
            this.content = content;
        }

        public int getTravelDay() {
            return travelDay;
        }

        public String getContent() {
            return content;
        }
    }



}
