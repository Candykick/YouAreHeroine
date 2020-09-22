# 2020.09.22 회의내용

## 앱 소개(by 이가은)
![1](http://drive.google.com/uc?export=view&id=1p7knh4BfBp1hzEpSM-HQW6pLSSr_hP7R)
![2](http://drive.google.com/uc?export=view&id=1mR66qS2zZAQJ8eVJ2fJho75NEbheybCQ)
![3](http://drive.google.com/uc?export=view&id=1CZDqvYSSYiQt9YfggUbMI01vFku6wLMC)
![4](http://drive.google.com/uc?export=view&id=1EdtXOo6bWewYBLwXZC5drWqemp4Wj5H0)
![5](http://drive.google.com/uc?export=view&id=12worb7voHldZv6FZwrJFw2TxFzZwJFf5)
![6](http://drive.google.com/uc?export=view&id=1O2ex062sY_oRYn_tJN_7bvjX_fY34evt)
![7](http://drive.google.com/uc?export=view&id=1XVaEe2yg0AsPc2LtJc7tCvXRkhqJJuSz)
![8](http://drive.google.com/uc?export=view&id=1376n4trJ31KTR0lP9BKq62yOE77l0pXZ)


## 필요한 기능
- 글 목록, 글 보기(사진 포함), 글 쓰기(사진 포함)
- 작가 페이지, 시리즈별 페이지
- SNS 로그인 : 로그인을 해야 글쓰기/댓글 가능.
- 마이페이지(작가 구독, 시리즈 구독)
- 검색 : 제목, 작가, 연예인 검색


## 세부사항
- 글에 음악도 들어갈 수 있다.(아직 필수사항은 아님. 앱 내에서 재생되도 되고, 링크만 달 수 있어도 되고...)
- 이름 후보 : 너도 여주다 (근데 너무 노린 듯한 이름 같다는 의견 있어서, 더 상의가 필요.)
- 글쓰기 : 로그인한 유저가 글을 올리면, 해당 글은 일단 저장된 상태로 두고, 관리자가 검수. 검수 통과 시 글이 업로드된다.
- 글쓰는 화면을 어떻게 만들어야할지  : 블로그 글 쓰는 방법을 사용.
- 검수 : 수위가 높은 글이나, 문제가 될 만한 글들만 제외하고 통과시킴. 현 단계에서는 글의 완성도나 작품성 등은 고려하지 않는다.
- 관리자 페이지 : 등록된 글을 검수 통과/반려 처리할 수 있는 기능이 필요하다.
- 검색 : 해시태그 검색 방식.
- 작품 페이지에 간단한 작품소개, 해시태그 게시하기.
- 연예인 목록 : 글을 등록하면서, 연예인 이름이 해시태그로 등록될 때마다 자동으로 DB에 추가되도록 구현.


## 기술 스택
- 프론트엔드 : 안드로이드 앱(Java)
- 백엔드 : Flask(Python 서버) + Postgres(DB, 아직 확실하진 않음) + AWS?(미정)


## 역할 분담
관리자 페이지 - 우수몽
디자인 - 이가은
백엔드/데이터베이스 설계 - 우수몽
벡엔드/Flask 서버, API 명세서 - 김이영
안드로이드 앱 - 이서진, 김이영, 우수몽


## 1주차 업무
이가은 - UX 디자인, git 공부
김이영 - UX 디자인, Flask 공부, git 공부
이서진 - UX 디자인, 안드로이드(자바) 공부, git 공부
우수몽 - DB 설계, 관리자 페이지 조사
* 디자인 UX 화면을 미리 짜놓으면(컴포넌트 배치 등) 이걸 바탕으로 간략한 화면과 기능을 만들 수 있다. 안드로이드 앱이 이 작업을 진행하는 사이 UI를 진행하고, UI 디자인을 나중에 씌운다.


## 향후 해야 하는 것들과 일정
※ 9/28, 10/26, 11/9 - 팀별 진행상황 발표
- 로고 디자인
- 앱 이름 정하기


## 의견
- 웹페이지 필요할텐데? : 글쓰기는 컴퓨터가 더 편하지만, 현재 상황상 웹은 어렵다는 결론.
