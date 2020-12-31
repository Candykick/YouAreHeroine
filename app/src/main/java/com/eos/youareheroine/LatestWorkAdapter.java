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
        holder.latest_tv_name.setText(dataList.get(position).writer_name);
        holder.latest_tv_episode.setText(dataList.get(position).episode);
        holder.latest_tv_title.setText(dataList.get(position).title);
        holder.latest_tv_hashtag.setText(dataList.get(position).hashtag);
        holder.latest_tv_watcher.setText("조회수 " + dataList.get(position).watcher);
        holder.latest_tv_comment.setText("댓글수 " + dataList.get(position).comment);
        holder.latest_tv_zzim.setText("찜꽁수 " + dataList.get(position).zzim);

        Glide.with(context).load(dataList.get(position).novel_pic).into(holder.latest_iv_novel_pic);

        holder.latest_container.setOnClickListener(new View.OnClickListener(){

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
