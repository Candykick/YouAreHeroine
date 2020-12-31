package com.eos.youareheroine;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WholeWorkAdapter extends RecyclerView.Adapter<WholeWorkAdapter.Holder>{

    private Context context;
    private ArrayList<WholeWork_data> dataList;

    public WholeWorkAdapter(Context context, ArrayList<WholeWork_data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        protected ConstraintLayout whole_container;
        protected ImageView whole_iv_novel_pic;
        protected TextView whole_tv_name;
        protected TextView whole_tv_episode;
        protected TextView whole_tv_title;
        protected TextView whole_tv_hashtag;
        protected TextView whole_tv_watcher;
        protected TextView whole_tv_comment;
        protected TextView whole_tv_zzim;


        public Holder(View view){
            super(view);
            this.whole_container = view.findViewById(R.id.whole_container);
            this.whole_iv_novel_pic = view.findViewById(R.id.whole_iv_novel_pic);
            this.whole_tv_name = view.findViewById(R.id.whole_tv_name);
            this.whole_tv_episode = view.findViewById(R.id.whole_tv_episode);
            this.whole_tv_title = view.findViewById(R.id.whole_tv_title);
            this.whole_tv_hashtag = view.findViewById(R.id.whole_tv_hashtag);
            this.whole_tv_watcher = view.findViewById(R.id.whole_tv_watcher);
            this.whole_tv_comment = view.findViewById(R.id.whole_tv_comment);
            this.whole_tv_zzim = view.findViewById(R.id.whole_tv_zzim);
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wholework_cell, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WholeWorkAdapter.Holder holder,final int position) {
        holder.whole_tv_name.setText(dataList.get(position).writer_name);
        holder.whole_tv_episode.setText(dataList.get(position).episode);
        holder.whole_tv_title.setText(dataList.get(position).title);
        holder.whole_tv_hashtag.setText(dataList.get(position).hashtag);
        holder.whole_tv_watcher.setText("조회수 " + dataList.get(position).watcher);
        holder.whole_tv_comment.setText("댓글수 " + dataList.get(position).comment);
        holder.whole_tv_zzim.setText("찜꽁수 " + dataList.get(position).zzim);

        Glide.with(context).load(dataList.get(position).novel_pic).into(holder.whole_iv_novel_pic);

        holder.whole_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, NovelPageActivity.class);
                intent.putExtra("id", dataList.get(position).id);
                context.startActivity(intent);
                //Toast.makeText(context, "Clicked" + dataList.get(position).writer_name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        if(dataList == null){
            return 0;
        }
        else{
            return dataList.size();
        }
    }
}
