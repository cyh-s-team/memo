package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.PepareClass.Like_List
import com.example.myapplication.PepareClass.Like_List_Adapter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.like_design.*
import java.util.ArrayList

class LIkeActivity : AppCompatActivity() {
    private val likelist= ArrayList<Like_List>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.like_design)
        StatusBarUtil.setLightMode(this)
        topAppBarLike.setNavigationOnClickListener {
            finish()
        }
        initlike()
        val layoutManager= LinearLayoutManager(this)
        recylerView2.layoutManager=layoutManager
        val adapter= Like_List_Adapter(likelist)
        recylerView2.adapter=adapter
    }

    private fun initlike(){


        likelist.add(Like_List(R.drawable.pic4,"Amy","3分钟前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic5,"陈宇航","10分钟前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic7,"叶泽楷","30分钟前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic1,"韩敬康","1小时前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic8,"杨玉琴","5小时前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic1,"张宇","1天前",R.drawable.pic6))
        likelist.add(Like_List(R.drawable.pic2,"刘裕晏","3天前或更多",R.drawable.pic6))

    }

}