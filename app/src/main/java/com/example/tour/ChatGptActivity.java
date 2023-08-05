package com.example.tour;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour.data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatGptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_gpt);

        // Retrofit 객체 생성
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ChatGptApi api = retrofit.create(ChatGptApi.class);

        // ChatRequest 객체를 생성하여 메시지를 설정
        Request.ChatRequest request = new Request.ChatRequest("Hello, how are you?");

        // API 호출 실행
        Call<Request.ChatResponse> call = api.sendMessage(request);
        call.enqueue(new Callback<Request.ChatResponse>() {
            @Override
            public void onResponse(Call<Request.ChatResponse> call, Response<Request.ChatResponse> response) {
                if (response.isSuccessful()) {
                    Request.ChatResponse chatResponse = response.body();
                    String message = chatResponse.getMessage();

                    // 서버로부터 받은 응답 처리
                    updateUiWithMessage(message);
                } else {
                    // API 호출은 성공했지만 서버가 오류 응답을 보낸 경우 처리
                    // ...
                }
            }

            @Override
            public void onFailure(Call<Request.ChatResponse> call, Throwable t) {
                // API 호출 실패 처리
                // ...
            }
        });
    }

    private void updateUiWithMessage(String message) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}

