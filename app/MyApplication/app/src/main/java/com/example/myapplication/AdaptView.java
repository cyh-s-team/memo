package com.example.myapplication.DIYclass;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class AdaptView extends GridView{//继承自GridView

    public AdaptView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
