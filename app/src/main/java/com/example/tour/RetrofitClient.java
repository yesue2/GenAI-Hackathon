package com.example.tour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.example.com/"; // API의 기본 URL을 여기에 기입

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Gson 컨버터를 사용하여 JSON 데이터를 자바 객체로 자동 변환
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}