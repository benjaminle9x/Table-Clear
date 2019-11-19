package com.n01216688.testing;

import android.graphics.drawable.Drawable;

public class CardItem {

    private String title;
    private Drawable iconDrawable;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "title='" + title + '\'' +
                '}';
    }
}
