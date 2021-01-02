package com.eos.youareheroine.MyPage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eos.youareheroine.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyPageMineAdapter extends RecyclerView.Adapter<MyPageMineAdapter.Holder> {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<MPMyWorkData> dataList;

    // Adapter 생성자
    public MyPageMineAdapter(Context context, ArrayList<MPMyWorkData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 셀 레이아웃을 불러오는 역할.
        View view = LayoutInflater.from(context).inflate(R.layout.cell_my_work, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        // cell의 모든 View에 데이터를 알맞게 넣어준다.
        String title = dataList.get(position).title;
        if(title.length() > 15){
            title = title.substring(0,15)+ "…";
        }
        holder.mp_tv_title.setText(title);
     //   holder.mp_tv_watcher.setText(Integer.toString(dataList.get(position).watcher));
    //    holder.mp_tv_comment.setText(dataList.get(position).comment);
        holder.mp_tv_date.setText(dataList.get(position).date);
        if(dataList.get(position).upload == true){
      //      holder.mp_tv_upload.setText("O");
        }else{
    //        holder.mp_tv_upload.setText("X");
        }
        Glide.with(context).load(dataList.get(position).image).into(holder.mp_iv);
        // 각 셀을 클릭 시 작업 
        holder.mpCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기서는 각 셀을 클릭 시 셀에 해당하는 이름 데이터를 Toast message로 띄운다.
                Toast.makeText(context, "Clicked "+dataList.get(position).title, Toast.LENGTH_SHORT).show();
            }
        });


        holder.mp_iv_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               holder.mp_iv_end.setImageResource(R.drawable.ic_end);
            }
        });
    }

    // RecyclerView의 아이템 갯수를 반환하는 함수.
    @Override
    public int getItemCount() {
        if(dataList == null)
            return 0;
        else
            return dataList.size();
    }

    // cell에 대한 ViewHolder
    public static class Holder extends RecyclerView.ViewHolder {


        protected ConstraintLayout mpCell;
        protected ImageView mp_iv;
        protected TextView mp_tv_title;
        protected TextView mp_tv_watcher;
        protected TextView mp_tv_comment;
        protected TextView mp_tv_date;
        protected TextView mp_tv_upload;
        protected ImageView mp_iv_end;

        public Holder(View view) {
            super(view);
            this.mpCell = view.findViewById(R.id.mpCell);
            this.mp_iv = view.findViewById(R.id.mp_iv_novel_pic);
            this.mp_tv_title = view.findViewById(R.id.mp_tv_title);
            this.mp_tv_watcher = view.findViewById(R.id.mp_tv_watcher);
            this.mp_tv_comment = view.findViewById(R.id.mp_tv_comment);
            this.mp_tv_date = view.findViewById(R.id.mp_tv_date);
            this.mp_tv_upload = view.findViewById(R.id.mp_tv_upload);
            this.mp_iv_end = view.findViewById(R.id.mp_iv_end);

        }
    }
}