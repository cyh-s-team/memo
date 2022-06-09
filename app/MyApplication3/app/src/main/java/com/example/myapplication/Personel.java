package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Personel extends AppCompatActivity {
    private TextView editbtn,fensinum,guanzhunum,huozannum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.personel);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }


    private void init() {
        editbtn = findViewById(R.id.editbtn);
        fensinum= findViewById(R.id.fensinum);
        guanzhunum=findViewById(R.id.guanzhunum);
        huozannum=findViewById(R.id.huozannum);

//        编辑按钮响应操作
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personel.this, MeEdit.class);
                intent.putExtra("editModel1", "newAdd1");
                startActivity(intent);
            }
        });

        fensinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personel.this, NewFansActivity.class);
                intent.putExtra("editModel2", "newAdd2");
                startActivity(intent);
            }
        });

        guanzhunum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personel.this, NewFollowActivity.class);
                intent.putExtra("111", "111");
                startActivity(intent);
            }
        });

        huozannum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personel.this, LIkeActivity.class);
                intent.putExtra("111", "111");
                startActivity(intent);
            }
        });




    }
}
