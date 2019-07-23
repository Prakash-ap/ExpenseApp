package com.eron.android.expenseapp.Model;

import android.widget.TextView;

public class CatItemData {

    String text;
    String imageId;

    public CatItemData(String text, String imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
