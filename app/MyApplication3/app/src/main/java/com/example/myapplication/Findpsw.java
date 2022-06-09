package com.example.myapplication;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Findpsw extends AppCompatActivity {
    private TextView tv_back;  //返回按钮
    private EditText et_find_usr,et_find_psw;
    private Button et_find;
    private String userName,psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpsw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        tv_back=findViewById(R.id.tv_back);
        et_find_usr=findViewById(R.id.et_find_usr);
        et_find_psw=findViewById(R.id.et_find_psw);
        et_find=findViewById(R.id.et_find);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                Findpsw.this.finish();
            }
        });

        et_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                et_find_psw.setText(findpsw(userName));
            }
        });
    }

    private void getEditString(){
        userName=et_find_usr.getText().toString().trim();
    }

    private String findpsw(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");//传入用户名获取密码
        if (!spPsw.isEmpty()) {
            return spPsw;
        } else {
            return "该用户名不存在";
        }
    }

}
