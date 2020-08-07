package com.example.prm3;

public class Favourite {
    private String username;
    private String title;
    private String link;
    private String icon;

    public Favourite(){}

    public Favourite(String username, String title, String link, String icon) {
        this.username = username;
        this.title = title;
        this.link = link;
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
