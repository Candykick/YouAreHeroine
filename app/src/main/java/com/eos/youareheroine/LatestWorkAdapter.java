package com.eos.youareheroine;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LatestWorkAdapter extends RecyclerView.Adapter<LatestWorkAdapter.Holder>{

    private Context context;
    private ArrayList<LatestWork_data> dataList;

    public LatestWorkAdapter(Context context, ArrayList<LatestWork_data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        protected ConstraintLayout latest_container;
        protected ImageView latest_iv_novel_pic;
        protected TextView latest_tv_name;
        protected TextView latest_tv_episode;
        protected TextView latest_tv_title;
        protected TextView latest_tv_hashtag;
        protected TextView latest_tv_watcher;
        protected TextView latest_tv_comment;
        protected TextView latest_tv_zzim;
        protected ImageView latest_iv_isEnd;


        public Holder(View view){
            super(view);
            this.latest_container = view.findViewById(R.id.latest_container);
            this.latest_iv_novel_pic = view.findViewById(R.id.latest_iv_novel_pic);
            this.latest_tv_name = view.findViewById(R.id.latest_tv_name);
            this.latest_tv_episode = view.findViewById(R.id.latest_tv_episode);
            this.latest_tv_title = view.findViewById(R.id.latest_tv_title);
            this.latest_tv_hashtag = view.findViewById(R.id.latest_tv_hashtag);
            this.latest_tv_watcher = view.findViewById(R.id.latest_tv_watcher);
            this.latest_tv_comment = view.findViewById(R.id.latest_tv_comment);
            this.latest_tv_zzim = view.findViewById(R.id.latest_tv_zzim);
            this.latest_iv_isEnd = view.findViewById(R.id.latest_iv_isEnd);
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.latest_cell, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LatestWorkAdapter.Holder holder,final int position) {
        //TODO: 작품 시간 순으로 정렬하기(내림차순)
        holder.latest_tv_name.setText(dataList.get(position).author_name);
        holder.latest_tv_episode.setText(" | 총 " + dataList.get(position).episode + "화");

        String title = dataList.get(position).title;
        if(title.length() > 16){
            title = title.substring(0, 15) + "…";
        }
        holder.latest_tv_title.setText(title);

        String hash_tag = dataList.get(position).hash_tag;
        hash_tag = hash_tag.replace(", ", " #");
        holder.latest_tv_hashtag.setText("#" + hash_tag);

        holder.latest_tv_watcher.setText("조회수 " + dataList.get(position).watcher);
        holder.latest_tv_comment.setText("댓글수 " + dataList.get(position).comment);
        holder.latest_tv_zzim.setText("찜꽁수 " + dataList.get(position).zzim);

        if(dataList.get(position).isEnd){
            holder.latest_iv_isEnd.setVisibility(View.VISIBLE);
        }
        else{
            holder.latest_iv_isEnd.setVisibility(View.INVISIBLE);
        }

        Glide.with(context).load(dataList.get(position).image).into(holder.latest_iv_novel_pic);

        holder.latest_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(this, NovelPageActivity.class);
//                intent.putExtra("id", dataList.get(position).id);
//                context.startActivity(intent);
                Toast.makeText(context, "Clicked" + dataList.get(position).author_name, Toast.LENGTH_SHORT).show();
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
