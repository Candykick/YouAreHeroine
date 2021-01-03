package com.eos.youareheroine.MyPage;

public class MPMyWorkData {
    public int id;
    public String title;
    public String image;
    public String date;
    public int watcher;
    public int comment;
    public boolean upload;
    public boolean isEnd;


    public MPMyWorkData(int id, String title, int watcher, int comment, String date, boolean upload, String img, boolean isEnd) {
        this.id = id;
        this.title = title;
        this.watcher = watcher;
        this.comment = comment;
        this.date = date;
        this.upload = upload;
        this.image = img;
        this.isEnd = isEnd;
    }
}
