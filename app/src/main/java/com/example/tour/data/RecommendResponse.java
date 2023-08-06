package com.example.tour.data;

import com.google.gson.annotations.SerializedName;

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
