package com.eos.youareheroine;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HallOfFameAdapter extends RecyclerView.Adapter<HallOfFameAdapter.Holder>{

    private Context context;
    private ArrayList<HallOfFame_data> dataList;

    public HallOfFameAdapter(Context context, ArrayList<HallOfFame_data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        protected ConstraintLayout rank_container;
        protected ImageView rank_iv_novel_pic;
        protected TextView rank_tv_name;
        protected TextView rank_tv_episode;
        protected TextView rank_tv_title;
        protected TextView rank_tv_hash_tag;
        protected TextView rank_tv_watcher;
        protected TextView rank_tv_comment;
        protected TextView rank_tv_zzim;
        protected TextView rank_tv_rank;
        protected ImageView rank_iv_isEnd;



        public Holder(View view){
            super(view);
            this.rank_container = view.findViewById(R.id.rank_container);
            this.rank_iv_novel_pic = view.findViewById(R.id.rank_iv_novel_pic);
            this.rank_tv_name = view.findViewById(R.id.rank_tv_name);
            this.rank_tv_episode = view.findViewById(R.id.rank_tv_episode);
            this.rank_tv_title = view.findViewById(R.id.rank_tv_title);
            this.rank_tv_hash_tag = view.findViewById(R.id.rank_tv_hash_tag);
            this.rank_tv_watcher = view.findViewById(R.id.rank_tv_watcher);
            this.rank_tv_comment = view.findViewById(R.id.rank_tv_comment);
            this.rank_tv_zzim = view.findViewById(R.id.rank_tv_zzim);
            this.rank_tv_rank = view.findViewById(R.id.rank_tv_rank);
            this.rank_iv_isEnd = view.findViewById(R.id.rank_iv_isEnd);

        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.halloffame_cell, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HallOfFameAdapter.Holder holder,final int position) {
        holder.rank_tv_name.setText(dataList.get(position).author_name);
        holder.rank_tv_episode.setText(" | 총 " + dataList.get(position).episode + "화");

        String title = dataList.get(position).title;
        if(title.length() > 13){
            title = title.substring(0, 12) + "…";
        }
        holder.rank_tv_title.setText(title);

        String hash_tag = dataList.get(position).hash_tag;
        hash_tag = hash_tag.replace(", ", " #");
        holder.rank_tv_hash_tag.setText("#" + hash_tag);


        holder.rank_tv_watcher.setText("조회수 " + dataList.get(position).watcher);
        holder.rank_tv_comment.setText("댓글수 " + dataList.get(position).comment);
        holder.rank_tv_zzim.setText("찜꽁수 " + dataList.get(position).zzim);
        holder.rank_tv_rank.setText((position+1) + "위  |");

        if(dataList.get(position).isEnd){
            holder.rank_iv_isEnd.setVisibility(View.VISIBLE);
        }
        else{
            holder.rank_iv_isEnd.setVisibility(View.INVISIBLE);
        }

        Glide.with(context).load(dataList.get(position).image).into(holder.rank_iv_novel_pic);

        holder.rank_container.setOnClickListener(new View.OnClickListener(){

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
