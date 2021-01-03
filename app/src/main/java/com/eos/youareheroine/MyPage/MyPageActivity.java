package com.eos.youareheroine.MyPage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eos.youareheroine.MainActivity;
import com.eos.youareheroine.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton main;
    private Button novel;
    private Button writer;
    private Button mine;
    private TextView num;
    private static final String TAG = "TEST";
    private RequestQueue queue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        main = findViewById(R.id.ibtn_main);
        novel = findViewById(R.id.myPage_btn_novel);
        writer = findViewById(R.id.myPage_btn_writer);
        mine = findViewById(R.id.myPage_btn_mine);
        num = findViewById(R.id.myPage_tv_num);


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        final String urlN = "https://my-json-server.typicode.com/candykick/apitest/series";
        final String urlM = "https://my-json-server.typicode.com/candykick/apitest/series";
        final String urlW = "https://my-json-server.typicode.com/candykick/apitest/author";
        //novel
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, urlN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MPNovelData> dataList = gson.fromJson(response, new TypeToken<ArrayList<MPNovelData>>(){}.getType());
                num.setText("전체 개수 : " + dataList.size() );
                RecyclerView rvMain = findViewById(R.id.mp_rv);
                MyPageNovelAdapter adapter = new MyPageNovelAdapter(MyPageActivity.this, dataList);
                rvMain.setAdapter(adapter);
                rvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 처리. 여기서는 에러 발생 시 토스트 메세지만 표시함.
                Toast.makeText(getApplicationContext(), "에러 발생 : "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ERR", error.getLocalizedMessage());
            }
        });
        stringRequest.setTag(TAG);
        queue.add(stringRequest);



        //writer
        final StringRequest stringRequest1 = new StringRequest(Request.Method.GET, urlW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MPWriterData> dataList = gson.fromJson(response, new TypeToken<ArrayList<MPWriterData>>(){}.getType());
                num.setText("전체 개수 : " + dataList.size() );
                RecyclerView rvMain = findViewById(R.id.mp_rv);
                MyPageWriterAdapter adapter = new MyPageWriterAdapter(MyPageActivity.this, dataList);
                rvMain.setAdapter(adapter);
                rvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 처리. 여기서는 에러 발생 시 토스트 메세지만 표시함.
                Toast.makeText(getApplicationContext(), "에러 발생 : "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ERR", error.getLocalizedMessage());
            }
        });

        stringRequest1.setTag(TAG);

        //mine
        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, urlM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MPMyWorkData> dataList = gson.fromJson(response, new TypeToken<ArrayList<MPMyWorkData>>(){}.getType());
                num.setText("전체 개수 : " + dataList.size() );
                RecyclerView rvMain = findViewById(R.id.mp_rv);
                MyPageMineAdapter adapter = new MyPageMineAdapter(MyPageActivity.this, dataList);
                rvMain.setAdapter(adapter);
                rvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 처리. 여기서는 에러 발생 시 토스트 메세지만 표시함.
                Toast.makeText(getApplicationContext(), "에러 발생 : "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ERR", error.getLocalizedMessage());
            }
        });

        stringRequest2.setTag(TAG);

        novel.setTextColor(Color.parseColor("#D6D5F4"));
        novel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                mine.setTextColor(Color.parseColor("#000000"));
                writer.setTextColor(Color.parseColor("#000000"));
                novel.setTextColor(Color.parseColor("#D6D5F4"));
                queue.add(stringRequest);
            }
        });

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novel.setTextColor(Color.parseColor("#000000"));
                writer.setTextColor(Color.parseColor("#000000"));
                mine.setTextColor(Color.parseColor("#D6D5F4"));
                queue.add(stringRequest2);


            }
        });

        writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novel.setTextColor(Color.parseColor("#000000"));
                mine.setTextColor(Color.parseColor("#000000"));
                writer.setTextColor(Color.parseColor("#D6D5F4"));
                queue.add(stringRequest1);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }


}
