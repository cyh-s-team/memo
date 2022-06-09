package com.example.myapplication.PepareClass;

import cn.bmob.v3.BmobObject;

public class SQLBean extends BmobObject {//备忘录类
    private String _id;
    private String title;
    private String context;
    private String time;
    private String datatype;
    private String datatime;
    private String locktype;
    private String lock;
    public String get_id() {//获取日记id
        return _id;
    }
    public void set_id(String _id) {//设置日记id
        this._id = _id;
    }
    public String getTitle() {//获取日记标题
        return title;
    }
    public void setTitle(String title) {//设置日记标题
        this.title = title;
    }
    public String getContext() {//获取日记内容
        return context;
    }
    public void setContext(String context) {//设置日记内容
        this.context = context;
    }
    public String getTime() {//获取日记添加时间
        return time;
    }
    public void setTime(String time) {//设置日记添加时间
        this.time = time;
    }
    public String getDatatype() {//获取是否设置了到时提醒
        return datatype;
    }
    public void setDatatype(String datatype) {//设置日日期到时提醒
        this.datatype = datatype;
    }
    public String getDatatime() {//获取提醒时间
        return datatime;
    }
    public void setDatatime(String datatime) {//设置提醒时间
        this.datatime = datatime;
    }
    public String getLocktype() {//获取是否添加了日记锁
        return locktype;
    }
    public void setLocktype(String locktype) {//设置日记锁
        this.locktype = locktype;
    }
    public String getLock() {//获取日记锁秘密
        return lock;
    }
    public void setLock(String lock) {//设置日记锁秘密
        this.lock = lock;
    }
}