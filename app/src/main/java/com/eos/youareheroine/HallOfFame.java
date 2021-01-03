package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HallOfFame extends AppCompatActivity {

    private static final String TAG = "TEST";
    private RequestQueue queue;
    private Gson gson;
    private Button rank_btn_week;
    private Button rank_btn_month;
    private Button rank_btn_legend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        String url = "https://my-json-server.typicode.com/candykick/apitest/series";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<HallOfFame_data> dataArrayList = gson.fromJson(response, new TypeToken<ArrayList<HallOfFame_data>>() {
                }.getType());

                RecyclerView rank_recy = findViewById(R.id.rank_recy);
                HallOfFameAdapter adapter = new HallOfFameAdapter(getApplicationContext(), dataArrayList);
                rank_recy.setAdapter(adapter);
                rank_recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO: 에러처리, 여기서는 토스트 메세지만 띄움.
                Toast.makeText(getApplicationContext(), "에러발생: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);

        rank_btn_month = findViewById(R.id.rank_btn_month);
        rank_btn_week = findViewById(R.id.rank_btn_week);
        rank_btn_legend = findViewById(R.id.rank_btn_legend);

        rank_btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 금주 랭킹 정렬해서 출력!
                rank_btn_week.setTextColor(Color.parseColor("#D6D5F4"));
                rank_btn_month.setTextColor(Color.parseColor("#000000"));
                rank_btn_legend.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), "Clicked weekly ranking", Toast.LENGTH_SHORT).show();
            }
        });

        rank_btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 한달 랭킹 정렬해서 출력!
                rank_btn_month.setTextColor(Color.parseColor("#D6D5F4"));
                rank_btn_week.setTextColor(Color.parseColor("#000000"));
                rank_btn_legend.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), "Clicked monthly ranking", Toast.LENGTH_SHORT).show();
            }
        });

        rank_btn_legend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 역대 랭킹 정렬해서 출력!
                rank_btn_legend.setTextColor(Color.parseColor("#D6D5F4"));
                rank_btn_week.setTextColor(Color.parseColor("#000000"));
                rank_btn_month.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), "Clicked legend ranking", Toast.LENGTH_SHORT).show();
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
