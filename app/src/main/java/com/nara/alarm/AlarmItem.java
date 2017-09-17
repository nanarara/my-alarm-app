package com.nara.alarm;

import android.graphics.drawable.Drawable;

/**
 * Created by nara.yoon on 2017-08-21.
 */

public class AlarmItem {

    private Drawable icon;
    private String name;
    private String content;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
