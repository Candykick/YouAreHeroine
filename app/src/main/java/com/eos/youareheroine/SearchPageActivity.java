package com.eos.youareheroine;


//import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class SearchPageActivity extends AppCompatActivity {
    MenuItem menu_search;
    TextView tv_home, tv_sorting;
    Button method_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        tv_home = (TextView)findViewById(R.id.sp_tv_home);
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tv_sorting = (TextView)findViewById(R.id.sp_tv_sorting);
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
        method_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup= new PopupMenu(getApplication(), view); // 현재 화면의 제어권자, 팝업 띄울 기준좌표 위젯
                final String[] temp = new String[1];

                getMenuInflater().inflate(R.menu.search_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        temp[0] = "" + item.getTitle();
                        method_search.setText(temp[0]);
                        return false;
                    }
                });
                popup.show();//Popup Menu 보이기
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                //select back button
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

