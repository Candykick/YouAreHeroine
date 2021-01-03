package com.eos.youareheroine;


//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class SearchPageActivity extends AppCompatActivity {
    private static final String TAG = "SearchPage";
    private RequestQueue queue;
    private Gson gson;
    
    String menu_search = "";
    TextView tv_home, tv_sorting;
    Button method_search;

    SPAdapter adapter;
    SearchView searchView;
    CharSequence search = "";
    RecyclerView sp_rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        searchView = findViewById(R.id.search_view);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        String url = "https://my-json-server.typicode.com/candykick/apitest/series";

       // searchView = findViewById(R.id.search_view);

        tv_home = (TextView)findViewById(R.id.sp_tv_home);
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tv_sorting = (TextView)findViewById(R.id.sp_tv_sorting);
        // 정렬 방법 | 바꾸기
        tv_sorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getApplication(), view); // 현재 화면의 제어권자, 팝업 띄울 기준좌표 위젯
                final String[] temp = new String[1];

                getMenuInflater().inflate(R.menu.sorting_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        temp[0] = "정렬 방법 | " + item.getTitle();
                        tv_sorting.setText(temp[0]);
                        menu_search = "" + item.getTitle();
                        return false;
                    }
                });
                popup.show();//Popup Menu 보이기
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        method_search = (Button) findViewById(R.id.sp_bt_search_method);
        // 작품명, 작가명, 키워드
        method_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getApplication(), view); // 현재 화면의 제어권자, 팝업 띄울 기준좌표 위젯
                final String[] temp = new String[1];

                getMenuInflater().inflate(R.menu.filter_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    temp[0] = "" + item.getTitle();
                    method_search.setText(temp[0]);
                    /*if (item.getTitle() == "작품명") {
                        url[0] = "https://my-json-server.typicode.com/candykick/apitest/series";
                    } else if (item.getTitle() == "작가명") {
                        url[0] = "https://my-json-server.typicode.com/candykick/apitest/series";
                    } else if (item.getTitle() == "키워드") {
                        url[0] = "https://my-json-server.typicode.com/candykick/apitest/series";
                    }*/
                    return false;
                    }
                });
                popup.show();//Popup Menu 보이기

            }
        });


        switch (menu_search) {
            case "최신순" :
                url = "https://my-json-server.typicode.com/candykick/apitest/series";
                break;
            case "가나다순" :
                url = "https://my-json-server.typicode.com/candykick/apitest/series";
                break;
            case "조회수순" :
                url = "https://my-json-server.typicode.com/candykick/apitest/series";
                break;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<SearchPageData> dataList = gson.fromJson(response, new TypeToken<ArrayList<SearchPageData>>() {}.getType());
                sp_rv_list = findViewById(R.id.sp_rv_list);
                adapter = new SPAdapter(getApplicationContext(), dataList);
                sp_rv_list.setAdapter(adapter);
                sp_rv_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                sp_rv_list.setVisibility(View.INVISIBLE);
                /*SearchView searchView = findViewById(R.id.search_view);*/
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    // 작성하고 눌렀을 때 호출되는 부분. 내가 작성하는 부분이 query라는 매개변수로 들어옴.
                    public boolean onQueryTextSubmit(String query) {
                        adapter.getFilter().filter(query);
                        return false;
                    }

                    @Override
                    // 칠 때마다 바로바로 변하는 거 하고 싶으면 onQueryTextChange써야 함.
                    public boolean onQueryTextChange(String newText) {
                        //adapter.filter(newText);
                        return false; //내가 이벤트 처리를 잘 했으면 true로 해줌.
                    }
                });

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error handling. 토스트 메시지만 띄울 거임
                Toast.makeText(getApplicationContext(), "에러 발생 : " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERR", error.getLocalizedMessage());
            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);
        //  searchView.onActionViewExpanded();
        searchView.setQueryHint("검색어를 입력하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                sp_rv_list.setVisibility(View.VISIBLE);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
        return true;
    }

    // 뒤로가기인듯
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                //select back button
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

}

