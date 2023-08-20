# 2023 관광분야 GEN AI 해커톤
**한국관광공사 주관**

## 💻 프로젝트 주제
Chat GPT를 활용한 **개인 성향 맞춤 여행코스 추천** 애플리케이션

## 📆 개발 기간
2023.08.03 ~ 07

## 👨‍👨‍👧‍👦 팀 구성
기획1, 안드로이드2, 백엔드1, 디자인1

## 📌 주요 기능
1. **Big Five Model기반**으로 **사용자의 성격유형을 분석**
2. **생성형 인공지능(Chat GPT)을 활용**하여 **사용자 성향 맞춤 여행 코스 분석** 및 다음 여행지 추천

## 📊 프로그램 구조
![시스템 흐름도](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/67e34b91-4ad7-4d78-bb30-68e2f91bda16)

## 📜 기능 설명
### 사용자의 성격유형 분석 및 결과 출력
![KakaoTalk_20230820_152314752](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/5632e08f-770a-41f6-ae43-c135109158ba)
![KakaoTalk_20230820_152314752_01](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/8d02005b-1a8f-49a0-8237-44ec6ca92fbd)
1. **동적인 설문지 질문 출력**: 설문지는 총 5개의 화면으로 구성되어 있으며, 질문지를 더 쉽게 업데이트하고 관리할 수 있도록 직접 액티비티에 질문을 하드코딩하지 않고 JSON 파일에 저장된 25개의 질문지를 동적으로 불러와 사용자에게 제시하도록 코드를 구현하였습니다.
2. **라디오 버튼을 사용한 UX 개선**: 질문에 대한 응답은 라디오버튼을 사용하며, 클릭시 선택된 옵션은 강조 표시되어 사용자 경험을 향상 및 개선했습니다. 또한 해당 페이지에 있는 모든 질문에 답하지 않았을 시 다음 페이지로의 이동을 제한하여 응답 누락을 방지하였습니다.
3. **Retrofit2 사용**: Retrofit2를 사용해 백엔드 서버와 연동하였습니다. Request 값으로 사용자 응답 데이터를 전송하여 성향 분석한 결과를 Response 값으로 받아와 사용자에게 표시하는 코드를 구현하였습니다.


### 희망 여행 정보 입력
![KakaoTalk_20230820_152314752_02](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/fc038943-c1fe-43b0-9cb8-a7002824f83d)
![KakaoTalk_20230820_152314752_03](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/67a06d1e-6c0f-474c-889f-f4eecfbb658e)
![KakaoTalk_20230820_152314752_04](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/67a4e29a-2369-4bec-beb2-2e00ddb0a15f)


### 추천 여행 코스 생성 로딩
![KakaoTalk_20230820_152314752_05](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/411b55c5-236c-4a4c-ada2-0c3d088ec436)


### 사용자 성향 맞춤 여행 코스 생성 완료
![KakaoTalk_20230820_152314752_06](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/68a22a9c-d61d-4035-b207-891c89fab179)

### 추천 여행 코스 만족도 설문조사 및 다음 여행지 추천 
![KakaoTalk_20230820_152314752_08](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/c6955a8b-b272-400f-9b6b-6a2cacd75cfe)
![KakaoTalk_20230820_152314752_09](https://github.com/yesue2/GenAI-Hackathon/assets/108323785/f2f370d6-3136-4c5f-abd1-578c53aa2bc5)

