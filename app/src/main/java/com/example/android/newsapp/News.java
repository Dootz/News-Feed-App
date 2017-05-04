package com.example.android.newsapp;

/**
 * Created by 1 on 04.05.2017.
 */

public class News {
    private String mTitle;
    private String mSection;
    private String mUrl;

    public News(String mTitle, String mSection, String mUrl) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mUrl = mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getUrl() {
        return mUrl;
    }
}
