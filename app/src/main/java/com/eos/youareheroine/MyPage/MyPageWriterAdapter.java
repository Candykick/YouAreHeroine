package com.eos.youareheroine.MyPage;

import android.content.Context;
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

public class MyPageWriterAdapter extends RecyclerView.Adapter<MyPageWriterAdapter.Holder> {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<MPWriterData> dataList;

    // Adapter 생성자
    public MyPageWriterAdapter(Context context, ArrayList<MPWriterData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 셀 레이아웃을 불러오는 역할.
        View view = LayoutInflater.from(context).inflate(R.layout.cell_writer, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        // cell의 모든 View에 데이터를 알맞게 넣어준다.
        holder.mp_tv_writer.setText(dataList.get(position).name);
        //     holder.mp_tv_novel.setText(Integer.toString(dataList.get(position).novel));
        //      holder.mp_tv_zzim.setText(dataList.get(position).zzim);
        //    holder.mp_tv_date.setText(dataList.get(position).date);



        // 각 셀을 클릭 시 작업
        holder.mpCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기서는 각 셀을 클릭 시 셀에 해당하는 이름 데이터를 Toast message로 띄운다.
                Toast.makeText(context, "Clicked " + dataList.get(position).name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // RecyclerView의 아이템 갯수를 반환하는 함수.
    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    // cell에 대한 ViewHolder
    public static class Holder extends RecyclerView.ViewHolder {

        protected ConstraintLayout mpCell;
        protected ImageView mp_iv;
        protected TextView mp_tv_writer;
        protected TextView mp_tv_novel;
        protected TextView mp_tv_zzim;
        protected TextView mp_tv_date;

        public Holder(View view) {
            super(view);
            this.mpCell = view.findViewById(R.id.mpCell);
            this.mp_iv = view.findViewById(R.id.mp_iv_novel_pic);
            this.mp_tv_writer = view.findViewById(R.id.mp_tv_writer);
            this.mp_tv_novel = view.findViewById(R.id.mp_tv_novel);
            this.mp_tv_zzim = view.findViewById(R.id.mp_tv_zzim);
            this.mp_tv_date = view.findViewById(R.id.mp_tv_date);

        }
    }
}