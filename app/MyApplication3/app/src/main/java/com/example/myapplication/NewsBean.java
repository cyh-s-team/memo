package com.example.myapplication;

import java.io.Serializable;

public class NewsBean implements Serializable {

    private String title;
    private String content;
    private int imageResourceId;

    public NewsBean() {
    }

    public NewsBean(String title, String content, int imageResourceId) {
        this.title = title;
        this.content = content;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
