package com.eos.youareheroine;


import android.content.Context;
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

public class FinishWorkAdapter extends RecyclerView.Adapter<FinishWorkAdapter.Holder>{

    private Context context;
    private ArrayList<FinishWork_data> dataList;

    public FinishWorkAdapter(Context context, ArrayList<FinishWork_data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        protected ConstraintLayout finish_container;
        protected ImageView finish_iv_novel_pic;
        protected TextView finish_tv_name;
        protected TextView finish_tv_episode;
        protected TextView finish_tv_title;
        protected TextView finish_tv_hash_tag;
        protected TextView finish_tv_watcher;
        protected TextView finish_tv_comment;
        protected TextView finish_tv_zzim;
        protected ImageView finish_iv_isEnd;


        public Holder(View view){
            super(view);
            this.finish_container = view.findViewById(R.id.finish_container);
            this.finish_iv_novel_pic = view.findViewById(R.id.finish_iv_novel_pic);
            this.finish_tv_name = view.findViewById(R.id.finish_tv_name);
            this.finish_tv_episode = view.findViewById(R.id.finish_tv_episode);
            this.finish_tv_title = view.findViewById(R.id.finish_tv_title);
            this.finish_tv_hash_tag = view.findViewById(R.id.finish_tv_hash_tag);
            this.finish_tv_watcher = view.findViewById(R.id.finish_tv_watcher);
            this.finish_tv_comment = view.findViewById(R.id.finish_tv_comment);
            this.finish_tv_zzim = view.findViewById(R.id.finish_tv_zzim);
            this.finish_iv_isEnd = view.findViewById(R.id.finish_iv_isEnd);
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.finishwork_cell, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FinishWorkAdapter.Holder holder, final int position) {
        holder.finish_tv_name.setText(dataList.get(position).author_name);
        holder.finish_tv_episode.setText(" | 총 " + dataList.get(position).episode + "화");

        String title = dataList.get(position).title;
        if(title.length() > 16){
            title = title.substring(0, 15) + "…";
        }
        holder.finish_tv_title.setText(title);

        String hash_tag = dataList.get(position).hash_tag;
        hash_tag = hash_tag.replace(", ", " #");
        holder.finish_tv_hash_tag.setText("#" + hash_tag);

        holder.finish_tv_watcher.setText("조회수 " + dataList.get(position).watcher);
        holder.finish_tv_comment.setText("댓글수 " + dataList.get(position).comment);
        holder.finish_tv_zzim.setText("찜꽁수 " + dataList.get(position).zzim);

        if(dataList.get(position).isEnd){
            holder.finish_iv_isEnd.setVisibility(View.VISIBLE);
        }
        else{
            holder.finish_iv_isEnd.setVisibility(View.INVISIBLE);
        }

        Glide.with(context).load(dataList.get(position).image).into(holder.finish_iv_novel_pic);

        holder.finish_container.setOnClickListener(new View.OnClickListener(){

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
