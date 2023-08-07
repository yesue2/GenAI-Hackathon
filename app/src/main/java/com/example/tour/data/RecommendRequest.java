package com.example.tour.data;

public class RecommendRequest {
    private String travelPreferences;
    private String desiredLocation;
    private String travelDuration;
    private String travelType;

    public RecommendRequest() {

    }

    public String getTravelPreferences() { return travelPreferences;}
    public void setTravelPreferences(String travelPreferences) {this.travelPreferences = travelPreferences;}

    public String getDesiredLocation() { return desiredLocation;}
    public void setDesiredLocation(String desiredLocation) {this.desiredLocation = desiredLocation;}

    public String getTravelDuration() { return travelDuration;}
    public void setTravelDuration(String travelDuration) {this.travelDuration = travelDuration;}

    public String getTravelType() { return travelType;}
    public void setTravelType(String travelType) {this.travelType = travelType;}

}
