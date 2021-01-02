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

public class WPAdapter extends RecyclerView.Adapter<WPAdapter.Holder> {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<WriterPageData> data;

    public WPAdapter(Context context, ArrayList<WriterPageData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public WPAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) { // 셀 레이아웃 불러옴
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wp_cell, parent, false);
        return new WPAdapter.Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull WPAdapter.Holder holder, final int pos) {
        // cell에 모든 데이터 넣어줌. 제 위치에.
        holder.wp_tv_novel_num.setText("총 " + data.get(pos).series_num + "화"); // 총 n화
        String title = data.get(pos).title;
        if(title.length() > 15) {
            title = title.substring(0, 15) + "…";
        }
        holder.wp_tv_title.setText(title); // 시리즈 제목
        holder.wp_tv_date.setText("최근 업로드일\n" + data.get(pos).date.substring(0,11)); // 2021-01-02
        Glide.with(context).load(data.get(pos).image).into(holder.wp_iv_novel_pic);

        boolean isEnd = data.get(pos).isEnd;
        if (isEnd) {
            holder.wp_iv_finish.setImageResource(R.drawable.ic_end);
            holder.wp_iv_finish.setVisibility(View.VISIBLE);
        }

        // 셀 클릭하면 글로 이동
        holder.wpCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 글 페이지(NovelActivity)로 이동
                Intent intent = new Intent(view.getContext(), NovelPageActivity.class);
                context.startActivity(intent);
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
        protected ConstraintLayout wpCell;
        protected ImageView wp_iv_novel_pic;
        protected ImageView wp_iv_finish;
        protected LinearLayout wp_ll_info;
        protected TextView wp_tv_novel_num;
        protected TextView wp_tv_title;
        protected TextView wp_tv_date;

        public Holder(View view) {
            super(view);
            this.wpCell = view.findViewById(R.id.wpCell);
            this.wp_iv_novel_pic = view.findViewById(R.id.wp_iv_novel_pic);
            this.wp_ll_info = view.findViewById(R.id.wp_ll_info);
            this.wp_iv_finish = view.findViewById(R.id.wp_iv_finish);
            this.wp_tv_novel_num = view.findViewById(R.id.wp_tv_novel_num);
            this.wp_tv_title = view.findViewById(R.id.wp_tv_title);
            this.wp_tv_date = view.findViewById(R.id.wp_tv_date);
        }
    }
}
