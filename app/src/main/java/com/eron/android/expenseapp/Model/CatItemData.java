package com.eron.android.expenseapp.Model;

import android.widget.TextView;

public class CatItemData {
    int id;
    String text;
    int imageId=-1;
    String type;

    public CatItemData(int id, String text, int imageId, String type) {
        this.id = id;
        this.text = text;
        this.imageId = imageId;
        this.type = type;
    }

    public CatItemData(String text, int imageId, String type) {
        this.text = text;
        this.imageId = imageId;
        this.type = type;
    }

    public CatItemData(String text, int imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public CatItemData(int id, String text, int imageId) {
        this.id = id;
        this.text = text;
        this.imageId = imageId;
    }

    public CatItemData() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
