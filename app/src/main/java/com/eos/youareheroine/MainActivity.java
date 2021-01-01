package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


import com.eos.youareheroine.MyPage.MyPageActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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



    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT) + "end");
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();

        latest = findViewById(R.id.main_ibtn_latest);
        writer = findViewById(R.id.main_ibtn_rank);
        search = findViewById(R.id.main_ibtn_search);
        all = findViewById(R.id.main_ibtn_whole);
        made = findViewById(R.id.main_ibtn_notice);
        write = findViewById(R.id.main_ibtn_write);

        main = findViewById(R.id.ibtn_main);
        mypage = findViewById(R.id.ibtn_mypage);

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });





}


}

