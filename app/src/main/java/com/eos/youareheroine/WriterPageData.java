package com.eos.youareheroine;

public class WriterPageData {
    public int id; // 시리즈 id
    public String title; // 시리즈 제목
    public String image;
    public String date;
    public int series_num; // 시리즈 화수
    public boolean isEnd; // 완결 여부


    public WriterPageData(int id, String title, String image, String date, int series_num, boolean isEnd) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
        this.series_num = series_num;
        this.isEnd = isEnd;
    }
}
