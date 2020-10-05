package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton latest;
    private ImageButton writer;
    private ImageButton search;
    private ImageButton king;
    private ImageButton all;
    private ImageButton made;
    private ImageButton write;

    private Toolbar toolbar;
    private ImageButton main;
    private ImageButton mypage;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        latest = findViewById(R.id.main_ibtn_latest);
        writer = findViewById(R.id.main_ibtn_writer);
        search = findViewById(R.id.main_ibtn_search);
        king = findViewById(R.id.main_ibtn_king);
        all = findViewById(R.id.main_ibtn_all);
        made = findViewById(R.id.main_ibtn_made);
        write = findViewById(R.id.main_ibtn_write);

        main = findViewById(R.id.ibtn_main);
        mypage = findViewById(R.id.ibtn_mypage);

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }


}

