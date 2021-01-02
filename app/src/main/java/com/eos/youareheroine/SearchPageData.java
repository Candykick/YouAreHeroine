package com.eos.youareheroine;

public class SearchPageData {
    public int id; // 시리즈의 id
    public int author_id; // 작가 id
    public String author_name; // 작가명
    public String title; // 시리즈명
    public String image; // 이미지 주소
    public String date; // 생성날짜
    public String introduction; // 소개글
    public String hash_tag; // 해쉬태그
    public int series_num; // 시리즈 글 개수
    public int watcher; // 조회수
    public int comment; // 댓글수
    public int zzim; // 찜꽁수
    public boolean isEnd; // 완결 여부


    public SearchPageData(int id, int author_id, String author_name, String title, String image, String date, String introduction, String hash_tag, int series_num, int watcher, int comment, int zzim, boolean isEnd) {
        this.id = id;
        this.author_id = author_id;
        this.author_name = author_name;
        this.title = title;
        this.image = image;
        this.date = date;
        this.introduction = introduction;
        this.hash_tag = hash_tag;
        this.series_num = series_num;
        this.watcher = watcher;
        this.comment = comment;
        this.zzim = zzim;
        this.isEnd = isEnd;
    }
}
