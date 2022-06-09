package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.PepareClass.NewFans_List
import com.example.myapplication.PepareClass.NewFans_List_Adapter
import com.example.myapplication.PepareClass.NewFollow_List
import com.example.myapplication.PepareClass.NewFollow_List_Adapter
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.newfans_design.*
import kotlinx.android.synthetic.main.newfans_design.topAppBarNewFans
import kotlinx.android.synthetic.main.newfollow_desgin.*

class NewFollowActivity : AppCompatActivity() {
    private val newfollowlist = ArrayList<NewFollow_List>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newfollow_desgin)

        StatusBarUtil.setLightMode(this)
        initFollows()
        val layoutManager = LinearLayoutManager(this)
        recylerView4.layoutManager = layoutManager
        val adapter = NewFollow_List_Adapter(newfollowlist)
        recylerView4.adapter = adapter

        topAppBarNewFans.setNavigationOnClickListener {
            finish()
        }


    }

    private fun initFollows() {

        newfollowlist.add(NewFollow_List(R.drawable.pic3, "Peter", "你有很多事放不下，做人要潇洒一点"))
        newfollowlist.add(NewFollow_List(R.drawable.pic7, "Amy", "猫咪爱好者"))
        newfollowlist.add(NewFollow_List(R.drawable.pic1, "韩敬康", "躬自厚而薄责于人"))
        newfollowlist.add(NewFollow_List(R.drawable.pic4, "叶泽楷", "往者不可谏,来着犹可追"))
        newfollowlist.add(NewFollow_List(R.drawable.pic5, "张宇", "博学而笃志,切问而近思"))
        newfollowlist.add(NewFollow_List(R.drawable.pic3, "杨玉琴", "敏而好学,不耻下问"))
        newfollowlist.add(NewFollow_List(R.drawable.pic2, "刘裕晏", "君子以俭德辟难"))
        newfollowlist.add(NewFollow_List(R.drawable.pic8, "陈宇航", "没完没了了是吧"))

    }

}