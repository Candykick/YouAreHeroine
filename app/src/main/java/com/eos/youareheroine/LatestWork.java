package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class LatestWork extends AppCompatActivity {

    private static final String TAG = "TEST";
    private RequestQueue queue;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_work);

        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        String url = "http://15.164.213.69:5000/test2";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<LatestWork_data> dataArrayList = gson.fromJson(response, new TypeToken<ArrayList<LatestWork_data>>() {
                }.getType());

                RecyclerView latest_recy = findViewById(R.id.latest_recy);
                LatestWorkAdapter adapter = new LatestWorkAdapter(getApplicationContext(), dataArrayList);
                latest_recy.setAdapter(adapter);
                latest_recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

}
