package com.eos.youareheroine;

public class HallOfFame_data {
    public String image;
    public String author_name;
    public String episode;
    public String title;
    public String hash_tag;
    public int watcher;
    public int comment;
    public int zzim;
    public int id;
    public boolean isEnd;

    public HallOfFame_data(String novel_pic, String writer_name, String episode, String title, String hashtag, int watcher, int comment, int zzim, int id, boolean isEnd){
        this.image = novel_pic;
        this.author_name = writer_name;
        this.episode = episode;
        this.title = title;
        this.hash_tag = hashtag;
        this.watcher = watcher;
        this.comment = comment;
        this.zzim = zzim;
        this.id = id;
        this.isEnd = isEnd;
    }
}
