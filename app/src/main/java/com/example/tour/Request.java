package com.example.tour;

//API 요청을 위한 데이터 클래스 정의
public class Request {
    public static class ChatRequest { //채팅 요청
        private String message;

        public ChatRequest(String message) {
            this.message = message;
        }
    }

    public class ChatResponse {  //채팅 응답
        private String message;

        public String getMessage() {
            return message;
        }
    }

}
