package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.editme.*

class MeEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editme)
        StatusBarUtil.setLightMode(this)
        topAppBarEditMe.setNavigationOnClickListener {
            finish()
        }
        imageView.setOnClickListener {
            Toast.makeText(this, "选择头像", Toast.LENGTH_SHORT).show()
        }
    }

}