package com.example.prm3;

import android.graphics.Bitmap;

public class Article {

    private String text;
    private String iconURL;
    private String title;
    private String articalURL;

    public Article(){

    }

    public Article(String text, String iconURL, String title, String articalURL) {
        this.text = text;
        this.iconURL = iconURL;
        this.title = title;
        this.articalURL = articalURL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticalURL() {
        return articalURL;
    }

    public void setArticalURL(String articalURL) {
        this.articalURL = articalURL;
    }
}
