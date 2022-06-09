package com.example.myapplication.PepareClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Alarm extends BroadcastReceiver {//继承自广播
    private SQLiteDatabase db;
    private DatabaseOperation dop;
    @Override
    public void onReceive(Context context, Intent intent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");//提醒时间的格式
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        dop = new DatabaseOperation(context, db);
        dop.create_db();//打开数据库
        Cursor cursor = dop.query_db();//查询全部
        if (cursor.getCount() > 0) {
            List<SQLBean> list = new ArrayList<SQLBean>();//实例化数据列表
            while (cursor.moveToNext()) {// 光标移动成功
                SQLBean bean = new SQLBean();
                //保存备忘录信息id
                bean.set_id("" + cursor.getInt(cursor.getColumnIndex("_id")));
                //保存备忘录内容
                bean.setContext(cursor.getString(cursor.getColumnIndex("context")));
                //保存备忘录标题
                bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                //保存备忘录生成时间
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
                //是否设置闹钟提醒
                bean.setDatatype(cursor.getString(cursor.getColumnIndex("datatype")));
                //保存闹钟提醒时间
                bean.setDatatime(cursor.getString(cursor.getColumnIndex("datatime")));
                //保存密码锁
                bean.setLocktype(cursor.getString(cursor.getColumnIndex("locktype")));
                //保存密码锁的密码
                bean.setLock(cursor.getString(cursor.getColumnIndex("lock")));
                if (bean.getDatatype().equals("1")&&str.equals(bean.getDatatime())) {//提醒的时间到了
                    dop.update_db(bean.getTitle(), bean.getContext(), bean.getTime(), "0", "0",bean.getLocktype() ,bean.getLock(),Integer.parseInt(bean.get_id()));
                    Intent myIntent = new Intent(context, AlarmAlert.class);//跳转到闹钟提醒界面
                    Bundle bundleRet = new Bundle();
                    bundleRet.putString("remindMsg", bean.getTitle());
                    bundleRet.putBoolean("shake", true);
                    bundleRet.putBoolean("ring", true);
                    myIntent.putExtras(bundleRet);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent);
                }
            }
        }
        dop.close_db();
    }
}
