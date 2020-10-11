package com.eos.youareheroine;

class Fame{
    public String writer_name;
    public String title;
    public int likes;
    public int watcher;
    public int comment;
    public String image;
        }

public class HallofFameAdapter {

    public int getItemCount(){
        return data.size;
    }
}
