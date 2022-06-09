package com.example.myapplication.PepareClass;

import org.litepal.crud.LitePalSupport;

public class User  extends LitePalSupport {
    //id，必须有的，数据库的检索标识
    private int id;
    //用户账户
    private String account;
    //用户密码
    private String password;
    //用户昵称
    private String nickName;
    //用户头像，只存储URI，需要时去服务器获取
    private String headPhoto;
    //关注列表
    private String guanzhu;
    //粉丝列表
    private String fensi;
    //获赞数
    private String huozanshu;
    //评论数
    private String pinglun;

    //下面分别实现相应的get和set方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getGuanzhu() {
        return guanzhu;
    }

    public String getFensi() {
        return fensi;
    }

    public String getHuozanshu() {
        return huozanshu;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setFensi(String fensi) {
        this.fensi = fensi;
    }

    public void setGuanzhu(String guanzhu) {
        this.guanzhu = guanzhu;
    }

    public void setHuozanshu(String huozanshu) {
        this.huozanshu = huozanshu;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }


    public boolean isLogin() {
    return true;}
}

