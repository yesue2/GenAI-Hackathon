package com.example.tour;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatGptApi {
    @POST("https://api.example.com/chat")
    Call<Request.ChatResponse> sendMessage(@Body Request.ChatRequest request);
}