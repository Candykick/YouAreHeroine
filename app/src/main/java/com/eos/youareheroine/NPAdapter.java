package com.eos.youareheroine;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NPAdapter extends RecyclerView.Adapter<NPAdapter.Holder> {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<NovelPageData> data;

    public NPAdapter(Context context, ArrayList<NovelPageData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) { // 셀 레이아웃 불러옴
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.np_cell, parent, false);
        return new Holder(view);
    }

    /*
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }*/

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int pos) {
        // cell에 모든 데이터 넣어줌. 제 위치에.
        holder.np_tv_novel_num.setText(data.get(pos).episode_num + "화: "); // n화:
        String title = data.get(pos).title;
        if(title.length() > 16) {
            title = title.substring(0, 15) + "…";
        }
        holder.np_tv_novel_title.setText(title); // 소제목
        holder.np_tv_novel_watcher.setText("조회수 " + data.get(pos).watcher + " "); // 조회수 32412
        holder.np_tv_novel_comment.setText("| 댓글수 " + data.get(pos).comment); // | 댓글수 3241
        holder.np_tv_date.setText(data.get(pos).date); // 2021-01-02
        Glide.with(context).load(data.get(pos).image).into(holder.np_iv_novel_pic);

        // 셀 클릭하면 글로 이동
        holder.npCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 글 페이지(NovelActivity)로 이동
                //Intent intent = new Intent(NovelPageActivity.this, NovelActivity.class);
                //startActivity(intent);
            }
        });
    }

    // RecyclerView의 아이템 갯수를 반환하는 함수.
    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }

    // cell에 대한 ViewHolder
    public static class Holder extends RecyclerView.ViewHolder {
        protected ConstraintLayout npCell;
        protected ImageView np_iv_novel_pic;
        protected LinearLayout np_ll_info;
        protected LinearLayout np_ll_numTitle;
        protected TextView np_tv_novel_num;
        protected TextView np_tv_novel_title;
        protected LinearLayout np_ll_watchCom;
        protected TextView np_tv_novel_watcher;
        protected TextView np_tv_novel_comment;
        protected TextView np_tv_date;

        public Holder(View view) {
            super(view);
            this.npCell = view.findViewById(R.id.npCell);
            this.np_iv_novel_pic = view.findViewById(R.id.np_iv_novel_pic);
            this.np_ll_info = view.findViewById(R.id.np_ll_info);
            this.np_ll_numTitle = view.findViewById(R.id.np_ll_numTitle);
            this.np_tv_novel_num = view.findViewById(R.id.np_tv_novel_num);
            this.np_tv_novel_title = view.findViewById(R.id.np_tv_novel_title);
            this.np_ll_watchCom = view.findViewById(R.id.np_ll_watchCom);
            this.np_tv_novel_watcher = view.findViewById(R.id.np_tv_novel_watcher);
            this.np_tv_novel_comment = view.findViewById(R.id.np_tv_novel_comment);
            this.np_tv_date = view.findViewById(R.id.np_tv_date);
        }
    }
}

