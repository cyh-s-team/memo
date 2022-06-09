package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Personel : AppCompatActivity() {
    private var editbtn: TextView? = null
    private var fensinum: TextView? = null
    private var guanzhunum: TextView? = null
    private var huozannum: TextView? = null
    private var mRecyclerView: RecyclerView? = null
    private var mNewsBeanList: MutableList<NewsBean>? = null
    private var mMyAdapter: MyAdapter? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置页面布局 ,注册界面
        setContentView(R.layout.personel)
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        init()
        initView()
        intData()
        initEvent()
    }

    private fun initEvent() {
        mMyAdapter = MyAdapter(this, mNewsBeanList)
        mRecyclerView.setAdapter(mMyAdapter)
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        val layoutManager: LayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.setLayoutManager(layoutManager)
    }

    private fun intData() {
        mNewsBeanList = ArrayList()
        val newsBean1 = NewsBean()
        newsBean1.imageResourceId = R.drawable.test11
        val newsBean2 = NewsBean()
        newsBean2.imageResourceId = R.drawable.test12
        val newsBean3 = NewsBean()
        newsBean3.imageResourceId = R.drawable.test13
        val newsBean4 = NewsBean()
        newsBean4.imageResourceId = R.drawable.test14
        val newsBean5 = NewsBean()
        newsBean5.imageResourceId = R.drawable.test15
        val newsBean6 = NewsBean()
        newsBean6.imageResourceId = R.drawable.test16
        val newsBean7 = NewsBean()
        newsBean7.imageResourceId = R.drawable.test17
        val newsBean8 = NewsBean()
        newsBean8.imageResourceId = R.drawable.test18
        val newsBean9 = NewsBean()
        newsBean9.imageResourceId = R.drawable.test19
        val newsBean10 = NewsBean()
        newsBean10.imageResourceId = R.drawable.test20
        mNewsBeanList.add(newsBean1)
        mNewsBeanList.add(newsBean2)
        mNewsBeanList.add(newsBean3)
        mNewsBeanList.add(newsBean4)
        mNewsBeanList.add(newsBean5)
        mNewsBeanList.add(newsBean6)
        mNewsBeanList.add(newsBean7)
        mNewsBeanList.add(newsBean8)
        mNewsBeanList.add(newsBean9)
        mNewsBeanList.add(newsBean10)
        mNewsBeanList.add(newsBean1)
        mNewsBeanList.add(newsBean2)
        mNewsBeanList.add(newsBean3)
        mNewsBeanList.add(newsBean4)
        mNewsBeanList.add(newsBean5)
        mNewsBeanList.add(newsBean6)
        mNewsBeanList.add(newsBean7)
        mNewsBeanList.add(newsBean8)
        mNewsBeanList.add(newsBean9)
        mNewsBeanList.add(newsBean10)
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.rlv)
    }

    private fun init() {
        editbtn = findViewById(R.id.editbtn)
        fensinum = findViewById(R.id.fensinum)
        guanzhunum = findViewById(R.id.guanzhunum)
        huozannum = findViewById(R.id.huozannum)

//        编辑按钮响应操作
        editbtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Personel, MeEdit::class.java)
            intent.putExtra("editModel1", "newAdd1")
            startActivity(intent)
        })
        fensinum.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Personel, NewFansActivity::class.java)
            intent.putExtra("editModel2", "newAdd2")
            startActivity(intent)
        })
        guanzhunum.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Personel, NewFollowActivity::class.java)
            intent.putExtra("111", "111")
            startActivity(intent)
        })
        huozannum.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Personel, LIkeActivity::class.java)
            intent.putExtra("111", "111")
            startActivity(intent)
        })
    }
}