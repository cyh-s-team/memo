package com.example.myapplication.PepareClass;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

public class AdaptScrollView extends android.widget.ScrollView { //继承自ScrollView
    private View inner;
    private static final int DEFAULT_POSITION = -1;
    private float y = DEFAULT_POSITION;// 点击时y的坐标
    private Rect normal = new Rect();

    //滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    public AdaptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    //布局的映射
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);

        }
    }

    @Override
    //触控轨迹
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }


    //实现滑动的反馈
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_UP:

                if (isNeedAnimation()) {
                    animation();
                }

                y = DEFAULT_POSITION;
                break;

            case MotionEvent.ACTION_MOVE:
                float preY = y;
                float nowY = ev.getY();
                if (isDefaultPosition(y)) {
                    preY = nowY;
                }
                int deltaY = (int) (preY - nowY);
                scrollBy(0, deltaY);
                y = nowY;
                // 当滑动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    if (normal.isEmpty()) {// 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                    }
                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY, inner.getRight(), inner.getBottom() - deltaY);
                }
                break;

            default:
                break;
        }
    }


    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(), normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        normal.setEmpty();

    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    // 是否需要移动布局
    public boolean isNeedMove() {

        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    // 检查是否处于默认位置
    private boolean isDefaultPosition(float position) {
        return position == DEFAULT_POSITION;
    }

    @Override
    //拦截器
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}