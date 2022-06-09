package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NewsBean> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MyAdapter(Context context, List<NewsBean> mBeanList) {
        this.mBeanList = mBeanList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NewsBean newsBean = mBeanList.get(position);
        holder.mTvTitle.setText(newsBean.getTitle());
        holder.mTvContent.setText("");
        holder.mIvImage.setImageResource(newsBean.getImageResourceId());
        Random random = new Random();
        int ran = random.nextInt(40) - 10;
        holder.mIvImage.setLayoutParams(new RelativeLayout.LayoutParams(dp2px(100), dp2px(100 + ran)));
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, GoodsdetailActivity.class);
                intent.putExtra("editModel2", "newAdd2");
                mContext.startActivity(intent);
            }
        });
    }


    private int dp2px(int dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        TextView mTvContent;
        ImageView follow;
        ImageView mIvImage;
        RelativeLayout rlContainer;
        int i = 0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvImage = itemView.findViewById(R.id.iv_img);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvContent = itemView.findViewById(R.id.tv_content);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);

            this.follow = itemView.findViewById(R.id.checkBox);
            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i == 1) {
                        follow.setImageResource(R.drawable.concern);//修改图片内容
                        i++;
                        Toast.makeText(mContext, "关注成功！", Toast.LENGTH_SHORT).show();
                    } else if (i == 2) {
                        follow.setImageResource(R.drawable.fans);
                        i--;
                        Toast.makeText(mContext, "取消关注成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            });

             */
        }
    }
}