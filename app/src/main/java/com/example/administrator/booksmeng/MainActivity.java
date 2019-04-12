package com.example.administrator.booksmeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import menu.BottomNavigationActivty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add by  zhaozhen 20190330  启动其他页面
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, BottomNavigationActivty.class);
        startActivity(intent);

    }

}
