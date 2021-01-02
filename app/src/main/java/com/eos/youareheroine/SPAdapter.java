package com.eos.youareheroine;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SPAdapter extends RecyclerView.Adapter<SPAdapter.Holder> implements Filterable {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<SearchPageData> data;
    private List<SearchPageData> items = null;
    private ArrayList<SearchPageData> filteredData;

    public SPAdapter(Context context, ArrayList<SearchPageData> data) {
        this.context = context;
        this.data = data;
    }

    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(data);
        } else {
            for (SearchPageData spData : data) {
                String name = spData.getAddress();
                if (name.toLowerCase().contains(charText)) {
                    items.add(spData);
                }
            }
        }
        notifyDataSetChanged();
    }*/
    @Override
    public SPAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) { // 셀 레이아웃 불러옴
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sp_cell, parent, false);
        return new SPAdapter.Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SPAdapter.Holder holder, final int pos) {
        // cell에 모든 데이터 넣어줌. 제 위치에.
        holder.sp_tv_writer.setText(" " + data.get(pos).author_name + " | "); // 작가 |
        holder.sp_tv_num.setText("총 " + data.get(pos).series_num + "화"); // 총 n화
        String title = data.get(pos).title;
        if(title.length() > 15) {
            title = title.substring(0, 15) + "…";
        }
        holder.sp_tv_title.setText(title); // 시리즈 제목
        holder.sp_tv_watcher.setText("조회수 " + data.get(pos).watcher);
        holder.sp_tv_comment.setText("댓글수 " + data.get(pos).comment);
        holder.sp_tv_zzim.setText("찜꽁수 " + data.get(pos).zzim);
        String[] hash_tags = data.get(pos).hash_tag.split(",");
        String hashTag = " ";
        for (int i = 0; i < hash_tags.length; i++) {
            hashTag += "#" + hash_tags[i] + " ";
            if (i == 7) break;
        }
        holder.sp_tv_hash_tag.setText(hashTag);
        Glide.with(context).load(data.get(pos).image).into(holder.sp_iv_novel_pic);

        boolean isEnd = data.get(pos).isEnd;
        if (isEnd) {
            holder.sp_iv_finish.setImageResource(R.drawable.ic_end);
            holder.sp_iv_finish.setVisibility(View.VISIBLE);
        }

        // 셀 클릭하면 글로 이동
        holder.spCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 작품 페이지(NovelPageActivity)로 이동
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
        protected ConstraintLayout spCell;
        protected LinearLayout sp_ll_wcz;
        protected TextView sp_tv_watcher;
        protected TextView sp_tv_comment;
        protected TextView sp_tv_zzim;
        protected ImageView sp_iv_novel_pic;
        protected ImageView sp_iv_finish;
        protected LinearLayout sp_ll_info;
        protected LinearLayout sp_ll_writNum;
        protected TextView sp_tv_writer;
        protected TextView sp_tv_num;        
        protected TextView sp_tv_title;
        protected TextView sp_tv_hash_tag;

        public Holder(View view) {
            super(view);
            this.spCell = view.findViewById(R.id.spCell);
            this.sp_ll_wcz = view.findViewById(R.id.sp_ll_wcz);
            this.sp_tv_watcher = view.findViewById(R.id.sp_tv_watcher);
            this.sp_tv_comment = view.findViewById(R.id.sp_tv_comment);
            this.sp_tv_zzim = view.findViewById(R.id.sp_tv_zzim);
            this.sp_iv_novel_pic = view.findViewById(R.id.sp_iv_novel_pic);
            this.sp_iv_finish = view.findViewById(R.id.sp_iv_finish);
            this.sp_ll_info = view.findViewById(R.id.sp_ll_info);
            this.sp_ll_writNum = view.findViewById(R.id.sp_ll_writNum);
            this.sp_tv_writer = view.findViewById(R.id.sp_tv_writer);
            this.sp_tv_num = view.findViewById(R.id.sp_tv_num);
            this.sp_tv_title = view.findViewById(R.id.sp_tv_title);
            this.sp_tv_hash_tag = view.findViewById(R.id.sp_tv_hash_tag);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filteredData = data;
                } else {
                    ArrayList<SearchPageData> filteringList = new ArrayList<>();
                    for (SearchPageData d : data) {
                        if (d.title.toLowerCase().contains(charString.toLowerCase())){
                            filteringList.add(d);
                        }
                    }
                    filteredData = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (ArrayList<SearchPageData>)results.values;
                notifyDataSetChanged();
            }
        };
    }

}
