package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Memo_share extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<NewsBean> mNewsBeanList;
    private MyAdapter mMyAdapter;
    private TextView see_note;//新增键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        intData();
        initEvent();
    }


    private void initEvent() {
        mMyAdapter = new MyAdapter(this, mNewsBeanList);
        mRecyclerView.setAdapter(mMyAdapter);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void intData() {
        mNewsBeanList = new ArrayList<>();

        NewsBean newsBean1 = new NewsBean();
        newsBean1.setTitle("雨中漫步");
        newsBean1.setContent("人生就像一场旅行，不必在乎目的地，在乎的是沿途的风景以及看风景的心情，让心灵去旅行。\n只想进行一场漫无目的的旅行，在一个有花有海、安静缓慢的地方晒着太阳无所事事。\n背着背包的路上，看过许多人，听过许多故事，见过旅行风景，就这样，慢慢学会了长大。");
        newsBean1.setImageResourceId(R.drawable.test1);

        NewsBean newsBean2 = new NewsBean();
        newsBean2.setTitle("林间穿梭");
        newsBean2.setContent("只想进行一场漫无目的的旅行，在一个有花有海、安静缓慢的地方晒着太阳无所事事。\n路旁边浪似地滚着高高低低的黄土。太阳给埋在黄土里，发着肉红色。可是太阳还烧得怪起劲的，把他们的皮肉烧得变成紫黑色，似乎还闻得到一股焦味儿。");
        newsBean2.setImageResourceId(R.drawable.test2);

        NewsBean newsBean3 = new NewsBean();
        newsBean3.setTitle("旅行花海");
        newsBean3.setContent("只想进行一场漫无目的的旅行，在一个有花有海、安静缓慢的地方晒着太阳无所事事。\n背着背包的路上，看过许多人，听过许多故事，见过旅行风景，就这样，慢慢学会了长大。\n" +
                "\n" +
                "想呼吸着每座城市的空气，想感受着每座城市的人儿，想看着每座城市的风景。");
        newsBean3.setImageResourceId(R.drawable.test3);


        NewsBean newsBean4 = new NewsBean();
        newsBean4.setTitle("非平衡的线");
        newsBean4.setContent("一根线如果绷得太紧，总有一天会断裂，一颗心若是禁锢得太久，总有一天会失去平衡，我们需要放飞心灵，让心翱翔在自由的太空。每个人在他的人生发轫之初，总有一段时光，没有什么可留恋，只有抑制不住的梦想，没有什么可凭仗，只有他的好身体，没有地方可去，只想到处流浪。");
        newsBean4.setImageResourceId(R.drawable.test4);

        NewsBean newsBean5 = new NewsBean();
        newsBean5.setTitle("说走就走");
        newsBean5.setContent("说走就走的旅行，要么缘由幸福稳定和宽裕，要么祸起无力无奈和逃避。\n在旅途中，我遇见了你，你我相识是缘分！看着你手中的戒指，你说，你可以把它取下来吗？当我要取的时候，你淘气的躲开了，你说只有有缘人才可以取下，我看着你手中的戒指，想做你的有缘人，可是我知道结果是惨淡的，但还是心存希望！");
        newsBean5.setImageResourceId(R.drawable.test5);

        NewsBean newsBean6 = new NewsBean();
        newsBean6.setTitle("美好的记忆");
        newsBean6.setContent("旅行不在乎终点，而是在意途中的人和事还有那些美好的记忆和景色。人生就是一次充满未知的旅行，在乎的是沿途的风景，在乎的是看风景的心情，旅行不会因为美丽的风景终止。走过的路成为背后的风景，不能回头不能停留，若此刻停留，将会错过更好的风景，保持一份平和，保持一份清醒。享受每一刻的感觉，欣赏每一处的风景，这就是人生。（作者：《遇到另一种生活》");
        newsBean6.setImageResourceId(R.drawable.test6);

        NewsBean newsBean7 = new NewsBean();
        newsBean7.setTitle("久违的感动");
        newsBean7.setContent("人生最好的旅行，就是你在一个陌生的地方，发现一种久违的感动。人生最好的旅行，就是你在一个陌生的地方，发现一种久违的感动");
        newsBean7.setImageResourceId(R.drawable.test7);

        NewsBean newsBean8 = new NewsBean();
        newsBean8.setTitle("流浪日记");
        newsBean8.setContent("着背包的路上，看过许多人，听过许多故事，见过旅行风景，就这样，慢慢学会了长大。\n每个人在他的人生发轫之初，总有一段时光，没有什么可留恋，只有抑制不住的梦想，没有什么可凭仗，只有他的好身体，没有地方可去，只想到处流浪。");
        newsBean8.setImageResourceId(R.drawable.test8);

        NewsBean newsBean9 = new NewsBean();
        newsBean9.setTitle("山的尽头");
        newsBean9.setContent("在向山靠近一点，才发现这座山，好象一位诗人遥望远方，等待故人的归来。山上的树，大多数是松树比较突出。松树亭亭玉立的耸立在周围小草小花的中间，仿佛松树就是一位威风的将军，守护着国家的国民。\n在向山靠近一点，才发现这座山，好象一位诗人遥望远方，等待故人的归来。山上的树，大多数是松树比较突出。松树亭亭玉立的耸立在周围小草小花的中间，仿佛松树就是一位威风的将军，守护着国家的国民。\n在向山靠近一点，才发现这座山，好象一位诗人遥望远方，等待故人的归来。山上的树，大多数是松树比较突出。松树亭亭玉立的耸立在周围小草小花的中间，仿佛松树就是一位威风的将军，守护着国家的国民。");
        newsBean9.setImageResourceId(R.drawable.test8);

        NewsBean newsBean10 = new NewsBean();
        newsBean10.setTitle("秦河魅影");
        newsBean10.setContent("秦淮河、夫子庙，繁华依旧，只少了些当年桨声灯影的风味。喝一碗鸭血粉丝，吃一客蟹黄汤包，坐一次龙舟游船，览一河别样风景，发一回思古幽情，我想，这也许就是所谓夜游秦淮的小资情调了。独游，却只有看客的心情。\n秦淮河、夫子庙，繁华依旧，只少了些当年桨声灯影的风味。喝一碗鸭血粉丝，吃一客蟹黄汤包，坐一次龙舟游船，览一河别样风景，发一回思古幽情，我想，这也许就是所谓夜游秦淮的小资情调了。独游，却只有看客的心情。\n秦淮河、夫子庙，繁华依旧，只少了些当年桨声灯影的风味。喝一碗鸭血粉丝，吃一客蟹黄汤包，坐一次龙舟游船，览一河别样风景，发一回思古幽情，我想，这也许就是所谓夜游秦淮的小资情调了。独游，却只有看客的心情。");
        newsBean10.setImageResourceId(R.drawable.test10);

        mNewsBeanList.add(newsBean1);
        mNewsBeanList.add(newsBean2);
        mNewsBeanList.add(newsBean3);
        mNewsBeanList.add(newsBean4);
        mNewsBeanList.add(newsBean5);
        mNewsBeanList.add(newsBean6);
        mNewsBeanList.add(newsBean7);
        mNewsBeanList.add(newsBean8);
        mNewsBeanList.add(newsBean9);
        mNewsBeanList.add(newsBean10);
        mNewsBeanList.add(newsBean1);
        mNewsBeanList.add(newsBean2);
        mNewsBeanList.add(newsBean3);
        mNewsBeanList.add(newsBean4);
        mNewsBeanList.add(newsBean5);
        mNewsBeanList.add(newsBean6);
        mNewsBeanList.add(newsBean7);
        mNewsBeanList.add(newsBean8);
        mNewsBeanList.add(newsBean9);
        mNewsBeanList.add(newsBean10);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_linear:
                RecyclerView.LayoutManager llManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(llManager);
                return true;
            case R.id.menu_gird:
                RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                return true;
            case R.id.menu_stagger:
                RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

