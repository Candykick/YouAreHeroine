package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
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

public class FinishWork extends AppCompatActivity {

    private static final String TAG = "TEST";
    private RequestQueue queue;
    private Gson gson;
    private Button finish_btn_time;
    private Button finish_btn_ascend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_work);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        String url = "https://my-json-server.typicode.com/candykick/apitest/series";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<FinishWork_data> dataArrayList = gson.fromJson(response, new TypeToken<ArrayList<FinishWork_data>>() {
                }.getType());

                RecyclerView finish_recy = findViewById(R.id.finish_recy);
                FinishWorkAdapter adapter = new FinishWorkAdapter(getApplicationContext(), dataArrayList);
                finish_recy.setAdapter(adapter);
                finish_recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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

        finish_btn_time = findViewById(R.id.finish_btn_time);
        finish_btn_ascend = findViewById(R.id.finish_btn_ascend);


        finish_btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 시간순 정렬해서 출력!
                finish_btn_time.setTextColor(Color.parseColor("#D6D5F4"));
                finish_btn_ascend.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), "Clicked time ranking", Toast.LENGTH_SHORT).show();
            }
        });

        finish_btn_ascend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 기니디 정렬해서 출력!
                finish_btn_ascend.setTextColor(Color.parseColor("#D6D5F4"));
                finish_btn_time.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), "Clicked ascending ranking", Toast.LENGTH_SHORT).show();
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
