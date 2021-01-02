package com.eos.youareheroine;

public class NovelPageData {
    public int id;
    public int episode_num;
    public String title;
    public String image;
    public String date;
    public int watcher;
    public int comment;

    public NovelPageData(int id, int episode_num, String title, String image, String date, int watcher, int comment) {
        this.id = id;
        this.episode_num = episode_num;
        this.title = title;
        this.image = image;
        this.date = date;
        this.watcher = watcher;
        this.comment = comment;
    }
}
